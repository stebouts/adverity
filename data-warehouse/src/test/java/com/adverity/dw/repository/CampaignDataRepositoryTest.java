package com.adverity.dw.repository;

import com.adverity.dw.TestUtil;
import com.adverity.dw.model.CampaignData;
import com.adverity.dw.model.CampaignDataPK;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(
        classes = { CampaignDataJpaConfig.class},
        loader = AnnotationConfigContextLoader.class)
public class CampaignDataRepositoryTest {

    @Autowired
    private  CampaignDataRepository campaignDataRepository;

    @Test
    public void givenCampaignData_whenSave_thenInsert() {
        campaignDataRepository.save(TestUtil.createCampaignData());

        long count = campaignDataRepository.count();
        assertEquals(1, count);
    }

    @Test
    public void givenSameCampaignData_whenSave_thenUpdate() {
        CampaignData campaignData = TestUtil.createCampaignData();
        campaignDataRepository.save(campaignData);

        campaignData.setClicks(200);
        campaignDataRepository.save(campaignData);
        long count = campaignDataRepository.count();
        assertEquals(1, count);
        CampaignDataPK  campaignDataPK = new CampaignDataPK(campaignData.getDatasource(), campaignData.getCampaignName(), campaignData.getDate());
        Optional<CampaignData> optionalCampaignData = campaignDataRepository.findById(campaignDataPK);
        assertEquals(200, optionalCampaignData.get().getClicks());
    }
}
