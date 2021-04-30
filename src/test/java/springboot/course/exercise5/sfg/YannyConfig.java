package springboot.course.exercise5.sfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class YannyConfig {

    @Bean
    YannyWordProducer yannyWordProducer(){
        return new YannyWordProducer();
    }
}