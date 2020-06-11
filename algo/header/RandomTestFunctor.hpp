#pragma once

#include <algorithm>

#include <vector>
#include <queue>

#include <chrono>
#include <random>

#include <cassert>

struct rnd_test_functor {
    // -- Randomize related --
    static bool _init_random_seed;
    static int  _test_count;
    static unsigned seed_value;
    static std::mt19937 rnd_gen;

    static void init_random_seed() {
        _init_random_seed = true;
        seed_value = std::chrono::system_clock::now().time_since_epoch().count();
        rnd_gen.seed(seed_value);
    }

    static int getRandomInt(int a, int b) {
        if (b - a == 0) return 0;
        return (rnd_gen() % (b - a) + a);
    }

    // -- generator
    enum IMAGE_PATTERN : int {
        RANDOMLY_SPREAD = 0, SIMPLE_COLOURED = 1, SPIRAL = 2, MONOCHROME = 3
    };

    unsigned TOTAL_COLOURS;
    bool _getNextColour_init = false;
    unsigned getNextColour(const int& H, const int& W, const int& img_patt) {
        if (not _getNextColour_init) {
            _getNextColour_init = true;
            switch ( IMAGE_PATTERN(img_patt) ) {
                case RANDOMLY_SPREAD:
                    TOTAL_COLOURS = getRandomInt(H*W/2 + 1, H*W);
                    break;
                case SIMPLE_COLOURED:
                    TOTAL_COLOURS = getRandomInt( (H+W)/4, (H+W)/2);
                    break;
                case SPIRAL:
                    TOTAL_COLOURS = 2;
                    break;
                case MONOCHROME:
                    TOTAL_COLOURS = 1;
                    break;
                default:
                    assert("IMAGE_PATTERN NOT RECOGNIZED" == "!");
            }
        }
        // --
        return getRandomInt(0, TOTAL_COLOURS - 1);
    }

    void image_generate(unsigned _bound_w, unsigned _bound_h, unsigned& image_colours, Image& img,
        const bool& __maximize_w_h, int img_patt = 0 
    ) { 
        // -- initialize
        _getNextColour_init = false;

        unsigned w; 
        unsigned h; 
        if (__maximize_w_h) {
            w = _bound_w;
            h = _bound_h;
        } else {
            if (_bound_w) w = getRandomInt(1, _bound_w);
            else w = getRandomInt(1, 16000000);
            if (_bound_h) h = getRandomInt(1, _bound_h); 
            else h = getRandomInt(1, 16000000 / w);
        }


        std::vector<std::vector<unsigned>> _2d_map (h, std::vector<unsigned> (w, 0));
        std::vector<std::vector<int>> _2d_map_visisted (h, std::vector<int> (w, 0));

        // -- generating
        auto drunkwalk = [&](unsigned x, unsigned y) -> void {
            unsigned colour = getNextColour(h, w, img_patt); 
            unsigned steps  = getRandomInt(1, w*h);
            std::queue< std::pair<int, int> > Q;
            Q.push( std::make_pair(x, y) );
            //
            while (not Q.empty() and steps > 0) {
                int subx = Q.front().first;
                int suby = Q.front().second;
                Q.pop();
                //
                if (subx < 0 or subx == h) continue;
                if (suby < 0 or suby == w) continue;
                if (_2d_map_visisted.at(subx).at(suby)) continue;
                _2d_map.at(subx).at(suby) = colour;
                _2d_map_visisted.at(subx).at(suby) = 1;
                steps--;
                //
                int directions = getRandomInt(0, 16);
                if (directions & (1 << 0))
                    Q.push( std::make_pair(subx - 1, suby) );
                if (directions & (1 << 1))
                    Q.push( std::make_pair(subx + 1, suby) );
                if (directions & (1 << 2))
                    Q.push( std::make_pair(subx, suby - 1) );
                if (directions & (1 << 3))
                    Q.push( std::make_pair(subx, suby + 1) );
            }
        };

        auto spiraling = [&]() -> void {
            unsigned col = 1;
            // --
            int x1 = -1, y1 = 0;
            int dx = 1, dy = 1;

            int dW = w;
            int dH = h - 2;

            int turn = 1;
            while (dW > 0 or dH > 0) {
                if (turn) {
                    for (int i = 0; i < dW; i++) {
                        x1 += dx;
                        _2d_map[x1][y1] = col;
                    }
                    turn ^= 1; dW -= 2; dx *= -1;
                } else {
                    for (int i = 0; i < dH; i++) {
                        y1 += dy;
                        _2d_map[x1][y1] = col;
                    }
                    turn ^= 1; dH -= 2; dy *= -1;
                }
            }

        };

        switch ( IMAGE_PATTERN(img_patt) ) {
            case RANDOMLY_SPREAD:
            case SIMPLE_COLOURED:
            case MONOCHROME:
                for (unsigned hi = 0; hi < h; hi++)
                    for (unsigned wi = 0; wi < w; wi++)
                        if (_2d_map_visisted.at(hi).at(wi) == 0)
                            drunkwalk(hi, wi);
                break;
            case SPIRAL:
                spiraling();
                break;
            default:
                assert("IMAGE_PATTERN NOT RECOGNIZED" == "!");
        }

        /*
        std::cout << "DUMP MAP: " << std::endl;
        for (unsigned hi = 0; hi < h; hi++, std::cout << std::endl)
            for (unsigned wi = 0; wi < w; wi++)
                std::cout << _2d_map.at(hi).at(wi) << ' ';
        */

        // -- convert data
        image_colours = TOTAL_COLOURS;
        
        delete[] img.pixels;
        img.pixels = new unsigned[h*w];
        img.height = h;
        img.width = w;
        for (unsigned row = 0; row < h; row++) 
            for (unsigned col = 0; col < w; col++) 
                img.pixels[row*w + col] = _2d_map.at(row).at(col);
    }
    // -- constructor
    rnd_test_functor() {}
    // -- function
    TestData operator()(int _bound_w = 0, int _bound_h = 0, const bool& __maximize_w_h = false, int img_patt = 0, const bool& __omit_result = false) {
        if (!_init_random_seed) init_random_seed();
        TestData td;
        td.test_index = _test_count++;
        
        std::cout << "Generating test#" << td.test_index << std::endl;
        // -- generate image
        unsigned image_colours;
        std::cout << "Generating image.." << std::endl;
        image_generate(_bound_w, _bound_h, image_colours, td.img, __maximize_w_h, img_patt);
        
        // -- generate test input
        // cap image_colours by image size
        image_colours = std::min(image_colours, int(2e7)/(td.img.height*td.img.width));

        td.subtest_input.clear();
        td.subtest_input.resize(image_colours);

        unsigned cl = 0;
        std::generate(td.subtest_input.begin(), td.subtest_input.end(), [&]() -> int { return cl++; } );

        // -- generate jury answer
        td.subtest_juryans.clear();
        td.subtest_juryans.resize(td.subtest_input.size());

        if (__omit_result) {
            std::cout << "Generating results is [Omitted]." << std::endl;
        } else {
            std::cout << "Generating results.." << std::endl;
            for (unsigned test = 0; test < td.subtest_input.size(); test++) {
                td.subtest_juryans.at(test) = td.img.central_pixels(td.subtest_input.at(test));
                // -- DEBUGING
                /*
                std::cout << "Color = " << td.subtest_input.at(test) << "\n";
                std::cout << "Answer size = " << td.subtest_juryans.at(test).size() << "\n";
                for (unsigned a : td.subtest_juryans.at(test)) std::cout << a << " ";
                std::cout << std::endl;
                */
            }
        }

        return td;
    }
};
bool rnd_test_functor::_init_random_seed = false;
unsigned rnd_test_functor::seed_value = 0;
int rnd_test_functor::_test_count = 0;
std::mt19937 rnd_test_functor::rnd_gen (0);