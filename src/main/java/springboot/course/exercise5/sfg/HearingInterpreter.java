package springboot.course.exercise5.sfg;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class HearingInterpreter {
    private final WordProducer wordProducer;

    public String whatIHeard(){
        String word = wordProducer.getWord();
        System.out.println(word);
        return word;
    }

}
