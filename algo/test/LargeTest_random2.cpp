//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
std::vector<TestData> get_test_data_vector() {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    // -- stress test
    std::cout << "Generating stress tests..." << std::endl;
    for (int i = 0; i < 1; i++)
        _TestDataVec.push_back(rndtest(4000, 4000, true));
    for (int i = 0; i < 1; i++)
        _TestDataVec.push_back(rndtest(16000000, 1, true));
    for (int i = 0; i < 1; i++)
        _TestDataVec.push_back(rndtest(1, 16000000, true));
    std::cout << "Finished generating tests." << std::endl;

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"