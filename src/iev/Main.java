package iev;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

/**
 * Problem
 * For a random variable X taking integer values between 1 and n, the expected value
 * of X is E(X)=∑nk=1k×Pr(X=k). The expected value offers us a way of taking the long-term
 * average of a random variable over a large number of trials.
 * As a motivating example, let X be the number on a six-sided die. Over a large number of
 * rolls, we should expect to obtain an average of 3.5 on the die (even though it's not possible
 * to roll a 3.5). The formula for expected value confirms that E(X)=∑6k=1k×Pr(X=k)=3.5.
 *
 * More generally, a random variable for which every one of a number of equally spaced outcomes
 * has the same probability is called a uniform random variable (in the die example, this
 * "equal spacing" is equal to 1). We can generalize our die example to find that if X is a uniform
 * random variable with minimum possible value a and maximum possible value b, then E(X)=a+b2.
 * You may also wish to verify that for the dice example, if Y is the random variable associated
 * with the outcome of a second die roll, then E(X+Y)=7.
 *
 * Given: Six positive integers, each of which does not exceed 20,000. The integers correspond
 * to the number of couples in a population possessing each genotype pairing for a given factor.
 * In order, the six given integers represent the number of couples having the following genotypes:
 *
 * AA-AA
 * AA-Aa
 * AA-aa
 * Aa-Aa
 * Aa-aa
 * aa-aa
 *
 * Return: The expected number of offspring displaying the dominant phenotype in the next generation,
 * under the assumption that every couple has exactly two offspring.
 */
public class Main extends AbstractCmdProgram {
    private final double AA_AA_pr = 1;
    private final double AA_Aa_pr = 1;
    private final double AA_aa_pr = 1;
    private final double Aa_Aa_pr = 0.75;
    private final double Aa_aa_pr = 0.5;
    private final double aa_aa_pr = 0;

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        int AA_AA_pairs = inputStream.nextInt();
        int AA_Aa_pairs = inputStream.nextInt();
        int AA_aa_pairs = inputStream.nextInt();
        int Aa_Aa_pairs = inputStream.nextInt();
        int Aa_aa_pairs = inputStream.nextInt();
        int aa_aa_pairs = inputStream.nextInt();
        double result = (AA_AA_pairs * AA_AA_pr + AA_Aa_pairs * AA_Aa_pr + AA_aa_pairs * AA_aa_pr +
        Aa_Aa_pairs * Aa_Aa_pr + Aa_aa_pairs * Aa_aa_pr +
        aa_aa_pairs * aa_aa_pr) * 2;
        outputStream.println(result);
    }

    @Override
    protected void initDescription() {
        description = "** Problem\n" +
                " * For a random variable X taking integer values between 1 and n, the expected value\n" +
                " * of X is E(X)=∑nk=1k×Pr(X=k). The expected value offers us a way of taking the long-term\n" +
                " * average of a random variable over a large number of trials.\n" +
                " * As a motivating example, let X be the number on a six-sided die. Over a large number of\n" +
                " * rolls, we should expect to obtain an average of 3.5 on the die (even though it's not possible\n" +
                " * to roll a 3.5). The formula for expected value confirms that E(X)=∑6k=1k×Pr(X=k)=3.5.\n" +
                " * \n" +
                " * More generally, a random variable for which every one of a number of equally spaced outcomes\n" +
                " * has the same probability is called a uniform random variable (in the die example, this\n" +
                " * \"equal spacing\" is equal to 1). We can generalize our die example to find that if X is a uniform\n" +
                " * random variable with minimum possible value a and maximum possible value b, then E(X)=a+b2.\n" +
                " * You may also wish to verify that for the dice example, if Y is the random variable associated\n" +
                " * with the outcome of a second die roll, then E(X+Y)=7.\n" +
                " * \n" +
                " * Given: Six positive integers, each of which does not exceed 20,000. The integers correspond\n" +
                " * to the number of couples in a population possessing each genotype pairing for a given factor.\n" +
                " * In order, the six given integers represent the number of couples having the following genotypes:\n" +
                " * \n" +
                " * AA-AA\n" +
                " * AA-Aa\n" +
                " * AA-aa\n" +
                " * Aa-Aa\n" +
                " * Aa-aa\n" +
                " * aa-aa\n" +
                " * \n" +
                " * Return: The expected number of offspring displaying the dominant phenotype in the next generation,\n" +
                " * under the assumption that every couple has exactly two offspring.";
    }
}
