package com.example.collection;

import com.example.collection.service.DepartmentService;
import com.example.collection.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@Service
public class DepartmentServiceTest {
    @Mock
    EmployeeService employeeService;
    @InjectMocks
    DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        Employee empl0 = new Employee("Иван", "Иванов", 1, 50000);
        Employee empl1 = new Employee("Иван", "Иванов", 2, 60000);
        Employee empl2 = new Employee("Пётр", "Петров", 3, 70000);
        Employee empl3 = new Employee("Василий", "Петров", 4, 80000);
        Employee empl4 = new Employee("Виктор", "Сидоров", 5, 90000);
        Employee empl5 = new Employee("Иван", "Иванов", 1, 90000);
        Employee empl6 = new Employee("Василий", "Иванов", 2, 110000);
        when(employeeService.getEmployees()).thenReturn(List.of(empl0, empl1, empl2, empl3, empl4, empl5, empl6));
    }

    @Test
    public void maxTest() {
        var actual = departmentService.max(1);
        var expected = new Employee("Иван", "Иванов", 1, 90000);
        assertEquals(actual, expected);
    }

    @Test
    public void minTest() {
        var actual = departmentService.min(1);
        var expected = new Employee("Иван", "Иванов", 1, 50000);
        assertEquals(actual, expected);
    }

    @Test
    public void sumTest() {
        int actual = departmentService.sum(2);
        int expected = 60000 + 110000;
        assertEquals(actual, expected);
    }

    @Test
    public void allOfDeptTest() {
        Collection<Employee> actual = departmentService.allOfDept(4);
        Collection<Employee> expected = List.of(new Employee("Василий", "Петров", 4, 80000));
        assertEquals(actual, expected);
        // Если отдела не существует, лист пустой.
        Collection<Employee> expectedEmpty = departmentService.allOfDept(44);
        assertEquals(List.of(), expectedEmpty);
    }

    @Test
    public void allTest() {
        Employee empl0 = new Employee("Иван", "Иванов", 1, 50000);
        Employee empl1 = new Employee("Иван", "Иванов", 2, 60000);
        Employee empl2 = new Employee("Пётр", "Петров", 3, 70000);
        Employee empl3 = new Employee("Василий", "Петров", 4, 80000);
        Employee empl4 = new Employee("Виктор", "Сидоров", 5, 90000);
        Employee empl5 = new Employee("Иван", "Иванов", 1, 90000);
        Employee empl6 = new Employee("Василий", "Иванов", 2, 110000);
        Map<Integer, List<Employee>> expected = List.of(empl0, empl1, empl2, empl3, empl4, empl5, empl6)
                .stream()
                .collect(groupingBy(Employee::getDepartment));
        Map<Integer, List<Employee>> actual = departmentService.all();
        assertEquals(actual, expected);
    }

}
