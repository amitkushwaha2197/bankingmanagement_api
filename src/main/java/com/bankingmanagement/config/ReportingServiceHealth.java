package com.bankingmanagement.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class ReportingServiceHealth implements HealthIndicator{
    @Override
    public Health health(){
        boolean isHealthy = checkReportingServiceHealth();
        if (isHealthy){
            return Health.up().withDetail("Reporting Service", "Available").build();
        } else{
            return Health.down().withDetail("Reporting Service", "Unavailable").build();
        }
    }

    private boolean checkReportingServiceHealth() {
        return false;
    }
}
