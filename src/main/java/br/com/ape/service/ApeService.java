package br.com.ape.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ApeService {

    private boolean isQuadratic (String [] dna){
        int count = 0;

        for (String d: dna) {
            if(d.length() == dna.length) count ++;
        }

        return (count == dna.length);
    }

    private boolean isDnaSequency (String [] dna){
        int count = 0;

        for (String d: dna) {
            if(d.matches("^[ATCG]+$")){
                count ++;
            }
        }

        return (count == dna.length);
    }

    private boolean isValidDna(String [] dna){
        return dna.length < 4;
    }

    private String[] splitDna(String [] dna){
        ArrayList<String> dnaList = new ArrayList<>();

        for(String i: dna)
            for(String j: i.split("")){
                dnaList.add(j);
            }

        String[] splitDna = dnaList.toArray(new String[dnaList.size()]);

        return splitDna;
    }

    private String[][] transformInTwoDimensional(String [] dna, int arraySize){
        String[][] dnaMatrix = new String[arraySize][arraySize];
        int position = 0;

        for(int row = 0; row < arraySize; row++)
            for(int column = 0; column < arraySize; column++){
                dnaMatrix[row][column] = dna[position];
                position++;
            }

        return dnaMatrix;
    }

    private boolean verifyDnaHorizontally(String[][] dna, int arraySize){
        boolean broke = false;

        for (int i = 0; i < arraySize; i++){
            for(int j = 0; j <= arraySize - 4; j++){
                if(dna[i][j].equals(dna[i][j+1])
                        && dna[i][j].equals(dna[i][j+2])
                        && dna[i][j].equals(dna[i][j+3])){
                    broke = true;
                }
            }

            if(broke) break;
        }

        return broke;
    }

    public void isSimian(String[] dna) {
    }
}
