package com.cnp.api.apirestcnp.service;


import com.cnp.api.apirestcnp.model.Client;
import com.cnp.api.apirestcnp.repository.ClientRepository;
import com.cnp.api.apirestcnp.service.imp.ClientServiceImpl;

import org.junit.Before;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {


    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


     @Test
     public void testGetClientById() throws ParseException {
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         Date date = df.parse("1908-02-17");
         Client client  = new Client(1, "1987", "Deloin", "Alain", date, "France" );
         when(clientRepository.findById(1)).thenReturn(Optional.of(client));

         Optional<Client> client1 = clientService.getClientById(1);
         Client client2 = client1.get();
         assertEquals(1, client2.getId().longValue());
         assertEquals("1987", client2.getRicNumero());
         assertEquals("Deloin", client2.getNomClient());
         assertEquals("Alain", client2.getPrenomClient());
         assertEquals(date, client2.getDateNaissance());
         assertEquals("France", client2.getLieuNaissance());
     }

    @Test
    public void testCreateClient() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date date = df.parse("1998-01-13");
        Client client = new Client(2, "rt89ioY6", "Jasper", "Jim", date, "Espagne");

        when(clientRepository.save(client)).thenReturn(client);

        Client client1 = clientService.createClient(client);

        assertEquals(2, client1.getId().longValue());
        assertEquals("Jasper", client1.getNomClient());
        assertEquals("Jim", client1.getPrenomClient());
        assertEquals(date, client1.getDateNaissance());
        assertEquals("Espagne", client1.getLieuNaissance());

    }




}
