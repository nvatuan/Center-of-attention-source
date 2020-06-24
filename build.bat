javac -encoding utf-8 -d out main/algo/PairRowColumn.java
javac -encoding utf-8 -d out main/algo/Image.java
javac -encoding utf-8 -d out main/algo/ImageCentralPixels.java

javac -encoding utf-8 -d out main/gui/ComponentDesign/*.java
javac -encoding utf-8 -d out main/gui/Constants/*.java
javac -encoding utf-8 -d out main/gui/Controller/*.java
javac -encoding utf-8 -d out main/gui/Controller/panel/*.java
javac -encoding utf-8 -d out main/gui/Event/*.java

javac -encoding utf-8 -d out main/runner/runner.java

java -cp out;main/lib/mysql-connector-java-5.1.49.jar main.runner