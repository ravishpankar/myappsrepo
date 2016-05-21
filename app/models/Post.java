package models;

import com.avaje.ebean.Model;
import controllers.Secured;
import helpers.Util;
import play.data.format.*;
import play.data.validation.*;
import play.mvc.Security;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

/**
 * Created by admin on 5/17/2016.
 */
@Entity
public class Post extends Model {
    @Id
    @Column(name="post_id")
    public String id;

    @Column(name="post",length=500)
    @Constraints.Required
    public String post;

    @Column(name="posted_on")
    @Formats.DateTime(pattern="EEE, d MMM yyyy HH:mm:ss:SSS zzzz")
    public Date postedOn;

    @Column(name="user_id", length=16)
    public String userId;
    @PrePersist
    public void setPostedOn() throws InterruptedException {
        postedOn = Util.getTimeStamp();
    }

    public void setUserId(String userId){
        this.userId = userId;
    }

    public static Finder<String, Post> find = new Finder<String, Post>(Post.class);
}
