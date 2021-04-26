package com.adverity.dw.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

@Component
public class MetricsValidator implements ConstraintValidator<ValidMetrics, List<String>> {

    private List<String> metrics = Arrays.asList("clicks_total","impressions_total","ctlr_total");

    @Override
    public boolean isValid(List<String> metricsList, ConstraintValidatorContext context) {
        return metrics.containsAll(metricsList);
    }
}