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
 * User RoleDAO
 * @author Rebecca
 */
public class JdbcUserRoleDAO implements UserRoleDAO{
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
    }
    

    @Override
    public String findUserRoleByName(String name, String service){
        String sql = "SELECT " + service  + " FROM userroles WHERE USERNAME = ?";
        String roles = "";
        
        Connection conn = null;
		
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                    roles = roles + "" + rs.getString(service);
                   
            }
            rs.close();
            ps.close();
            return roles;
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
