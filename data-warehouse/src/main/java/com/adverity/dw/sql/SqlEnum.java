package com.adverity.dw.sql;

public enum SqlEnum {
    DATASOURCE("datasource", "datasource"),
    CAMPAIGN("campaignName", "campaignName"),
    DATE("date", "date"),
    CLICKS("clicks_total","clicks AS clicks"),
    IMPRESSIONS("impressions_total","impressions AS impressions"),
    CLICK_THROUGH_RATE("ctlr_total","clicks/impressions AS ctlr"),
    CLICKS_AGGREGATION("clicks_total","SUM(clicks) AS clicks_aggregation"),
    IMPRESSIONS_AGGREGATION("impressions_total","SUM(impressions) AS impressions_aggregation"),
    CLICK_THROUGH_RATE_AGGREGATION("ctlr_total","SUM(clicks)/SUM(impressions) AS ctlr_aggregation");

    private final String field;
    private final String sqlExpression;

    SqlEnum(String field, String sqlExpression) {
        this.field = field;
        this.sqlExpression = sqlExpression;
    }

    public String getField() {
        return field;
    }

    public String getSqlExpression() {
        return sqlExpression;
    }
}
