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

    static Stream<Arguments> unformattedArray(){
        return Stream.of(
                Arguments.of(new String[]{"atty", "tgbv", "AfvT", "eVVF"}, new String[]{"ATTY", "TGBV", "AFVT", "EVVF"}),
                Arguments.of(new String[]{"aTtT", "lNVF"},  new String[]{"ATTT", "LNVF"}),
                Arguments.of(new String[]{"attg"},  new String[]{"ATTG"})
        );
    }

    @ParameterizedTest
    @MethodSource("unformattedArray")
    public void givenUnformattedArray_whenArrayToUpperCase_thenReturnArrayInUpperCase(String[] unformattedDna, String[] formattedDna){
        //Act
        String [] result = service.arrayToUpperCase(unformattedDna);

        //Assert
        for(int i = 0; i < formattedDna.length; i++){
            Assertions.assertEquals(formattedDna[i], result[i]);
        }
    }

    static Stream<Arguments> invalidDna(){
        return Stream.of(
                Arguments.of(new String[]{"ATTG", "ATTT"}, false),
                Arguments.of(new String[]{"ATTT", "LNVF"}, false),
                Arguments.of(new String[]{"ATTG"}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidDna")
    public void givenInvalidDna_whenIsDnaValid_thenReturnFalse(String[] dna, boolean expectedResult){
        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    static Stream<Arguments> validDna(){
        return Stream.of(
                Arguments.of(new String[]{"ATTG", "ATTT", "TGCA", "GCAT"}, true),
                Arguments.of(new String[]{"ATTGTT", "ATTTCC", "TGCAAC", "GCATTC", "ATTGTC", "GGGGCC"}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("validDna")
    public void givenValidDna_whenIsDnaValid_thenReturnTrue(String[] dna, boolean expectedResult) {
        //Act
        boolean result = service.isValidDna(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    static Stream<Arguments> simianDna(){
        return Stream.of(
                Arguments.of(new String[]{"CATA", "ATCT", "CGTG", "AAAA"}, true),
                Arguments.of(new String[]{"CATG", "ATCG", "CGTG", "AAAG"}, true),
                Arguments.of(new String[]{"TATA", "ATCT", "CGTG", "AAAT"}, true),
                Arguments.of(new String[]{"TATC", "ATCT", "CCAG", "CAAT"}, true),
                Arguments.of(new String[]{"TATCA", "ATCTT", "ACTAA", "CATGG", "GATCG"}, true)
        );
    }

    @ParameterizedTest
    @MethodSource("simianDna")
    public void givenSimianDna_whenIsSimian_returnTrue(String[] dna, boolean expectedResult){
        //Act
        boolean result = service.isSimian(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    static Stream<Arguments> humanDna(){
        return Stream.of(
                Arguments.of(new String[]{"ATTG", "ATTT", "TGCA", "GCAT"}, false),
                Arguments.of(new String[]{"ATTGTT", "ATTTCC", "TGCAAC", "GCATTC", "ATTGTA", "GGGTCC"}, false)
        );
    }

    @ParameterizedTest
    @MethodSource("humanDna")
    public void givenHumanDna_whenIsSimian_returnFalse(String[] dna, boolean expectedResult){
        //Act
        boolean result = service.isSimian(dna);

        //Assert
        Assertions.assertEquals(expectedResult, result);
    }

    @Test
    public void whenRetrieveStatistics_returnFilledStatsDto(){
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
