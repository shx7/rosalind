package util;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractCmdProgram {
    protected String description;
    protected DataInputStream inputStream;
    protected PrintStream outputStream;
    protected String inputFileName = "";
    protected String outputFileName = "";

    public final void run(String[] args) throws IOException {
        init();
        printDescription();
        try {
            doWork(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    protected void doWork(String[] args) throws FileNotFoundException {}

    private void init() throws IOException {
        initInputFileName();
        initOutputFileName();
        initInputFileStream();
        initOutputFileStream();
        initDescription();
    }

    protected void initDescription() {
        description = "No description";
    }

    protected void initInputFileName() {
        inputFileName = "";
    }

    protected void initOutputFileName() {
        outputFileName = "";
    }

    private void initOutputFileStream() throws IOException {
        Path path = Paths.get(outputFileName);
        outputStream = new PrintStream(Files.newOutputStream(path));
    }

    private void initInputFileStream() throws IOException {
        Path path = Paths.get(inputFileName);
        if (Files.exists(path) == false) {
            throw new FileNotFoundException("Input file " + inputFileName + " not found");
        }

        inputStream = new DataInputStream(Files.newInputStream(path));
    }

    public final String getDescription() {
        return description;
    }

    private void printDescription() {
        System.out.println(description);
    }
}
