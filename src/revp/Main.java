package revp;

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;
import java.util.LinkedList;

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
    class ComplementPalindromeLocus {
        public int position = 0;
        public int length = 0;

        public ComplementPalindromeLocus(int position, int length) {
            this.position = position;
            this.length = length;
        }

        @Override
        public String toString() {
            return Integer.toString(position) + " " + Integer.toString(length);
        }
    }

    private LinkedList<ComplementPalindromeLocus> result = null;

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        result = new LinkedList<>();
        FastaReader fastaReader = new FastaReader(inputStream);
        for (FastaReader.FastaRecord rec : fastaReader.parse()) {
            processFastaRecord(rec);
        }

        for (ComplementPalindromeLocus loc : result) {
            outputStream.println(loc);
        }
    }

    private void processFastaRecord(FastaReader.FastaRecord rec) {
        byte[] data = rec.data;
        for (int i = 0; i < data.length; i++) {
            for (int j = 4; j <= 12; j += 2) {
                if (isReversePalindrome(data, i, j)) {
                    result.add(new ComplementPalindromeLocus(i + 1, j));
                }
            }
        }
    }

    boolean isReversePalindrome(byte[] data, int offset, int length) {
        int last = offset + length;
        if (last > data.length) return false;
        for (int i = 0; i < length; i++) {
//            System.out.println("cmp " + (char)(data[offset + i]) + " : " + (char)(complement(data[last - 1 - i])));
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
