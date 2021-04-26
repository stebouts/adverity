package com.adverity.dw.camel.processor;

import com.adverity.dw.model.CampaignData;
import com.adverity.dw.repository.CampaignDataRepository;
import com.adverity.dw.service.CampaignDataServiceImpl;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CampaignDataProcessor implements Processor {
    private Logger logger = LoggerFactory.getLogger(CampaignDataServiceImpl.class);
    private CampaignDataRepository campaignDataRepository;

    public CampaignDataProcessor(CampaignDataRepository campaignDataRepository) {
        this.campaignDataRepository = campaignDataRepository;
    }

    @Override
    public void process(Exchange exchange) {
        CampaignData campaignData = exchange.getIn().getBody(CampaignData.class);
        logger.debug("Saving campaign data " + campaignData.toString());
        Optional.ofNullable(campaignData).ifPresent(cd ->  campaignDataRepository.save(campaignData));
    }
}
