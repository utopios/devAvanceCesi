package com.cnp.api.apirestcnp.service.imp;


import com.cnp.api.apirestcnp.model.Contrat;
import com.cnp.api.apirestcnp.repository.ContratRepository;
import com.cnp.api.apirestcnp.service.ContratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContratServiceImpl implements ContratService {

    @Autowired
    ContratRepository contratRepository;


    @Override
    public List<Contrat> getListContrat() {
        return (List<Contrat>) contratRepository.findAll();
    }

    @Override
    public Optional<Contrat> getContratById(Integer idContrat) {
        return contratRepository.findById(idContrat);
    }

    @Override
    public void deleteContrat(Integer idContrat) {
        contratRepository.deleteById(idContrat);
    }

    @Override
    public Contrat modifyContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    @Override
    public Contrat createContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }
}
