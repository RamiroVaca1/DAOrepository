package com.solvd.Controller.MySQLDAO;

import com.solvd.DAO.Products_infoDAO;
import com.solvd.beams.Products_info;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLProducts_infoDAO implements Products_infoDAO {

    private Connection conn;

    public MySQLProducts_infoDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO products_info (product_description, product_brand, product_id) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE products_info SET product_description = ?, product_brand = ?, product_id = ? WHERE product_info_id = ?";
    final String DELETE = "DELETE FROM products_info WHERE product_info_id = ?";
    final String GETONE = "SELECT product_info_id, product_description, product_brand, product_id FROM products_info WHERE product_info_id = ?";
    final String GETALL = "SELECT product_info_id, product_description, product_brand, product_id FROM products_info";


    @Override
    public void insert(Products_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getProduct_description());
            stat.setString(2, a.getProduct_brand());
            stat.setInt(3, a.getProduct_id());
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
    public void update(Products_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getProduct_description());
            stat.setString(2, a.getProduct_brand());
            stat.setInt(3, a.getProduct_id());
            stat.setLong(4, a.getProduct_info_id());
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
    public void delete(Products_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getProduct_info_id());
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

    private Products_info convert(ResultSet rs) throws SQLException {
        Long product_infoId = rs.getLong("product_info_id");
        String description = rs.getString("product_description");
        String brand = rs.getString("product_brand");
        int productId = rs.getInt("product_id");
        Products_info products_info = new Products_info(product_infoId, description, brand, productId);
        return products_info;

    }

    @Override
    public Products_info getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Products_info p = null;
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
    public List<Products_info> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Products_info> products_info = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                products_info.add(convert(rs));
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
        return products_info;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            Products_infoDAO dao = new MySQLProducts_infoDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Products_info> products_info = dao.getAll();
            for (Products_info p: products_info){
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
