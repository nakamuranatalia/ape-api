package br.com.ape.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ApeService {

    private boolean isQuadratic (String [] dna){
        int count = 0;
        for (String i: dna) {
            if(i.length() == dna.length) count ++;
        }
        if(count == dna.length){
            return  true;
        }else{
            return false;
        }
    }
    public void isSimian(String[] dna) {
    }
}
