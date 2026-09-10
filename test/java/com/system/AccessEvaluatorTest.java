package com.system;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccessEvaluatorTest {

    @Test
    public void testNormalScenario_Eligible() {
        Employee emp = new Employee("E101", "John", 28, "IT", true, true, SecurityLevel.CONFIDENTIAL);
        AccessEvaluator.EvaluationResult res = AccessEvaluator.evaluateAccess(emp, SecurityLevel.CONFIDENTIAL);
        assertEquals("Eligible", res.status);
        assertTrue(res.reasons.isEmpty());
    }

    @Test
    public void testBoundaryScenario_AgeExactly21() {
        Employee emp = new Employee("E102", "Jane", 21, "HR", true, true, SecurityLevel.LOW);
        AccessEvaluator.EvaluationResult res = AccessEvaluator.evaluateAccess(emp, SecurityLevel.LOW);
        assertEquals("Eligible", res.status);
    }

    @Test
    public void testConditionallyEligible_LowClearanceConfidentialResource() {
        Employee emp = new Employee("E103", "Smith", 35, "Finance", true, true, SecurityLevel.MEDIUM);
        AccessEvaluator.EvaluationResult res = AccessEvaluator.evaluateAccess(emp, SecurityLevel.CONFIDENTIAL);
        assertEquals("Conditionally Eligible", res.status);
        assertEquals(1, res.reasons.size());
    }

    @Test
    public void testMultipleFailureScenario_AccumulateAllReasons() {
        // Underage (19), Bad Dept (Sales), Inactive Status
        Employee emp = new Employee("E104", "Jake", 19, "Sales", false, true, SecurityLevel.LOW);
        AccessEvaluator.EvaluationResult res = AccessEvaluator.evaluateAccess(emp, SecurityLevel.LOW);
        
        assertEquals("Not Eligible", res.status);
        assertEquals(3, res.reasons.size()); // Should catch Age, Dept, and Status violations
    }

    @Test
    public void testInvalidInput_ConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Employee("", "Invalid", 25, "IT", true, true, SecurityLevel.LOW);
        });
    }
}
