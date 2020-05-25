`Image.hpp` chứa data structure của bài toán, kèm theo các hàm hỗ trợ và prototype của hàm thuật toán

`Image_implemX.hpp` sẽ chứa implementation thứ X của hàm thuật toán có prototype trong `Image.hpp`

`Test_manualX.hpp` là file test thứ X, được tạo thủ công. File sẽ khởi tạo các biến trong `Main.cpp` phục vụ testing.

`Main.cpp` sẽ là nơi chạy test, gồm có `run_test()` và hàm checker `is_correct(test_index)`.