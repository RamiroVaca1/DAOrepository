package com.solvd.MySQLDAO;

import com.solvd.DAO.Boss_infoDAO;
import com.solvd.beams.Boss_info;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBoss_infoDAO implements Boss_infoDAO {

    private Connection conn;

    public MySQLBoss_infoDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO boss_info (boss_street, boss_phone, boss_gender, boss_country, boss_city, boss_id) VALUES (?, ?, ?, ?, ?, ?)";
    final String UPDATE = "UPDATE boss_info SET boss_street = ?, boss_phone = ?, boss_gender = ?, boss_country = ?, boss_city = ?, boss_id = ? WHERE boss_info_id = ?";
    final String DELETE = "DELETE FROM boss_info WHERE boss_info_id = ?";
    final String GETONE = "SELECT boss_info_id, boss_street, boss_phone, boss_gender, boss_country, boss_city, boss_id FROM boss_info WHERE boss_info_id = ?";
    final String GETALL = "SELECT boss_info_id, boss_street, boss_phone, boss_gender, boss_country, boss_city, boss_id FROM boss_info";


    @Override
    public void insert(Boss_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getBoss_street());
            stat.setString(2, a.getBoss_phone());
            stat.setString(3, a.getBoss_gender());
            stat.setString(4, a.getBoss_country());
            stat.setString(5, a.getBoss_city());
            stat.setInt(6, a.getBoss_id());
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
    public void update(Boss_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getBoss_street());
            stat.setString(2, a.getBoss_phone());
            stat.setString(3, a.getBoss_gender());
            stat.setString(4, a.getBoss_country());
            stat.setString(5, a.getBoss_city());
            stat.setInt(6, a.getBoss_id());
            stat.setLong(7,a.getBoss_info_id());
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
    public void delete(Boss_info a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getBoss_info_id());
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

    private Boss_info convert(ResultSet rs) throws SQLException {
        String street = rs.getString("boss_street");
        String phone = rs.getString("boss_phone");
        String gender = rs.getString("boss_gender");
        String country = rs.getString("boss_country");
        String city = rs.getString("boss_city");
        int id = rs.getInt("boss_id");
        Boss_info boss_info = new Boss_info(street, phone, gender, country, city,id);
        boss_info.setBoss_info_id(rs.getLong("boss_info_id"));
        return boss_info;

    }

    @Override
    public Boss_info getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Boss_info b = null;
        try {
            stat = conn.prepareStatement(GETONE);
            stat.setLong(1, id);
            rs = stat.executeQuery();
            if (rs.next()) {
                b = convert(rs);
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
        return b;
    }


    @Override
    public List<Boss_info> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Boss_info> bosses_info = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                bosses_info.add(convert(rs));
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
        return bosses_info;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            Boss_infoDAO dao = new MySQLBoss_infoDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Boss_info> bosses_info = dao.getAll();
            for (Boss_info b: bosses_info){
                System.out.println(b.toString());
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
