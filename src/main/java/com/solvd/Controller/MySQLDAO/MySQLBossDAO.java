package com.solvd.Controller.MySQLDAO;

import com.solvd.DAO.BossDAO;
import com.solvd.beams.Boss;
import static com.solvd.hideConnection.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBossDAO implements BossDAO {

    private Connection conn;

    public MySQLBossDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO boss(boss_fullname, boss_age, boss_salary, boss_section) VALUES (?, ?, ?, ?)";
    final String UPDATE = "UPDATE boss SET boss_fullname = ?, boss_age = ?, boss_salary = ?, boss_section = ? WHERE boss_id = ?";
    final String DELETE = "DELETE FROM boss WHERE boss_id = ?";
    final String GETONE = "SELECT boss_id, boss_fullname, boss_age, boss_salary, boss_section FROM boss WHERE boss_id = ?";
    final String GETALL = "SELECT boss_id, boss_fullname, boss_age, boss_salary, boss_section FROM boss";


    @Override
    public void insert(Boss a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setString(1, a.getBoss_fullname());
            stat.setInt(2, a.getBoss_age());
            stat.setDouble(3, a.getBoss_salary());
            stat.setString(4, a.getBoss_section());
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
    public void update(Boss a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setString(1, a.getBoss_fullname());
            stat.setInt(2, a.getBoss_age());
            stat.setDouble(3, a.getBoss_salary());
            stat.setString(4, a.getBoss_section());
            stat.setLong(5, a.getBoss_id());
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
    public void delete(Boss a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getBoss_id());
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

    private Boss convert(ResultSet rs) throws SQLException {
        String fullname = rs.getString("boss_fullname");
        int age = rs.getInt("boss_age");
        double salary = rs.getDouble("boss_salary");
        String section = rs.getString("boss_section");
        Boss boss = new Boss(fullname, age, salary, section);
        boss.setBoss_id(rs.getLong("boss_id"));
        return boss;

    }

    @Override
    public Boss getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Boss b = null;
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
    public List<Boss> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Boss> bosses = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                bosses.add(convert(rs));
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
        return bosses;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            BossDAO dao = new MySQLBossDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Boss> bosses = dao.getAll();
            for (Boss b: bosses){
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
