package com.adverity.dw.controller;

import com.adverity.dw.DataWareHouseApplication;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = DataWareHouseApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@PropertySource("classpath:application-test.properties")
public class CamaignDataControllerIT {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void  whenRequestParameterIsInvalid_thenReturnsStatus400() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:"+randomServerPort+"/campaignData/get?metrics=invalidMetric";
        URI uri = new URI(baseUrl);
        HttpEntity<List<Any>> requestEntity = new HttpEntity<>(null, null);
        try
        {
            restTemplate.exchange(uri, HttpMethod.GET, requestEntity, List.class);
            Assertions.fail();
        }
        catch(HttpClientErrorException ex)
        {
            //Verify bad request
            assertEquals(400, ex.getRawStatusCode());
        }
    }


    @Test
    public void whenPathVariableHasNoMetrics_thenReturnsStatus200() throws URISyntaxException
    {
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/campaignData/get";
        URI uri = new URI(baseUrl);
        HttpEntity<List<Any>> requestEntity = new HttpEntity<>(null, null);
        ResponseEntity<List> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, List.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
    }


//    @Test
//    public void whenPathVariableHasNoMetrics_thenReturnsStatus200() throws Exception {
//        String uri = "/campaignData/get";
//        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.ALL)).andExpect(status().isOk());
//    }

}
