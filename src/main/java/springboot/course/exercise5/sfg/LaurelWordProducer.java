package springboot.course.exercise5.sfg;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class LaurelWordProducer implements WordProducer{
    @Override
    public String getWord() {
        return "Laurel";
    }
}
