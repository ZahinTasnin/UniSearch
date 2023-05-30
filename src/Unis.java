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

    public String getURL(){
        return URL;
    }
    public String getName(){
        return name;
    }


}
