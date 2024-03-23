package business;

import persistence.*;
import java.util.ArrayList;
import helper.*;

public class ArticleBusiness {
    public ArticlesXML getPublicArticles() {
        ArrayList<Article> articles = ArticleCRUD.readPublicArticles();
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }

    public ArticlesXML getUserArticles(String query) {
        int uid = Integer.parseInt(query);
        ArrayList<Article> articles = ArticleCRUD.readUserArticles(uid);
        ArticlesXML ax = new ArticlesXML();
        ax.setArticles(articles);
        return ax;
    }
}