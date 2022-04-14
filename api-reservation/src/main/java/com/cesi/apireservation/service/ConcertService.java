package com.cesi.apireservation.service;

import com.cesi.apireservation.dto.ConcertDTO;
import com.cesi.apireservation.model.Concert;
import com.cesi.apireservation.repository.ConcertRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ConcertService {
    @Autowired
    private ConcertRepository concertRepository;

    private ModelMapper modelMapper;

    public ConcertService() {
        modelMapper = new ModelMapper();
    }

    public ConcertDTO save(ConcertDTO concertDTO) throws Exception {
        //Vérification des champs title, date, nombrePlax ...
        try {
            Concert concert = concertRepository.save(modelMapper.map(concertDTO, Concert.class));
            return modelMapper.map(concert, ConcertDTO.class);
        }catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public List<ConcertDTO> getConcerts() {
        List<ConcertDTO> concerts = new ArrayList<>();
        concertRepository.findAll().forEach(c -> {
            concerts.add(modelMapper.map(c, ConcertDTO.class));
        });
        return concerts;
    }
}
