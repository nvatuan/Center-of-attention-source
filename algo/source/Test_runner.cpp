#include <vector>
#include <algorithm>
#include <functional>
#include <iomanip>

#include "../header/ImageDataStructure.hpp"
#include "../header/TestDataStructure.hpp"

#include <ctime>

// -- global variables

// -- checker

// rewrite is_correct()
// supply participant, jury is in is_correct()
bool is_correct(const Image& img, const int& input, const std::vector<unsigned>& juryans) {
    std::vector<unsigned> output = img.central_pixels(input);
    std::sort(output.begin(), output.end());
    //
    return output == juryans;
}

// -- import test_data
std::vector<TestData> get_test_data_vector(int GENERATE_TEST);
//
int correct;
int subtest;

void run_test(int GENERATE_TEST) {
    std::vector<TestData> tests = get_test_data_vector(GENERATE_TEST);
    // -- test initialize function vector
    int test_count = 0;
    int total_test = 0;
    int total_correct = 0;

    int test_time = 0;
    int total_time = 0;
    clock_t before;
    // -- run test
    if (GENERATE_TEST) {
        std::cout << "Test is generated on the fly, correctness checking is not available in this mode." << std::endl;
    }
    for (const TestData& test : tests) {
        test_count++;
        //
        if (test.subtest_juryans.size() == test.subtest_input.size()) {
            // -- initialize
            correct = 0;
            subtest = test.subtest_input.size();
            // --
            std::cout << "@Test" << test_count << "'s ";
            std::cout << "Subtest = " << subtest << std::endl;
            // -- reset test_time
            test_time = 0;

            for (int t = 0; t < subtest; t++) {
                before = clock();
                if (is_correct(test.img, test.subtest_input.at(t), test.subtest_juryans.at(t))) {
                    correct++;
                    //std::cout << "Correct! @Test = " << test_count << " @Subtest = " << t << "\n";
                } else {
                    if (not GENERATE_TEST)
                        std::cout << "Wrong answer! @Test = " << test_count << " @Subtest = " << t << "\n";
                }
                total_time += (clock() - before);
                test_time += (clock() - before);
            }
            // --
            if (not GENERATE_TEST)
                std::cout << "Correct: " << correct << " subtest(s) out of " << subtest << std::endl;

            std::cout << "Test Executed time: " << test_time*1.0/CLOCKS_PER_SEC << "s" << std::endl;
            std::cout << "Test Average Executed time: " << (test_time*1.0/subtest)/CLOCKS_PER_SEC << "s" << std::endl;

            total_test += subtest;
            total_correct += correct;
        } else {
            std::cerr << "@Test" << test_count << std::endl;
            std::cerr << "Test input and answer has their size mismatched.";
        }
    }
    // --
    std::cout << std::endl;
    std::cout << tests.size() << " test(s) ran." << std::endl;
    std::cout << "Total test(s): " << total_test << std::endl;
    if (not GENERATE_TEST)
        std::cout << "Total correct(s): " << total_correct << std::endl;
    std::cout << "Total Executed time: " << total_time*1.0/CLOCKS_PER_SEC << "s" << std::endl;
    std::cout << "Total Average Executed time: " << (total_time*1.0/total_test)/CLOCKS_PER_SEC << "s" << std::endl;

}

int main(int argc, char** argv) {
    std::cout << std::setprecision(4) << std::fixed;
    //run_test( !(argc > 1) );
    if (argc > 1 and (std::string(argv[1]) == "generate")) {
        run_test(true);
    } else run_test(false);
}
