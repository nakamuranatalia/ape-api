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

    private boolean isDnaSequence (String [] dna){
        int count = 0;

        for (String d: dna) {
            if(d.matches("^[ATCG]+$")){
                count ++;
            }
        }

        return (count == dna.length);
    }

    private boolean isValidArray(String [] dna){
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

        for (int row = 0; row < arraySize; row++){
            for(int column = 0; column <= arraySize - 4; column++){
                if(dna[row][column].equals(dna[row][column+1])
                        && dna[row][column].equals(dna[row][column+2])
                        && dna[row][column].equals(dna[row][column+3])){
                    broke = true;
                }
            }

            if(broke) break;
        }

        return broke;
    }

    private boolean verifyDnaVertically(String[][] dna, int arraySize){
        boolean broke = false;

        for (int column = 0; column < arraySize; column++){
            for(int row = 0; row <= arraySize - 4; row++){
                if(dna[row][column].equals(dna[row+1][column])
                        && dna[row][column].equals(dna[row+2][column])
                        && dna[row][column].equals(dna[row+3][column])){
                    broke = true;
                }
            }

            if(broke) break;
        }

        return broke;
    }

    private boolean verifyDnaDiagonallyLeftToRight(String[][] dna, int arraySize){
        boolean broke = false;

        for (int row = 0; row < arraySize - 4; row++){
            for(int column = 0; column <= arraySize - 4; column++){
                if(dna[row][column].equals(dna[row+1][column+1])
                        && dna[row][column].equals(dna[row+2][column+2])
                        && dna[row][column].equals(dna[row+3][column+3])){
                    broke = true;
                }
            }

            if(broke) break;
        }

        return broke;
    }

    private boolean verifyDnaDiagonallyRightToLeft(String[][] dna, int arraySize){
        boolean broke = false;

        for (int row = 0; row < arraySize - 4; row++){
            for(int column = arraySize - 1; column > arraySize - 3; column--){
                if(dna[row][column].equals(dna[row+1][column-1])
                        && dna[row][column].equals(dna[row+2][column-2])
                        && dna[row][column].equals(dna[row+3][column-3])){
                    broke = true;
                }
            }

            if(broke) break;
        }

        return broke;
    }

    private boolean isValidDna(String[] dna){
        return isQuadratic(dna) && isDnaSequence(dna) && isValidArray(dna);
    }

    public void isSimian(String[] dna) {
    }
}
