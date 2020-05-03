package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Employee;
import by.bia.labAssist.repository.EmployeeRepository;
import by.bia.labAssist.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public void save(String name, String position) {
        employeeRepository.save(new Employee(name, position));
    }

    @Override
    public void edit(Employee employeeEdit, String name, String position) {
        employeeEdit.setName(name);
        employeeEdit.setPosition(position);

        employeeRepository.save(employeeEdit);
    }

    @Override
    public void delete(Integer employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
