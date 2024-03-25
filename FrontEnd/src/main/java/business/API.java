package business;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;

import helper.ArticlesXML;
import helper.CommentsXML;
import helper.User;
import persistence.UserCRUD;

public class API {
    private static ArticlesXML xmlToArticles(String xml) {
        try {
            Unmarshaller ju = JAXBContext.newInstance(ArticlesXML.class).createUnmarshaller();
            ArticlesXML articles = (ArticlesXML) ju.unmarshal(new StringReader(xml));
            return articles;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static CommentsXML xmlToComments(String xml) {
        try {
            Unmarshaller ju = JAXBContext.newInstance(CommentsXML.class).createUnmarshaller();
            CommentsXML comments = (CommentsXML) ju.unmarshal(new StringReader(xml));
            return comments;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CommentsXML getArticleComments(int uid) {
        try {
            Client c = ClientBuilder.newClient();
            WebTarget t = c.target("http://localhost:8080/CommentService/api/article").path(Integer.toString(uid));
            InputStream is = t.request(MediaType.APPLICATION_XML).get(InputStream.class);
            String res = IOUtils.toString(is, "utf-8");
            CommentsXML comments = xmlToComments(res);
            return comments;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createArticle(User user, String title, String content, String token) {
        Client c = ClientBuilder.newClient();
        WebTarget t = c.target("http://localhost:8080/ArticleService/api/create");

        Form form = new Form()
                .param("uid", Integer.toString(user.getUid()))
                .param("title", title)
                .param("content", content)
                .param("token", token);

        t.request().post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
    }

    public static ArticlesXML getPublicArticles() {
        try {
            Client c = ClientBuilder.newClient();
            WebTarget t = c.target("http://localhost:8080/ArticleService/api/public");
            InputStream is = t.request(MediaType.APPLICATION_XML).get(InputStream.class);
            String res = IOUtils.toString(is, "utf-8");
            ArticlesXML articles = xmlToArticles(res);
            return articles;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArticlesXML getUserArticles(int uid) {
        try {
            Client c = ClientBuilder.newClient();
            WebTarget t = c.target("http://localhost:8080/ArticleService/api/user").path(Integer.toString(uid));
            InputStream is = t.request(MediaType.APPLICATION_XML).get(InputStream.class);
            String res = IOUtils.toString(is, "utf-8");
            ArticlesXML articles = xmlToArticles(res);
            return articles;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User getUser(int uid) {
        return UserCRUD.getUser(uid);
    }

    public static User getUser(String username, String password) {
        return UserCRUD.getUser(username, password);
    }

}
