package dao;

import entity.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDao {
    List<Employee> findAll() throws SQLException;
    Employee findById(int id);
    void save(Employee employee);
    void deleteById(int id);
}
