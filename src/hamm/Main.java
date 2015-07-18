package hamm;

/**
 * Problem
 *
 * Given two strings s and t of equal length, the Hamming distance between s and t,
 * denoted dH(s,t), is the number of corresponding symbols that differ in s and t.
 *
 * Given: Two DNA strings s and t of equal length (not exceeding 1 kbp).
 * Return: The Hamming distance dH(s,t).
 */
import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

public class Main extends AbstractCmdProgram {

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        String s = inputStream.nextLine();
        String t = inputStream.nextLine();
        int len = s.length();
        int result = 0;
        if (s.length() != t.length()) {
            System.out.println("String have not equal length");
        }
        for (int i = 0; i < len; i++) {
            if (t.charAt(i) != s.charAt(i)) {
                result++;
            }
        }
        outputStream.print(result);
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "Given two strings s and t of equal length, the Hamming distance between s and t,\n" +
                "denoted dH(s,t), is the number of corresponding symbols that differ in s and t.\n" +
                "Given: Two DNA strings s and t of equal length (not exceeding 1 kbp).\n" +
                "Return: The Hamming distance dH(s,t).";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/hamm/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/hamm/output.txt";
    }
}
