/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

/**
 * User DAO
 * @author Rebecca
 */
public class JdbcUserDataDAO implements UserDataDAO{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }


    @Override
    public UserData findUserDataByName(String name){
        String sql = "SELECT * FROM userdata WHERE USERNAME = ?";

        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            UserData user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    user = new UserData(
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"), 
                            rs.getInt("ENABLED"),
                            rs.getString("USERTOKEN"),
                            rs.getInt("PERSONCODE"),
                            rs.getString("DOCUMENTTYPE"),
                            rs.getString("DOCUMENTNUMBER"),
                            rs.getString("EMAIL"),
                            rs.getString("NAME"),
                            rs.getString("SURENAME"),
                            rs.getString("PHONE")

                    );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }
    }
    

    @Override
    public UserData findUserDataByToken(String tk){
        String sql = "SELECT * FROM userdata WHERE USERTOKEN = ?";

        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk);
            UserData user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    user = new UserData(
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"), 
                            rs.getInt("ENABLED"),
                            rs.getString("USERTOKEN"),
                            rs.getInt("PERSONCODE"),
                            rs.getString("DOCUMENTTYPE"),
                            rs.getString("DOCUMENTNUMBER"),
                            rs.getString("EMAIL"),
                            rs.getString("NAME"),
                            rs.getString("SURENAME"),
                            rs.getString("PHONE")
                    );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }       
    }


    @Override
    public void insertUserToken(String tk, String username){
        
        String sql = "UPDATE userdata set usertoken = ? where username = ?";

        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk);
            ps.setString(2, username);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
                throw new RuntimeException(e);

        } finally {
                if (conn != null) {
                        try {
                                conn.close();
                        } catch (SQLException e) {}
                }
        }
    }

    @Override
    public void destroyToken(String tk){
        String sql = "UPDATE userdata SET USERTOKEN = NULL WHERE USERTOKEN = ? ";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, tk);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
                throw new RuntimeException(e);

        } finally {
                if (conn != null) {
                        try {
                                conn.close();
                        } catch (SQLException e) {}
                }
        }
    }

    @Override
    public UserData findUserByNameAndPass(String username, String password){
        String sql = "SELECT * FROM userdata WHERE USERNAME = ? AND PASSWORD = ?";

        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(1, password);
            UserData user = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                    user = new UserData(
                            rs.getString("USERNAME"),
                            rs.getString("PASSWORD"), 
                            rs.getInt("ENABLED"),
                            rs.getString("USERTOKEN"),
                            rs.getInt("PERSONCODE"),
                            rs.getString("DOCUMENTTYPE"),
                            rs.getString("DOCUMENTNUMBER"),
                            rs.getString("EMAIL"),
                            rs.getString("NAME"),
                            rs.getString("SURENAME"),
                            rs.getString("PHONE")

                    );
            }
            rs.close();
            ps.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {}
            }
        }      
    }
}
