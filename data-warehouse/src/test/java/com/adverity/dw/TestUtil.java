package com.adverity.dw;

import com.adverity.dw.model.CampaignData;
import com.adverity.dw.model.CampaignDataBo;

import java.util.Arrays;

import static java.time.LocalDate.now;

public class TestUtil {

    public static CampaignData createCampaignData(){
        CampaignData campaignData = new CampaignData();
        campaignData.setCampaignName("TestCampaign");
        campaignData.setDatasource("TestDs");
        campaignData.setImpressions(10);
        campaignData.setClicks(100);
        campaignData.setDate(now());

        return campaignData;
    }

    public static CampaignDataBo createCampaignDataBo(){
        CampaignDataBo campaignDataBo = new CampaignDataBo();
        campaignDataBo.setMetrics(Arrays.asList("clicks_total","impressions_total","ctlr_total"));
        campaignDataBo.setAggregations(Arrays.asList("data_source","date"));
        campaignDataBo.setFilterDatasource(Arrays.asList("TestDs"));
        campaignDataBo.setFilterCampaign(Arrays.asList("TestCampaign"));
        campaignDataBo.setFilterStartDate(now());
        campaignDataBo.setFilterEndDate(now());
        return campaignDataBo;
    }
}
