package prot;

import util.AbstractCmdProgram;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;

public class Main extends AbstractCmdProgram {
    private HashMap<String, Character> aminoAcidsCodonTable;
    private LinkedList<String> stopCodonTable;

    public Main() {
        initAminoAcidsCodonTable();
        initStopCodonTable();
    }

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        String data;
        data = inputStream.nextLine();
        for (int i = 0; i < data.length(); i += 3) {
            String tmp = data.substring(i, i + 3);
            if (isStopCodon(tmp)) continue;
            outputStream.print(getAminoAcid(tmp));
        }
    }

    private boolean isStopCodon(String codon) {
        return stopCodonTable.contains(codon);
    }

    private Character getAminoAcid(String codon) {
        return aminoAcidsCodonTable.get(codon);
    }

    private void initAminoAcidsCodonTable() {
        aminoAcidsCodonTable = new HashMap<>();
        // UUU F      CUU L      AUU I      GUU V
        aminoAcidsCodonTable.put("UUU", 'F');
        aminoAcidsCodonTable.put("CUU", 'L');
        aminoAcidsCodonTable.put("AUU", 'I');
        aminoAcidsCodonTable.put("GUU", 'V');
        // UUC F      CUC L      AUC I      GUC V
        aminoAcidsCodonTable.put("UUC", 'F');
        aminoAcidsCodonTable.put("CUC", 'L');
        aminoAcidsCodonTable.put("AUC", 'I');
        aminoAcidsCodonTable.put("GUC", 'V');
        // UUA L      CUA L      AUA I      GUA V
        aminoAcidsCodonTable.put("UUA", 'L');
        aminoAcidsCodonTable.put("CUA", 'L');
        aminoAcidsCodonTable.put("AUA", 'I');
        aminoAcidsCodonTable.put("GUA", 'V');
        // UUG L      CUG L      AUG M      GUG V
        aminoAcidsCodonTable.put("UUG", 'L');
        aminoAcidsCodonTable.put("CUG", 'L');
        aminoAcidsCodonTable.put("AUG", 'M');
        aminoAcidsCodonTable.put("GUG", 'V');
        // UCU S      CCU P      ACU T      GCU A
        aminoAcidsCodonTable.put("UCU", 'S');
        aminoAcidsCodonTable.put("CCU", 'P');
        aminoAcidsCodonTable.put("ACU", 'T');
        aminoAcidsCodonTable.put("GCU", 'A');
        // UCC S      CCC P      ACC T      GCC A
        aminoAcidsCodonTable.put("UCC", 'S');
        aminoAcidsCodonTable.put("CCC", 'P');
        aminoAcidsCodonTable.put("ACC", 'T');
        aminoAcidsCodonTable.put("GCC", 'A');
        // UCA S      CCA P      ACA T      GCA A
        aminoAcidsCodonTable.put("UCA", 'S');
        aminoAcidsCodonTable.put("CCA", 'P');
        aminoAcidsCodonTable.put("ACA", 'T');
        aminoAcidsCodonTable.put("GCA", 'A');
        // UCG S      CCG P      ACG T      GCG A
        aminoAcidsCodonTable.put("UCG", 'S');
        aminoAcidsCodonTable.put("CCG", 'P');
        aminoAcidsCodonTable.put("ACG", 'T');
        aminoAcidsCodonTable.put("GCG", 'A');
        // UAU Y      CAU H      AAU N      GAU D
        aminoAcidsCodonTable.put("UAU", 'Y');
        aminoAcidsCodonTable.put("CAU", 'H');
        aminoAcidsCodonTable.put("AAU", 'N');
        aminoAcidsCodonTable.put("GAU", 'D');
        // UAC Y      CAC H      AAC N      GAC D
        aminoAcidsCodonTable.put("UAC", 'Y');
        aminoAcidsCodonTable.put("CAC", 'H');
        aminoAcidsCodonTable.put("AAC", 'N');
        aminoAcidsCodonTable.put("GAC", 'D');
        // UAA Stop   CAA Q      AAA K      GAA E
        aminoAcidsCodonTable.put("CAA", 'Q');
        aminoAcidsCodonTable.put("AAA", 'K');
        aminoAcidsCodonTable.put("GAA", 'E');
        // UAG Stop   CAG Q      AAG K      GAG E
        aminoAcidsCodonTable.put("CAG", 'Q');
        aminoAcidsCodonTable.put("AAG", 'K');
        aminoAcidsCodonTable.put("GAG", 'E');
        // UGU C      CGU R      AGU S      GGU G
        aminoAcidsCodonTable.put("UGU", 'C');
        aminoAcidsCodonTable.put("CGU", 'R');
        aminoAcidsCodonTable.put("AGU", 'S');
        aminoAcidsCodonTable.put("GGU", 'G');
        // UGC C      CGC R      AGC S      GGC G
        aminoAcidsCodonTable.put("UGC", 'C');
        aminoAcidsCodonTable.put("CGC", 'R');
        aminoAcidsCodonTable.put("AGC", 'S');
        aminoAcidsCodonTable.put("GGC", 'G');
        // UGA Stop   CGA R      AGA R      GGA G
        aminoAcidsCodonTable.put("CGA", 'R');
        aminoAcidsCodonTable.put("AGA", 'R');
        aminoAcidsCodonTable.put("GGA", 'G');
        // UGG W      CGG R      AGG R      GGG G
        aminoAcidsCodonTable.put("UGG", 'W');
        aminoAcidsCodonTable.put("CGG", 'R');
        aminoAcidsCodonTable.put("AGG", 'R');
        aminoAcidsCodonTable.put("GGG", 'G');
    }

    private void initStopCodonTable() {
        stopCodonTable = new LinkedList<>();
        stopCodonTable.add("UAA");
        stopCodonTable.add("UAG");
        stopCodonTable.add("UGA");
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "The 20 commonly occurring amino acids are abbreviated by using 20 letters from the\n" +
                "English alphabet (all letters except for B, J, O, U, X, and Z). Protein strings are\n" +
                "constructed from these 20 symbols. Henceforth, the term genetic string will incorporate\n" +
                "protein strings along with DNA strings and RNA strings.\n" +
                "The RNA codon table dictates the details regarding the encoding\n" +
                "of specific codons into the amino acid alphabet.\n" +
                "Given: An RNA string s corresponding to a strand of mRNA (of length at most 10 kbp).\n" +
                "Return: The protein string encoded by s.";
    }

    @Override
    protected void initInputFileName() {
        inputFileName = "src/prot/input.txt";
    }

    @Override
    protected void initOutputFileName() {
        outputFileName = "src/prot/output.txt";
    }
}
