package com.cnp.api.apirestcnp.service;


import com.cnp.api.apirestcnp.model.ReseauDistribution;

import java.util.List;
import java.util.Optional;

public interface ReseauDistributionService {

    public List<ReseauDistribution> getListReseauDistribution();

    public Optional<ReseauDistribution> getReseauDistributionId(Integer idReseauDistribution);

    public boolean deleteReseauDistribution(Integer idReseauDistribution);

    public ReseauDistribution modifyReseauDistribution(ReseauDistribution reseauDistribution);

    public ReseauDistribution createReseauDistribution(ReseauDistribution reseauDistribution);


}
