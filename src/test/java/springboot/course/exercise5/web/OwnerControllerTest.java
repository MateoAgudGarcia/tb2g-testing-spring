package springboot.course.exercise5.web;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import springboot.course.exercise5.service.ClinicService;

import static org.junit.jupiter.api.Assertions.*;

@Disabled
@SpringJUnitConfig(locations = {"classpath:spring/mvc-test-config.xml","classpath:spring/mvc-core-config.xml"})
class OwnerControllerTest {
    @Autowired
    OwnerController ownerController;

    @Autowired
    ClinicService clinicService;

    @Test
    void template(){}
}