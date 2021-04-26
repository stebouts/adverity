package com.adverity.dw.sql;

import com.adverity.dw.controller.CampaignDataController;
import com.adverity.dw.model.CampaignData;
import com.adverity.dw.model.CampaignDataBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqlCreator {
    private static Logger logger = LoggerFactory.getLogger(SqlCreator.class);

    public static String createSql(CampaignDataBo campaignDataBO) {
        String sql = buildSelect(campaignDataBO) + buildWhere(campaignDataBO) + buildGroupBys(campaignDataBO);
        logger.info("Executing SQL " + sql);
        return sql;
    }

    private static String buildSelect(CampaignDataBo campaignDataBO) {
        StringBuilder select = new StringBuilder();

        // in this case if there are no aggregations then we will add to the results all the dimensions and no sum ups
        if (campaignDataBO.getAggregations().isEmpty()) {

            select.append(SqlEnum.DATASOURCE.getSqlExpression()).append(",")
                    .append(SqlEnum.CAMPAIGN.getSqlExpression()).append(",")
                    .append(SqlEnum.DATE.getSqlExpression());

            if (campaignDataBO.getMetrics().contains(SqlEnum.CLICKS.getField())) {
                select.append(",").append(SqlEnum.CLICKS.getSqlExpression());
            }

            if (campaignDataBO.getMetrics().contains(SqlEnum.IMPRESSIONS.getField())) {
                select.append(",").append(SqlEnum.IMPRESSIONS.getSqlExpression());
            }

            if (campaignDataBO.getMetrics().contains(SqlEnum.CLICK_THROUGH_RATE.getField())) {
                select.append(",").append(SqlEnum.CLICK_THROUGH_RATE.getSqlExpression());
            }
        } else { // In this case we have aggregations in the query and we use sum ups for the results
            buildSqlPart(campaignDataBO, select);

            if (campaignDataBO.getMetrics().contains(SqlEnum.CLICKS_AGGREGATION.getField())) {
                if (select.toString().isEmpty()) {
                    select.append(SqlEnum.CLICKS_AGGREGATION.getSqlExpression());
                } else {
                    select.append(",").append(SqlEnum.CLICKS_AGGREGATION.getSqlExpression());
                }
            }

            if (campaignDataBO.getMetrics().contains(SqlEnum.IMPRESSIONS_AGGREGATION.getField())) {
                if (select.toString().isEmpty()) {
                    select.append(SqlEnum.IMPRESSIONS_AGGREGATION.getSqlExpression());
                } else {
                    select.append(",").append(SqlEnum.IMPRESSIONS_AGGREGATION.getSqlExpression());
                }
            }

            if (campaignDataBO.getMetrics().contains(SqlEnum.CLICK_THROUGH_RATE_AGGREGATION.getField())) {
                if (select.toString().isEmpty()) {
                    select.append(SqlEnum.CLICK_THROUGH_RATE_AGGREGATION.getSqlExpression());
                } else {
                    select.append(",").append(SqlEnum.CLICK_THROUGH_RATE_AGGREGATION.getSqlExpression());
                }
            }
        }
        return "SELECT " + select.append(" FROM ").append(CampaignData.class.getName()).append(" ").toString();
    }

    private static String buildWhere(CampaignDataBo campaignDataBO){
        StringBuilder where = new StringBuilder();

        if (!campaignDataBO.getFilterCampaign().isEmpty()) {
            where.append(SqlEnum.CAMPAIGN.getSqlExpression()).append(" IN ('").append(String.join("','", campaignDataBO.getFilterCampaign())).append("')");
        }

        if (!campaignDataBO.getFilterDatasource().isEmpty()) {
            if (!where.toString().isEmpty()) {
                where.append(" AND ");
            }
            where.append(SqlEnum.DATASOURCE.getSqlExpression()).append(" IN ('").append(String.join("','", campaignDataBO.getFilterDatasource())).append("')");
        }

        if (campaignDataBO.getFilterStartDate() != null) {
            if (!where.toString().isEmpty()) {
                where.append(" AND ");
            }
            where.append(SqlEnum.DATE.getSqlExpression()).append(" >= ").append("'").append(campaignDataBO.getFilterStartDate()).append("'");
        }

        if (campaignDataBO.getFilterEndDate() != null) {
            if (!where.toString().isEmpty()) {
                where.append(" AND ");
            }
            where.append(SqlEnum.DATE.getSqlExpression()).append(" <= ").append("'").append(campaignDataBO.getFilterEndDate()).append("'");
        }

        return !where.toString().isEmpty() ? "WHERE " + where.toString() : where.toString() ;
    }

    private static String buildGroupBys(CampaignDataBo campaignDataBO) {
        StringBuilder groupBy = new StringBuilder();
        if (!campaignDataBO.getAggregations().isEmpty()){
            return " GROUP BY " + buildSqlPart(campaignDataBO, groupBy);
        }
        return "";
    }

    private static String buildSqlPart(CampaignDataBo campaignDataBO, StringBuilder sqlPart) {
        if (campaignDataBO.getAggregations().contains(SqlEnum.DATASOURCE.getField())) {
            sqlPart.append(SqlEnum.DATASOURCE.getSqlExpression());
        }

        if (campaignDataBO.getAggregations().contains(SqlEnum.CAMPAIGN.getField())) {
            if (sqlPart.toString().isEmpty()){
                sqlPart.append(SqlEnum.CAMPAIGN.getSqlExpression());
            }else {
                sqlPart.append(",").append(SqlEnum.CAMPAIGN.getSqlExpression());
            }
        }

        if (campaignDataBO.getAggregations().contains(SqlEnum.DATE.getField())) {
            if (sqlPart.toString().isEmpty()){
                sqlPart.append(SqlEnum.DATE.getSqlExpression());
            }else {
                sqlPart.append(",").append(SqlEnum.DATE.getSqlExpression());
            }
        }
        return  sqlPart.toString();
    }
}
