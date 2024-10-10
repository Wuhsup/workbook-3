package com.pluralsight.payroll;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PayrollCalculator {
    public static void main(String[] args) throws IOException {
        //String filePath = "./src/main/resources/dataFiles/employees.csv";

        FileReader fileReader = new FileReader("./src/main/resources/employees.csv");
        BufferedReader bufReader = new BufferedReader(fileReader);
        bufReader.readLine();
        String line;
        while ((line = bufReader.readLine()) != null) {
            String[] tokens = line.split("\\|");
            int id = Integer.parseInt(tokens[0]);
            String name = tokens[1];
            double hoursWorked = Double.parseDouble(tokens[2]);
            double payRate = Double.parseDouble(tokens[3]);

            Employee employee = new Employee(id, name, hoursWorked, payRate);
            System.out.printf("ID: %d, Name: %s, Gross Pay: %.2f%n",
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getGrossPay());
        }

        bufReader.close();
        fileReader.close();
    }
}

