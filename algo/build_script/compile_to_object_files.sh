### no hash bang because im on windows
rm ./object/*.o
# g++ -c ./source/Image_implem1.cpp -o ./object/Image.o
g++ -c ./source/Image_implem2.cpp -o ./object/Image.o
# g++ -c ./source/Image_implem3.cpp -o ./object/Image.o

# g++ -c ./test/Test_manual0.cpp -o ./object/Test.o
 g++ -c ./test/Test_random0.cpp -o ./object/Test.o
# g++ -c ./test/Test_random1.cpp -o ./object/Test.o

g++ -c ./source/Test_runner.cpp -o ./object/test_runner.o
exit 0