#include <iostream>
#include <string>
#include <stdexcept>

void export_to_files(std::string);

int main(int argc, const char* argv[]) {
    try {

        std::string holder("");
        if (argc < 2) {
            holder = std::string("");
        } else {
            holder = std::string(argv[1]);
            for (int i = 2; i < argc; i++) holder = holder + std::string(" ") + std::string(argv[i]);
            holder += "/";
        }

        export_to_files(holder);

    } catch (std::invalid_argument iarg) {
        std::cerr << iarg.what();
    } catch (std::exception e) {
        std::cerr << e.what();
    }
}