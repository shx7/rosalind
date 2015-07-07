package gc;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedList;

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader reader = new FastaReader();
        LinkedList<FastaRecord> parseResult = reader.parse();

        FastaRecord maxRecord = parseResult.get(0);
        for (Iterator<FastaRecord> iterator = parseResult.iterator(); iterator.hasNext(); ) {
            FastaRecord rec = iterator.next();
            if (rec.gcContent > maxRecord.gcContent) {
                maxRecord = rec;
            }
        }

        System.out.println("Max record\n" + maxRecord);
        outputStream.println(maxRecord);
    }

    static class FastaRecord {
        public String id = "";
        public double gcContent = 0;

        @Override
        public String toString() {
            return id + "\n" + gcContent;
        }
    }

    class FastaReader {

        public LinkedList<FastaRecord> parse() {
            String tmp;
            String id = null;
            FastaRecord record;
            int gcContentSize = 0;
            int contentSize = 0;
            LinkedList<FastaRecord> result = new LinkedList<>();
            while (inputStream.hasNextLine()) {
                tmp = inputStream.nextLine();
                if (tmp.charAt(0) == '>') {
                    if (id == null) {
                        id = tmp.substring(1, tmp.length());
                    } else {
                        record = new FastaRecord();
                        record.id = id;
                        record.gcContent = gcContentSize / (double)contentSize * 100;
                        result.add(record);
                        gcContentSize = 0;
                        contentSize = 0;
                        id = tmp.substring(1, tmp.length());
                    }
                } else {
                    for (int i = 0; i < tmp.length(); i++) {
                        if (tmp.charAt(i) == 'C' || tmp.charAt(i) == 'G') {
                            gcContentSize++;
                        }
                    }
                    contentSize += tmp.length();
                }
            }
            record = new FastaRecord();
            record.id = id;
            record.gcContent = gcContentSize / (double)contentSize * 100;
            result.add(record);
            return result;
        }

    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "The GC-content of a DNA string is given by the percentage of symbols inputStream the string\n" +
                "that are 'C' or 'G'. For example, the GC-content of \"AGCTATAG\" is 37.5%. Note that the\n" +
                "reverse complement of any DNA string has the same GC-content.\n" +
                "DNA strings must be labeled when they are consolidated into a database. A commonly used\n" +
                "method of string labeling is called FASTA format. In this format, the string is introduced\n" +
                "by a line that begins with '>', followed by some labeling information. Subsequent lines contain\n" +
                "the string itself; the first line to begin with '>' indicates the label of the next string.\n" +
                "In Rosalind's implementation, a string inputStream FASTA format will be labeled by the ID \"Rosalind_xxxx\",\n" +
                "where \"xxxx\" denotes a four-digit code between 0000 and 9999.\n" +
                "Given: At most 10 DNA strings inputStream FASTA format (of length at most 1 kbp each).\n" +
                "Return: The ID of the string having the highest GC-content, followed by the GC-content\n" +
                "of that string. Rosalind allows for a default error of 0.001 inputStream all decimal answers unless\n" +
                "otherwise stated; please see the note on absolute error below.";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/gc/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/gc/output.txt";
    }
}
