package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import main.ImageCentralPixels;
import test.TestData;

public class Tester {
    public static ArrayList<TestData> tds = new ArrayList<TestData>();
    public static void parse(String path) {
        for (int counter = 0; ; counter++) {
            File fi = new File(path + "in" + counter + ".txt");
            File fo = new File(path + "out" + counter + ".txt");

            // System.out.println("FILEIN> " + fi.getAbsolutePath());
            // System.out.println("FILEOUT> " + fo.getAbsolutePath());

            if (fi.exists() && fo.exists()) {
                try {
                    TestData td = new TestData(fi, fo);
                    tds.add(td);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                // System.out.println("Files do not exist. Stopping..");
                break;
            } 
        }
    }

    public static boolean checker(int[] p, Integer[] j) {
        Arrays.sort(p);
        if (p.length != j.length) return false;
        for (int i = 0; i < p.length; i++)
            if (p[i] != ((int)j[i])) return false;
        return true;
    }

    public static void runTest() {
        System.out.println("There are " + tds.size() + " tests.");
        int test = 0;

        for (TestData td : tds) {
            System.out.println("@Test" + test++);

            int correct = 0;
            int subtests = td.subtestInput.size();
            long start = System.currentTimeMillis();

            for (int subtest = 0; subtest < subtests; subtest++) {
                int[] participant = td.img.centralPixels(td.subtestInput.get(subtest));
                Integer[] jury = new Integer[0];
                
                jury = td.subtestJuryans.get(subtest).toArray(new Integer[0]);

                if (checker(participant, jury)) {
                    // System.out.println("Correct!");
                    correct++;
                } else {
                    System.out.print("@Subtest" + subtest + " gives Wrong result!");
                }
            }

            double testDuration = (System.currentTimeMillis() - start)*0.001;
            System.out.println("Correct: " + correct + " subtest(s) out of " + subtests);
            System.out.println("Test Execution time: " + String.format("%.4f", testDuration));
            System.out.println("Test Average Execution time: " + String.format("%.4f", testDuration/subtests));
        }
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            String fold = String.join(" ", args);
            String path = "./exported/" + fold + "/";
            
            Tester.parse(path);
            /*for (TestData td : tds) {
                System.out.println(td);
            }*/

            Tester.runTest();
        } else {
            System.out.println("Please provide an argument.");
        }
    }
}