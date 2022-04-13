package com.cnp.api.apirestcnp.service.imp;


import com.cnp.api.apirestcnp.model.ReseauDistribution;
import com.cnp.api.apirestcnp.repository.ReseauDistributionRepository;
import com.cnp.api.apirestcnp.service.ReseauDistributionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class ReseauDistributionServiceImpl implements ReseauDistributionService {

    @Autowired
    ReseauDistributionRepository reseauDistributionRepository;

    @Override
    public List<ReseauDistribution> getListReseauDistribution() {
        return (List<ReseauDistribution>) reseauDistributionRepository.findAll();
    }

    @Override
    public Optional<ReseauDistribution> getReseauDistributionId(Integer idReseauDistribution) {
        return reseauDistributionRepository.findById(idReseauDistribution);
    }

    @Override
    public boolean deleteReseauDistribution(Integer idReseauDistribution) {
        boolean reseauDistributionDelete = true;
        ReseauDistribution reseauDistribution = reseauDistributionRepository.findById(idReseauDistribution).get();
        reseauDistributionRepository.delete(reseauDistribution);
        reseauDistributionDelete = (!reseauDistributionRepository.findById(idReseauDistribution).isPresent()) ? true : false;
        return reseauDistributionDelete;
    }

    @Override
    public ReseauDistribution modifyReseauDistribution(ReseauDistribution reseauDistribution) {
        return reseauDistributionRepository.save(reseauDistribution);
    }

    @Override
    public ReseauDistribution createReseauDistribution(ReseauDistribution reseauDistribution) {
        return reseauDistributionRepository.save(reseauDistribution);
    }
}
