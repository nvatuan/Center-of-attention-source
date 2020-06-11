#pragma once
#include <iostream>
#include <vector>
#include <fstream>

std::vector<TestData> import_to_vector(std::string path) {
    std::ifstream TEST;
    std::ifstream JURYANS;
    std::vector<TestData> test_vector;
    
    for (int counter = 0; ; counter++) {
        TEST   .open(path + "/in" + std::to_string(counter) + ".txt", std::fstream::in);
        JURYANS.open(path + "/out" + std::to_string(counter) + ".txt", std::fstream::in);
        std::cerr << "counter = " << counter << std::endl;
        std::cerr << "TEST is " << (TEST.is_open() ? "opened." : "closed.") << std::endl;
        std::cerr << "JURYANS is " << (JURYANS.is_open() ? "opened." : "closed.") << std::endl;

        if (TEST.is_open() and JURYANS.is_open()) {
            // --
            unsigned w, h;
            std::vector<unsigned> p;
            int t;
            std::vector<unsigned> t_in;
            std::vector<std::vector<unsigned> > t_out;

            TEST >> w >> h;
            p.resize(w*h);
            for (unsigned i = 0; i < h*w; i++) TEST >> p.at(i);

            TEST >> t;
            t_in.resize(t);
            for (int i = 0; i < t; i++) TEST >> t_in.at(i);

            t_out.resize(t);
            for (int i = 0; i < t; i++) {
                std::string line;
                std::getline(JURYANS, line);

                line.erase(
                    std::find_if(line.rbegin(), line.rend(), [](int ch) {
                        return not (ch == '\n');
                    }).base(), line.end());
                line += ' ';
                
                int ptr = 0;
                while (true) {
                    int found = line.find(' ', ptr);
                    if (found == std::string::npos) break;
                    if (found == ptr) break;

                    //std::cerr << "ptr = " << ptr << " | found = " << found << '\n';
                    //std::cerr << "'" << line.substr(ptr, found - ptr) << "'\n";
                    t_out.at(i).push_back( (unsigned) std::stoi(line.substr(ptr, found - ptr)));
                    ptr = found + 1;
                }
            }
            // --
            TestData td;
            td.test_index = counter;

            td.img = Image(p, w, h);
            td.subtest_input = t_in;
            td.subtest_juryans = t_out;
            //
            test_vector.push_back(td);
            //
            TEST.close();
            JURYANS.close();
        } else break;
    }

    // -- TestData dump
    /*
    for (TestData td : test_vector) {
        std::cerr << td.to_string();
    }
    */
    return test_vector;
}