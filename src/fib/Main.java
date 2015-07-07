package fib;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.math.BigDecimal;

public class Main extends AbstractCmdProgram {
    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        int n, k;
        BigDecimal result = new BigDecimal(1);
        BigDecimal prev = new BigDecimal(0);
        BigDecimal tmp;
        n = inputStream.nextInt();
        k = inputStream.nextInt();
        for (int i = 1; i < n; i++) {
            tmp = result;
            result = result.add(prev.multiply(new BigDecimal(k)));
            prev = tmp;
        }
        outputStream.println(result);
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "A sequence is an ordered collection of objects (usually numbers), which are allowed to repeat.\n" +
                "Sequences can be finite or infinite. Two examples are the finite sequence (π,−2‾√,0,π) and the\n" +
                "infinite sequence of odd numbers (1,3,5,7,9,…). We use the notation an to represent the n-th\n" +
                "term of a sequence.\n" +
                "A recurrence relation is a way of defining the terms of a sequence with respect to the values\n" +
                "of previous terms. In the case of Fibonacci's rabbits from the introduction, any given month will\n" +
                "contain the rabbits that were alive the previous month, plus any new offspring. A key observation\n" +
                "is that the number of offspring in any month is equal to the number of rabbits that were alive two\n" +
                "months prior. As a result, if Fn represents the number of rabbit pairs alive after the n-th month,\n" +
                "then we obtain the Fibonacci sequence having terms Fn that are defined by the recurrence relation\n" +
                "Fn=Fn−1+Fn−2 (with F1=F2=1 to initiate the sequence). Although the sequence bears Fibonacci's name,\n" +
                "it was known to Indian mathematicians over two millennia ago.\n" +
                "When finding the n-th term of a sequence defined by a recurrence relation, we can simply use the\n" +
                "recurrence relation to generate terms for progressively larger values of n. This problem introduces\n" +
                "us to the computational technique of dynamic programming, which successively builds up solutions by\n" +
                "using the answers to smaller cases.\n" +
                "Given: Positive integers n≤40 and k≤5.\n" +
                "Return: The total number of rabbit pairs that will be present after n months if we begin with 1 pair\n" +
                "and in each generation, every pair of reproduction-age rabbits produces a litter of k rabbit pairs\n" +
                "(instead of only 1 pair).";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/fib/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/fib/output.txt";
    }
}
