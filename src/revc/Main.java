package revc;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.util.Stack;

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        Stack<Character> stack = new Stack<>();
        char nt;
        char complementNt;
        while (inputStream.hasNext()) {
            nt = (char)inputStream.nextByte();
            switch (nt) {
                case 'A':
                    complementNt = 'T';
                    break;

                case 'C':
                    complementNt = 'G';
                    break;

                case 'G':
                    complementNt = 'C';
                    break;

                case 'T':
                    complementNt = 'A';
                    break;

                default:
                    complementNt = 'E';
            }
            stack.push(complementNt);
        }

        while (!stack.empty()) {
            outputStream.append(stack.pop());
        }
    }

    @Override
    protected void initDescription() {
        description = "In DNA strings, symbols 'A' and 'T' are complements of each other, as are 'C' and 'G'.\n" +
                "The reverse complement of a DNA string s is the string sc formed by reversing the symbols of s, then taking the complement of each symbol (e.g., the reverse complement of \"GTCA\" is \"TGAC\").\n" +
                "Given: A DNA string s of length at most 1000 bp.\n" +
                "Return: The reverse complement sc of s.\n";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/revc/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/revc/output.txt";
    }
}
