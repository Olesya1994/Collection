package com.example.collection.controller;

import com.example.collection.Employee;
import com.example.collection.service.EmployeeService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class Controller {

    //private final static Logger logger= LoggerFactory.getLogger(Controller.class);

   private final EmployeeService service;

    public Controller(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam int salary) {
        return service.add(firstName, lastName,department,salary);
    }

    @GetMapping("/remove")
    public Employee delete(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam int salary) {
        return service.delete(firstName, lastName,department,salary);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName, @RequestParam String lastName, @RequestParam int department, @RequestParam int salary) {
        return service.find(firstName, lastName,department,salary);
    }

    @GetMapping()
    public Collection<Employee> print() {
        return service.print();
    }
}
