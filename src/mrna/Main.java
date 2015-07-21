package mrna;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

/**
 * Problem
 *
 * For positive integers a and n, a modulo n (written amodn in shorthand) is
 * the remainder when a is divided by n. For example, 29mod11=7 because 29=11×2+7.
 * Modular arithmetic is the study of addition, subtraction, multiplication, and
 * division with respect to the modulo operation. We say that a and b are congruent
 * modulo n if amodn=bmodn; in this case, we use the notation a≡bmodn.
 * Two useful facts in modular arithmetic are that if a≡bmodn and c≡dmodn, then
 * a+c≡b+dmodn and a×c≡b×dmodn. To check your understanding of these rules, you may
 * wish to verify these relationships for a=29, b=73, c=10, d=32, and n=11.
 * As you will see in this exercise, some Rosalind problems will ask for a (very large)
 * integer solution modulo a smaller number to avoid the computational pitfalls that
 * arise with storing such large numbers.
 *
 * Given: A protein string of length at most 1000 aa.
 * Return: The total number of different RNA strings from which the protein could have
 * been translated, modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)
 */
public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        String data = inputStream.nextLine();
        Character x;
        long result = 1;
        for (int i = 0; i < data.length(); i++) {
            x = data.charAt(i);
            switch (x) {
                case 'M':
                    result *= 1;
                    break;

                case 'F':
                    result *= 2;
                    break;

                case 'L':
                    result *= 6;
                    break;

                case 'I':
                    result *= 3;
                    break;

                case 'V':
                    result *= 4;
                    break;

                case 'S':
                    result *= 6;
                    break;

                case 'P':
                    result *= 4;
                    break;

                case 'T':
                    result *= 4;
                    break;

                case 'A':
                    result *= 4;
                    break;

                case 'Y':
                    result *= 2;
                    break;

                case 'H':
                    result *= 2;
                    break;

                case 'N':
                    result *= 2;
                    break;

                case 'D':
                    result *= 2;
                    break;

                case 'Q':
                    result *= 2;
                    break;

                case 'K':
                    result *= 2;
                    break;

                case 'E':
                    result *= 2;
                    break;

                case 'C':
                    result *= 2;
                    break;

                case 'R':
                    result *= 6;
                    break;

                case 'G':
                    result *= 4;
                    break;

                case 'W':
                    result *= 1;
                    break;

                default:
                    System.out.println("ERROR: " + x);
            }
            result %= 1_000_000;
        }

        result *= 3;
        result %= 1_000_000;
        outputStream.println(result);
    }

    @Override
    protected void initDescription() {
        description = " * Problem\n" +
                " * \n" +
                " * For positive integers a and n, a modulo n (written amodn in shorthand) is\n" +
                " * the remainder when a is divided by n. For example, 29mod11=7 because 29=11×2+7.\n" +
                " * Modular arithmetic is the study of addition, subtraction, multiplication, and\n" +
                " * division with respect to the modulo operation. We say that a and b are congruent\n" +
                " * modulo n if amodn=bmodn; in this case, we use the notation a≡bmodn.\n" +
                " * Two useful facts in modular arithmetic are that if a≡bmodn and c≡dmodn, then \n" +
                " * a+c≡b+dmodn and a×c≡b×dmodn. To check your understanding of these rules, you may \n" +
                " * wish to verify these relationships for a=29, b=73, c=10, d=32, and n=11.\n" +
                " * As you will see in this exercise, some Rosalind problems will ask for a (very large)\n" +
                " * integer solution modulo a smaller number to avoid the computational pitfalls that\n" +
                " * arise with storing such large numbers.\n" +
                " * \n" +
                " * Given: A protein string of length at most 1000 aa.\n" +
                " * Return: The total number of different RNA strings from which the protein could have\n" +
                " * been translated, modulo 1,000,000. (Don't neglect the importance of the stop codon in protein translation.)";
    }
}
