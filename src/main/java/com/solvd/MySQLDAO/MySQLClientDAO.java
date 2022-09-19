package com.solvd.MySQLDAO;

import com.solvd.DAO.ClientDAO;
import com.solvd.beams.Client;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLClientDAO implements ClientDAO {

    private Connection conn;

    public MySQLClientDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO client (client_fullname, client_phone, client_address, client_city, client_country) VALUES (?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE client SET client_fullname = ?, client_phone = ?, client_address = ?, client_city = ?, client_country = ? WHERE client_id = ?";
    final String DELETE = "DELETE FROM client WHERE client_id = ?";
    final String GETONE = "SELECT client_id, client_fullname, client_phone, client_address, client_city, client_country FROM client WHERE client_id = ?";
    final String GETALL = "SELECT client_id, client_fullname, client_phone, client_address, client_city, client_country FROM client";


    @Override
    public void insert(Client a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getClient_fullname());
            stat.setString(2, a.getClient_phone());
            stat.setString(3, a.getClient_address());
            stat.setString(4, a.getClient_city());
            stat.setString(5, a.getClient_country());
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
    public void update(Client a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getClient_fullname());
            stat.setString(2, a.getClient_phone());
            stat.setString(3, a.getClient_address());
            stat.setString(4, a.getClient_city());
            stat.setString(5, a.getClient_country());
            stat.setLong(6,a.getClient_id());
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
    public void delete(Client a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getClient_id());
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

    private Client convert(ResultSet rs) throws SQLException {
        String fullname = rs.getString("client_fullname");
        String phone = rs.getString("client_phone");
        String address = rs.getString("client_address");
        String city = rs.getString("client_city");
        String country = rs.getString("client_country");
        Client client = new Client(fullname, phone, address, city, country);
        client.setClient_id(rs.getLong("client_id"));
        return client;

    }

    @Override
    public Client getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Client c = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                c = convert(rs);
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
        return c;
    }


    @Override
    public List<Client> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Client> clients = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                clients.add(convert(rs));
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
        return clients;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            ClientDAO dao = new MySQLClientDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Client> clients = dao.getAll();
            for (Client c: clients){
                System.out.println(c.toString());
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
