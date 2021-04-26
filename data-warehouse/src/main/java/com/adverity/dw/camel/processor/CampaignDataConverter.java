package com.adverity.dw.camel.processor;

import com.adverity.dw.model.CampaignData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/** POJO that transforms a raw campaign data  String to a bean. It is designed to use in a Camel route. */
public class CampaignDataConverter {

    /**
     * Transformation of a campaign data string to Java Bean the Camel way.
     *
     * @param campaignDataStr a campaign data string record
     * @return a CampaignData bean
     */
    public static CampaignData map(String campaignDataStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");

        final String[] splat = campaignDataStr.split(",");

        final String datasource = splat[0];
        final String campaign = splat[1];
        final String daily = splat[2];
        final String clicks = splat[3];
        final String impressions = splat[4];

        final CampaignData campaignData = new CampaignData();
        Optional.ofNullable(datasource).ifPresent(campaignData::setDatasource);
        Optional.ofNullable(campaign).ifPresent(campaignData::setCampaignName);
        try {
            Optional.ofNullable(daily).ifPresent(d -> campaignData.setDate(LocalDate.parse(d,formatter)));
            Optional.ofNullable(clicks).ifPresent(c -> campaignData.setClicks(new Long(c)));
            Optional.ofNullable(impressions).ifPresent(i ->campaignData.setImpressions(new Long(i)));
        }catch (DateTimeParseException e){
            System.out.println("Error handling record " + e.getMessage());
            return null;
        }


        return campaignData;
    }
}
