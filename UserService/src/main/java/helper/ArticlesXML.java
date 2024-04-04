package helper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ArticlesXML {
    @XmlElementWrapper(name = "articles")
    @XmlElement(name = "article")
    private ArrayList<Article> articles;

    public ArticlesXML() {
    }

    public ArticlesXML setArticles(ArrayList<Article> articles) {
        this.articles = articles;
        return this;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }
}