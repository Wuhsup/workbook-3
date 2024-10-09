package com.pluralsight.payroll;

public class Employee {

    private int employeeId;
    private String name;
    private double hoursWorked;
    private double payRate;

    public Employee(int employeeId, String name, double hoursWorked, double payRate) {
        this.employeeId = employeeId;
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.payRate = payRate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }


    public double getPayRate() {
        return payRate;
    }


    public double getGrossPay() {
        return hoursWorked * payRate;
    }


    public static void main(String[] args) {
        Employee emp = new Employee(1, "Null", 40, 40.00);
        System.out.println("Employee ID: " + emp.getEmployeeId());
        System.out.println("Name: " + emp.getName());
        System.out.println("Gross Pay: $" + emp.getGrossPay());
    }
}

