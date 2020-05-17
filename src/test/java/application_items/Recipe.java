package application_items;

public class Recipe {
    public String recipename;
    public Ingredient[] ingredlist;
    public String preptime;

    public Recipe() {
    }

    public Recipe(String recipename, Ingredient[] ingredlist, String preptime) {
        this.recipename = recipename;
        this.ingredlist = ingredlist;
        this.preptime = preptime;
    }
}