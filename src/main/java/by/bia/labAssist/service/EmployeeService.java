package by.bia.labAssist.service;

import by.bia.labAssist.model.Employee;

import java.util.List;

/**
 * Provides service logic for{@link Employee} entity
 */
public interface EmployeeService {
    /**
     * Returns List with all Employee instances
     * @return {@link List<Employee>} object
     */
    List<Employee> findAll();

    /**
     * Returns Employee instance by id
     * @param id Employee id
     * @return {@link Employee} object
     */
    Employee findById(Integer id);

    /**
     * Creates new Employee instance and persists it into database
     * @param name Employee name
     * @param position Employee position
     */
    void create(String name, String position);

    /**
     * Edits passed Employee instance and persists it into database
     * @param employeeEdit edited instance of Employee
     * @param name Employee name
     * @param position Employee position
     */
    void edit(Employee employeeEdit, String name, String position);

    /**
     * Deletes instance of Employee from database
     * @param id Employee id
     */
    void delete(Integer id);
}