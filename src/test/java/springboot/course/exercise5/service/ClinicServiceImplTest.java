package springboot.course.exercise5.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springboot.course.exercise5.model.PetType;
import springboot.course.exercise5.repository.OwnerRepository;
import springboot.course.exercise5.repository.PetRepository;
import springboot.course.exercise5.repository.VetRepository;
import springboot.course.exercise5.repository.VisitRepository;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class ClinicServiceImplTest {
    @Mock
    PetRepository petRepository;

    @Mock
    VetRepository vetRepository;

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    ClinicServiceImpl clinicService;

    @Test
    void findPetTypes() {
        //GIVEN
        List<PetType> petTypes = new ArrayList<>();
        given(petRepository.findPetTypes()).willReturn(petTypes);

        //WHEN
        Collection<PetType> service = clinicService.findPetTypes();

        //THEN
        assertNotNull(service);
        assertThat(service).isEqualTo(petTypes);
        then(petRepository).should().findPetTypes();
    }
}