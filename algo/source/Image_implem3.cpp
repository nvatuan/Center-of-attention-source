#include "../header/ImageDataStructure.hpp"

std::vector<unsigned> Image::central_pixels (unsigned colour) const {
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

    // -- wave in
    std::vector<int> depth_of (this->height * this->width, -1);
    int MAX_DEPTH = 0;

    std::queue< std::pair<std::pair<int, int>, int> > Q;
    for (const unsigned& u : border) {
        std::pair<int, int> coord = this->imgidx_to_pair(u);
        Q.push( std::make_pair(coord, 1) );
    }


    while (not Q.empty()) {
        int ih = Q.front().first.first;
        int iw = Q.front().first.second;
        int depth = Q.front().second;
        Q.pop();

        if (ih < 0 or ih == this->height) continue;
        if (iw < 0 or iw == this->width) continue;
        if (this->pixels[this->pair_to_imgidx(ih, iw)] != colour) continue;
        if (depth_of.at(this->pair_to_imgidx(ih, iw)) != -1) continue;

        MAX_DEPTH = std::max(MAX_DEPTH, depth);
        depth_of.at(this->pair_to_imgidx(ih, iw)) = depth;

        Q.push(std::make_pair(std::make_pair(ih + 1, iw), depth + 1));
        Q.push(std::make_pair(std::make_pair(ih - 1, iw), depth + 1));
        Q.push(std::make_pair(std::make_pair(ih, iw + 1), depth + 1));
        Q.push(std::make_pair(std::make_pair(ih, iw - 1), depth + 1));
    }

    // -- scoop answer
    std::vector<unsigned> answer;
    for (unsigned i = 0; i < this->height*this->width; i++)
        if (depth_of.at(i) == MAX_DEPTH)
            answer.push_back(i);

    return answer;
}