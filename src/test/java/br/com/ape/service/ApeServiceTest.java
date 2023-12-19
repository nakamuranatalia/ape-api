package br.com.ape.service;

import br.com.ape.repository.ApeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ApeServiceTest {

    @Mock
    private ApeRepository repository;

    @InjectMocks
    private ApeService service;

    @Test
    public void givenNonSquareDna_whenIsDnaValid_thenReturnFalse(){
        //Arrange
        String[] dna = new String[]{"ATTG", "ATTT"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenNonDnaSequence_whenIsDnaValid_thenReturnFalse(){
        //Arrange
        String[] dna = new String[]{"ATTT", "LNVF"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenInvalidDnaArray_whenIsDnaValid_thenReturnFalse(){
        //Arrange
        String[] dna = new String[]{"ATTG"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenValidDna_whenIsDnaValid_thenReturnTrue(){
        //Arrange
        String[] dna = new String[]{"ATTG", "ATTT", "TGCA", "GCAT"};
        boolean expectedResult = true;

        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }
}
