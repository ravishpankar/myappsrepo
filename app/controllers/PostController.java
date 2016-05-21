package controllers;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import helpers.PageFooter;
import helpers.Util;
import play.data.Form;
import play.mvc.*;

import models.Post;

import views.html.*;


/**
 * Created by admin on 5/17/2016.
 */
public class PostController  extends Controller {

    private static int pageSize = 5;

    private class PostComparator implements Comparator<Post> {
        public int compare(Post p1, Post p2) {
            int cR = p1.postedOn.compareTo(p2.postedOn);
            if (cR < 0)
                cR = 1;
            else if (cR > 0)
                cR = -1;
            return cR;
        }
    }

    @Security.Authenticated(Secured.class)
    public Result addPost() throws Exception {
        Post post = Form.form(Post.class).bindFromRequest().get();
        post.setUserId(session().get("userId"));
        post.save();
        PageFooter pgF;
        List<Post> psts = Post.find.where().setMaxRows(pageSize).where().le("postedOn", post.postedOn).where().orderBy("postedOn desc").findList();
        if (psts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Post> pst1 = Post.find.where().setMaxRows(1).where().gt("postedOn", Util.getFirstDateAD()).where().orderBy("postedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, pst1.get(0).postedOn, psts.get(psts.size() - 1).postedOn, psts.get(0).postedOn);
        }
        return ok(firstpostspagecount.render(psts, new Integer(Post.find.findRowCount()), pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getPosts() throws Exception {
        PageFooter pgF;
        List<Post> psts = Post.find.where().setMaxRows(pageSize).where().lt("postedOn", new Date()).where().orderBy("postedOn desc").findList();
        if (psts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Post> pst1 = Post.find.where().setMaxRows(1).where().gt("postedOn", Util.getFirstDateAD()).where().orderBy("postedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, pst1.get(0).postedOn, psts.get(psts.size() - 1).postedOn, psts.get(0).postedOn);
        }
        return ok(posts.render(psts, new Integer(Post.find.findRowCount()), pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getPreviousPage(String firstD) throws Exception {
        PageFooter pgF;

        List<Post> psts = Post.find.where().setMaxRows(pageSize).where().lt("postedOn", Util.getDateFromNonReadableString(firstD)).where().orderBy("postedOn desc").findList();
        if (psts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Post> pst1 = Post.find.where().setMaxRows(1).where().gt("postedOn", Util.getFirstDateAD()).where().orderBy("postedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, pst1.get(0).postedOn, psts.get(psts.size() - 1).postedOn, psts.get(0).postedOn);
        }

        return ok(postspage.render(psts, pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getNextPage(String lastD) throws Exception {
        PageFooter pgF;
        List<Post> psts = Post.find.where().setMaxRows(pageSize).where().gt("postedOn", Util.getDateFromNonReadableString(lastD)).where().orderBy("postedOn asc").findList();

        if (psts.size() != pageSize)
            return getPreviousPage(Util.getNonReadableDateString(new Date()));

        psts.sort(new PostComparator());

        List<Post> pst1 = Post.find.where().setMaxRows(1).where().gt("postedOn", Util.getFirstDateAD()).where().orderBy("postedOn asc").findList();
        pgF = PageFooter.getPageFooter(false, pst1.get(0).postedOn, psts.get(psts.size()-1).postedOn, psts.get(0).postedOn);
        return ok(postspage.render(psts, pgF));
    }
}
