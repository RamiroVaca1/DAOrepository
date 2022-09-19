package com.solvd.MySQLDAO;

import com.solvd.DAO.Employee_infoDAO;
import com.solvd.beams.Employee_info;
import static com.solvd.hideConnections.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLEmployee_infoDAO implements Employee_infoDAO {

    private Connection conn;

    public MySQLEmployee_infoDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO employee_info (employee_street, employee_phone, employee_gender, employee_country, employee_city, employee_id) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE employee_info SET employee_street = ?, employee_phone = ?, employee_gender = ?, employee_country = ?, employee_city = ?, employee_id = ? WHERE employee_info_id = ?";
    final String DELETE = "DELETE FROM employee_info WHERE employee_info_id = ?";
    final String GETONE = "SELECT employee_info_id, employee_street, employee_phone, employee_gender, employee_country, employee_city, employee_id FROM employee_info WHERE employee_info_id = ?";
    final String GETALL = "SELECT employee_info_id, employee_street, employee_phone, employee_gender, employee_country, employee_city, employee_id FROM employee_info";


    @Override
    public void insert(Employee_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getEmployee_street());
            stat.setString(2, a.getEmployee_phone());
            stat.setString(3, a.getEmployee_gender());
            stat.setString(4, a.getEmployee_country());
            stat.setString(5, a.getEmployee_city());
            stat.setInt(6, a.getEmployee_id());
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
    public void update(Employee_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getEmployee_street());
            stat.setString(2, a.getEmployee_phone());
            stat.setString(3, a.getEmployee_gender());
            stat.setString(4, a.getEmployee_country());
            stat.setString(5, a.getEmployee_city());
            stat.setInt(6, a.getEmployee_id());
            stat.setLong(7,a.getEmployee_info_id());
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
    public void delete(Employee_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getEmployee_info_id());
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

    private Employee_info convert(ResultSet rs) throws SQLException {
        String street = rs.getString("employee_street");
        String phone = rs.getString("employee_phone");
        String gender = rs.getString("employee_gender");
        String country = rs.getString("employee_country");
        String city = rs.getString("employee_city");
        int id = rs.getInt("employee_id");
        Employee_info employee_info = new Employee_info(street, phone, gender, country, city,id);
        employee_info.setEmployee_info_id(rs.getLong("employee_info_id"));
        return employee_info;

    }

    @Override
    public Employee_info getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Employee_info em = null;
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
    public List<Employee_info> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Employee_info> employees_info = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                employees_info.add(convert(rs));
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
        return employees_info;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            Employee_infoDAO dao = new MySQLEmployee_infoDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Employee_info> employees_info= dao.getAll();
            for (Employee_info em: employees_info){
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
