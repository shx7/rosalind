package iprb;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;

/**
 * Given: Three positive integers k, m, and n, representing a population containing k+m+n organisms:
 * k individuals are homozygous dominant for a factor, m are heterozygous, and n are homozygous recessive.
 *
 * Return: The probability that two randomly selected mating organisms will produce an individual possessing
 * a dominant allele (and thus displaying the dominant phenotype). Assume that any two organisms can mate.
 */
public class Main extends AbstractCmdProgram {
    private double COEFF_Aa_Aa = 0.75;
    private double COEFF_Aa_aa = 0.5;

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        int count_AA = inputStream.nextInt();
        int count_Aa = inputStream.nextInt();
        int count_aa = inputStream.nextInt();
        int sum = count_AA + count_Aa + count_aa;
        double result = 0;
        ProbabilityCounter prC = new ProbabilityCounter(sum);
        result += prC.getCurrentProbability(count_AA) *
                (prC.getLastProbability(count_AA - 1) + prC.getLastProbability(count_Aa) + prC.getLastProbability(count_aa));
        result += prC.getCurrentProbability(count_Aa) *
                (prC.getLastProbability(count_AA) + prC.getLastProbability(count_Aa - 1) * COEFF_Aa_Aa + prC.getLastProbability(count_aa) * COEFF_Aa_aa);
        result += prC.getCurrentProbability(count_aa) *
                (prC.getLastProbability(count_AA) + prC.getLastProbability(count_Aa) * COEFF_Aa_aa);
        outputStream.println(result);
    }

    class ProbabilityCounter {
        private int sumOrganismCount;

        public ProbabilityCounter(int sumOrganismCount) {
            this.sumOrganismCount = sumOrganismCount;
        }

        double getCurrentProbability(int organismCount) {
            if (organismCount <= 0) {
                return 0;
            }
            return organismCount / (double) sumOrganismCount;
        }

        double getLastProbability(int organismCount) {
            if (organismCount <= 0) {
                return 0;
            }
            return organismCount / (double) (sumOrganismCount - 1);
        }
    }

    @Override
    protected void initDescription() {
        description = "Given:\n" +
                "Three positive integers k, m, and n, representing a population containing k+m+n organisms: \n" +
                "k individuals are homozygous dominant for a factor, m are heterozygous, and n are homozygous recessive.\n" +
                "\n" +
                "Return:\n" +
                "The probability that two randomly selected mating organisms will produce an individual possessing\n" +
                "a dominant allele (and thus displaying the dominant phenotype). Assume that any two organisms can mate.";
    }
}
