package com.adverity.dw.controller;

import com.adverity.dw.model.CampaignDataBo;
import com.adverity.dw.service.CampaignDataService;
import com.adverity.dw.validation.ValidMetrics;
import org.hibernate.mapping.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@RestController
@Validated
@RequestMapping("/campaignData")
public class CampaignDataController<T> {
    private Logger logger = LoggerFactory.getLogger(CampaignDataController.class);

    final CampaignDataService campaignDataService;

    @Autowired
    public CampaignDataController(CampaignDataService campaignDataService) {
        this.campaignDataService = campaignDataService;
    }

    @GetMapping(value = "get", produces = "application/json")
    public ResponseEntity<List<Any>> getCampaignData(
            @RequestParam(defaultValue = "clicks_total,impressions_total,ctlr_total") @NotNull @ValidMetrics List<String> metrics,
            @RequestParam(required = false)  List<String> aggregations,
            @RequestParam(required = false)  List<String> datasources,
            @RequestParam(required = false)  List<String> campaigns,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
            HttpServletRequest request){

        logger.info("Request received " + request.getQueryString());
        List<Any>   results = campaignDataService.getCampaignData(new CampaignDataBo(metrics, aggregations, datasources,
              campaigns,fromDate, toDate));
        ResponseEntity<List<Any>> responseEntity = new ResponseEntity<>(results, HttpStatus.OK);
        return responseEntity;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("not valid due to validation error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>("Not valid argument type: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
