package com.example.collection.controller;

import com.example.collection.Employee;
import com.example.collection.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
        private final DepartmentService service;
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }
    @GetMapping("/{id}/salary/max")
    public Employee max (@PathVariable int id){
        return service.max(id);
    }
    @GetMapping("/{id}/salary/min")
    public Employee min (@PathVariable int id){
        return service.min(id);
    }
    @GetMapping("/{id}/salary/sum")
    public int sum (@PathVariable int id){
        return service.sum(id);
    }
    @GetMapping("{id}/all")
    public Collection<Employee> allOfDept (@PathVariable int id){
        return service.allOfDept(id);
    }
    @GetMapping("/employees")
    public Map<Integer, List<Employee>>  all (){
        return service.all();
    }
}
