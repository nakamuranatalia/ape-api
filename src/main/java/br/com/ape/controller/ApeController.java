package br.com.ape.controller;

import br.com.ape.service.ApeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/simian")
@RequiredArgsConstructor
public class ApeController {

    private final ApeService service;

    @GetMapping
    public void isSimian(String [] dna){
        service.isSimian(dna);
    }
}
