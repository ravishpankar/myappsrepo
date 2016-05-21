package controllers;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

import helpers.PageFooter;
import helpers.Util;
import models.Post;
import play.data.Form;

import play.mvc.*;
import models.Comment;

import views.html.*;


/**
 * Created by admin on 5/17/2016.
 */
public class CommentController  extends Controller {

    private static int pageSize = 3;

    private class CommentComparator implements Comparator<Comment> {
        public int compare(Comment c1, Comment c2) {
            int cR = c1.commentedOn.compareTo(c2.commentedOn);
            if (cR < 0)
                cR = 1;
            else if (cR > 0)
                cR = -1;
            return cR;
        }
    }

    @Security.Authenticated(Secured.class)
    public Result addComment(String postId) throws Exception {
        Comment comment = Form.form(Comment.class).bindFromRequest().get();
        comment.setPostId(postId);
        comment.setUserId(session().get("userId"));
        comment.save();

        PageFooter pgF;
        List<Comment> cmts = Comment.find.where().setMaxRows(pageSize).where().eq("postId", postId).where().le("commentedOn", comment.commentedOn).where().orderBy("commentedOn desc").findList();
        if (cmts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Comment> cmt1 = Comment.find.where().setMaxRows(1).where().eq("postId", postId).where().gt("commentedOn", Util.getFirstDateAD()).where().orderBy("commentedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, cmt1.get(0).commentedOn, cmts.get(cmts.size() - 1).commentedOn, cmts.get(0).commentedOn);
        }
        Post pst = Post.find.where().idEq(postId).findUnique();
        return ok(firstcommentspagecount.render(cmts, Comment.find.where().eq("postId", postId).findRowCount(), pst,  pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getComments(String postId) throws Exception {
        PageFooter pgF;
        List<Comment> cmts = Comment.find.where().setMaxRows(pageSize).where().eq("postId", postId).where().lt("commentedOn", new Date()).where().orderBy("commentedOn desc").findList();
        if (cmts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Comment> cmt1 = Comment.find.where().setMaxRows(1).where().eq("postId", postId).where().gt("commentedOn", Util.getFirstDateAD()).where().orderBy("commentedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, cmt1.get(0).commentedOn, cmts.get(cmts.size() - 1).commentedOn, cmts.get(0).commentedOn);
        }
        Post pst = Post.find.where().idEq(postId).findUnique();
        return ok(comments.render(cmts, Comment.find.where().eq("postId", postId).findRowCount(), pst,  pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getPreviousPage(String firstD, String postId) throws Exception {
        PageFooter pgF;
        List<Comment> cmts = Comment.find.where().setMaxRows(pageSize).where().eq("postId", postId).where().lt("commentedOn", Util.getDateFromNonReadableString(firstD)).where().orderBy("commentedOn desc").findList();
        if (cmts.size() == 0)
            pgF = PageFooter.getPageFooter(true, null, null, null);
        else {
            List<Comment> cmt1 = Comment.find.where().setMaxRows(1).where().eq("postId", postId).where().gt("commentedOn", Util.getFirstDateAD()).where().orderBy("commentedOn asc").findList();
            pgF = PageFooter.getPageFooter(false, cmt1.get(0).commentedOn, cmts.get(cmts.size() - 1).commentedOn, cmts.get(0).commentedOn);
        }
        return ok(commentspage.render(cmts, postId, pgF));
    }

    @Security.Authenticated(Secured.class)
    public Result getNextPage(String lastD, String postId) throws Exception {
        PageFooter pgF;
        List<Comment> cmts = Comment.find.where().setMaxRows(pageSize).where().eq("postId", postId).where().gt("commentedOn", Util.getDateFromNonReadableString(lastD)).where().orderBy("commentedOn asc").findList();
        if (cmts.size() != pageSize)
            return getPreviousPage(Util.getNonReadableDateString(new Date()), postId);

        cmts.sort(new CommentComparator());

        List<Comment> cmt1 = Comment.find.where().setMaxRows(1).where().eq("postId", postId).where().gt("commentedOn", Util.getFirstDateAD()).where().orderBy("commentedOn asc").findList();
        pgF = PageFooter.getPageFooter(false, cmt1.get(0).commentedOn, cmts.get(cmts.size() - 1).commentedOn, cmts.get(0).commentedOn);

        return ok(commentspage.render(cmts, postId, pgF));
    }
}
