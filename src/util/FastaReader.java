package util;

import java.util.LinkedList;
import java.util.Scanner;

public class FastaReader {
    private Scanner in;

    public FastaReader(Scanner in) {
        this.in = in;
    }

    public LinkedList<FastaRecord> parse() {
        String tmp;
        String id = null;
        FastaRecord record;
        int contentSize = 0;
        byte[] data = new byte[0];
        LinkedList<FastaRecord> result = new LinkedList<>();
        while (in.hasNextLine()) {
            tmp = in.nextLine();
            if (tmp.charAt(0) == '>') {
                if (id == null) {
                    id = tmp.substring(1, tmp.length());
                } else {
                    record = new FastaRecord();
                    record.id = id;
                    record.data = data;
                    result.add(record);
                    contentSize = 0;
                    id = tmp.substring(1, tmp.length());
                }
            } else {
                int newContentSize = contentSize + tmp.length();
                byte[] newData = new byte[newContentSize];
                for (int i = 0; i < contentSize; i++) {
                    newData[i] = data[i];
                }
                for (int i = contentSize; i < newContentSize; i++) {
                    newData[i] = tmp.getBytes()[i - contentSize];
                }
                contentSize = newContentSize;
                data = newData;
            }
        }
        record = new FastaRecord();
        record.id = id;
        record.data = data;
        result.add(record);
        return result;
    }


    public class FastaRecord {
        public String id = "";
        public byte[] data = null;

        @Override
        public String toString() {
            return id + "\n" + (new String(data));
        }
    }
}
