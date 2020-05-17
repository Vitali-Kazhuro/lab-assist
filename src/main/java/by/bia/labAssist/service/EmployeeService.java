package by.bia.labAssist.service;

import by.bia.labAssist.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(Integer id);

    void create(String name, String position);

    void edit(Employee employeeEdit, String name, String position);

    void delete(Integer employeeId);
}
