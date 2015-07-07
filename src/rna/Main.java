package rna;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

public class Main extends AbstractCmdProgram{
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        char x;

        while (inputStream.hasNext()) {
            x = (char)inputStream.nextByte();
            if (x == 'T') {
                outputStream.append('U');
            } else {
                outputStream.append(x);
            }
        }
    }

    @Override
    protected void initDescription() {
        description = "Given: A DNA string t having length at most 1000 nt.\n" +
                "Return: The transcribed RNA string of t.";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/rna/output.txt";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/rna/input.txt";
    }
}
