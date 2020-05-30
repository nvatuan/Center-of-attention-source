#pragma once

#include <algorithm>

#include <vector>
#include <queue>
#include <cstdlib>
#include <ctime>

struct rnd_test_functor {
    // -- Randomize related --
    static bool _set_srand_seed;
    static int  _test_count;

    static void set_srand_seed() {
        std::srand(std::time(NULL));
        _set_srand_seed = true;
    }
    static int getRandomInt(int a, int b) {
        if (b - a == 0) return 0;
        return (std::rand() % (b - a) + a);
        // -- NOTE: RAND_MAX can't reaches 10**6, use C++ mt1997 instead
    }

    // -- generator
    static void image_generate(unsigned _bound_w, unsigned _bound_h, unsigned& image_colours, Image& img) {
        // -- initialize
        unsigned w; 
        if (_bound_w) w = getRandomInt(1, _bound_w);
        else w = getRandomInt(1, RAND_MAX);

        unsigned h; 
        if (_bound_h) h = getRandomInt(1, _bound_h); 
        else h = getRandomInt(1, RAND_MAX);

        unsigned colours = 0;

        std::vector<std::vector<unsigned>> _2d_map (h, std::vector<unsigned> (w, 0));
        std::vector<std::vector<int>> _2d_map_visisted (h, std::vector<int> (w, 0));
        // -- generating
        auto drunkwalk = [&](unsigned x, unsigned y) -> void {
            unsigned colour = colours++; 
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
        
        for (unsigned hi = 0; hi < h; hi++)
            for (unsigned wi = 0; wi < w; wi++)
                if (_2d_map_visisted.at(hi).at(wi) == 0)
                    drunkwalk(hi, wi);

        /*
        std::cout << "DUMP MAP: " << std::endl;
        for (unsigned hi = 0; hi < h; hi++, std::cout << std::endl)
            for (unsigned wi = 0; wi < w; wi++)
                std::cout << _2d_map.at(hi).at(wi) << ' ';
        */
        // -- convert data
        image_colours = colours;
        
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
    TestData operator()(int _bound_w = 0, int _bound_h = 0) {
        if (!_set_srand_seed) set_srand_seed();
        // -- 
        TestData td;
        td.test_index = _test_count++;
        
        // -- generate image
        unsigned image_colours;
        image_generate(_bound_w, _bound_h, image_colours, td.img);
        
        // -- generate test input
        td.subtest_input.clear();
        td.subtest_input.resize(image_colours);

        unsigned cl = 0;
        std::generate(td.subtest_input.begin(), td.subtest_input.end(), [&]() -> int { return cl++; } );

        // -- generate jury answer
        td.subtest_juryans.clear();
        td.subtest_juryans.resize(td.subtest_input.size());

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

        return td;
    }
};
bool rnd_test_functor::_set_srand_seed = false;
int rnd_test_functor::_test_count = 0;