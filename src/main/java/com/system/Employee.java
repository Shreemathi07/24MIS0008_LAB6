package com.system;

public class Employee {
    private String id;
    private String name;
    private int age;
    private String department;
    private boolean isActiveStatus;
    private boolean isIdValid;
    private SecurityLevel securityClearance;

    public Employee(String id, String name, int age, String department, 
                    boolean isActiveStatus, boolean isIdValid, SecurityLevel securityClearance) {
        if(id == null || id.trim().isEmpty()) throw new IllegalArgumentException("ID cannot be empty");
        if(name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Name cannot be empty");
        
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.isActiveStatus = isActiveStatus;
        this.isIdValid = isIdValid;
        this.securityClearance = securityClearance;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getDepartment() { return department; }
    public boolean isActiveStatus() { return isActiveStatus; }
    public boolean isIdValid() { return isIdValid; }
    public SecurityLevel getSecurityClearance() { return securityClearance; }
}
