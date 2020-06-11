//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"

// --
static std::string TEST_FOLDER_PATH = "test/export/edge1";
#include "../header/ImportFileToTestData.hpp"

std::vector<TestData> get_test_data_vector(int GENERATE_TEST) {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    if (GENERATE_TEST) {
        // -- special cases
        std::cout << "Generating large-size edge case tests:" << std::endl;
        //
        std::cout << "1: Generating image with simple colours..." << std::endl;
        _TestDataVec.push_back(rndtest(4000, 4000, true, 1, true));
        //_TestDataVec.push_back(rndtest(16000000, 1, true, 1, true));
        //_TestDataVec.push_back(rndtest(1, 16000000, true, 1, true));
    
        std::cout << "2: Generating image with spiral pattern..." << std::endl;
        _TestDataVec.push_back(rndtest(4000, 4000, true, 2, true));
        //_TestDataVec.push_back(rndtest(16000000, 1, true, 2, true));
        //_TestDataVec.push_back(rndtest(1, 16000000, true, 2, true));
    
        std::cout << "3: Generating image with monochrome colour..." << std::endl;
        _TestDataVec.push_back(rndtest(4000, 4000, true, 3, true));
        //_TestDataVec.push_back(rndtest(16000000, 1, true, 3, true));
        //_TestDataVec.push_back(rndtest(1, 16000000, true, 3, true));
    
        std::cout << "Finished generating tests." << std::endl;
    } else {
        std::cerr << "@Test_LargeTest_Edge1 importing tests.." << std::endl;
        _TestDataVec = import_to_vector(TEST_FOLDER_PATH);
    }

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"