package springboot.course.exercise5.sfg;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HearingInterpreterTest {

    HearingInterpreter hearingInterpreter;

    @BeforeAll
    void setUp() throws Exception{
        hearingInterpreter = new HearingInterpreter(new LaurelWordProducer());
    }

    @Test
    void whatIHeard() {
        String word = hearingInterpreter.whatIHeard();

        assertEquals("Laurel", word);
    }
}