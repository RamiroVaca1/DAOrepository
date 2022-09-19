package com.solvd.MySQLDAO;

import com.solvd.DAO.PaymentDAO;
import com.solvd.beams.Payment;
import static com.solvd.hideConnections.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLPaymentDAO implements PaymentDAO {

    private Connection conn;

    public MySQLPaymentDAO(Connection conn){
        this.conn = conn;
    }

    final String INSERT = "INSERT INTO payment (paymentDate, payment_amount, client_id) VALUES (?, ?, ?)";
    final String UPDATE = "UPDATE payment SET paymentDate = ?, payment_amount = ?, client_id = ? WHERE payment_id = ?";
    final String DELETE = "DELETE FROM payment WHERE payment_id = ?";
    final String GETONE = "SELECT payment_id, paymentDate, payment_amount, client_id FROM payment WHERE payment_id = ?";
    final String GETALL = "SELECT payment_id, paymentDate, payment_amount, client_id FROM payment";


    @Override
    public void insert(Payment a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(INSERT);
            stat.setDate(1,new Date(a.getPaymentDate().getTime()));
            stat.setDouble(2, a.getPayment_amount());
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
    public void update(Payment a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(UPDATE);
            stat.setDate(1,new Date(a.getPaymentDate().getTime()));
            stat.setDouble(2, a.getPayment_amount());
            stat.setDouble(3, a.getClient_id());
            stat.setLong(4, a.getPayment_id());
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
    public void delete(Payment a) {
        PreparedStatement stat = null;
        try {
            stat = conn.prepareStatement(DELETE);
            stat.setLong(1, a.getPayment_id());
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

    private Payment convert(ResultSet rs) throws SQLException {
        Long orderId = rs.getLong("payment_id");
        Date date = rs.getDate("paymentDate");
        double amount = rs.getDouble("payment_amount");
        int clientId = rs.getInt("client_id");
        Payment payment = new Payment(orderId, date, amount, clientId);
        return payment;

    }

    @Override
    public Payment getOne(Long id) {
        PreparedStatement stat = null;
        ResultSet rs = null;
        Payment p = null;
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
    public List<Payment> getAll() {
        PreparedStatement stat = null;
        ResultSet rs = null;
        List<Payment> payments = new ArrayList<>();
        try {
            stat = conn.prepareStatement(GETALL);
            rs = stat.executeQuery();
            while (rs.next()){
                payments.add(convert(rs));
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
        return payments;
    }


    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbc,root,password);
            PaymentDAO dao = new MySQLPaymentDAO(conn);
            /* Boss nuevo = new Boss("Anote10", 22, 42000,"All");
            nuevo.setBoss_id(4L);
            dao.insert(nuevo);
            */
            List<Payment> payments = dao.getAll();
            for (Payment p: payments){
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
