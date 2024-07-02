package br.com.ape.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StatsDto implements Serializable {
    private float countMutantDna;
    private float countHumanDna;
    private float ratio;
}
