package cons;

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Problem
 *
 * A matrix is a rectangular table of values divided into rows and columns.
 * An m×n matrix has m rows and n columns. Given a matrix A, we write Ai,j to
 * indicate the value found at the intersection of row i and column j.
 *
 * Say that we have a collection of DNA strings, all having the same length n.
 * Their profile matrix is a 4×n matrix P in which P1,j represents the number of
 * times that 'A' occurs in the jth position of one of the strings, P2,j represents
 * the number of times that C occurs in the jth position, and so on (see below).
 *
 * A consensus string c is a string of length n formed from our collection by taking
 * the most common symbol at each position; the jth symbol of c therefore corresponds
 * to the symbol having the maximum value in the j-th column of the profile matrix.
 * Of course, there may be more than one most common symbol, leading to multiple possible
 * consensus strings.
 *
 * A T C C A G C T
 * G G G C A A C T
 * A T G G A T C T
 * DNA Strings	A A G C A A C C
 * T T G G A A C T
 * A T G C C A T T
 * A T G G C A C T
 * A   5 1 0 0 5 5 0 0
 * Profile	C   0 0 1 4 2 0 6 1
 * G   1 1 6 3 0 1 0 0
 * T   1 5 0 0 0 1 1 6
 * Consensus	A T G C A A C T
 *
 * Given: A collection of at most 10 DNA strings of equal length (at most 1 kbp)
 * in FASTA format.
 *
 * Return: A consensus string and profile matrix for the collection. (If several
 * possible consensus strings exist, then you may return any one of them.)
 */
public class Main extends AbstractCmdProgram {
    enum NT_ENUM {
        A, C, G, T
    }
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader reader = new FastaReader(inputStream);
        LinkedList<FastaReader.FastaRecord> recordsCollection = reader.parse();

        FastaReader.FastaRecord firstRecord = recordsCollection.poll();
        int[][] profile = new int[4][firstRecord.data.length];
        processFastaRecord(firstRecord, profile);

        for (FastaReader.FastaRecord rec : recordsCollection) {
            processFastaRecord(rec, profile);
        }

        outputStream.println(calculateConsensus(profile));

        for (int i = 0; i < NT_ENUM.values().length; i++) {
            outputStream.print(NT_ENUM.values()[i] + ": ");
            for (int x : profile[i]) {
                outputStream.print(x + " ");
            }
            outputStream.println();
        }
    }

    private String calculateConsensus(int[][] profile) {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < profile[0].length; i++) {
            int max_id = 0;
            for (int j = 1; j < 4; j++) {
                if (profile[max_id][i] < profile[j][i]) {
                    max_id = j;
                }
            }
            result.append(NT_ENUM.values()[max_id]);
        }
        return new String(result);
    }

    private void processFastaRecord(FastaReader.FastaRecord rec, int[][] profile) {
        for (int i = 0; i < rec.data.length; i++) {
            byte x = rec.data[i];

            switch (x) {
                case 'A':
                    profile[NT_ENUM.A.ordinal()][i]++;
                    break;

                case 'C':
                    profile[NT_ENUM.C.ordinal()][i]++;
                    break;

                case 'G':
                    profile[NT_ENUM.G.ordinal()][i]++;
                    break;

                case 'T':
                    profile[NT_ENUM.T.ordinal()][i]++;
                    break;
            }
        }
    }

    @Override
    protected void initDescription() {
        description = "* Problem\n" +
                " * \n" +
                " * A matrix is a rectangular table of values divided into rows and columns.\n" +
                " * An m×n matrix has m rows and n columns. Given a matrix A, we write Ai,j to\n" +
                " * indicate the value found at the intersection of row i and column j.\n" +
                " * \n" +
                " * Say that we have a collection of DNA strings, all having the same length n.\n" +
                " * Their profile matrix is a 4×n matrix P in which P1,j represents the number of\n" +
                " * times that 'A' occurs in the jth position of one of the strings, P2,j represents\n" +
                " * the number of times that C occurs in the jth position, and so on (see below).\n" +
                " * \n" +
                " * A consensus string c is a string of length n formed from our collection by taking\n" +
                " * the most common symbol at each position; the jth symbol of c therefore corresponds\n" +
                " * to the symbol having the maximum value in the j-th column of the profile matrix.\n" +
                " * Of course, there may be more than one most common symbol, leading to multiple possible\n" +
                " * consensus strings.\n" +
                " * \n" +
                " * A T C C A G C T\n" +
                " * G G G C A A C T\n" +
                " * A T G G A T C T\n" +
                " * DNA Strings\tA A G C A A C C\n" +
                " * T T G G A A C T\n" +
                " * A T G C C A T T\n" +
                " * A T G G C A C T\n" +
                " * A   5 1 0 0 5 5 0 0\n" +
                " * Profile\tC   0 0 1 4 2 0 6 1\n" +
                " * G   1 1 6 3 0 1 0 0\n" +
                " * T   1 5 0 0 0 1 1 6\n" +
                " * Consensus\tA T G C A A C T\n" +
                " * \n" +
                " * Given: A collection of at most 10 DNA strings of equal length (at most 1 kbp)\n" +
                " * in FASTA format.\n" +
                " * \n" +
                " * Return: A consensus string and profile matrix for the collection. (If several\n" +
                " * possible consensus strings exist, then you may return any one of them.)";
    }
}
