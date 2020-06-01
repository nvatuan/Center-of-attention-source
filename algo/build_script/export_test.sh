## no hash bang because im on windows 

rm ./object/*.o
#g++ source/Image_implem1.cpp -c -o object/Image.o
g++ source/Image_implem2.cpp -c -o object/Image.o
# g++ source/Image_implem3.cpp -c -o object/Image.o

g++ source/test_export.cpp -c -o object/test_export.o

### Small tests:
# g++ test/Test_manual0.cpp -c -o object/Test.o
# g++ object/*.o -o bin/test_export.exe
# bin/test_export.exe manual0
# 
# g++ test/Test_random0.cpp -c -o object/Test.o
# g++ object/*.o -o bin/test_export.exe
# bin/test_export.exe random0 
# 
# g++ test/Test_random1.cpp -c -o object/Test.o
# g++ object/*.o -o bin/test_export.exe
# bin/test_export.exe random1 

### Large test!! Be sure to compile with 
### good algorithm or it will take ages to finish
 g++ test/Test_random2.cpp -c -o object/Test.o
 g++ object/*.o -o bin/test_export.exe
 bin/test_export.exe stress_test_no_output