//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
std::vector<TestData> get_test_data_vector() {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    // -- special cases
    std::cout << "Generating small-size edge case tests:" << std::endl;
    //
    std::cout << "1: Generating image with simple colours..." << std::endl;
    _TestDataVec.push_back(rndtest(100, 100, true, 1));

    std::cout << "2: Generating image with spiral pattern..." << std::endl;
    _TestDataVec.push_back(rndtest(100, 100, true, 2));

    std::cout << "3: Generating image with monochrome colour..." << std::endl;
    _TestDataVec.push_back(rndtest(100, 100, true, 3));

    std::cout << "Finished generating tests." << std::endl;

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"