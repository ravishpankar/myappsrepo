package models;

import com.avaje.ebean.Model;
import helpers.Util;
import play.data.format.*;
import play.data.validation.*;

import java.math.BigInteger;
import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;


/**
 * Created by admin on 5/17/2016.
 */
@Entity
public class Comment extends Model {
    @Id
    @Column(name="comment_id")
    public BigInteger id;

    @Column(name="post_id")
    public String postId;

    @Column(name="comment",length=500)
    @Constraints.Required
    public String comment;

    @Column(name="commented_on")
    @Formats.DateTime(pattern="EEE, d MMM yyyy HH:mm:ss:SSS zzzz")
    public Date commentedOn;

    @Column(name="user_id", length=16)
    public String userId;

    @PrePersist
    public void setCommentedOn() throws InterruptedException {
        commentedOn = Util.getTimeStamp();
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public static Finder<String, Comment> find = new Finder<String, Comment>(Comment.class);
}
