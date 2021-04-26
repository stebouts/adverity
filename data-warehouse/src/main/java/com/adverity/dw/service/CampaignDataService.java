package com.adverity.dw.service;

import com.adverity.dw.model.CampaignDataBo;
import org.hibernate.mapping.Any;

import javax.persistence.EntityManager;
import java.util.List;

public interface CampaignDataService<T> {

    /**
     * This method is used to retrieve the required data from the repository by creating
     * the appropriate sql depending on  the data contained in the input
     * @param campaignDataBO The query data received as input
     * @return The results of the query
     */
    List<T> getCampaignData(CampaignDataBo campaignDataBO);
}
