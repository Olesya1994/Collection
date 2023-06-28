package com.example.collection;

import com.example.collection.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;


public class EmployeeServiceTest {
    EmployeeService emplServForTest = new EmployeeService();
    Collection<Employee> ExpectedEmpls = new ArrayList<>();

    @BeforeEach
    void setUp() {

        Employee empl0 = new Employee("Иван", "Иванов", 1, 50000);
        Employee empl1 = new Employee("Иван", "Иванов", 1, 60000);
        Employee empl2 = new Employee("Пётр", "Петров", 3, 70000);
        Employee empl3 = new Employee("Василий", "Петров", 4, 80000);
        Employee empl4 = new Employee("Виктор", "Сидоров", 5, 90000);
        Employee empl5 = new Employee("Виктор", "Ульянов", 2, 90000);
        Employee empl6 = new Employee("Василий", "Иванов", 2, 110000);
        Employee[] emplsForTest = new Employee[]{empl0, empl1, empl2, empl3, empl4, empl5, empl6};

        for (int i = 0; i < emplsForTest.length; i++) {
            ExpectedEmpls.add(emplsForTest[i]);
            emplServForTest.add(emplsForTest[i].getFirstName(), emplsForTest[i].getLastName(), emplsForTest[i].getDepartment(), emplsForTest[i].getSalary());
        }
    }


    @Test
    public void addTest() {
        Employee addEmpl = new Employee("Алексей", "Иванов", 6, 80000);
        emplServForTest.add("Алексей", "Иванов", 6, 80000);
        Assertions.assertTrue(emplServForTest.getEmployees().containsAll(ExpectedEmpls));
        //ExpectedEmpls.add(addEmpl);
        //ExpectedEmpls.stream().sorted(Comparator.comparing(Employee::getSalary));
        //Assertions.assertEquals(ExpectedEmpls, emplServForTest.getEmployees());
    }

    @Test
    public void EmployeeAlreadyAddedExceptionTest() {
        Assertions.assertThrows(EmployeeAlreadyAddedException.class, () -> emplServForTest.add("Василий", "Петров", 4, 80000));
    }

    @Test
    public void deleteTest() {
        Employee deleteEmpl = new Employee("Василий", "Петров", 4, 80000);
        emplServForTest.delete("Василий", "Петров", 4, 80000);
        ExpectedEmpls.remove(deleteEmpl);
        Assertions.assertTrue(emplServForTest.getEmployees().containsAll(ExpectedEmpls));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Василий", "Петров", 4, 80000);
        Employee actual = emplServForTest.find("Василий", "Петров", 4, 80000);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void EmployeeNotFoundExceptionTest() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> emplServForTest.find("Алексей", "Иванов", 6, 80000));
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> emplServForTest.delete("Алексей", "Иванов", 6, 80000));
    }


}

