//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
std::vector<TestData> get_test_data_vector() {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    for (int i = 0; i < 5; i++)
        _TestDataVec.push_back(rndtest(50, 50));

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"
