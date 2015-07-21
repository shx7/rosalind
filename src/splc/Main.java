package splc;

import util.AbstractCmdProgram;
import util.FastaReader;
import util.ProteineDecoder;

import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Problem
 *
 * After identifying the exons and introns of an RNA string, we only need
 * to delete the introns and concatenate the exons to form a new string ready
 * for translation.
 *
 * Given: A DNA string s (of length at most 1 kbp) and a collection of substrings
 * of s acting as introns. All strings are given in FASTA format.
 *
 * Return: A protein string resulting from transcribing and translating the exons of s.
 * (Note: Only one solution will exist for the dataset provided.)
 */
public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader reader = new FastaReader(inputStream);
        ProteineDecoder proteineDecoder = new ProteineDecoder();
        LinkedList<FastaReader.FastaRecord> records = reader.parse();
        String inputData = new String(records.poll().data);
        for (FastaReader.FastaRecord rec : records) {
            inputData = inputData.replaceAll(new String(rec.data), "");
        }
        outputStream.println(proteineDecoder.encodeFromDNA(inputData));
    }

    @Override
    protected void initDescription() {
        description = "* Problem\n" +
                " * \n" +
                " * After identifying the exons and introns of an RNA string, we only need\n" +
                " * to delete the introns and concatenate the exons to form a new string ready\n" +
                " * for translation.\n" +
                " * \n" +
                " * Given: A DNA string s (of length at most 1 kbp) and a collection of substrings\n" +
                " * of s acting as introns. All strings are given in FASTA format.\n" +
                " * \n" +
                " * Return: A protein string resulting from transcribing and translating the exons of s.\n" +
                " * (Note: Only one solution will exist for the dataset provided.)";
    }
}
