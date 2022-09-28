package com.solvd.Controller.MySQLDAO;

import com.solvd.DAO.Order_detailsDAO;
import com.solvd.beams.Order_details;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLOrder_detailsDAO implements Order_detailsDAO {

    private Connection conn;

    public MySQLOrder_detailsDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO order_details (order_id, product_id, order_quantity, order_price) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE order_details SET order_id = ?, product_id = ?, order_quantity = ?, order_price = ? WHERE order_id = ?";
    final String DELETE = "DELETE FROM order_details WHERE order_id = ?";
    final String GETONE = "SELECT order_id, product_id, order_quantity, order_price FROM order_details WHERE order_id = ?";
    final String GETALL = "SELECT order_id, product_id, order_quantity, order_price FROM order_details";


    @Override
    public void insert(Order_details a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setLong(1, a.getOrder_id());
            stat.setLong(2, a.getProduct_id());
            stat.setInt(3, a.getOrder_quantity());
            stat.setDouble(4, a.getOrder_price());
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
    public void update(Order_details a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setLong(1, a.getOrder_id());
            stat.setLong(2, a.getProduct_id());
            stat.setInt(3, a.getOrder_quantity());
            stat.setDouble(4, a.getOrder_price());
            stat.setLong(5, a.getOrder_id());
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
    public void delete(Order_details a) {
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

    private Order_details convert(ResultSet rs) throws SQLException {
        Long orderId = rs.getLong("order_id");
        Long productId = rs.getLong("product_id");
        int quantity = rs.getInt("order_quantity");
        double price = rs.getDouble("order_price");
        Order_details order_details = new Order_details(orderId, productId, quantity, price);
        return order_details;

    }

    @Override
    public Order_details getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Order_details or = null;
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
    public List<Order_details> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Order_details> orders_details = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                orders_details.add(convert(rs));
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
        return orders_details;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            Order_detailsDAO dao = new MySQLOrder_detailsDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Order_details> order_details = dao.getAll();
            for (Order_details or: order_details){
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
