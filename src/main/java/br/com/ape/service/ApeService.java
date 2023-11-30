package br.com.ape.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ApeService {

    private boolean isQuadratic (String [] dna){
        int count = 0;

        for (String d: dna) {
            if(d.length() == dna.length) count ++;
        }

        return (count == dna.length) ? true : false;
    }

    private boolean isDnaSequency (String [] dna){
        int count = 0;

        for (String d: dna) {
            if(d.matches("^[ATCG]+$")){
                count ++;
            }
        }

        return (count == dna.length) ? true : false;
    }

    private boolean isValidDna(String [] dna){
        return dna.length < 4 ? false : true;
    }

    public void isSimian(String[] dna) {
    }
}
