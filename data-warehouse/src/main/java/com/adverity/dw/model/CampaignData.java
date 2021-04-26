package com.adverity.dw.model;

import java.io.Serializable;
import java.time.LocalDate;

import com.sun.istack.NotNull;
//import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
//import org.apache.camel.dataformat.bindy.annotation.DataField;

import javax.persistence.*;

//@CsvRecord(separator = ",", skipFirstLine = true)
@Entity
@IdClass(CampaignDataPK.class)
@Table(name = "campaign_data")
public class CampaignData implements Serializable {
//    @DataField(pos=1)
    @Id
    @NotNull
    @Column(name = "data_source")
    private String datasource;

 //   @DataField(pos=2)
    @Id
    @NotNull
    @Column(name = "campaign_name")
    private String campaignName;

 //   @DataField(pos=3)
    @Id
    @NotNull
    @Column(name = "date")
    private LocalDate date;

 //   @DataField(pos=4)
    @NotNull
    @Column(name = "clicks_number")
    private double clicks;

//    @DataField(pos=5)
    @NotNull
    @Column(name = "impressions_number")
    private double impressions;

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setClicks(double clicks) {
        this.clicks = clicks;
    }

    public void setImpressions(double impressions) {
        this.impressions = impressions;
    }

    public String getDatasource() {
        return datasource;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getClicks() {
        return clicks;
    }

    public double getImpressions() {
        return impressions;
    }

    @Override
    public String toString() {
        return "CampaignData{" +
                "datasource='" + datasource + '\'' +
                ", campaignName='" + campaignName + '\'' +
                ", date='" + date.toString() + '\'' +
                ", clicks='" + clicks + '\'' +
                ", impressions='" + impressions + '\'' +
                '}';
    }
}
