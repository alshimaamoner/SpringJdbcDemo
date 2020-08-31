
import dao.EmployeeDao;
import dao.JdbcEmployeeDaoImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.xml.XmlBeanFactory;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("beans.xml");
        EmployeeDao dao= (EmployeeDao) applicationContext.getBean("jdbcEmployeeDaoImpl");
        dao.findAll().forEach((user)-> System.out.println(user));
       // System.out.println(dao.findById(1));

    }
}
