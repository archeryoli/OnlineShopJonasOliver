package Json;

import Models.Article;
import Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonWriter {
    static RuntimeTypeAdapterFactory<Article> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
            .of(Article.class,"type")
            .registerSubtype(Electronics.class, "Electronics")
            .registerSubtype(Clothing.class, "Clothing")
            .registerSubtype(Book.class, "Book");
    static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().registerTypeAdapterFactory(runtimeTypeAdapterFactory).setPrettyPrinting().create();
    public static void writeObjectToJson(Object objectToWrite, Path path){
        String json = gson.toJson(objectToWrite);
        try{
            Files.writeString(path, json);
        } catch(IOException e){
            System.out.println("Fehler beim Schreiben von Objekt");
            System.out.println(e.getMessage());
        }
    }
}
