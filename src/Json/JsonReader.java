package Json;

import Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {
    static RuntimeTypeAdapterFactory<Article> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
            .of(Article.class,"type")
            .registerSubtype(Electronics.class, "Electronics")
            .registerSubtype(Clothing.class, "Clothing")
            .registerSubtype(Book.class, "Book");
    static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();

    private static String readJsonFromFile(Path path){
        List<String> contentOfFile = new ArrayList<>();
        try{
            contentOfFile = Files.readAllLines(path);
        }catch(IOException e){
            System.out.println("Fehler beim Lesen von Basket");
            System.out.println(e.getMessage());
        }
        return String.join("", contentOfFile);
    }
    public static Basket getObjectFromFile(Path path){
        return gson.fromJson(readJsonFromFile(path), Basket.class);
    }
}
