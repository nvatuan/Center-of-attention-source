#pragma once

struct TestData {
    int test_index;
    Image img;
    std::vector<unsigned> subtest_input;
    std::vector< std::vector<unsigned> > subtest_juryans;
    //
    std::string to_string() {
        std::string st = "";
        st += "Test_index = " + std::to_string(this->test_index) + "\n";
        st = st + "Image = " + "\n";
        st += "\tWidth = " + std::to_string(this->img.width);
        st += " Height = " + std::to_string(this->img.height) + "\n";

        for (int i = 0; i < this->img.height; i++) {
            st += "\t";
            for (int j = 0; j < this->img.width; j++) {
                st += std::to_string(this->img.pixels[this->img.pair_to_imgidx(i, j)]) + " ";
            }
            st += "\n";
        }

        st += "Test count = " + std::to_string(subtest_input.size()) + "\n";
        for (unsigned u : subtest_input)
            st += std::to_string(u) + " ";
        st += "\n";
        for (std::vector<unsigned> v : subtest_juryans) {
            st += "{";
            for (unsigned u : v) {
                st += " " + std::to_string(u) + " ";
            }
            st += "}\n";
        }
        //
        return st;
    }
};