package com.adverity.dw.service;

import com.adverity.dw.controller.CampaignDataController;
import com.adverity.dw.model.CampaignDataBo;
import com.adverity.dw.repository.CampaignDataRepository;
import com.adverity.dw.sql.SqlCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CampaignDataServiceImpl<T> implements CampaignDataService<T> {
    private Logger logger = LoggerFactory.getLogger(CampaignDataServiceImpl.class);

    private final CampaignDataRepository campaignDataRepository;

    @Autowired
    public CampaignDataServiceImpl(CampaignDataRepository campaignDataRepository) {
        this.campaignDataRepository = campaignDataRepository;
    }

    @Override
    public List<T> getCampaignData(CampaignDataBo campaignDataBo) {
       List<T> list =  campaignDataRepository.fetchCampaignData(SqlCreator.createSql(campaignDataBo));
        logger.debug("Response is:");
        list.forEach(cd -> logger.debug(cd.toString()));
        return campaignDataRepository.fetchCampaignData(SqlCreator.createSql(campaignDataBo));
    }
}

