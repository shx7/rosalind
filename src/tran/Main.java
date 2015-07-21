package tran;

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Problem
 *
 * For DNA strings s1 and s2 having the same length, their transition/transversion
 * ratio R(s1,s2) is the ratio of the total number of transitions to the total number
 * of transversions, where symbol substitutions are inferred from mismatched corresponding
 * symbols as when calculating Hamming distance (see “Counting Point Mutations”).
 *
 * Given: Two DNA strings s1 and s2 of equal length (at most 1 kbp).
 *
 * Return: The transition/transversion ratio R(s1,s2).
 */

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader reader = new FastaReader(inputStream);
        LinkedList<FastaReader.FastaRecord> records = reader.parse();
        int transversionsCount = 0;
        int transitionsCount = 0;
        String strand1 = new String(records.poll().data);
        String strand2 = new String(records.poll().data);
        Character chr1;
        Character chr2;
        for (int i = 0; i < strand1.length(); i++) {
            chr1 = strand1.charAt(i);
            chr2 = strand2.charAt(i);
            if ((chr1 == 'A' && chr2 == 'G') ||
                (chr1 == 'G' && chr2 == 'A')) {
                transitionsCount++;
            } else if ((chr1 == 'C' && chr2 == 'T') ||
                    (chr1 == 'T' && chr2 == 'C')) {
                transitionsCount++;
            } else if (chr1 != chr2){
                transversionsCount++;
            }
        }
        double result = transitionsCount / (double) transversionsCount;
        outputStream.print(result);
    }

    @Override
    protected void initDescription() {
        description = " * Problem\n" +
                " * \n" +
                " * For DNA strings s1 and s2 having the same length, their transition/transversion\n" +
                " * ratio R(s1,s2) is the ratio of the total number of transitions to the total number\n" +
                " * of transversions, where symbol substitutions are inferred from mismatched corresponding\n" +
                " * symbols as when calculating Hamming distance (see “Counting Point Mutations”).\n" +
                " * \n" +
                " * Given: Two DNA strings s1 and s2 of equal length (at most 1 kbp).\n" +
                " * \n" +
                " * Return: The transition/transversion ratio R(s1,s2).";
    }
}
