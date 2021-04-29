package springboot.course.exercise5.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.course.exercise5.model.Vet;
import springboot.course.exercise5.model.Vets;
import springboot.course.exercise5.service.ClinicService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VetControllerTest {
    @Mock
    ClinicService clinicService;

    @Mock
    Map<String, Object> model;

    @InjectMocks
    VetController vetController;

    List<Vet> vetList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        //GIVEN
        vetList.add(new Vet());
        given(clinicService.findVets()).willReturn(vetList);
    }

    @Test
    void showVetList() {
        //WHEN
        String viewList = vetController.showVetList(model);

        //THEN
        assertNotNull(viewList);
        then(clinicService).should().findVets();
        then(model).should().put(anyString(),any());
        assertThat("vets/vetList").isEqualToIgnoringCase(viewList);
    }

    @Test
    void showResourcesVetList() {
        //WHEN
        Vets vets = vetController.showResourcesVetList();

        //THEN
        assertNotNull(vets);
        then(clinicService).should().findVets();
        assertThat(vets.getVetList()).hasSize(1);
    }
}