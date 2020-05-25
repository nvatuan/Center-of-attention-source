#include <iostream>
#include <vector>
#include <queue>

// -- Data structure used with prototyped functions
// -- Quoted from problem prompt
struct Image
{
    unsigned (*pixels) = NULL;
    unsigned width, height;

    std::vector<unsigned> central_pixels (unsigned colour) const;
    // other functions ...
    Image(std::initializer_list<unsigned> list, unsigned w, unsigned h){
        width = w; height = h;
        if (pixels != NULL) delete[] pixels;
        pixels = new unsigned[width*height];
        for (unsigned i = 0; i < w*h; i++) pixels[i] = *(list.begin() + i);
    }
    Image(){
        width = 0; height = 0;
        if (pixels != NULL) delete[] pixels;
    }

    unsigned pair_to_imgidx (unsigned first, unsigned second) const {
        return first * width + second;
    }
    unsigned pair_to_imgidx (std::pair<unsigned, unsigned> pair) const {
        return pair.first * width + pair.second;
    }

    std::pair<unsigned, unsigned> imgidx_to_pair (unsigned imgidx) const {
        return std::make_pair(imgidx / width, imgidx % width);
    }
};

// -- algorithm implementation file

// #include "Image_implem1.hpp"
// #include "Image_implem2.hpp"
// #include "Image_implem3.hpp"

