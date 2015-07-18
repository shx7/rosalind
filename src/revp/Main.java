package revp;

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;

/**
 * Problem
 * A DNA string is a reverse palindrome if it is equal to its reverse complement.
 * For instance, GCATGC is a reverse palindrome because its reverse complement is GCATGC.
 *
 * Given: A DNA string of length at most 1 kbp in FASTA format.
 *
 * Return: The position and length of every reverse palindrome in the string having
 * length between 4 and 12. You may return these pairs in any order.
 */
public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        byte[] data = {'G', 'A', 'T', 'C'};
        System.out.println("isReversePalindrome(\"GCATAC\") " + isReversePalindrome(data, 0, data.length));

        FastaReader fastaReader = new FastaReader(inputStream);
        for (FastaReader.FastaRecord rec : fastaReader.parse()) {
            System.out.println(rec);
        }
    }

    boolean isReversePalindrome(byte[] data, int offset, int length) {
        int last = offset + length;
        for (int i = 0; i < length; i++) {
            System.out.println("cmp " + data[offset + i] + " : " + complement(data[last - 1 - i]));
            if (data[offset + i] != complement(data[last - 1 - i])) return false;
        }
        return true;
    }

    byte complement(byte nt) {
        switch (nt) {
            case 'A':
                return 'T';

            case 'C':
                return 'G';

            case 'G':
                return 'C';

            case 'T':
                return 'A';

            default:
                return 'E';
        }
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "A DNA string is a reverse palindrome if it is equal to its reverse complement.\n" +
                "For instance, GCATGC is a reverse palindrome because its reverse complement is GCATGC.\n" +
                "\n" +
                "Given: A DNA string of length at most 1 kbp in FASTA format.\n" +
                "\n" +
                "Return: The position and length of every reverse palindrome in the string having length\n" +
                "between 4 and 12. You may return these pairs in any order.";
    }
}
