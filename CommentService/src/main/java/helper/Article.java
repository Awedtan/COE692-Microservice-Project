package helper;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "article")
@XmlAccessorType(XmlAccessType.FIELD)
public class Article {
    private int uid;
    private String date;
    private String title;
    private String content;
    private boolean isPublished;
    private int authorId;

    public Article() {
    }

    public Article(int uid, String date, String title, String content, boolean isPublished, int authorId) {
        this.uid = uid;
        this.date = date;
        this.title = title;
        this.content = content;
        this.isPublished = isPublished;
        this.authorId = authorId;
    }

    public int getUid() {
        return this.uid;
    }

    public String getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public boolean getIsPublished() {
        return this.isPublished;
    }

    public int getAuthorId() {
        return this.authorId;
    }
}