package springboot.course.exercise5.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import springboot.course.exercise5.model.Owner;
import springboot.course.exercise5.service.ClinicService;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerMVCTest {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";

    @Mock
    ClinicService clinicService;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    Map<String, Object> model = new HashMap<>();

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @AfterEach
    void tearDown(){
        reset(clinicService);
    }

    @Test
    void processUpdateOwnerFormValid() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",69)
                                .param("firstName","Mateo")
                                .param("lastName","Agudelo")
                                .param("Address","Mzl St")
                                .param("City","Manizales")
                                .param("Telephone","3001112233"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void processUpdateOwnerFormNegative() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit",69)
                .param("firstName","Mateo")
                .param("lastName","Agudelo")    
                .param("Telephone","3001112233"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processFindFormValid() throws Exception {
        mockMvc.perform(post("/owners/new").param("firstName","Mateo")
                                                     .param("lastName","Agudelo")
                                                     .param("Address","Mzl St")
                                                     .param("City","Manizales")
                                                     .param("Telephone","3001112233"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void processFindFormNegative() throws Exception {
        mockMvc.perform(post("/owners/new").param("firstName","Mateo")
                                                     .param("lastName","Agudelo")
                                                     .param("City","Manizales"))
                .andExpect(status().isOk())
                .andExpect(model().attributeHasErrors("owner"))
                .andExpect(model().attributeHasFieldErrors("owner","address"))
                .andExpect(model().attributeHasFieldErrors("owner","telephone"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }

    @Test
    void processFindFormNotFound() throws Exception {
        mockMvc.perform(get("/owners").param("lastName","Don't find me"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindFormNotFoundString() throws Exception {
        given(clinicService.findOwnerByLastName("")).willReturn(Lists.newArrayList(new Owner(), new Owner()));

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"));

        then(clinicService).should().findOwnerByLastName(stringArgumentCaptor.capture());

        assertThat(stringArgumentCaptor.getValue()).isEqualToIgnoringCase("");
    }

    @Test
    void processFindFormOneValueFound() throws Exception {
        Owner justOne = new Owner();
        justOne.setId(1234);
        final String findJustOne = "Find just one";

        given(clinicService.findOwnerByLastName(findJustOne)).willReturn(Lists.newArrayList(justOne));

        mockMvc.perform(get("/owners").param("lastName",findJustOne))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1234"));

        then(clinicService).should().findOwnerByLastName(anyString());
    }

    @Test
    void initCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("owner"))
                .andExpect(view().name(VIEWS_OWNER_CREATE_OR_UPDATE_FORM));
    }
}