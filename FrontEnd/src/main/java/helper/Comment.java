package helper;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "comment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Comment {
    private int uid;
    private String date;
    private String content;
    private int articleId;
    private int authorId;

    public Comment() {
    }

    public Comment(int uid, String date, String content, int articleId, int authorId) {
        this.uid = uid;
        this.date = date;
        this.content = content;
        this.articleId = articleId;
        this.authorId = authorId;
    }

    public int getUid() {
        return this.uid;
    }

    public String getDate() {
        return this.date;
    }

    public String getContent() {
        return this.content;
    }

    public int getArticleId() {
        return this.articleId;
    }

    public int getAuthorId() {
        return this.authorId;
    }
}