package prtm;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

/**
 * Problem
 *
 * In a weighted alphabet, every symbol is assigned a positive real
 * number called a weight. A string formed from a weighted alphabet
 * is called a weighted string, and its weight is equal to the sum of
 * the weights of its symbols.
 *
 * The standard weight assigned to each member of the 20-symbol amino acid
 * alphabet is the monoisotopic mass of the corresponding amino acid.
 *
 * Given: A protein string P of length at most 1000 aa.
 *
 * Return: The total weight of P. Consult the monoisotopic mass table.
 */

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        double result = 0;
        String data = inputStream.nextLine();
        Character x;
        for (int i = 0; i < data.length(); i++) {
            x = data.charAt(i);
            switch (x) {
                case 'A':
                    result += 71.03711;
                    break;

                case 'C':
                    result += 103.00919;
                    break;

                case 'D':
                    result += 115.02694;
                    break;

                case 'E':
                    result += 129.04259;
                    break;

                case 'F':
                    result += 147.06841;
                    break;

                case 'G':
                    result += 57.02146;
                    break;

                case 'H':
                    result += 137.05891;
                    break;

                case 'I':
                    result += 113.08406;
                    break;

                case 'K':
                    result += 128.09496;
                    break;

                case 'L':
                    result += 113.08406;
                    break;

                case 'M':
                    result += 131.04049;
                    break;

                case 'N':
                    result += 114.04293;
                    break;

                case 'P':
                    result += 97.05276;
                    break;

                case 'Q':
                    result += 128.05858;
                    break;

                case 'R':
                    result += 156.10111;
                    break;

                case 'S':
                    result += 87.03203;
                    break;

                case 'T':
                    result += 101.04768;
                    break;

                case 'V':
                    result += 99.06841;
                    break;

                case 'W':
                    result += 186.07931;
                    break;

                case 'Y':
                    result += 163.06333;
                    break;
            }
        }
        outputStream.println(result);
    }

    @Override
    protected void initDescription() {
        description = "** Problem\n" +
                " * In a weighted alphabet, every symbol is assigned a positive real\n" +
                " * number called a weight. A string formed from a weighted alphabet\n" +
                " * is called a weighted string, and its weight is equal to the sum of\n" +
                " * the weights of its symbols.\n" +
                " * The standard weight assigned to each member of the 20-symbol amino acid \n" +
                " * alphabet is the monoisotopic mass of the corresponding amino acid.\n" +
                " * \n" +
                " * Given: A protein string P of length at most 1000 aa.\n" +
                " * \n" +
                " * Return: The total weight of P. Consult the monoisotopic mass table.";
    }
}
