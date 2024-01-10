package br.com.ape.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDto {
    private float countMutantDna;
    private float countHumanDna;
    private float ratio;
}
