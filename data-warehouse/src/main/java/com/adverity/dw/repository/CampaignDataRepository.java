package com.adverity.dw.repository;

import com.adverity.dw.model.CampaignData;
import com.adverity.dw.model.CampaignDataPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CampaignDataRepository<T> extends JpaRepository<CampaignData, CampaignDataPK>, CampaignDataRepositoryCustom<T> {
}
