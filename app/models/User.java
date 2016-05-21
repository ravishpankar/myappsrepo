package models;

import com.avaje.ebean.Model;
import play.data.format.*;
import play.data.validation.*;

import java.util.*;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;


/**
 * Created by admin on 5/17/2016.
 */
@Entity
public class User extends Model {
    @Column(name="user_id", length=16)
    public String userId;

    @Column(name="pwd")
    public String password;

    public static Finder<String, User> find = new Finder<String, User>(User.class);
}



