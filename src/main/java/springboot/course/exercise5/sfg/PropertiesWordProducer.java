package springboot.course.exercise5.sfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("externalized")
@Component
public class PropertiesWordProducer implements WordProducer{
    private String word;

    @Value("${say.word}")
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public String getWord() {
        return word;
    }
}

