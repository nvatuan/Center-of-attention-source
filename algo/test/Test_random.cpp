#include <functional>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include <vector>
#include <queue>
#include <cstdlib>
#include <ctime>

#include <iostream>

TestData man0_test1();

struct rnd_test_functor {
    static bool _set_srand_seed;
    static int  _test_count;
    static void set_srand_seed() {
        std::srand(std::time(NULL));
        _set_srand_seed = true;
    }
    static Image image_generate(unsigned _bound_w = 0, unsigned _bound_h = 0) {
        // -- initialize
        unsigned h; 
        if (_bound_h) h = std::rand() % _bound_h + 1; 
        else h = std::rand() + 1;
        
        unsigned w; 
        if (_bound_w) w = std::rand() % _bound_w + 1;
        else w = std::rand() + 1;

        unsigned colours = 0;

        std::vector<std::vector<unsigned>> _2d_map (w, std::vector<unsigned> (h, 0));
        std::vector<std::vector<int>> _2d_map_visisted (w, std::vector<int> (h, 0));
        // -- generating
        auto drunkwalk = [&](unsigned x, unsigned y) -> void {
            unsigned colour = colours++; 
            unsigned steps  = std::rand() % (w*h) + 1;
            std::queue< std::pair<int, int> > Q;
            Q.push( std::make_pair(x, y) );
            //
            while (not Q.empty() and steps > 0) {
                int subx = Q.front().first;
                int suby = Q.front().second;
                Q.pop();
                //
                if (subx < 0 or subx == w) continue;
                if (suby < 0 or suby == h) continue;
                if (_2d_map_visisted.at(subx).at(suby)) continue;
                _2d_map.at(subx).at(suby) = colour;
                _2d_map_visisted.at(subx).at(suby) = 1;
                steps--;
                //
                int directions = std::rand() % (1 << 4);
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
        
        for (unsigned wi = 0; wi < w; wi++)
            for (unsigned hi = 0; hi < h; hi++)
                if (_2d_map_visisted.at(wi).at(hi) == 0)
                    drunkwalk(wi, hi);
        
        for (unsigned wi = 0; wi < w; wi++, std::cout << std::endl)
            for (unsigned hi = 0; hi < h; hi++)
                std::cout << _2d_map[wi][hi] << ' ';
        // -- convert data

        // NOTE: missing code
    }
    // -- constructor
    rnd_test_functor() {}
    // -- function
    TestData operator()() {
        if (!_set_srand_seed) set_srand_seed();
        // -- 
        TestData td;
        td.test_index = _test_count++;
        //
        td.img = image_generate();
        //

        /*
        td.subtest_input = {
            1, 2, 3, 4,
        };
        td.subtest_juryans = {
            {6, 16},
            {2, 3, 4, 8, 9, 12, 13, 14},
            {18, 19, 22, 23, 24},
            {}
        };
        */
        return td;
    }
};
bool rnd_test_functor::_set_srand_seed = false;
int rnd_test_functor::_test_count = 0;

// --
std::vector<TestData> get_test_data_vector() {
    std::vector<TestData> _TestDataVec;
    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"

int main() {
    std::cout << RAND_MAX;
    // -- NOTE: RAND_MAX can't reaches 10**6, use C++ mt1997 instead
    // -- generate 2d matrix is finished
    // what is left: convert it to Image struct
    // somehow generate answer
    // modify get_test_data_vector()
    rnd_test_functor rndtest;
    //rndtest();
}