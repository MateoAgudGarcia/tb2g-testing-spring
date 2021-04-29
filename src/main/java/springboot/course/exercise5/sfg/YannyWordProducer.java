package springboot.course.exercise5.sfg;

import org.springframework.stereotype.Component;

@Component
public class YannyWordProducer implements WordProducer{
    @Override
    public String getWord() {
        return "Yanny";
    }
}
