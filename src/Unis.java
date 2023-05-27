import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Unis {

    private String name;
    private String URL;

    public Unis(String name, String URL){
        this.name = name;
        this.URL = URL;
    }

    @Override
    public String toString() {
        return name + URL;
    }

    /*

    public static String getURL(String school){
        for(int i = 0; i<buildUniList().size(); i++){
            if(buildUniList().get(i).name.equals(school)){
                return buildUniList().get(i).URL;
            }
        } return "";
    }*/
    public String getURL(){
        return URL;
    }
    public String getName(){
        return name;
    }


}
