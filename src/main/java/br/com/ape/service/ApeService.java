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

    private ArrayList<String> transformInTwoDimensional(String [] dna){
        ArrayList<String> dnaList = new ArrayList<>();
        for (String d:dna) {
           dnaList.add(Arrays.toString(d.split("")));
        }
        return dnaList;
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

    public void isSimian(String[] dna) {
    }
}
