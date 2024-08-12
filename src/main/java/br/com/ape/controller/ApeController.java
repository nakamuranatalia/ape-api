package br.com.ape.controller;

import br.com.ape.dto.ApeDto;
import br.com.ape.dto.StatsDto;
import br.com.ape.service.ApeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name="Ape-api")

public class ApeController {

    private final ApeService service;

    @PostMapping
    @RequestMapping(value = "/simian", method = RequestMethod.POST)
    @Operation(summary = "Checks if given DNA is simian or human.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Array format is incorrect"),
            @ApiResponse(responseCode = "200", description = "Informed DNA is simian"),
            @ApiResponse(responseCode = "403", description = "Informed DNA is human")
    })
    public ResponseEntity<String> isSimian(@RequestBody ApeDto ape){

        String[] dnaUpperCase = service.arrayToUpperCase(ape.getDna());

        if(!service.isValidDna(dnaUpperCase)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Array format is incorrect");

        if(service.isSimian(dnaUpperCase)){
            return ResponseEntity.status(HttpStatus.OK).body("Informed DNA is simian");
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Informed DNA is human");
        }
    }

    @GetMapping
    @RequestMapping(value = "/stats", method = RequestMethod.GET)
    @Operation(summary = "Returns the statistics of DNA checks")
    @Cacheable("dna_stats")
    public StatsDto dnaStatistics(){
        return service.retrieveStatistics();
    }
}
