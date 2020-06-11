//#include <functional>
#include <iostream>
#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include "../header/RandomTestFunctor.hpp"
// --
static std::string TEST_FOLDER_PATH = "test/export/random0";
#include "../header/ImportFileToTestData.hpp"

std::vector<TestData> get_test_data_vector(int GENERATE_TEST) {
    std::vector<TestData> _TestDataVec;
    rnd_test_functor rndtest;

    if (GENERATE_TEST) {
        std::cout << "Generating light-size tests..." << std::endl;
        for (int i = 0; i < 20; i++)
            _TestDataVec.push_back(rndtest(50, 50));
        std::cout << "Finished generating tests." << std::endl;
    } else {
        std::cerr << "@Test_random0: " << "importing tests..\n";
        _TestDataVec = import_to_vector(TEST_FOLDER_PATH);
    }

    return _TestDataVec;
}


#include "../header/ExportTestDataToFile.hpp"