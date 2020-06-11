//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
static std::string TEST_FOLDER_PATH = "test/export/random1";
#include "../header/ImportFileToTestData.hpp"

std::vector<TestData> get_test_data_vector(int GENERATE_TEST) {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    if (GENERATE_TEST) {
        std::cout << "Generating medium-size tests..." << std::endl;
        for (int i = 0; i < 10; i++)
            _TestDataVec.push_back(rndtest(200, 200, true));
        std::cout << "Finished generating tests." << std::endl;
    } else {
        std::cerr << "@Test_random1: " << "importing tests..\n";
        _TestDataVec = import_to_vector(TEST_FOLDER_PATH);
    }

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"