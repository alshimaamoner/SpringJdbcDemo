package dao;

import entity.Employee;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//@Repository
public class JdbcEmployeeDaoImpl implements EmployeeDao {
    private DataSource dataSource;
    //use Constructor Injection
    //new Version of spring no need to inject if one constructor available
   // @Autowired
    public JdbcEmployeeDaoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }



    @Override
//    @Transactional
    public List<Employee> findAll()  {
        String sql="SELECT * from employee ";
        List<Employee> employees=new ArrayList<>();
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Employee employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));
                employees.add(employee);
            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return employees;

    }
    @Override
  //  @Transactional
    public Employee findById(int id) {
        String sql="select * from employee where id = ? ";
        Employee employee=null;
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                employee=new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setEmail(resultSet.getString("email"));

            }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return employee;

    }

    @Override
    public void save(Employee employee) {
        String sql="INSERT INTO employee values (?,?,?,?)";
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,employee.getId());
            preparedStatement.setString(2,employee.getFirstName());
            preparedStatement.setString(3,employee.getLastName());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteById(int id) {
        String sql="delete  from employee where id = ? ";
        Connection connection=null;
        try {
            connection=dataSource.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
           int result=preparedStatement.executeUpdate();
          if(result==1){
              System.out.println("deleted Success");
          }else{
              System.out.println("Operation Failed");
          }
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }


    }


}
