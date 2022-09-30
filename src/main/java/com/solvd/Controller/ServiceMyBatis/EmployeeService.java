package com.solvd.Controller.ServiceMyBatis;

import com.solvd.beams.Employee;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class EmployeeService {

    public List<Employee> getAll() {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        return employeeMapper.getAll();
    }

    public void insert(Employee employee) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employeeMapper.insert(employee);
        sqlSession.commit();
        sqlSession.close();
    }

    public Employee select(Long id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        return employeeMapper.select(id);
    }

    public void update(Employee employee) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employeeMapper.update(employee);
        sqlSession.commit();
        sqlSession.close();
    }

    public void delete(Long id) {
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        employeeMapper.delete(id);
        sqlSession.commit();
        sqlSession.close();
    }
}
