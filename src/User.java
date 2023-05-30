import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList <String> uniList;
    public User(String name, String password) {
        this.username = name;
        this.password = password;
        uniList = new ArrayList<String>();
    }

    public String getName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String toString(){
        return username + "," + password;
    }
    public void addUni(String university) {
        uniList.add(university);
    }



}
