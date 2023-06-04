import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

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

    private User validUser;
    public Login(){

        validUser = new User("","");
        //frame
        frame = new JFrame();
        frame.setTitle("UniSearch");
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        //initializing
        logIn = new JButton("LOGIN");
        signUp = new JButton("SIGNUP");
        user = new JLabel("USERNAME:");
        password = new JLabel("PASSWORD:");
        userName = new JTextField();
        passwordField = new JPasswordField();
        userList = new ArrayList<User>();
        userList.add(new User("z","1"));
        Border empty = BorderFactory.createEmptyBorder();

        //font, style, size
        user.setFont(new Font("Serif", Font.BOLD, 23));
        user.setForeground(new Color(203, 153, 126));
        password.setFont(new Font("Serif", Font.BOLD, 23));
        password.setForeground(new Color(203, 153, 126));
        logIn.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        logIn.setForeground(Color.WHITE);
        logIn.setBackground(new Color(203, 153, 126));
        logIn.setBorder(empty);
        signUp.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        signUp.setForeground(Color.WHITE);
        signUp.setBackground(new Color(203, 153, 126));
        signUp.setBorder(empty);
        userName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userName.setBorder(BorderFactory.createEmptyBorder(1,5,1,1));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordField.setBorder(BorderFactory.createEmptyBorder(1,5,1,1));

        //add components to the panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.add(user);
        panel.add(password);
        panel.add(userName);
        panel.add(passwordField);
        panel.add(logIn);
        panel.add(signUp);
        panel.setBackground(new Color(240, 239, 235));

        //button responding
        signUp.addActionListener(this::actionPerformed);
        signUp.setFocusable(false);
        logIn.addActionListener(this::actionPerformed);
        logIn.setFocusable(false);

        //size
        user.setBounds(20, 20, 200, 30);
        password.setBounds(20, 50, 200, 30);
        passwordField.setBounds(120, 50, 300, 35);
        userName.setBounds(120, 20, 300, 35);
        logIn.setBounds(20, 20, 100, 30);
        signUp.setBounds(20, 20, 120, 30);

        //location
        user.setLocation(50,100);
        password.setLocation(50, 200);
        userName.setLocation(220, 100);
        passwordField.setLocation(220, 200);
        logIn.setLocation(100, 300);
        signUp.setLocation(350, 300);


        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent event) {
        signUp.setVisible(false);
        logIn.setVisible(false);
        JButton src = (JButton) event.getSource();
        JLabel success = new JLabel("");
        success.setBounds(50, 20, 1000, 80);
        success.setVisible(true);
        panel.add(success);
        if (src.getText().equals("SIGNUP")) {
            String name = userName.getText();
            char[] password1 = passwordField.getPassword();
            String password = "";
            for(int i = 0; i<password1.length; i++){    //get password
                password+=password1[i];
            }            boolean add = true;
            for (User user2 : userList) {               //determine duplicates
                if (user2.getName().equals(name)) {
                    add = false;
                }
            }
            if (add) {
                userList.add(new User(name, password)); //add user + success
                success.setText("   S U C C E S S F U L");
                ImageIcon image = new ImageIcon("smiley.png");
                Image image1 = image.getImage().getScaledInstance(100, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);
                success.setHorizontalTextPosition(JLabel.RIGHT);
                success.setForeground(new Color(40, 100, 10));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                    logIn.setVisible(true);
                });
                thread.start();
            } else {
                success.setText("   U N S U C C E S S F U L");     //unsuccessful
                ImageIcon image = new ImageIcon("sad.png");
                Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);

                success.setForeground(new Color(250, 30, 50));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 25));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    signUp.setVisible(true);
                    logIn.setVisible(true);
                });
                thread.start();
            }
            userName.setText("");
            passwordField.setText("");
        } else if (src.getText().equals("LOGIN")) {     //login attempt
            logIn.setVisible(false);
            String name = userName.getText();
            validUser.setName(name);
            char[] password1 = passwordField.getPassword();
            String password = "";
            for(int i = 0; i<password1.length; i++){
                password+=password1[i];
            }
            validUser.setPassword(password);
            boolean add1 = false;
            for (User user2 : userList) {
                if (user2.getName().equals(name) && user2.getPassword().equals(password)) {
                    add1 = true;
                }
            }
            if (add1==false) {
                success.setText("   U N S U C C E S S F U L");
                ImageIcon image = new ImageIcon("sad.png");
                Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
                image = new ImageIcon(image1);
                success.setIcon(image);

                success.setForeground(new Color(250, 30, 50));
                success.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 30));

                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    success.setVisible(false);
                    logIn.setVisible(true);
                    signUp.setVisible(true);
                });
                thread.start();
                userName.setText("");
                passwordField.setText("");
            } else{
                success.setText("Successful. LOADING........");
                success.setForeground(new Color(40, 100, 10));
                success.setFont(new Font("Serif", Font.BOLD, 20));
                Thread thread = new Thread(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    new Search(validUser);
                    success.setVisible(false);
                    logIn.setVisible(true);
                    signUp.setVisible(true);
                });
                thread.start();
            }
        }


    }
    public static void main(String[]args){
        new Login();
    }

}
