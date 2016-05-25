package cookMe.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by djbranbran on 24/05/16.
 */
@ManagedBean
@SessionScoped
public class RecipeModelBean {
    private String title;
    private String description;
    private int expertise;
    private int nbpeople;
    private int duration;
    private String type;
    private String image;
    private int id;




    public RecipeModelBean() {
    }

    public RecipeModelBean(String title, String description, int expertise, int nbpeople, int duration, String type, String image, int id) {
        this.title = title;
        this.description = description;
        this.expertise = expertise;
        this.nbpeople = nbpeople;
        this.duration = duration;
        this.type = type;
        this.image = image;
        this.id = id;
    }


    @Override
    public String toString() {
        return "RecipeModelBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", expertise=" + expertise +
                ", nbpeople=" + nbpeople +
                ", duration=" + duration +
                ", type='" + type + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getExpertise() {
        return expertise;
    }

    public void setExpertise(int expertise) {
        this.expertise = expertise;
    }

    public int getNbpeople() {
        return nbpeople;
    }

    public void setNbpeople(int nbpeople) {
        this.nbpeople = nbpeople;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage(){ return image; }

    public void setImage(String image){
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
