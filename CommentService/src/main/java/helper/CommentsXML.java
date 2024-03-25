package helper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class CommentsXML {
    @XmlElementWrapper(name = "comments")
    @XmlElement(name = "comment")
    private ArrayList<Comment> comments;

    public CommentsXML() {
    }

    public CommentsXML setComments(ArrayList<Comment> comments) {
        this.comments = comments;
        return this;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}