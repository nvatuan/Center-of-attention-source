//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"

// --
static std::string TEST_FOLDER_PATH = "test/export/edge0";
#include "../header/ImportFileToTestData.hpp"

std::vector<TestData> get_test_data_vector(int GENERATE_TEST) {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    if (GENERATE_TEST) {
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
    } else {
        std::cerr << "@Test_edge0: " << "importing tests..\n";
        _TestDataVec = import_to_vector(TEST_FOLDER_PATH);
    }

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"