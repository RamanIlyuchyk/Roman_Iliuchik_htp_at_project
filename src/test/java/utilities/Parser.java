package utilities;

import application_items.Ingredient;
import application_items.Recipe;
import application_items.Search;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {
    private final static String JSON = "src/test/resources/testData/recipe.json";
    File file = new File(JSON);

    public void parseJSON() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println("Recipe name: " + obj.getString("recipename"));
    }

    public void parseGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
        System.out.println(recipe.ingredlist[0]);
        System.out.println(recipe.ingredlist[1]);
        System.out.println(recipe.ingredlist[2]);
    }

    public void parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipe = mapper.readValue(file, Recipe.class);
        System.out.println("Preparation time: " + recipe.preptime);
    }

    public void fromGSON() throws IOException {
        Gson gson = new Gson();
        Recipe recipe = new Recipe("Borsch", new Ingredient[]{}, "120 minutes");
        System.out.println(gson.toJson(recipe));
        Files.write(Paths.get("src/test/resources/testData/borsch.json"), gson.toJson(recipe).getBytes());
        System.out.println("Borsch is written to borsch.json");
    }

    public static String fromGSON(Search search) {
        Gson gson = new Gson();
        return gson.toJson(search);
    }
}