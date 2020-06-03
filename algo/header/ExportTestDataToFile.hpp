#pragma once 
#include <stdexcept>
#include <direct.h>

std::string output_folder ("./test/export/");

void export_to_files(std::string folder) {
    output_folder += folder;
    std::cerr << "Output folder> " << output_folder << std::endl;
    int status = _mkdir(output_folder.c_str()); 
    if ( (status < 0) && (errno != EEXIST) ) throw new std::invalid_argument(output_folder);

    std::vector<TestData> test_data_vector = get_test_data_vector();
    for (int i = 0; i < test_data_vector.size(); i++) {
        std::ofstream in_file ( output_folder + std::string("in") + std::to_string(i) + std::string(".txt"), std::ofstream::out | std::ofstream::trunc);
        std::ofstream out_file ( output_folder + std::string("out") + std::to_string(i) + std::string(".txt"), std::ofstream::out | std::ofstream::trunc);
        std::cerr << "     Attempting to Write file#" << i << std::endl;
        std::cerr << output_folder << "in" << i << ".txt is " << (in_file.is_open() ? "open." : "closed." ) << std::endl;
        std::cerr << output_folder << "out" << i << ".txt is " << (in_file.is_open() ? "open." : "closed." ) << std::endl;
        //
        const TestData& test = test_data_vector.at(i);
        // -- Write input file
        // write image file
        in_file << test.img.width << ' ' << test.img.height << std::endl;

        for (unsigned pixel = 0; pixel < test.img.width * test.img.height; pixel++) {
            in_file << test.img.pixels[pixel];
            if (pixel + 1 < test.img.width * test.img.height) in_file << ' ';
            else in_file << std::endl;
        }
        // write subtests
        in_file << test.subtest_input.size() << std::endl;
        for (unsigned input : test.subtest_input) in_file << input << std::endl;

        in_file.close();
        // -- Write output file
        // write subtests answer
        for (std::vector<unsigned> juryans : test.subtest_juryans) {
            for (unsigned i = 0; i < juryans.size(); i++) {
                out_file << juryans.at(i);
                if (i + 1 < juryans.size()) out_file << ' ';
            }
            out_file << std::endl;
        }

        out_file.close();
        // finish
    }
}