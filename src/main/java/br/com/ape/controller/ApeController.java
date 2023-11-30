package br.com.ape.controller;

import br.com.ape.service.ApeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/simian")
@RequiredArgsConstructor
public class ApeController {

    private final ApeService service;

    @PostMapping
    public void isSimian(@RequestBody String[] dna){
        service.isSimian(dna);
    }
}
