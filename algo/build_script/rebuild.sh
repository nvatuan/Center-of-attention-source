##
javac -d bin src/main/PairRowColumn/PairRowColumn.java
javac -d bin -cp bin src/main/Image/Image.java
javac -d bin -cp bin src/main/ImageCentralPixels/ImageCentralPixels.java

javac -d bin -cp bin src/test/Tester/Tester.java

java -cp bin test/Tester arg
