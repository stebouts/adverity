package com.adverity.dw.service;

import com.adverity.dw.TestUtil;
import com.adverity.dw.model.CampaignDataBo;
import com.adverity.dw.repository.CampaignDataJpaConfig;
import com.adverity.dw.repository.CampaignDataRepository;
import com.adverity.dw.sql.SqlEnum;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(
        classes = { CampaignDataJpaConfig.class},
        loader = AnnotationConfigContextLoader.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CampaignDataServiceTest {

    @Autowired
    private CampaignDataRepository campaignDataRepository;

    private CampaignDataService campaignDataService;

    @BeforeAll
    public void setup(){
        campaignDataService = new CampaignDataServiceImpl(campaignDataRepository);
        campaignDataRepository.save(TestUtil.createCampaignData());
    }

    @Test
    public void whenNoAggregationsAndSpecificMetricsRequested_ThenDefaultAggregationsAndSpecificMetricsRetrieved(){
        CampaignDataBo campaignDataBo = TestUtil.createCampaignDataBo();
        campaignDataBo.setAggregations(new ArrayList<>());
        campaignDataBo.setFilterEndDate(null);
        campaignDataBo.setFilterStartDate(null);

        List<Object[]> response = campaignDataService.getCampaignData(campaignDataBo);
        // only one row is received since noe filters applied and only one row is inserted
        assertTrue("Error in row count", 1 == response.size());
        // we must have 6 object returned  since the default dimensions should be returned along with the 3 default metrics
        assertTrue("Error in data", Arrays.asList((response.get(0))).size() == 6);
    }

    @Test
    public void whenNoAggregationsNoMetricsRequested_ThenDefaultAggregationsAndMetricsRetrieved(){
        CampaignDataBo campaignDataBo = TestUtil.createCampaignDataBo();
        campaignDataBo.setMetrics(new ArrayList<>());
        campaignDataBo.setAggregations(new ArrayList<>());
        campaignDataBo.setFilterEndDate(null);
        campaignDataBo.setFilterStartDate(null);

        List<Object[]> response = campaignDataService.getCampaignData(campaignDataBo);
        // only one row is received since noe filters applied and only one row is inserted
        assertTrue("Error in row count", 1 == response.size());
        // we must have 3 objects returned  since the default dimensions should be returned
        assertTrue("Error in data", Arrays.asList((response.get(0))).size() == 3);
    }

    @Test
    public void whenNoAggregationsAndClickTotalMetricsRequested_ThenDefaultAggregationsAndClickTotalMetricsRetrieved(){
        CampaignDataBo campaignDataBo = TestUtil.createCampaignDataBo();
        campaignDataBo.setMetrics(Arrays.asList(SqlEnum.CLICKS.getField()));
        campaignDataBo.setAggregations(new ArrayList<>());
        campaignDataBo.setFilterEndDate(null);
        campaignDataBo.setFilterStartDate(null);

        List<Object[]> response = campaignDataService.getCampaignData(campaignDataBo);
        // only one row is received since noe filters applied and only one row is inserted
        assertTrue("Error in row count", 1 == response.size());
        // we must have 4 objects returned  since the default dimensions should be returned and the clicks_total metric
        assertTrue("Error in data", (Double)response.get(0)[3] == 100);
    }
}
