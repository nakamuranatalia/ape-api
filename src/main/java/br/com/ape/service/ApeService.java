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

    public void isSimian(String[] dna) {
    }
}
