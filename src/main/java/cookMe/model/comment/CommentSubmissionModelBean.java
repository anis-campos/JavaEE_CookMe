package cookMe.model.comment;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.regex.Pattern;

/**
 * Created by Antoine on 04/06/2016.
 */
@ManagedBean
@RequestScoped
public class CommentSubmissionModelBean extends CommentModelBean {
    public CommentSubmissionModelBean(){

    }

    public boolean isValid(){
        return getComment() != null && getComment().length() < 255 && !Pattern.compile("[<>]+").matcher(this.getComment()).matches();
    }

}
