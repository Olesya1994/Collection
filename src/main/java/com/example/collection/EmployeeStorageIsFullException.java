package com.example.collection;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus()
public class EmployeeStorageIsFullException extends RuntimeException {
}
