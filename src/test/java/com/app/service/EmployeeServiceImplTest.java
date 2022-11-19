package com.app.service;

import com.app.model.Employee;
import com.app.model.EmployeesDTO;
import com.app.model.User;
import com.app.repo.EmployeeRepo;
import com.app.repo.UserRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;
    @Mock
    private EmployeeRepo employeeRepo;
    @Mock
    private UserRepo userRepo;
    @Mock
    private userServiceImpl userService;

    @Test
    void getEmployees() {
        Employee employee = new Employee();
        Employee employee1 = new Employee();
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee);
        employees.add(employee1);
        given(this.employeeRepo.findAll()).willReturn(employees);
        List<Employee> employeesService = this.employeeService.getEmployees();
        Assertions.assertThat(employeesService).isNotNull();
    }

    @Test
    void testGetEmployees() {
        Employee employee = new Employee();
        given(this.employeeRepo.findById(1)).willReturn(Optional.of(employee));
        Employee employees = this.employeeService.getEmployees(1);
        Assertions.assertThat(employees).isNotNull();

    }

    @Test
   public void addEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmail("");
        employee.setGender(1);
        employee.setSalary(10.20);
        employee.setFirstName("");
        employee.setLastName("");
        User user=null;
        given(this.userRepo.findByEmail("")).willReturn(new User());
//        User user = this.userService.checkEmail("");
        given(this.employeeRepo.save(employee)).willReturn(employee);
//        verify(this.employeeRepo.save(employee));
        Employee employee1 = this.employeeService.addEmployee(employee);
        Assertions.assertThat(employee1).isNotNull();
    }

    @Test
    void addEmployeeAlreadyExist() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setEmail("");
        employee.setGender(1);
        employee.setSalary(10.20);
        employee.setFirstName("");
        employee.setLastName("");
        User user = new User();
//given(this.userRepo.findByEmail("")).willReturn(user);
//        User user1 = this.userService.checkEmail("");
////        assertThrows(Exception.class,
////                () -> this.employeeService.addEmployee(employee),
////                () -> "ERROR mAH");
        when(userService.checkEmail("")).thenReturn(user);

        Exception exception = assertThrows(Exception.class, () -> this.employeeService.addEmployee(employee));
        assertEquals("ERROR mAH", exception.getMessage());

    }

    @Test
    void updateEmployee() {
        Employee employee = new Employee();
        given(this.employeeRepo.save(employee)).willReturn(employee);
        Employee updateEmployee = this.employeeService.updateEmployee(employee);
        Assertions.assertThat(updateEmployee).isNotNull();
    }

    @Test
    void deleteEmployee() {
        Employee employee = new Employee();
        given(this.employeeRepo.findById(1)).willReturn(Optional.of(employee));
        willDoNothing().given(this.employeeRepo).delete(employee);
        this.employeeService.deleteEmployee(1);
    }

    @Test
    void getEmployeesMalesOnly_testLoop() {
        Employee employee = new Employee();
        employee.setLastName("");
        employee.setGender(1);
        employee.setSalary(202);

        Employee employee1 = new Employee();
        employee1.setLastName("");
        employee1.setGender(1);
        employee1.setSalary(201);
//        List<EmployeesDTO> employeesDTOS = new ArrayList<EmployeesDTO>();
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(employee1);
        employees.add(employee);
        given(this.employeeRepo.findAll()).willReturn(employees);
        List<EmployeesDTO> employeesMalesOnly = this.employeeService.getEmployeesMalesOnly();

        Assertions.assertThat(employeesMalesOnly).isNotEmpty();

    }
}