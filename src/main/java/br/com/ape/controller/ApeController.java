package br.com.ape.controller;

import br.com.ape.service.ApeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequiredArgsConstructor
public class ApeController {

    private final ApeService service;

    @PostMapping
    @RequestMapping("/simian")
    public ResponseEntity<String> isSimian(@RequestBody String[] dna){
        if(!service.isValidDna(dna)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Array format is incorrect");

        if(service.isSimian(dna)){
            return ResponseEntity.status(HttpStatus.OK).body("Informed DNA is simian");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Informed DNA is human");
        }
    }
}
