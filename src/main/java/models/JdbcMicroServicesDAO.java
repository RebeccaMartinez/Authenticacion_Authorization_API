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
 * MicroServices DAO
 * @author Rebecca
 */
public class JdbcMicroServicesDAO implements MicroServicesDAO{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    @Override
    public String findMicroServiceID(String id){
        String sql = "SELECT NAME FROM microservicesxroles WHERE ID = ?";

        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            String msID = null;
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                msID = rs.getString("NAME");
            }
            rs.close();
            ps.close();
            return msID;
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
