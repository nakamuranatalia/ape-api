package br.com.ape.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="ape")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ape {

    @Id
    @Column(columnDefinition = "TEXT")
    private String dna;

    private boolean isSimian;
}
