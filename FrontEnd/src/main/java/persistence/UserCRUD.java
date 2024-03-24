package persistence;

import java.sql.*;
import helper.*;

public class UserCRUD {
    public static User getUser(int uid) {
        User user = null;
        try {
            Connection conn = Database.connect();
            String query = "select * from Users where uid = " + uid;
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                user = new User(rs.getInt("uid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("emailAddress"), rs.getString("firstName"), rs.getString("lastName"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }

    public static User getUser(String username, String password) {
        User user = null;
        try {
            Connection conn = Database.connect();
            String query = "select * from Users where username = \"" + username + "\"";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("password").equals(password))
                user = new User(rs.getInt("uid"), rs.getString("username"), rs.getString("password"),
                        rs.getString("emailAddress"), rs.getString("firstName"), rs.getString("lastName"));
            else
                System.out.println("bad pw:" + password + " " + rs.getString("password"));
        } catch (Exception e) {
            System.out.println(e);
        }
        return user;
    }
}
