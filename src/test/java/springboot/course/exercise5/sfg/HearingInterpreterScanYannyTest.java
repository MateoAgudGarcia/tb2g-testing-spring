package springboot.course.exercise5.sfg;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("yanny")
@SpringJUnitConfig(classes = HearingInterpreterScanYannyTest.TestConfig.class)
class HearingInterpreterScanYannyTest {

    @Configuration
    @ComponentScan("springboot.course.exercise5.sfg")
    static class TestConfig{}

    @Autowired
    HearingInterpreter hearingInterpreter;

    @Test
    void whatIHeard(){
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Laurel", word);
    }
}