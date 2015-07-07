package dna;

import util.AbstractCmdProgram;

import java.io.*;

public class Main extends AbstractCmdProgram {

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        char x;
        int adenineCounter = 0, cytosineCounter = 0, guanineCounter = 0, thymineCounter = 0;
        while (inputStream.hasNext()) {
            x = (char)inputStream.nextByte();
            switch (x) {
                case 'A':
                    adenineCounter++;
                    break;

                case 'C':
                    cytosineCounter++;
                    break;

                case 'T':
                    thymineCounter++;
                    break;

                case 'G':
                    guanineCounter++;
                    break;
            }
        }
        System.out.println();
        outputStream.println(adenineCounter + " " + cytosineCounter + " " +
        guanineCounter + " " + thymineCounter);
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/dna/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/dna/output.txt";
    }

    @Override
    protected void initDescription() {
        description = "Given: A DNA string s of length at most 1000 nt.\n" +
                "Return: Four integers (separated by spaces) counting the respective number " +
                "of times that the symbols 'A', 'C', 'G', and 'T' occur in s.";
    }
}
