package com.solvd.Controller.MySQLDAO;

import com.solvd.DAO.OrdersDAO;
import com.solvd.beams.Orders;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrdersDAO implements OrdersDAO {

    private Connection conn;

    public MySQLOrdersDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO orders (order_date, order_status, client_id) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE orders SET order_date = ?, order_status = ?, client_id = ? WHERE order_id = ?";
    final String DELETE = "DELETE FROM orders WHERE order_id = ?";
    final String GETONE = "SELECT order_id, order_date, order_status, client_id FROM orders WHERE order_id = ?";
    final String GETALL = "SELECT order_id, order_date, order_status, client_id FROM orders";


    @Override
    public void insert(Orders a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setDate(1,new Date(a.getOrder_date().getTime()));
            stat.setString(2, a.getOrder_status());
            stat.setDouble(3, a.getClient_id());
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
    public void update(Orders a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setDate(1,new Date(a.getOrder_date().getTime()));
            stat.setString(2, a.getOrder_status());
            stat.setDouble(3, a.getClient_id());
            stat.setLong(4, a.getOrder_id());
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
    public void delete(Orders a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getOrder_id());
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

    private Orders convert(ResultSet rs) throws SQLException {
        Long orderId = rs.getLong("order_id");
        Date date = rs.getDate("order_date");
        String status = rs.getString("order_status");
        int clientId = rs.getInt("client_id");
        Orders orders = new Orders(orderId, date, status, clientId);
        return orders;

    }

    @Override
    public Orders getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Orders or = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                or = convert(rs);
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
        return or;
    }


    @Override
    public List<Orders  > getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Orders> orders = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                orders.add(convert(rs));
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
        return orders;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            OrdersDAO dao = new MySQLOrdersDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Orders> orders = dao.getAll();
            for (Orders or: orders){
                System.out.println(or.toString());
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
