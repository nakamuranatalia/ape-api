package br.com.ape.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name="ape")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ape {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String dna;

    private boolean isSimian;
}
