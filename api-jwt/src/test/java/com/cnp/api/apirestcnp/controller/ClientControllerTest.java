package com.cnp.api.apirestcnp.controller;

import com.cnp.api.apirestcnp.ApiRestCnpApplication;
import com.cnp.api.apirestcnp.controller.ClientController;
import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.service.ClientService;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApiRestCnpApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientService clientService;

    @Autowired
    private WebApplicationContext wac;

    @InjectMocks
    private ClientController clientController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void verifyGetAllClients() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/clients").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(104))).andDo(print());
    }


    @Test
    public void verifyGetClientById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/1097").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.ricNumero").exists())
                .andExpect(jsonPath("$.nomClient").exists())
                .andExpect(jsonPath("$.dateNaissance").exists())
                .andExpect(jsonPath("$.lieuNaissance").exists())
                .andExpect(jsonPath("$.prenomClient").exists())
                .andExpect(jsonPath("$.id").value(1096))
                .andExpect(jsonPath("$.nomClient").value("Valencia"))
                .andExpect(jsonPath("$.ricNumero").value("CDY27BJR7WZ"))
                .andExpect(jsonPath("$.prenomClient").value("Sierra"))
                .andExpect(jsonPath("$.dateNaissance").value("1985-08-26"))
                .andExpect(jsonPath("$.lieuNaissance").value("Netherlands"))
                .andDo(print());
    }


    @Test
    public void verifyDeleteClientById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete-client/1196").accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").value("Client has been deleted"))
                .andDo(print());
    }


    @Test
    public void verifyCreateOrUpdateClient() throws Exception {
        doNothing().when(clientService).createClient(any(Client.class));
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/client/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nomClient\" : \"Michel\", \"lieuNaissance\" : \"France\", \"prenomClient\" : \"Tom\", \"dateNaissance\" : \"1980-09-14T00:00:00.000+00:00\" }")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.ricNumero").exists())
                .andExpect(jsonPath("$.nomClient").exists())
                .andExpect(jsonPath("$.prenomClient").exists())
                .andExpect(jsonPath("$.lieuNaissance").exists())
                .andExpect(jsonPath("$.dateNaissance").exists())
                .andExpect(jsonPath("$.nomClient").value("Michel"))
                .andExpect(jsonPath("$.lieuNaissance").value("France"))
                .andExpect(jsonPath("$.dateNaissance").value("1980-09-14T00:00:00.000+00:00"))
                .andExpect(jsonPath("$.prenomClient").value("Tom"))
                .andDo(print());

    }


    @Test
    public void verifyCreateOrUpdateClient2() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("1980-01-13");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/v1/client")
                        .content(asJsonString(new Client(1, "1987", "Albert", "Mar", date, "France")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void verifyInvalidClientByIdArgument() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/client/f").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    public void verifyDeleteClientById2() throws Exception {
        Mockito.when(clientService.deleteClient(1096)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/delete-client/1096"))
                .andExpect(status().isOk());
    }


}
