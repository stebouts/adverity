package com.adverity.dw.model;

import com.adverity.dw.validation.ValidMetrics;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampaignDataBo {

    @ValidMetrics
    private List<String> metrics;
    private List<String> aggregations;
    private List<String> filterDatasource;
    private List<String> filterCampaign;
    private LocalDate filterStartDate;
    private LocalDate filterEndDate;

    public CampaignDataBo(List<String> metrics, List<String> aggregations, List<String> filterDatasource, List<String> filterCampaign, LocalDate filterStartDate, LocalDate filterEndDate) {
        this.metrics = metrics;
        this.aggregations = aggregations == null ? new ArrayList<>() : aggregations;
        this.filterDatasource = filterDatasource == null? new ArrayList<>() : filterDatasource;
        this.filterCampaign = filterCampaign == null? new ArrayList<>() : filterCampaign;
        this.filterStartDate = filterStartDate;
        this.filterEndDate = filterEndDate;
    }

    public CampaignDataBo() {
    }

    public List<String> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<String> metrics) {
        this.metrics = metrics;
    }

    public List<String> getAggregations() {
        return aggregations;
    }

    public void setAggregations(List<String> aggregations) {
        this.aggregations = aggregations;
    }

    public List<String> getFilterDatasource() {
        return filterDatasource;
    }

    public void setFilterDatasource(List<String> filterDatasource) {
        this.filterDatasource = filterDatasource;
    }

    public List<String> getFilterCampaign() {
        return filterCampaign;
    }

    public void setFilterCampaign(List<String> filterCampaign) {
        this.filterCampaign = filterCampaign;
    }

    public LocalDate getFilterStartDate() {
        return filterStartDate;
    }

    public void setFilterStartDate(LocalDate filterStartDate) {
        this.filterStartDate = filterStartDate;
    }

    public LocalDate getFilterEndDate() {
        return filterEndDate;
    }

    public void setFilterEndDate(LocalDate filterEndDate) {
        this.filterEndDate = filterEndDate;
    }
}
