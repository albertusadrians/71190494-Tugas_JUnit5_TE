import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UnitTest {
    static Pajak pajak;

    @BeforeAll
    static void init(){
        pajak = new Pajak();
    }

    @AfterAll
    static void destroy(){
        pajak = null;
    }

    // EC: Equivalence Class Test Scenario
    private static Stream<Arguments> vECTest(){
        return Stream.of(
                // Equivalence Class Nilai Valid
                Arguments.of(0,3500000),
                Arguments.of(10,14500000),
                Arguments.of(22,39500000),
                Arguments.of(40f,999500000000f),
                // Equivalence Class Nilai Error (Return -1)
                Arguments.of(-1,-4000000),
                Arguments.of(-1,1500000000000f)
        );
    }

    // Method untuk Test Equivalence Class Scenario
    @ParameterizedTest
    @MethodSource("vECTest")
    void parameterizedTestPajakEC(double expected, double salary){
        Assertions.assertEquals(expected,pajak.getPajak(salary));
    }

    // BVA: Boundary Value Analysis Test Scenario
    private static Stream<Arguments> bva1Test(){
        return Stream.of(
                // BVA untuk vEC 1 dan vEC 2
                Arguments.of(true,3999999),
                Arguments.of(true,4000000),
                Arguments.of(false,4000001)
        );
    }

    private static Stream<Arguments> bva2Test(){
        return Stream.of(
                // BVA untuk vEC 2 dan vEC 3
                Arguments.of(true,14999999),
                Arguments.of(true,15000000),
                Arguments.of(false,15000001)
        );
    }

    private static Stream<Arguments> bva3Test(){
        return Stream.of(
                // BVA untuk vEC 3 dan vEC 4
                Arguments.of(true,39999999),
                Arguments.of(true,40000000),
                Arguments.of(false,40000001)
        );
    }

    private static Stream<Arguments> bva4Test(){
        return Stream.of(
                Arguments.of(true,999999999998f),
                Arguments.of(true,999999999999f),
                Arguments.of(false,1000000000000d)
        );
    }

    // Method untuk Boundary Value Analysis Scenario
    @ParameterizedTest
    @MethodSource("bva1Test")
    void parameterizedTestPajakBVA1(boolean expected, double salary){
        assertNotNull(pajak);
        // BVA untuk vEC 1 dan vEC 2
        Assertions.assertEquals(expected,pajak.getPajak(salary)==0);
    }

    @ParameterizedTest
    @MethodSource("bva2Test")
    void parameterizedTestPajakBVA2(boolean expected, double salary){
        assertNotNull(pajak);
        // BVA untuk vEC 2 dan vEC 3
        Assertions.assertEquals(expected,pajak.getPajak(salary)==10);
    }

    @ParameterizedTest
    @MethodSource("bva3Test")
    void parameterizedTestPajakBVA3(boolean expected, double salary){
        assertNotNull(pajak);
        // BVA untuk vEC 3 dan vEC 4
        Assertions.assertEquals(expected,pajak.getPajak(salary)==22);
    }

    @ParameterizedTest
    @MethodSource("bva4Test")
    void parameterizedTestPajakBVA4(boolean expected, double salary){
        assertNotNull(pajak);
        // BVA untuk vEC 4
        Assertions.assertEquals(expected,pajak.getPajak(salary)==40f);
    }
}
