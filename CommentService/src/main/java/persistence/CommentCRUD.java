package persistence;

import java.sql.*;
import java.util.ArrayList;
import helper.*;

public class CommentCRUD {
    public static boolean createComment(int uid, String content, int article) {
        try {
            Connection conn = Database.connect();
            String query = String.format(
                    "insert into Comments (date, content, article, author) values (\"%s\", \"%s\", %d, %d)",
                    new Date(System.currentTimeMillis()), content, article, uid);
            System.out.println(query);
            conn.prepareStatement(query).executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static ArrayList<Comment> readArticleComments(int uid) {
        ArrayList<Comment> comments = new ArrayList<Comment>();
        try {
            Connection conn = Database.connect();
            String query = "select * from Comments where article = \"" + uid + "\"";
            ResultSet rs = conn.prepareStatement(query).executeQuery();
            if (rs.next()) {
                do {
                    comments.add(new Comment(rs.getInt("uid"), rs.getString("date"), rs.getString("content"),
                            rs.getInt("article"), rs.getInt("author")));
                } while (rs.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return comments;
    }
}