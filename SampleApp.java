package com.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SampleApp {
	public static void main(String[] args){
		List<Employee> employees = new ArrayList<>();
		
		employees.add(new Employee("Ashish", "A", "IT", "Pune", "Software Engineer", new Double(10000)));
		employees.add(new Employee("Amit", "R", "HR", "Pune", "Recruiter", new Double(12000)));
		employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Senior Recruiter", new Double(14000)));
		employees.add(new Employee("Jaya", "S", "IT", "Pune", "Tech Lead", new Double(15000)));
		employees.add(new Employee("Smita", "M", "IT", "Bangalore", "Recruiter", new Double(16000)));
		employees.add(new Employee("Umesh", "A", "IT", "Bangalore", "Software Engineer", new Double(12000)));
		employees.add(new Employee("Pooja", "R", "HR", "Bangalore", "Software Engineer", new Double(12000)));
		employees.add(new Employee("Ramesh", "D", "HR", "Pune", "Recruiter", new Double(16000)));
		employees.add(new Employee("Bobby", "S", "IT", "Bangalore", "Tech Lead", new Double(20000)));
		employees.add(new Employee("Vipul", "M", "IT", "Bangalore", "Software Engineer", new Double(14000)));
		
		FindAverageSalaryApp app = new FindAverageSalaryApp();
		app.findAverageSalary(employees);
	}

}

class Employee{
	private String firstName;
	private String lastName;
	private String department;
	private String officeLocation;
	private String designation;
	private Double salary;

	public Employee(String firstName, String lastName, String department, String officeLocation, String designation, Double salary){
		this.firstName= firstName;
		this.lastName = lastName;
		this.department = department;
		this.officeLocation = officeLocation;
		this.designation = designation;
		this.salary = salary;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getOfficeLocation() {
		return officeLocation;
	}

	public void setOfficeLocation(String officeLocation) {
		this.officeLocation = officeLocation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
}


class FindAverageSalaryApp{

	public void findAverageSalary(List<Employee> employees){
		
		Map<String,Map<String, Double>> stringEmployeeMap= employees.stream()
	               .collect(Collectors.groupingBy(Employee::getOfficeLocation,
	            		   Collectors.groupingBy(Employee::getDesignation,
	            				   Collectors.averagingDouble(Employee::getSalary))));
	                       stringEmployeeMap.forEach((k, v) -> {
					
	    for (Map.Entry<String, Double> entry :v.entrySet()) {
						System.out.println(entry.getKey()+":"+entry.getValue());
					}
				});
	}
}

