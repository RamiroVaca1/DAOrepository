package com.solvd.MySQLDAO;

import com.solvd.DAO.EmployeeDAO;
import com.solvd.beams.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployeeDAO implements EmployeeDAO {

    private Connection conn;

    public MySQLEmployeeDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO employee (employee_fullname, employee_age, employee_salary, employee_type, boss_id) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE employee SET employee_fullname = ?, employee_age = ?, employee_salary = ?, employee_type = ?, boss_id = ? WHERE employee_id = ?";
    final String DELETE = "DELETE FROM employee WHERE employee_id = ?";
    final String GETONE = "SELECT employee_id, employee_fullname, employee_age, employee_salary, employee_type, boss_id FROM employee WHERE employee_id = ?";
    final String GETALL = "SELECT employee_id, employee_fullname, employee_age, employee_salary, employee_type, boss_id FROM employee";


    @Override
    public void insert(Employee a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getEmployee_fullname());
            stat.setInt(2, a.getEmployee_age());
            stat.setDouble(3, a.getEmployee_salary());
            stat.setString(4, a.getEmployee_type());
            stat.setInt(5, a.getBoss_id());
            if (stat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Employee a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getEmployee_fullname());
            stat.setInt(2, a.getEmployee_age());
            stat.setDouble(3, a.getEmployee_salary());
            stat.setString(4, a.getEmployee_type());
            stat.setInt(5, a.getBoss_id());
            stat.setLong(6,a.getEmployee_id());
            if (stat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void delete(Employee a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getEmployee_id());
            if (stat.executeUpdate() == 0) {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Employee convert(ResultSet rs) throws SQLException {
        String fullname = rs.getString("employee_fullname");
        int age = rs.getInt("employee_age");
        double salary = rs.getDouble("employee_salary");
        String type = rs.getString("employee_type");
        int id = rs.getInt("boss_id");
        Employee employee = new Employee(fullname, age, salary, type,id);
        employee.setEmployee_id(rs.getLong("employee_id"));
        return employee;

    }

    @Override
    public Employee getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Employee em = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                em = convert(rs);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return em;
    }


    @Override
    public List<Employee> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Employee> employees = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                employees.add(convert(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stat != null) {
                try {
                    stat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return employees;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarket","root","tierrasperdidas123");
            EmployeeDAO dao = new MySQLEmployeeDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Employee> employees = dao.getAll();
            for (Employee em: employees){
                System.out.println(em.toString());
            }
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }

    }


}
