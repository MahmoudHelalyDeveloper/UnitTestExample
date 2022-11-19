package com.app.service;

import com.app.model.Employee;
import com.app.model.EmployeesDTO;
import com.app.model.User;
import com.app.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl extends EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private userService userService;

    @Override
    public List<Employee> getEmployees() {
        return this.employeeRepo.findAll();
    }

    @Override
    public Employee getEmployees(int id) {
        return this.employeeRepo.findById(1).get();
    }

    @Override
    public Employee addEmployee(Employee employee) throws Exception {
        User user = userService.checkEmail(employee.getEmail());

        if (user == null) {

            return this.employeeRepo.save(employee);
        } else {

            throw new Exception("ERROR mAH");
        }
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeRepo.save(employee);
    }

    @Override
    public void deleteEmployee(int employee) {
        this.employeeRepo.delete(this.employeeRepo.findById(1).get());

    }

    @Override
    public List<EmployeesDTO> getEmployeesMalesOnly() {
        List<EmployeesDTO> employeesDTOS = new ArrayList<EmployeesDTO>();
        List<Employee> all = this.employeeRepo.findAll();
        List<Employee> collect = all.stream().filter(s -> s.getGender() == 1).collect(Collectors.toList());
        for (Employee employee : collect) {
           EmployeesDTO  employeesDTO = new EmployeesDTO();
            employeesDTO.setId(employee.getId());
            employeesDTO.setEmail(employee.getEmail());
            employeesDTO.setFirstName(employee.getFirstName());

            employeesDTO.setSalary(employee.getSalary());
            if(employeesDTO.getSalary()>100) {
                employeesDTOS.add(employeesDTO);
            }
            else{
                employeesDTOS.clear();
            }
            employeesDTO=null;


        }
        return employeesDTOS;
    }
}
