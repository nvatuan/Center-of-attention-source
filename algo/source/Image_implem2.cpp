#include "../header/ImageDataStructure.hpp"

#include <unordered_map>
#include <queue>
#include <cstdlib>
#include <climits>

#include <iostream>

std::vector<unsigned> Image::central_pixels (unsigned colour) const {
    // -- this was written when multi-colours were considered
    // std::vector<std::vector<unsigned>> border_of_area (0);
    // std::unordered_map<unsigned, int> color_area_index;
    // -- consider single colour only
    std::vector<unsigned> border (0);

    auto on_border = [&](const int& ih, const int& iw, const int& colour) -> bool {
        bool flag = false;
        for (int dh = -1; dh <= 1; dh += 2) 
            flag |= ((ih+dh < 0 or ih+dh == this->height) or (this->pixels[this->pair_to_imgidx(ih+dh, iw)] != colour));
        
        for (int dw = -1; dw <= 1; dw += 2) 
            flag |= ((iw+dw < 0 or iw+dw == this->width) or (this->pixels[this->pair_to_imgidx(ih, iw+dw)] != colour));
        
        return flag;
    };

    std::vector<std::vector<bool>> visited (this->height, std::vector<bool> (this->width, 0));
    auto bfs = [&](const int& ih, const int& iw, const unsigned& colour) -> void {
        // -- initialize map
        // -- this was written when multi-colours were considered
        // if (color_area_index.find(colour) == color_area_index.end()) {
        //     border_of_area.push_back( std::vector<unsigned>(0) );
        //     colour_area_index[colour] = border_of_area.size() - 1;
        // }

        // --
        std::queue<std::pair<int, int>> Q;
        Q.push(std::make_pair(ih, iw));
        //
        while (not Q.empty()) {
            int ih = Q.front().first;
            int iw = Q.front().second;
            Q.pop();

            if (ih < 0 or ih == this->height) continue;
            if (iw < 0 or iw == this->width) continue;
            if (this->pixels[this->pair_to_imgidx(ih, iw)] != colour) continue;
            if (visited.at(ih).at(iw)) continue;
            visited.at(ih).at(iw) = true;

            if (on_border(ih, iw, colour)) {
                // -- multi colours
                //border_of_area[colour_area_index[colour]].push_back(this->pair_to_imgidx(ih, iw));
                // -- single colour
                border.push_back(this->pair_to_imgidx(ih, iw));
            }

            Q.push(std::make_pair(ih + 1, iw));
            Q.push(std::make_pair(ih - 1, iw));
            Q.push(std::make_pair(ih, iw + 1));
            Q.push(std::make_pair(ih, iw - 1));
        }

    };

    for (int ih = 0; ih < this->height; ih++)
        for (int iw = 0; iw < this->width; iw++)
            if (this->pixels[ this->pair_to_imgidx(ih, iw) ] == colour)
                if (not visited.at(ih).at(iw))
                    bfs(ih, iw, colour);
    
    // --
    
    auto manhattan_dist = [&](std::pair<int, int> A, std::pair<int, int> B) -> int {
        return abs(A.first - B.first) + abs(A.second - B.second) + 1;
    };

    std::vector<int> depth_of (this->height * this->width, 0);
    int MAX_DEPTH = 0;

    for (int ih = 0; ih < this->height; ih++)
        for (int iw = 0; iw < this->width; iw++)
            if (this->pixels[ this->pair_to_imgidx(ih, iw) ] == colour) {
                std::pair<int, int> inspected = std::make_pair(ih, iw);
                // -- multi colours
                //for (const unsigned& idx : border_of_area[color_area_index[colour]])
                // -- single colour
                int depth = INT_MAX;
                for (const unsigned& idx : border) {
                    depth = std::min(depth, manhattan_dist(inspected, this->imgidx_to_pair(idx)));
                }
                depth_of[ this->pair_to_imgidx(ih, iw) ] = depth;
                MAX_DEPTH = std::max(MAX_DEPTH, depth);
            } else {
                depth_of[ this->pair_to_imgidx(ih, iw) ] = -1;
            }
    
    // --
    /*
    std::cerr << "BORDER: " << std::endl;
    for (unsigned idx : border)
        std::cerr << idx << " ";
    std::cerr << std::endl;
    */
    
    std::vector<unsigned> answer (0);
    for (unsigned idx = 0; idx < depth_of.size(); idx++)
        if (depth_of[idx] == MAX_DEPTH) {
            //std::cerr << idx << ' ';
            answer.push_back(idx);
        }
    //std::cerr << std::endl;

    return answer;
}
