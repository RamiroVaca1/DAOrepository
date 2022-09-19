package com.solvd.MySQLDAO;

import com.solvd.DAO.ProductsDAO;
import com.solvd.beams.Products;
import static com.solvd.hideConnections.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProductsDAO implements ProductsDAO {

    private Connection conn;

    public MySQLProductsDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO products (product_name, product_quantity, product_price) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE products SET product_name = ?, product_quantity = ?, product_price = ? WHERE product_id = ?";
    final String DELETE = "DELETE FROM products WHERE product_id = ?";
    final String GETONE = "SELECT product_id, product_name, product_quantity, product_price FROM products WHERE product_id = ?";
    final String GETALL = "SELECT product_id, product_name, product_quantity, product_price FROM products";


    @Override
    public void insert(Products a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getProduct_name());
            stat.setString(2, a.getProduct_quantity());
            stat.setDouble(3, a.getProduct_price());
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
    public void update(Products a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getProduct_name());
            stat.setString(2, a.getProduct_quantity());
            stat.setDouble(3, a.getProduct_price());
            stat.setLong(4, a.getProduct_id());
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
    public void delete(Products a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getProduct_id());
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

    private Products convert(ResultSet rs) throws SQLException {
        Long productId = rs.getLong("product_id");
        String name = rs.getString("product_name");
        String quantity = rs.getString("product_quantity");
        double price = rs.getDouble("product_price");
        Products products = new Products(productId, name, quantity, price);
        return products;

    }

    @Override
    public Products getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Products p = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                p = convert(rs);
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
        return p;
    }


    @Override
    public List<Products> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Products> products = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                products.add(convert(rs));
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
        return products;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            ProductsDAO dao = new MySQLProductsDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Products> products = dao.getAll();
            for (Products p: products){
                System.out.println(p.toString());
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
