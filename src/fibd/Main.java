package fibd;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.LinkedList;

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        int n, m;
        n = inputStream.nextInt();
        m = inputStream.nextInt();

        BigDecimal grown = new BigDecimal(0);
        BigDecimal prevGrown;
        BigDecimal offspring = new BigDecimal(1);
        BigDecimal dead = new BigDecimal(0);
        LinkedList<BigDecimal> deadList = new LinkedList<>();
        BigDecimal result = null;
        /**
         * Using recurrent relation
         * M_n - count of adult rabbit pairs
         * O_n - count of offspring
         *
         * F_n = M_n + O_n full population count
         * M_n = M_(n-1) + O_(n+1) - M_(n-m+1) minus count
         * of dead mature population(they have been young at n - m + 1 iteration)
         * O_n = M_(n-1)
         */
        for (int i = 1; i <= n; i++) {
            result = grown.add(offspring);
//            System.out.println("Dead is " + dead + ", grown is " + grown + " offspring is " + offspring +
//                    " full is " + result);
            if (m > 1) {
                m--;
            } else {
                dead = deadList.pollFirst();
            }
            deadList.addLast(offspring);
//            System.out.println(deadList);
            prevGrown = grown;
            grown = grown.add(offspring).subtract(dead);
            offspring = prevGrown;
        }
//        System.out.println();
//        System.out.println("Result is " + result);
        outputStream.println(result);
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "Recall the definition of the Fibonacci numbers from “Rabbits and Recurrence Relations”, which" +
                "followed the recurrence relation Fn=Fn−1+Fn−2 and assumed that each pair of rabbits\n" +
                "reaches maturity in one month and produces a single pair of offspring (one male, one female)\n" +
                "each subsequent month.\n" +
                "Our aim is to somehow modify this recurrence relation to achieve a dynamic programming solution\n" +
                "in the case that all rabbits die out after a fixed number of months. See Figure 4 for a depiction\n" +
                "of a rabbit tree in which rabbits live for three months (meaning that they reproduce only twice before dying).\n" +
                "Given: Positive integers n≤100 and m≤20.\n" +
                "Return: The total number of pairs of rabbits that will remain after the n-th month if all rabbits live for m months.";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/fibd/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/fibd/output.txt";
    }
}
