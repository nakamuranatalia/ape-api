package br.com.ape.service;

import br.com.ape.dto.StatsDto;
import lombok.RequiredArgsConstructor;
import br.com.ape.repository.ApeRepository;
import br.com.ape.model.Ape;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.apache.tomcat.util.buf.StringUtils;
import java.util.ArrayList;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ApeService {
    private final ApeRepository repository;

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
        return dna.length >= 4;
    }

    private String[] splitDna(String [] dna){
        ArrayList<String> dnaList = new ArrayList<>();

        for(String i: dna)
            Collections.addAll(dnaList, i.split(""));

        return dnaList.toArray(new String[0]);
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

        for (int row = 0; row < arraySize; row++){
            for(int column = 0; column <= arraySize - 4; column++){
                if(dna[row][column].equals(dna[row][column+1])
                        && dna[row][column].equals(dna[row][column+2])
                        && dna[row][column].equals(dna[row][column+3])){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verifyDnaVertically(String[][] dna, int arraySize){

        for (int column = 0; column < arraySize; column++){
            for(int row = 0; row <= arraySize - 4; row++){
                if(dna[row][column].equals(dna[row+1][column])
                        && dna[row][column].equals(dna[row+2][column])
                        && dna[row][column].equals(dna[row+3][column])){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verifyDnaDiagonallyLeftToRight(String[][] dna, int arraySize){

        for (int row = 0; row <= arraySize - 4; row++){
            for(int column = 0; column <= arraySize - 4; column++){
                if(dna[row][column].equals(dna[row+1][column+1])
                        && dna[row][column].equals(dna[row+2][column+2])
                        && dna[row][column].equals(dna[row+3][column+3])){
                    return true;
                }
            }
        }

        return false;
    }

    private boolean verifyDnaDiagonallyRightToLeft(String[][] dna, int arraySize){

        for (int row = 0; row <= arraySize - 4; row++){
            for(int column = arraySize - 1; column >= 3; column--){
                if(dna[row][column].equals(dna[row+1][column-1])
                        && dna[row][column].equals(dna[row+2][column-2])
                        && dna[row][column].equals(dna[row+3][column-3])){
                    return true;
                }
            }
        }

        return false;
    }

    public String[] arrayToUpperCase(String[] dna){
        for(int i = 0; i < dna.length; i++){
            dna[i] = dna[i].toUpperCase();
        }

        return dna;
    }

    public boolean isValidDna(String[] dna){
        return isQuadratic(dna) && isDnaSequence(dna) && isValidArray(dna);
    }

    public boolean isSimian(String[] dna) {

        String dnaString = StringUtils.join(dna);

        if (repository.existsByDna(dnaString)){
            return repository.findByDna(dnaString).isSimian();
        } else{
            int length = dna.length;
            String[][] dnaMatrix = transformInTwoDimensional(splitDna(dna), length);

            if(verifyDnaHorizontally(dnaMatrix, length)
                    || verifyDnaVertically(dnaMatrix, length)
                    || verifyDnaDiagonallyLeftToRight(dnaMatrix, length)
                    || verifyDnaDiagonallyRightToLeft(dnaMatrix, length)){
                Ape ape = new Ape();

                ape.setDna(dnaString);
                ape.setSimian(true);

                repository.save(ape);

                return true;
            }else{
                Ape ape = new Ape();

                ape.setDna(dnaString);
                ape.setSimian(false);

                repository.save(ape);

                return false;
            }
        }
    }

    @Cacheable("dna_stats")
    public StatsDto retrieveStatistics(){
        float mutantDna = repository.countByIsSimian(true);
        float humanDna = repository.countByIsSimian(false);
        float ratio = mutantDna/humanDna;

        StatsDto stats = new StatsDto();
        stats.setCountMutantDna(mutantDna);
        stats.setCountHumanDna(humanDna);
        stats.setRatio(ratio);

        return stats;
    }
}
