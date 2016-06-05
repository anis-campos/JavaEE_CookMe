package cookMe.model.recipe;

import cookMe.model.EnumParser;

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
    private int nbPeople;
    private int duration;
    private RecipeType type;
    private String image;
    private int id;


    public RecipeModelBean() {
        type = RecipeType.None;
    }

    public RecipeModelBean(String title, String description, int expertise, int nbPeople, int duration, String type, String image, int id) {
        this.title = title;
        this.description = description;
        this.expertise = expertise;
        this.nbPeople = nbPeople;
        this.duration = duration;
        this.type = EnumParser.ParseWithDefault(RecipeType.class, type);
        this.image = image;
        this.id = id;
    }

    @Override
    public String toString() {
        return "RecipeModelBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", expertise=" + expertise +
                ", nbPeople=" + nbPeople +
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

    public int getNbPeople() {
        return nbPeople;
    }

    public void setNbPeople(int nbPeople) {
        this.nbPeople = nbPeople;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecipeModelBean that = (RecipeModelBean) o;

        if (expertise != that.expertise) return false;
        if (nbPeople != that.nbPeople) return false;
        if (duration != that.duration) return false;
        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return image != null ? image.equals(that.image) : that.image == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + expertise;
        result = 31 * result + nbPeople;
        result = 31 * result + duration;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
