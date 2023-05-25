import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;

public class Login {
    private JFrame frame;
    private JPanel panel;
    private JButton logIn;
    private JLabel user;
    private JTextField userName;
    private JButton signUp;
    private JLabel password;
    private JPasswordField passwordField;
    private static ArrayList<User> userList;

    public Login(){

        userList = new ArrayList<User>();
        //frame
        frame = new JFrame();
        frame.setTitle("UniSearch");
        frame.setSize(850,500);

        logIn = new JButton("LOGIN");
        signUp = new JButton("SIGNUP");
        user = new JLabel("USERNAME:");
        password = new JLabel("PASSWORD:");
        userName = new JTextField();
        passwordField = new JPasswordField();

        user.setFont(new Font("Serif", Font.BOLD, 23));
        password.setFont(new Font("Serif", Font.BOLD, 23));
        logIn.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        logIn.setForeground(Color.WHITE);
        logIn.setBackground(Color.BLUE);
        signUp.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(Color.BLUE);


        panel = new JPanel();
        panel.setLayout(null);
        panel.add(user);
        panel.add(password);
        panel.add(userName);
        panel.add(passwordField);
        panel.add(logIn);
        panel.add(signUp);


        signUp.addActionListener(this::actionPerformed);
        //size
        user.setBounds(20, 20, 200, 30);
        password.setBounds(20, 50, 200, 30);
        passwordField.setBounds(120, 50, 300, 35);
        userName.setBounds(120, 20, 300, 35);
        logIn.setBounds(20, 20, 100, 30);
        signUp.setBounds(20, 20, 120, 30);

        //location
        user.setLocation(150,150);
        password.setLocation(150, 220);
        userName.setLocation(350, 150);
        passwordField.setLocation(350, 220);
        logIn.setLocation(200, 320);
        signUp.setLocation(500, 320);

        frame.add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event){
        signUp.setVisible(false);
        JButton src = (JButton) event.getSource();
        JLabel success = new JLabel("");
        success.setBounds(150,50,1000,80);
        success.setVisible(true);
        panel.add(success);
        if(src.getText().equals("SIGNUP")){
            String name = userName.getText();
            String password = String.valueOf(passwordField);
            boolean add = true;
            for(User user2: userList){
                if(user2.getName().equals(name)){
                    add = false;
                }
            }
            if(add){
                userList.add(new User(name,password));
                success.setText("   S U C C E S S F U L");
                ImageIcon image = new ImageIcon("smiley.png");
                Image image1 = image.getImage().getScaledInstance(100, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);
                success.setHorizontalTextPosition(JLabel.RIGHT);
                success.setForeground(new Color(40,100,10));
                success.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 30));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");
                System.out.println(userList);

            } else{
                success.setText("   U N S U C C E S S F U L");
                ImageIcon image = new ImageIcon("sad.png");
                Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);

                success.setForeground(new Color(250, 30, 50));
                success.setFont(new Font("Serif", Font.BOLD|Font.ITALIC, 30));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");
            }
        }
    }
    public static void main(String[]args){
        new Login();
    }


}
