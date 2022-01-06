package com.example.wineservice;

import com.example.wineservice.model.Wine;
import com.example.wineservice.repository.WineRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WineControllerUnitTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WineRepository wineRepository;

    private ObjectMapper mapper = new ObjectMapper();

    //Get tests
    @Test
    public void givenWine_whenGetWineByCountry_thenReturnJsonWines() throws Exception{
        Wine wine1 = new Wine( "Testwine1",  "Testregion1", "Testcountry1", 4, "Testgrape1");
        Wine wine11 = new Wine( "Testwine1.1",  "Testregion5", "Testcountry1", 3, "Testgrape2");

        List<Wine> wineList = new ArrayList<>();
        wineList.add(wine1);
        wineList.add(wine11);

        given(wineRepository.findWinesByCountry("Testcountry1")).willReturn(wineList);

        mockMvc.perform(get("/wines/country/{country}", "Testcountry1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Testwine1")))
                .andExpect(jsonPath("$[0].region", is("Testregion1")))
                .andExpect(jsonPath("$[0].country", is("Testcountry1")))
                .andExpect(jsonPath("$[0].score", is(4.0)))
                .andExpect(jsonPath("$[0].grapeName", is("Testgrape1")))

                .andExpect(jsonPath("$[1].name", is("Testwine1.1")))
                .andExpect(jsonPath("$[1].region", is("Testregion5")))
                .andExpect(jsonPath("$[1].country", is("Testcountry1")))
                .andExpect(jsonPath("$[1].score", is(3.0)))
                .andExpect(jsonPath("$[1].grapeName", is("Testgrape2")));
    }

    @Test
    public void givenWine_whenGetWineByCountryAndRegion_thenReturnJsonWines() throws Exception{
        Wine wine1 = new Wine( "Testwine1",  "Testregion1", "Testcountry1", 4, "Testgrape1");

        List<Wine> wineList = new ArrayList<>();
        wineList.add(wine1);

        given(wineRepository.findWinesByCountryAndRegion("Testcountry1", "Testregion1")).willReturn(wineList);

        mockMvc.perform(get("/wines/country/{country}/region/{region}", "Testcountry1", "Testregion1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Testwine1")))
                .andExpect(jsonPath("$[0].region", is("Testregion1")))
                .andExpect(jsonPath("$[0].country", is("Testcountry1")))
                .andExpect(jsonPath("$[0].score", is(4.0)))
                .andExpect(jsonPath("$[0].grapeName", is("Testgrape1")));
    }

    @Test
    public void givenWine_whenGetWineByGrapeName_thenReturnJsonWines() throws Exception{
        Wine wine1 = new Wine( "Testwine1",  "Testregion1", "Testcountry1", 4, "Testgrape1");

        List<Wine> wineList = new ArrayList<>();
        wineList.add(wine1);

        given(wineRepository.findWinesByGrapeName("Testgrape1")).willReturn(wineList);

        mockMvc.perform(get("/wines/grape/{grapeName}", "Testgrape1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Testwine1")))
                .andExpect(jsonPath("$[0].region", is("Testregion1")))
                .andExpect(jsonPath("$[0].country", is("Testcountry1")))
                .andExpect(jsonPath("$[0].score", is(4.0)))
                .andExpect(jsonPath("$[0].grapeName", is("Testgrape1")));
    }

    @Test
    public void givenWine_whenGetWineByName_thenReturnJsonWine() throws Exception{
        Wine wine2 = new Wine( "Testwine2",  "Testregion2", "Testcountry2", 4.5, "Testgrape2");

        given(wineRepository.findWineByName("Testwine2")).willReturn(wine2);

        mockMvc.perform(get("/wines/name/{name}", "Testwine2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Testwine2")))
                .andExpect(jsonPath("$.region", is("Testregion2")))
                .andExpect(jsonPath("$.country", is("Testcountry2")))
                .andExpect(jsonPath("$.score", is(4.5)))
                .andExpect(jsonPath("$.grapeName", is("Testgrape2")));
    }

    @Test
    public void givenWine_whenGetWines_thenReturnJsonWines() throws Exception{
        Wine wine1 = new Wine( "Testwine1",  "Testregion1", "Testcountry1", 4, "Testgrape1");
        Wine wine11 = new Wine( "Testwine1.1",  "Testregion5", "Testcountry1", 3, "Testgrape2");
        Wine wine2 = new Wine( "Testwine2",  "Testregion2", "Testcountry2", 4.5, "Testgrape2");

        List<Wine> wineList = new ArrayList<>();
        wineList.add(wine1);
        wineList.add(wine11);
        wineList.add(wine2);

        given(wineRepository.findAll()).willReturn(wineList);

        mockMvc.perform(get("/wines"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is("Testwine1")))
                .andExpect(jsonPath("$[0].region", is("Testregion1")))
                .andExpect(jsonPath("$[0].country", is("Testcountry1")))
                .andExpect(jsonPath("$[0].score", is(4.0)))
                .andExpect(jsonPath("$[0].grapeName", is("Testgrape1")))

                .andExpect(jsonPath("$[1].name", is("Testwine1.1")))
                .andExpect(jsonPath("$[1].region", is("Testregion5")))
                .andExpect(jsonPath("$[1].country", is("Testcountry1")))
                .andExpect(jsonPath("$[1].score", is(3.0)))
                .andExpect(jsonPath("$[1].grapeName", is("Testgrape2")))

                .andExpect(jsonPath("$[2].name", is("Testwine2")))
                .andExpect(jsonPath("$[2].region", is("Testregion2")))
                .andExpect(jsonPath("$[2].country", is("Testcountry2")))
                .andExpect(jsonPath("$[2].score", is(4.5)))
                .andExpect(jsonPath("$[2].grapeName", is("Testgrape2")));
    }

    //Post test
    @Test
    public void whenPostWine_thenReturnJsonWine() throws Exception{
        Wine wine3 = new Wine( "Testwine3",  "Testregion3", "Testcountry3", 5, "Testgrape3");

        mockMvc.perform(post("/wines")
                .content(mapper.writeValueAsString(wine3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Testwine3")))
                .andExpect(jsonPath("$.region", is("Testregion3")))
                .andExpect(jsonPath("$.country", is("Testcountry3")))
                .andExpect(jsonPath("$.score", is(5.0)))
                .andExpect(jsonPath("$.grapeName", is("Testgrape3")));
    }

    //Put test
    @Test
    public void givenWine_whenPutWine_thenReturnJsonWine() throws Exception{
        Wine wine4 = new Wine("Testwine1", "Testregion1", "Testcountry1", 5, "Testgrape1");

        given(wineRepository.findWineByName("Testwine1")).willReturn(wine4);

        Wine updatedWine = new Wine("Testwine1", "Testregion1", "Testcountry1", 1, "Testgrape1");

        mockMvc.perform(put("/wines")
                .content(mapper.writeValueAsString(updatedWine))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Testwine1")))
                .andExpect(jsonPath("$.region", is("Testregion1")))
                .andExpect(jsonPath("$.country", is("Testcountry1")))
                .andExpect(jsonPath("$.score", is(1.0)))
                .andExpect(jsonPath("$.grapeName", is("Testgrape1")));
    }

    //Delete tests
    @Test
    public void givenWine_whenDeleteWine_thenStatusOk() throws Exception{
        Wine wineToBeDeleted = new Wine("Testwine999", "Testregion999", "Testcountry999", 1, "Testgrape999");

        given(wineRepository.findWineByName("Testwine999")).willReturn(wineToBeDeleted);

        mockMvc.perform(delete("/wines/name/{name}", "Testwine999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenWine_whenDeleteWine_thenStatusNotFound() throws Exception{
        given(wineRepository.findWineByName("Testwine888")).willReturn(null);

        mockMvc.perform(delete("/wines/name/{name}", "Testwine888")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
