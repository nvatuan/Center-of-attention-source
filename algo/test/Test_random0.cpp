//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
std::vector<TestData> get_test_data_vector() {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    std::cout << "Generating light-size tests..." << std::endl;
    for (int i = 0; i < 20; i++)
        _TestDataVec.push_back(rndtest(50, 50));
    std::cout << "Finished generating tests." << std::endl;

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"