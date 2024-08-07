package br.com.ape.controller;

import br.com.ape.dto.ApeDto;
import br.com.ape.dto.StatsDto;
import br.com.ape.service.ApeService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApeController {

    private final ApeService service;

    @PostMapping
    @RequestMapping("/simian")
    public ResponseEntity<String> isSimian(@RequestBody ApeDto ape){

        String[] dnaUpperCase = service.arrayToUpperCase(ape.getDna());

        if(!service.isValidDna(dnaUpperCase)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Array format is incorrect");

        if(service.isSimian(dnaUpperCase)){
            return ResponseEntity.status(HttpStatus.OK).body("Informed DNA is simian");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Informed DNA is human");
        }
    }

    @GetMapping
    @RequestMapping("/stats")
    @Cacheable("dna_stats")
    public StatsDto dnaStatistics(){
        return service.retrieveStatistics();
    }
}
