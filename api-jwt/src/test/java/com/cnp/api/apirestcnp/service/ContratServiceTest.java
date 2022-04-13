package com.cnp.api.apirestcnp.service;

import com.cnp.api.apirestcnp.model.Contrat;
import com.cnp.api.apirestcnp.repository.ContratRepository;
import com.cnp.api.apirestcnp.service.imp.ContratServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ContratServiceTest {

    @Mock
    private ContratRepository contratRepository;

    @InjectMocks
    private ContratServiceImpl contratService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testDeleteContrat() throws ParseException {
        Contrat contrat = new Contrat(2, "assurrance vie", 75.0, "Avantage");
        contratService.deleteContrat(contrat.getId());
        verify(contratRepository, times(1)).deleteById(contrat.getId());
    }


}
