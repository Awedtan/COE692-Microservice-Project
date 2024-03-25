package business;

import java.util.ArrayList;
import java.util.Map.Entry;

import helper.Article;
import helper.ArticlesXML;
import persistence.ArticleCRUD;

public class ArticleBusiness {
    public boolean insertArticle(int uid, String title, String content, String token) {
        boolean response = false;
        Entry<Boolean, String> valid = Authenticate.verifyToken(token);
        if (valid.getKey() && Integer.parseInt(valid.getValue()) == uid) {
            response = ArticleCRUD.insertArticle(uid, title, content);
        }
        return response;
    }

    public ArticlesXML getPublicArticles() {
        ArrayList<Article> articles = ArticleCRUD.selectPublicArticles();
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }

    public ArticlesXML getUserArticles(String query) {
        int uid = Integer.parseInt(query);
        ArrayList<Article> articles = ArticleCRUD.selectUserArticles(uid);
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }
}