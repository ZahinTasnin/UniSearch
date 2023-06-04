import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private ArrayList <Unis> uniList;
    public User(String name, String password) {
        this.username = name;
        this.password = password;
        uniList = new ArrayList<Unis>();
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
    public void addUni(Unis university) {
        uniList.add(university);
    }

    public void setName(String newName){
        username = newName;
    }

    public void setPassword(String newPassWord){
        password = newPassWord;
    }
    public int checkDuplicates(Unis uni){
        return uniList.indexOf(uni);
    }

    public void removeUni(Unis uni){
        int num = uniList.indexOf(uni);
        uniList.remove(num);
    }
    public ArrayList<Unis> uniList(){
        return uniList;
    }
}
