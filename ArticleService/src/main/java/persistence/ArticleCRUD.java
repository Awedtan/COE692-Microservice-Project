package persistence;

import java.sql.*;
import java.util.ArrayList;
import helper.*;

public class ArticleCRUD {
    public static boolean insertArticle(int uid, String title, String content) {
        try {
            Connection conn = Database.connect();
            String query = String.format(
                    "insert into Articles (date, title, content, isPublished, author) values (\"%s\", \"%s\", \"%s\", %s, %d)",
                    new Date(System.currentTimeMillis()), title, content, true, uid);
            System.out.println(query);
            conn.prepareStatement(query).executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public static ArrayList<Article> selectPublicArticles() {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            Connection conn = Database.connect();
            String query = "select * from Articles where isPublished = true";
            ResultSet rs = conn.prepareStatement(query).executeQuery();
            if (rs.next()) {
                do {
                    articles.add(new Article(rs.getInt("uid"), rs.getString("date"), rs.getString("title"),
                            rs.getString("content"), rs.getBoolean("isPublished"), rs.getInt("author")));
                } while (rs.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return articles;
    }

    public static ArrayList<Article> selectUserArticles(int uid) {
        ArrayList<Article> articles = new ArrayList<Article>();
        try {
            Connection conn = Database.connect();
            String query = "select * from Articles where author = \"" + uid + "\"";
            ResultSet rs = conn.prepareStatement(query).executeQuery();
            if (rs.next()) {
                do {
                    articles.add(new Article(rs.getInt("uid"), rs.getString("date"), rs.getString("title"),
                            rs.getString("content"), rs.getBoolean("isPublished"), rs.getInt("author")));
                } while (rs.next());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return articles;
    }
}