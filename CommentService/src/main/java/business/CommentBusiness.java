package business;

import persistence.*;
import java.util.ArrayList;
import helper.*;

public class CommentBusiness {
    public CommentsXML getArticleComments(String query) {
        int uid  = Integer.parseInt(query);
        ArrayList<Comment> comments = CommentCRUD.readArticleComments(uid);
        CommentsXML cx = new CommentsXML();
        cx.setComments(comments);
        return cx;
    }
}