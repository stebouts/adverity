package com.adverity.dw.repository;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class CampaignDataRepositoryCustomImpl<T> implements CampaignDataRepositoryCustom<T> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<T> fetchCampaignData(String sql) { return entityManager.createQuery(sql).getResultList();
    }
}
