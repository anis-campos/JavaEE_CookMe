package cookMe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djbranbran on 25/05/16.
 */

public class ListRecipeTypeBean{

    public void update() {
        this.list = getAll();
    }

    public enum RecipeType {
        Salade,
        Meal,
        Fish,
        Dessert;

    }


    private List<String> list;

    public ListRecipeTypeBean(){
        this.list = new ArrayList<String>();
        this.list = getAll();
    }

    public void addRecipeList(String type){
        this.list.add(type);
    }

    public List<String> getList(){
        return list;
    }


    public static List<String> getAll(){
        List<String> list = new ArrayList<String>();
        list.add(RecipeType.Salade.toString());
        list.add(RecipeType.Meal.toString());
        list.add(RecipeType.Fish.toString());
        list.add(RecipeType.Dessert.toString());
        return list;
    }
}

