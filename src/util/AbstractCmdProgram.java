package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public abstract class AbstractCmdProgram {
    protected String description;
    protected Scanner inputStream;
    protected PrintStream outputStream;
    protected String inputFileName = "";
    protected String outputFileName = "";

    public final void run(String[] args) throws IOException {
        long startTime;
        long endTime;
        init();
        printDescription();
        startTime = System.currentTimeMillis();
        try {
            doWork(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        endTime = System.currentTimeMillis();
        System.out.println("Work done for " + (endTime - startTime) + " milli's");
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
        inputFileName = "src/" + this.getClass().getPackage().getName() + "/input.txt";
    }

    protected void initOutputFileName() {
        outputFileName ="src/" + this.getClass().getPackage().getName() + "/output.txt";
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

        inputStream = new Scanner(Files.newInputStream(path), String.valueOf(StandardCharsets.ISO_8859_1));
    }

    private void printDescription() {
        System.out.println(description);
    }
}
