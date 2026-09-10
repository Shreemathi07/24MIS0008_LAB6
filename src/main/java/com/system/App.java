package com.system;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<Employee> fleet = new ArrayList<>();
        
        try {
            fleet.add(new Employee("EMP001", "Alice", 25, "IT", true, true, SecurityLevel.CONFIDENTIAL));
            fleet.add(new Employee("EMP002", "Bob", 19, "Marketing", false, true, SecurityLevel.LOW));
            fleet.add(new Employee("EMP003", "Charlie", 30, "HR", true, true, SecurityLevel.MEDIUM));
        } catch (IllegalArgumentException e) {
            System.err.println("Input Initialization Error: " + e.getMessage());
        }

        System.out.println("=== Employee Access Report ===");
        for (Employee emp : fleet) {
            AccessEvaluator.EvaluationResult res = AccessEvaluator.evaluateAccess(emp, SecurityLevel.CONFIDENTIAL);
            System.out.println("\nEmployee: " + emp.getName() + " [" + emp.getId() + "]");
            System.out.println("Status: " + res.status);
            if (!res.reasons.isEmpty()) {
                System.out.println("Remarks / Violations:");
                res.reasons.forEach(reason -> System.out.println(" - " + reason));
            }
        }
    }
}
