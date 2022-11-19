package com.app.service;

import com.app.model.Employee;
import com.app.model.EmployeesDTO;

import java.util.List;

public abstract class EmployeeService {

    public abstract List<Employee> getEmployees();

    public abstract  Employee getEmployees(int id);

    public abstract  Employee addEmployee(Employee employee) throws Exception;

    public abstract  Employee updateEmployee(Employee employee);

    public abstract  void deleteEmployee(int employee);

    public abstract List<EmployeesDTO> getEmployeesMalesOnly();


}
