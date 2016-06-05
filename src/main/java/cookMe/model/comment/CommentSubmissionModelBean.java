package cookMe.model.comment;

import cookMe.model.ISubmissionModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.regex.Pattern;

/**
 * Created by Antoine on 04/06/2016.
 */
@ManagedBean
@RequestScoped
public class CommentSubmissionModelBean extends CommentModelBean implements ISubmissionModel {
    public CommentSubmissionModelBean() {

    }

    @Override
    public boolean isValid() {
        return getComment() != null && getComment().length() < 255 && Pattern.compile("[^<>]+").matcher(this.getComment()).matches();
    }

}
