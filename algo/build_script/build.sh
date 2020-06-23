##
javac -d bin src/main/PairRowColumn/PairRowColumn.java
javac -d bin -cp bin src/main/Image/Image.java
javac -d bin -cp bin src/main/ImageCentralPixels/ImageCentralPixels.java

javac -d bin -cp bin src/test/TestData/TestData.java
javac -d bin -cp bin src/test/Tester/Tester.java

# java -cp bin test/Tester manual0 > log/manual0.txt
# java -cp bin test/Tester random0 > log/random0.txt
# java -cp bin test/Tester random1 > log/random1.txt
# java -cp bin test/Tester edge0 > log/edge0.txt
java -cp bin test/Tester large_random2 > log/largerandom2.txt
java -cp bin test/Tester large_edge1 > log/large_edge1.txt
