package com.system;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccessEvaluator {
    private static final List<String> AUTHORIZED_DEPTS = Arrays.asList("IT", "HR", "FINANCE", "ADMINISTRATION");

    public static class EvaluationResult {
        public String status;
        public List<String> reasons = new ArrayList<>();
    }

    public static EvaluationResult evaluateAccess(Employee emp, SecurityLevel requiredResourceLevel) {
        EvaluationResult result = new EvaluationResult();
        
        // Critical Criteria Checks
        if (emp.getAge() < 21) {
            result.reasons.add("Access Denied: Employee age is below 21.");
        }
        if (emp.getDepartment() == null || !AUTHORIZED_DEPTS.contains(emp.getDepartment().toUpperCase())) {
            result.reasons.add("Access Denied: Department is unauthorized.");
        }
        if (!emp.isActiveStatus()) {
            result.reasons.add("Access Denied: Employment status is inactive.");
        }
        if (!emp.isIdValid()) {
            result.reasons.add("Access Denied: Employee ID is invalid.");
        }

        // If any core metrics failed, classify as Not Eligible right away
        if (!result.reasons.isEmpty()) {
            result.status = "Not Eligible";
            return result;
        }

        // Security Clearance Evaluation (For Confidential Resources)
        if (requiredResourceLevel.getRank() >= SecurityLevel.CONFIDENTIAL.getRank()) {
            if (emp.getSecurityClearance().getRank() >= requiredResourceLevel.getRank()) {
                result.status = "Eligible";
            } else {
                result.status = "Conditionally Eligible";
                result.reasons.add("Conditional: Higher clearance level required for confidential access.");
            }
        } else {
            result.status = "Eligible";
        }

        return result;
    }
}
