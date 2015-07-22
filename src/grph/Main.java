package grph;

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Problem
 *
 * A graph whose nodes have all been labeled can be represented by an adjacency list,
 * in which each row of the list contains the two node labels corresponding to a unique edge.
 *
 * A directed graph (or digraph) is a graph containing directed edges, each of which has an
 * orientation. That is, a directed edge is represented by an arrow instead of a line segment;
 * the starting and ending nodes of an edge form its tail and head, respectively. The directed
 * edge with tail v and head w is represented by (v,w) (but not by (w,v)). A directed loop is
 * a directed edge of the form (v,v).
 *
 * For a collection of strings and a positive integer k, the overlap graph for the strings is a
 * directed graph Ok in which each string is represented by a node, and string s is connected to
 * string t with a directed edge when there is a length k suffix of s that matches a length k prefix
 * of t, as long as s≠t; we demand s≠t to prevent directed loops in the overlap graph (although
 * directed cycles may be present).
 *
 * Given: A collection of DNA strings in FASTA format having total length at most 10 kbp.
 *
 * Return: The adjacency list corresponding to O3. You may return edges in any order.
 */


public class Main extends AbstractCmdProgram {
    class StringFastaRecord {
        String data;
        String id;

        public StringFastaRecord(String data, String id) {
            this.data = data;
            this.id = id;
        }
    }

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader reader = new FastaReader(inputStream);
        LinkedList<FastaReader.FastaRecord> records = reader.parse();
        ArrayList<StringFastaRecord> recordsData = new ArrayList<>(records.size());
        for (FastaReader.FastaRecord record : records) {
            recordsData.add(new StringFastaRecord(new String(record.data), record.id));
        }

        ArrayList<String> result = new ArrayList<>();

        for (int i = 0; i < recordsData.size(); i++) {
            for (int j = 0; j < recordsData.size(); j++) {
                if (i != j) {
                    if ((isOverlapped(recordsData.get(j).data, recordsData.get(i).data)) &&
                        (!recordsData.get(j).data.equals(recordsData.get(i).data))) {
                        result.add(recordsData.get(i).id + " " + recordsData.get(j).id);
                    }
                }
            }
        }

        for (String x : result) {
            outputStream.println(x);
        }
    }

    private boolean isOverlapped(String dna1, String dna2) {
        for (int i = 0; i < 3; i++) {
            if (dna1.charAt(2 - i) != dna2.charAt(dna2.length() - i - 1)) return false;
        }
        return true;
    }

    @Override
    protected void initDescription() {
        description = " * Problem\n" +
                " * A graph whose nodes have all been labeled can be represented by an adjacency list,\n" +
                " * in which each row of the list contains the two node labels corresponding to a unique edge.\n" +
                " * A directed graph (or digraph) is a graph containing directed edges, each of which has an\n" +
                " * orientation. That is, a directed edge is represented by an arrow instead of a line segment;\n" +
                " * the starting and ending nodes of an edge form its tail and head, respectively. The directed\n" +
                " * edge with tail v and head w is represented by (v,w) (but not by (w,v)). A directed loop is\n" +
                " * a directed edge of the form (v,v).\n" +
                " * For a collection of strings and a positive integer k, the overlap graph for the strings is a\n" +
                " * directed graph Ok in which each string is represented by a node, and string s is connected to\n" +
                " * string t with a directed edge when there is a length k suffix of s that matches a length k prefix\n" +
                " * of t, as long as s≠t; we demand s≠t to prevent directed loops in the overlap graph (although\n" +
                " * directed cycles may be present).\n" +
                " * \n" +
                " * Given: A collection of DNA strings in FASTA format having total length at most 10 kbp.\n" +
                " * \n" +
                " * Return: The adjacency list corresponding to O3. You may return edges in any order.";
    }
}
