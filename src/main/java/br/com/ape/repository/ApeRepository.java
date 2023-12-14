package br.com.ape.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ape.model.Ape;

@Repository
public interface ApeRepository extends JpaRepository<Ape, Long> {

    boolean existsByDna (String dna);

    Ape findByDna (String dna);
}
