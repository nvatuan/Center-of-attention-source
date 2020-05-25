#include "../header/ImageDataStructure.hpp"

static std::vector<unsigned> _DEPTH = std::vector<unsigned>();
static unsigned bfs_max_depth_of (unsigned row, unsigned col);

static unsigned bfs_max_depth_of (unsigned row, unsigned col, const Image& Img) { 
    std::queue< std::pair<std::pair<int, int>, unsigned> > Q;
    std::vector<bool> Visited = std::vector<bool> (Img.width * Img.height, false);
    unsigned current_color = Img.pixels[Img.pair_to_imgidx(row, col)];
    
    Q.push( std::make_pair(std::make_pair(row, col), 0) );
    while (not Q.empty()) {
        std::pair<int, int> coor = Q.front().first;
        unsigned manhattan_dist = Q.front().second;
        unsigned image_idx = Img.pair_to_imgidx(coor);
        Q.pop();

        if (    coor.first < 0 or coor.first == Img.height or
                coor.second < 0 or coor.second == Img.width or
                Img.pixels[image_idx] != current_color ) {
            return manhattan_dist;
        }

        if ( Visited[image_idx] == true ) continue;
        Visited[image_idx] = true;

        Q.push( std::make_pair(std::make_pair(coor.first - 1, coor.second), manhattan_dist+1) );
        Q.push( std::make_pair(std::make_pair(coor.first + 1, coor.second), manhattan_dist+1) );
        Q.push( std::make_pair(std::make_pair(coor.first, coor.second - 1), manhattan_dist+1) );
        Q.push( std::make_pair(std::make_pair(coor.first, coor.second + 1), manhattan_dist+1) );
    }

    return -1;
}

std::vector<unsigned> Image::central_pixels (unsigned colour) const {
    unsigned current_max_depth = 0;
    _DEPTH = std::vector<unsigned> (width * height, 0);
    for (int x = 0; x < height; x++) 
        for (int y = 0; y < width; y++) 
            if (pixels[pair_to_imgidx(x, y)] == colour) {
                _DEPTH.at(pair_to_imgidx(x, y)) = bfs_max_depth_of(x, y, *this); 
                current_max_depth = std::max (current_max_depth, _DEPTH.at(pair_to_imgidx(x, y)));
            }

    std::vector<unsigned> indexes;
    for (unsigned ind = 0; ind < width * height; ind++)
        if ( pixels[ind] == colour and _DEPTH.at(ind) == current_max_depth ) 
            indexes.push_back(ind);
    // -- dump result to screen
    /*
    for (int x = 0; x < height; x++) {
        for (int y = 0; y < width; y++) 
        {
            std::cout << _DEPTH.at(pair_to_imgidx(x, y)) << ' ';
        }
        std::cout << std::endl;
    }
    std::cout << "{ ";
    for (unsigned ans : indexes) std::cout << ans << " ";
    std::cout << "}" << std::endl;
    */
    // -- End dump
    return indexes;
}