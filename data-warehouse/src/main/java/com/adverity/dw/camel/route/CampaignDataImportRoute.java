package com.adverity.dw.camel.route;

import com.adverity.dw.camel.processor.CampaignDataConverter;
import com.adverity.dw.camel.processor.CampaignDataProcessor;
import com.adverity.dw.repository.CampaignDataRepository;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CampaignDataImportRoute extends RouteBuilder {
    private static final String LOGGER = "com.adverity.dw.camel.route";

    @Value("${file.consumer.directory:/home/steb/Documents/Interviews/Adverity/data}")
    private String directory;

    @Value("${file.poll.delay:60000}")
    private int pollDelay;

    final CampaignDataRepository campaignDataRepository;

    @Autowired
    public CampaignDataImportRoute(CampaignDataRepository campaignDataRepository) {
        this.campaignDataRepository = campaignDataRepository;
    }

    @Override
    public void configure() throws Exception {
        from("file:"+directory+"?readLock=none&autoCreate=true&move=.done&delay="+pollDelay)
                .log("body: ${body}")
                .split(body().tokenize("\n"))
                .bean(new CampaignDataConverter())
                .process(new CampaignDataProcessor(campaignDataRepository))
                .end();
    }
}
