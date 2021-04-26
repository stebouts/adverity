package com.adverity.dw.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ValidMetricsValidatorTest {

    private  MetricsValidator metricsValidator;
    private ConstraintValidatorContext constraintValidatorContext = null;

    @BeforeAll
    public void setup(){
        metricsValidator = new MetricsValidator();
    }

    @Test
    public void allValuesContained_thenValid(){
        List<String> testList =  Arrays.asList("clicks_total","impressions_total","ctlr_total");
        assertTrue(metricsValidator.isValid(testList, constraintValidatorContext));
    }

    @Test
    public void noneValueIsContained_thenInvalid(){
        List<String> testList =  Arrays.asList("no","no","no");
        assertFalse(metricsValidator.isValid(testList, constraintValidatorContext));
    }

    @Test
    public void oneValueNotContained_thenInvalid(){
        List<String> testList =  Arrays.asList("clicks_total","impressions_total","no");
        assertFalse(metricsValidator.isValid(testList, constraintValidatorContext));
    }

    @Test
    public void oneValueContained_thenValid(){
        List<String> testList =  Arrays.asList("clicks_total");
        assertTrue(metricsValidator.isValid(testList, constraintValidatorContext));
    }
}
