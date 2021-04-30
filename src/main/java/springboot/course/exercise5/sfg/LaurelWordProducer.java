package springboot.course.exercise5.sfg;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("laurel")
@Component
public class LaurelWordProducer implements WordProducer{
    @Override
    public String getWord() {
        return "Laurel";
    }
}
