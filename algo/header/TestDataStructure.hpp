#pragma once

struct TestData {
    int test_index;
    Image img;
    std::vector<unsigned> subtest_input;
    std::vector< std::vector<unsigned> > subtest_juryans;
};