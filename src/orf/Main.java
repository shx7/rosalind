package orf;
/**
 * Problem
 *
 * Either strand of a DNA double helix can serve as the coding strand for RNA
 * transcription. Hence, a given DNA string implies six total reading frames,
 * or ways in which the same region of DNA can be translated into amino acids:
 * three reading frames result from reading the string itself, whereas three
 * more result from reading its reverse complement.
 *
 * An open reading frame (ORF) is one which starts from the start codon and ends
 * by stop codon, without any other stop codons in between. Thus, a candidate
 * protein string is derived by translating an open reading frame into amino acids
 * until a stop codon is reached.
 *
 * Given: A DNA string s of length at most 1 kbp in FASTA format.
 * Return: Every distinct candidate protein string that can be translated from ORFs
 * of s. Strings can be returned in any order.
 */

import util.AbstractCmdProgram;
import util.FastaReader;

import java.io.FileNotFoundException;
import java.util.*;

public class Main extends AbstractCmdProgram {

    private HashMap<String, Character> aminoAcidsCodonTable = new HashMap<>();
    private LinkedList<String> stopCodonTable = new LinkedList<>();

    @Override
    protected void doWork(String[] args) throws FileNotFoundException {
        FastaReader fastaReader = new FastaReader(inputStream);
        HashSet<String> result = new HashSet<>();

        // Init codons collections
        initStopCodonTable();
        initAminoAcidsCodonTable();

        for (FastaReader.FastaRecord x : fastaReader.parse()) {
            String data = new String(x.data);
            processData(data, result);
            processData(reverseComplement(data), result);
        }

        for (String x : result) {
            outputStream.println(x);
        }
    }

    private String reverseComplement(String data) {
        StringBuffer res = new StringBuffer();
        Character complementNt;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < data.length(); i++) {
            Character nt = data.charAt(i);
            switch (nt) {
                case 'A':
                    complementNt = 'T';
                    break;

                case 'C':
                    complementNt = 'G';
                    break;

                case 'G':
                    complementNt = 'C';
                    break;

                case 'T':
                    complementNt = 'A';
                    break;

                default:
                    complementNt = 'E';
            }
            stack.push(complementNt);
        }

        while (!stack.empty()) {
            res.append(stack.pop());
        }
        return new String(res);
    }

    private void processData(String data, HashSet<String> result) {
        StringBuffer tmpResult;
        for (int i = 0; i < data.length() - 3; i++) {
            if (isStartCodon(data.substring(i, i + 3))) {
//                System.out.println("Start codon at " + i + " : " + (i + 3));
                String codon;
                tmpResult = new StringBuffer();
//                System.out.println("NEW frame at " + i);
                for (int j = i; j < data.length(); j += 3) {
                    codon = data.substring(j, j + 3);
                    if (isStopCodon(codon)) {
//                        System.out.println("Stop codon at " + j + " : " + (j + 3));
                        result.add(new String(tmpResult));
                        break;
                    }
                    tmpResult.append(aminoAcidsCodonTable.get(codon));
                }
            }
        }
    }

    private boolean isStopCodon(String codon) {
        return stopCodonTable.contains(codon);
    }

    private boolean isStartCodon(String codon) {
        return !isStopCodon(codon) && aminoAcidsCodonTable.get(codon) == 'M';
    }

    private void initStopCodonTable() {
        stopCodonTable = new LinkedList<>();
        stopCodonTable.add("TAA");
        stopCodonTable.add("TAG");
        stopCodonTable.add("TGA");
    }

    private void initAminoAcidsCodonTable() {
        aminoAcidsCodonTable = new HashMap<>();
        // UUU F      CUU L      AUU I      GUU V
        aminoAcidsCodonTable.put("TTT", 'F');
        aminoAcidsCodonTable.put("CTT", 'L');
        aminoAcidsCodonTable.put("ATT", 'I');
        aminoAcidsCodonTable.put("GTT", 'V');
        // UUC F      CUC L      AUC I      GUC V
        aminoAcidsCodonTable.put("TTC", 'F');
        aminoAcidsCodonTable.put("CTC", 'L');
        aminoAcidsCodonTable.put("ATC", 'I');
        aminoAcidsCodonTable.put("GTC", 'V');
        // UUA L      CUA L      AUA I      GUA V
        aminoAcidsCodonTable.put("TTA", 'L');
        aminoAcidsCodonTable.put("CTA", 'L');
        aminoAcidsCodonTable.put("ATA", 'I');
        aminoAcidsCodonTable.put("GTA", 'V');
        // UUG L      CUG L      AUG M      GUG V
        aminoAcidsCodonTable.put("TTG", 'L');
        aminoAcidsCodonTable.put("CTG", 'L');
        aminoAcidsCodonTable.put("ATG", 'M');
        aminoAcidsCodonTable.put("GTG", 'V');
        // UCU S      CCU P      ACU T      GCU A
        aminoAcidsCodonTable.put("TCT", 'S');
        aminoAcidsCodonTable.put("CCT", 'P');
        aminoAcidsCodonTable.put("ACT", 'T');
        aminoAcidsCodonTable.put("GCT", 'A');
        // UCC S      CCC P      ACC T      GCC A
        aminoAcidsCodonTable.put("TCC", 'S');
        aminoAcidsCodonTable.put("CCC", 'P');
        aminoAcidsCodonTable.put("ACC", 'T');
        aminoAcidsCodonTable.put("GCC", 'A');
        // UCA S      CCA P      ACA T      GCA A
        aminoAcidsCodonTable.put("TCA", 'S');
        aminoAcidsCodonTable.put("CCA", 'P');
        aminoAcidsCodonTable.put("ACA", 'T');
        aminoAcidsCodonTable.put("GCA", 'A');
        // UCG S      CCG P      ACG T      GCG A
        aminoAcidsCodonTable.put("TCG", 'S');
        aminoAcidsCodonTable.put("CCG", 'P');
        aminoAcidsCodonTable.put("ACG", 'T');
        aminoAcidsCodonTable.put("GCG", 'A');
        // UAU Y      CAU H      AAU N      GAU D
        aminoAcidsCodonTable.put("TAT", 'Y');
        aminoAcidsCodonTable.put("CAT", 'H');
        aminoAcidsCodonTable.put("AAT", 'N');
        aminoAcidsCodonTable.put("GAT", 'D');
        // UAC Y      CAC H      AAC N      GAC D
        aminoAcidsCodonTable.put("TAC", 'Y');
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
        aminoAcidsCodonTable.put("TGT", 'C');
        aminoAcidsCodonTable.put("CGT", 'R');
        aminoAcidsCodonTable.put("AGT", 'S');
        aminoAcidsCodonTable.put("GGT", 'G');
        // UGC C      CGC R      AGC S      GGC G
        aminoAcidsCodonTable.put("TGC", 'C');
        aminoAcidsCodonTable.put("CGC", 'R');
        aminoAcidsCodonTable.put("AGC", 'S');
        aminoAcidsCodonTable.put("GGC", 'G');
        // UGA Stop   CGA R      AGA R      GGA G
        aminoAcidsCodonTable.put("CGA", 'R');
        aminoAcidsCodonTable.put("AGA", 'R');
        aminoAcidsCodonTable.put("GGA", 'G');
        // UGG W      CGG R      AGG R      GGG G
        aminoAcidsCodonTable.put("TGG", 'W');
        aminoAcidsCodonTable.put("CGG", 'R');
        aminoAcidsCodonTable.put("AGG", 'R');
        aminoAcidsCodonTable.put("GGG", 'G');
    }

    @Override
    protected void initDescription() {
        description = "Problem\n" +
                "Either strand of a DNA double helix can serve as the coding strand for RNA \n" +
                "transcription. Hence, a given DNA string implies six total reading frames, \n" +
                "or ways in which the same region of DNA can be translated into amino acids: \n" +
                "three reading frames result from reading the string itself, whereas three \n" +
                "more result from reading its reverse complement.\n" +
                "An open reading frame (ORF) is one which starts from the start codon and ends \n" +
                "by stop codon, without any other stop codons in between. Thus, a candidate \n" +
                "protein string is derived by translating an open reading frame into amino acids \n" +
                "until a stop codon is reached.\n" +
                "\n" +
                "Given: A DNA string s of length at most 1 kbp in FASTA format.\n" +
                "\n" +
                "Return: Every distinct candidate protein string that can be translated from ORFs \n" +
                "of s. Strings can be returned in any order.";
    }
}
