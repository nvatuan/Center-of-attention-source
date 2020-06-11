//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
static std::string TEST_FOLDER_PATH = "test/export/random2";
#include "../header/ImportFileToTestData.hpp"

std::vector<TestData> get_test_data_vector(int GENERATE_TEST) {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    if (GENERATE_TEST) {
        // -- stress test
        std::cout << "Generating stress tests..." << std::endl;
        for (int i = 0; i < 1; i++)
            _TestDataVec.push_back(rndtest(4000, 4000, true));
        for (int i = 0; i < 1; i++)
            _TestDataVec.push_back(rndtest(16000000, 1, true));
        for (int i = 0; i < 1; i++)
            _TestDataVec.push_back(rndtest(1, 16000000, true));
        std::cout << "Finished generating tests." << std::endl;
    } else {
        std::cerr << "@LargeTest_random2: " << "importing tests..\n";
        _TestDataVec = import_to_vector(TEST_FOLDER_PATH);
    }

    return _TestDataVec;
}

#include <iostream>
#include <fstream>

#include "../header/ExportTestDataToFile.hpp"