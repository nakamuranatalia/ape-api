package br.com.ape.service;

import br.com.ape.dto.StatsDto;
import br.com.ape.repository.ApeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApeServiceTest {

    @Mock
    private ApeRepository repository;

    @InjectMocks
    private ApeService service;

    @Test
    public void givenUnformattedArray_whenArrayToUpperCase_thenReturnArrayInUpperCase(){
        //Arrange
        String [] unformattedDna = new String[]{"atty", "tgbv", "AfvT", "eVVF"};
        String [] formattedDna = new String[]{"ATTY", "TGBV", "AFVT", "EVVF"};

        //Act
        String [] result = service.arrayToUpperCase(unformattedDna);

        //Assert
        for(int i = 0; i < formattedDna.length; i++){
            Assertions.assertEquals(formattedDna[i], result[i]);
        }
    }

    @Test
    public void givenNonQuadraticDna_whenIsValidDna_thenReturnFalse(){
        //Arrange
        String [] invalidDna = new String[]{"ATTG", "ATTT", "ACCG", "ACCG", "ACCG"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(invalidDna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenNonDnaSequence_whenIsValidDna_thenReturnFalse(){
        //Arrange
        String [] invalidDna = new String[]{"INFW", "WWWF", "UIIK", "KLMO"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(invalidDna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenArraySmallerThanFour_whenIsValidDna_thenReturnFalse(){
        //Arrange
        String [] invalidDna = new String[]{"AT", "CG"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isValidDna(invalidDna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenValidDna_whenIsValidDna_thenReturnTrue() {
        //Arrange
        String [] dna = new String[]{"ATTG", "ATTT", "TGCA", "GCAT"};
        boolean expectedResult = true;

        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenSimianDna_whenIsSimian_returnTrue(){
        //Arrange
        String [] dna = new String[]{"TATCA", "ATCTT", "ACTAA", "CATGG", "GATCG"};
        boolean expectedResult = true;

        //Act
        boolean result = service.isSimian(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void givenHumanDna_whenIsSimian_returnFalse()
    {
        //Arrange
        String [] dna = new String[]{"ATTG", "ATTT", "TGCA", "GCAT"};
        boolean expectedResult = false;

        //Act
        boolean result = service.isSimian(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void whenRetrieveStatistics_returnStatsDto(){
        //Arrange
        when(repository.countByIsSimian(true)).thenReturn(10L);
        when(repository.countByIsSimian(false)).thenReturn(4L);

        StatsDto expectedResult = new StatsDto();
        expectedResult.setCountMutantDna(10L);
        expectedResult.setCountHumanDna(4L);
        expectedResult.setRatio(2.5f);

        //Act
        StatsDto result = service.retrieveStatistics();

        //Assert
        Assertions.assertEquals(expectedResult.getCountHumanDna(),result.getCountHumanDna());
        Assertions.assertEquals(expectedResult.getCountMutantDna(),result.getCountMutantDna());
        Assertions.assertEquals(expectedResult.getRatio(),result.getRatio());
    }
}
