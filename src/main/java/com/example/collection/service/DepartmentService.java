package com.example.collection.service;

import com.example.collection.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Employee max(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==(department))
                .max(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }

    public Employee min(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==(department))
                .min(Comparator.comparing(Employee::getSalary))
                .orElse(null);
    }
    public int sum(int department) {
        Collection<Employee> EmplOfDept = allOfDept(department);
        int sum=0;
        for (Employee empl: EmplOfDept ){
            sum += empl.getSalary();
        }
        return sum;
    }

    public Collection<Employee> allOfDept(int department) {
        return employeeService.getEmployees()
                .stream()
                .filter(employee -> employee.getDepartment()==(department))
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> all() {
        return employeeService.getEmployees()
                .stream()
                .collect(groupingBy(Employee::getDepartment));
    }
}
