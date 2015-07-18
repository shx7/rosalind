package subs;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Problem
 * Given two strings s and t, t is a substring of s if t is contained as a contiguous collection
 * of symbols in s (as a result, t must be no longer than s).
 * The position of a symbol in a string is the total number of symbols found to its left, including
 * itself (e.g., the positions of all occurrences of 'U' in "AUGCUUCAGAAAGGUCUUACG" are 2, 5, 6, 15, 17,
 * and 18). The symbol at position i of s is denoted by s[i].
 * A substring of s can be represented as s[j:k], where j and k represent the starting and ending
 * positions of the substring in s; for example, if s = "AUGCUUCAGAAAGGUCUUACG", then s[2:5] = "UGCU".
 *
 * The location of a substring s[j:k] is its beginning position j; note that t will have multiple locations
 * in s if it occurs more than once as a substring of s (see the Sample below).
 *
 * Given: Two DNA strings s and t (each of length at most 1 kbp).
 *
 * Return: All locations of t as a substring of s.
 */
public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        String data = inputStream.nextLine();
        String pattern = inputStream.nextLine();
        ArrayList<Integer> result = new ArrayList<>();
        int index = data.length() - pattern.length();
        for (int i = 0; i < index; i++) {
            if (data.regionMatches(i, pattern, 0, pattern.length())) {
                result.add(i);
            }
        }
        for (Integer x : result) {
            outputStream.print((x + 1) + " ");
        }
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                " Given two strings s and t, t is a substring of s if t is contained as a contiguous collection \n" +
                " of symbols in s (as a result, t must be no longer than s).\n" +
                " The position of a symbol in a string is the total number of symbols found to its left, including\n" +
                " itself (e.g., the positions of all occurrences of 'U' in \"AUGCUUCAGAAAGGUCUUACG\" are 2, 5, 6, 15, 17,\n" +
                " and 18). The symbol at position i of s is denoted by s[i].\n" +
                " A substring of s can be represented as s[j:k], where j and k represent the starting and ending\n" +
                " positions of the substring in s; for example, if s = \"AUGCUUCAGAAAGGUCUUACG\", then s[2:5] = \"UGCU\".\n" +
                " The location of a substring s[j:k] is its beginning position j; note that t will have multiple locations\n" +
                " in s if it occurs more than once as a substring of s (see the Sample below).\n" +
                " Given: Two DNA strings s and t (each of length at most 1 kbp).\n" +
                " Return: All locations of t as a substring of s.";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/subs/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/subs/output.txt";
    }
}
