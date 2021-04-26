package com.adverity.dw.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class CampaignDataPK  implements Serializable {

    private String datasource;
    private String campaignName;
    private LocalDate date;

    public CampaignDataPK(String datasource, String campaignName, LocalDate date) {
        this.datasource = datasource;
        this.campaignName = campaignName;
        this.date = date;
    }

    public CampaignDataPK() {
    }

    @Override
    public boolean equals(Object o) {
        if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        CampaignDataPK pk = (CampaignDataPK) o;
        return Objects.equals(datasource, pk.datasource) &&
                Objects.equals(campaignName, pk.campaignName) &&
                Objects.equals(date, pk.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash( datasource, campaignName, date );
    }
}
