package util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class ProteineDecoder {

    private HashMap<String, Character> aminoAcidsDnaCodonTable = new HashMap<>();
    private LinkedList<String> stopDnaCodonTable = new LinkedList<>();

    public ProteineDecoder() {
        initAminoAcidsCodonTable();
        initStopCodonTable();
    }

    public String reverseComplement(String data) {
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

    public String encodeFromDNA(String data) {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < data.length() - 3; i += 3) {
            res.append(aminoAcidsDnaCodonTable.get(data.substring(i, i + 3)));
        }
        return new String(res);
    }

    public boolean isStopCodon(String codon) {
        return stopDnaCodonTable.contains(codon);
    }

    public boolean isStartCodon(String codon) {
        return !isStopCodon(codon) && aminoAcidsDnaCodonTable.get(codon) == 'M';
    }

    private void initStopCodonTable() {
        stopDnaCodonTable = new LinkedList<>();
        stopDnaCodonTable.add("TAA");
        stopDnaCodonTable.add("TAG");
        stopDnaCodonTable.add("TGA");
    }

    private void initAminoAcidsCodonTable() {
        aminoAcidsDnaCodonTable = new HashMap<>();
        // UUU F      CUU L      AUU I      GUU V
        aminoAcidsDnaCodonTable.put("TTT", 'F');
        aminoAcidsDnaCodonTable.put("CTT", 'L');
        aminoAcidsDnaCodonTable.put("ATT", 'I');
        aminoAcidsDnaCodonTable.put("GTT", 'V');
        // UUC F      CUC L      AUC I      GUC V
        aminoAcidsDnaCodonTable.put("TTC", 'F');
        aminoAcidsDnaCodonTable.put("CTC", 'L');
        aminoAcidsDnaCodonTable.put("ATC", 'I');
        aminoAcidsDnaCodonTable.put("GTC", 'V');
        // UUA L      CUA L      AUA I      GUA V
        aminoAcidsDnaCodonTable.put("TTA", 'L');
        aminoAcidsDnaCodonTable.put("CTA", 'L');
        aminoAcidsDnaCodonTable.put("ATA", 'I');
        aminoAcidsDnaCodonTable.put("GTA", 'V');
        // UUG L      CUG L      AUG M      GUG V
        aminoAcidsDnaCodonTable.put("TTG", 'L');
        aminoAcidsDnaCodonTable.put("CTG", 'L');
        aminoAcidsDnaCodonTable.put("ATG", 'M');
        aminoAcidsDnaCodonTable.put("GTG", 'V');
        // UCU S      CCU P      ACU T      GCU A
        aminoAcidsDnaCodonTable.put("TCT", 'S');
        aminoAcidsDnaCodonTable.put("CCT", 'P');
        aminoAcidsDnaCodonTable.put("ACT", 'T');
        aminoAcidsDnaCodonTable.put("GCT", 'A');
        // UCC S      CCC P      ACC T      GCC A
        aminoAcidsDnaCodonTable.put("TCC", 'S');
        aminoAcidsDnaCodonTable.put("CCC", 'P');
        aminoAcidsDnaCodonTable.put("ACC", 'T');
        aminoAcidsDnaCodonTable.put("GCC", 'A');
        // UCA S      CCA P      ACA T      GCA A
        aminoAcidsDnaCodonTable.put("TCA", 'S');
        aminoAcidsDnaCodonTable.put("CCA", 'P');
        aminoAcidsDnaCodonTable.put("ACA", 'T');
        aminoAcidsDnaCodonTable.put("GCA", 'A');
        // UCG S      CCG P      ACG T      GCG A
        aminoAcidsDnaCodonTable.put("TCG", 'S');
        aminoAcidsDnaCodonTable.put("CCG", 'P');
        aminoAcidsDnaCodonTable.put("ACG", 'T');
        aminoAcidsDnaCodonTable.put("GCG", 'A');
        // UAU Y      CAU H      AAU N      GAU D
        aminoAcidsDnaCodonTable.put("TAT", 'Y');
        aminoAcidsDnaCodonTable.put("CAT", 'H');
        aminoAcidsDnaCodonTable.put("AAT", 'N');
        aminoAcidsDnaCodonTable.put("GAT", 'D');
        // UAC Y      CAC H      AAC N      GAC D
        aminoAcidsDnaCodonTable.put("TAC", 'Y');
        aminoAcidsDnaCodonTable.put("CAC", 'H');
        aminoAcidsDnaCodonTable.put("AAC", 'N');
        aminoAcidsDnaCodonTable.put("GAC", 'D');
        // UAA Stop   CAA Q      AAA K      GAA E
        aminoAcidsDnaCodonTable.put("CAA", 'Q');
        aminoAcidsDnaCodonTable.put("AAA", 'K');
        aminoAcidsDnaCodonTable.put("GAA", 'E');
        // UAG Stop   CAG Q      AAG K      GAG E
        aminoAcidsDnaCodonTable.put("CAG", 'Q');
        aminoAcidsDnaCodonTable.put("AAG", 'K');
        aminoAcidsDnaCodonTable.put("GAG", 'E');
        // UGU C      CGU R      AGU S      GGU G
        aminoAcidsDnaCodonTable.put("TGT", 'C');
        aminoAcidsDnaCodonTable.put("CGT", 'R');
        aminoAcidsDnaCodonTable.put("AGT", 'S');
        aminoAcidsDnaCodonTable.put("GGT", 'G');
        // UGC C      CGC R      AGC S      GGC G
        aminoAcidsDnaCodonTable.put("TGC", 'C');
        aminoAcidsDnaCodonTable.put("CGC", 'R');
        aminoAcidsDnaCodonTable.put("AGC", 'S');
        aminoAcidsDnaCodonTable.put("GGC", 'G');
        // UGA Stop   CGA R      AGA R      GGA G
        aminoAcidsDnaCodonTable.put("CGA", 'R');
        aminoAcidsDnaCodonTable.put("AGA", 'R');
        aminoAcidsDnaCodonTable.put("GGA", 'G');
        // UGG W      CGG R      AGG R      GGG G
        aminoAcidsDnaCodonTable.put("TGG", 'W');
        aminoAcidsDnaCodonTable.put("CGG", 'R');
        aminoAcidsDnaCodonTable.put("AGG", 'R');
        aminoAcidsDnaCodonTable.put("GGG", 'G');
    }
}
