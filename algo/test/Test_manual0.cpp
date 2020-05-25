#include <functional>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

TestData man0_test1();
TestData man0_test2();
TestData man0_test3();
TestData man0_test4();

std::vector<TestData> get_test_data_vector() {
    std::vector<std::function<TestData()>> _test = { 
        man0_test1, man0_test2, man0_test3, man0_test4
    };
    //
    std::vector<TestData> _TestDataVec;
    for (std::function<TestData()> f : _test) {
        _TestDataVec.push_back(f());
    }
    return _TestDataVec;
}

#include <iostream>
#include <fstream>

void export_to_files() {
    std::vector<TestData> test_data_vector = get_test_data_vector();
    for (int i = 0; i < test_data_vector.size(); i++) {
        std::ofstream in_file ( std::string("../test/export/in") + std::to_string(i) + std::string(".txt"), std::ofstream::out | std::ofstream::trunc);
        std::ofstream out_file ( std::string("../test/export/out") + std::to_string(i) + std::string(".txt"), std::ofstream::out | std::ofstream::trunc);
        std::cerr << "     Attempting to Write file#" << i << std::endl;
        std::cerr << "../test/export/in" << i << ".txt is " << (in_file.is_open() ? "open." : "closed." ) << std::endl;
        std::cerr << "../test/export/out" << i << ".txt is " << (in_file.is_open() ? "open." : "closed." ) << std::endl;
        //
        const TestData& test = test_data_vector.at(i);
        // -- Write input file
        // write image file
        in_file << test.img.width << ' ' << test.img.height << std::endl;

        for (unsigned pixel = 0; pixel < test.img.width * test.img.height; pixel++) {
            in_file << pixel;
            if (pixel + 1 < test.img.width * test.img.height) in_file << ' ';
            else in_file << std::endl;
        }
        // write subtests
        in_file << test.subtest_input.size() << std::endl;
        for (unsigned input : test.subtest_input) in_file << input << std::endl;

        in_file.close();
        // -- Write output file
        // write subtests answer
        for (std::vector<unsigned> juryans : test.subtest_juryans) {
            for (unsigned i = 0; i < juryans.size(); i++) {
                out_file << juryans.at(i);
                if (i + 1 < juryans.size()) out_file << ' ';
                else out_file << std::endl;
            }
        }

        out_file.close();
        // finish
    }
}

// --
TestData man0_test1() {
    TestData td;
    td.test_index = 1;
    td.img = Image ({ 
                    1, 1, 2, 2, 2,
                    1, 1, 1, 2, 2,
                    1, 1, 2, 2, 2,
                    1, 1, 1, 3, 3,
                    1, 1, 3, 3, 3
                }, 5, 5);
    td.subtest_input = {
        1, 2, 3, 4,
    };
    td.subtest_juryans = {
        {6, 16},
        {2, 3, 4, 8, 9, 12, 13, 14},
        {18, 19, 22, 23, 24},
        {}
    };
    return td;
}

TestData man0_test2() {
    TestData td;
    td.test_index = 2;
    td.img = Image({
        1, 2, 3, 4, 5, 6
    }, 6, 1);
    td.subtest_input = {
        1, 2, 3, 4, 5, 6
    };
    td.subtest_juryans = {
        {0},
        {1},
        {2},
        {3},
        {4},
        {5}
    };
    return td;
}

TestData man0_test3() {
    TestData td;
    td.test_index = 3;
    td.img = Image({
        1, 1, 1, 2, 2, 3, 3, 3,
        1, 1, 2, 2, 2, 2, 2, 3,
        1, 1, 2, 2, 2, 2, 2, 2,
        2, 2, 2, 2, 2, 2, 2, 2,
        2, 2, 4, 4, 2, 2, 3, 3,
        2, 2, 4, 4, 4, 2, 3, 3,
        2, 4, 4, 4, 4, 3, 3, 3,
        4, 4, 4, 3, 3, 3, 3, 3,
        4, 4, 4, 4, 3, 3, 3, 3
    }, 8, 9);
    td.subtest_input = {
        1, 2, 3, 4
    };
    td.subtest_juryans = {
        {0, 1, 2, 8, 9, 16, 17},
        {20},
        {54, 61, 62},
        {43, 50, 57}
    };
    return td;
}

TestData man0_test4() {
    TestData td;
    td.test_index = 4;
    td.img = Image ({ 
                    1, 2, 2, 2, 1, 1, 3, 1, 1, 4, 4, 4, 4, 4, 1,
                    2, 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4,
                    2, 2, 2, 2, 2, 1, 3, 1, 4, 4, 4, 4, 4, 4, 4,
                    2, 2, 2, 2, 2, 1, 5, 1, 4, 4, 4, 4, 4, 4, 4,
                    2, 2, 2, 2, 2, 5, 5, 5, 4, 4, 4, 4, 4, 4, 4,
                    2, 2, 2, 2, 2, 1, 5, 1, 4, 4, 4, 4, 4, 4, 4,
                    1, 2, 2, 2, 1, 1, 1, 1, 1, 4, 4, 4, 4, 4, 1
                }, 15, 7);
    td.subtest_input = {
        1, 2, 3, 4, 5
    };
    td.subtest_juryans = {
        {0, 4, 5, 7, 8, 14, 35, 37, 50, 52, 80, 82, 90, 94, 95, 96, 97, 98, 104},
        {32, 47, 62},
        {21},
        {56},
        {66}
    };
    return td;
}