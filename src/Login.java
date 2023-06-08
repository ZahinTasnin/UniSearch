import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Locale;

public class Login {
    private JFrame frame;
    private JPanel panel;
    private JButton logIn;
    private JLabel user;
    private JTextField userName;
    private JButton signUp;
    private JLabel password;
    private JPasswordField passwordField;
    private JLabel success;
    private static ArrayList<User> userList;

    public Login(){

        //frame
        frame = new JFrame();
        frame.setTitle("UniSearch");
        frame.setSize(600,400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        ImageIcon icon= new ImageIcon("university.png");
        frame.setIconImage(icon.getImage());

        //initializing
        logIn = new JButton("LOGIN");
        signUp = new JButton("SIGNUP");
        user = new JLabel("USERNAME:");
        password = new JLabel("PASSWORD:");
        userName = new JTextField();
        passwordField = new JPasswordField();
        userList = new ArrayList<User>();
        userList.add(new User("z","1"));

        fontStyle();        //font, style, size

        //add components to the panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.add(user);
        panel.add(password);
        panel.add(userName);
        panel.add(passwordField);
        panel.add(logIn);
        panel.add(signUp);
        panel.setBackground(new Color(236, 230, 220));

        //button responding
        signUp.addActionListener(this::actionPerformed);
        signUp.setFocusable(false);
        logIn.addActionListener(this::actionPerformed);
        logIn.setFocusable(false);

        sizeAndLocation();      //setBounds and setLocation

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    //check if sign up and/or log in is successful or unsuccessful
    public void actionPerformed(ActionEvent event) {
        User validUser = new User("","");
        signUp.setVisible(false);
        logIn.setVisible(false);
        JButton src = (JButton) event.getSource();
        success = new JLabel("");
        success.setBounds(50, 20, 1000, 80);
        success.setVisible(true);
        panel.add(success);
        if (src.getText().equals("SIGNUP")) {
            String name = userName.getText();
            char[] password1 = passwordField.getPassword();
            String password = "";
            for (int i = 0; i < password1.length; i++) {    //get password
                password += password1[i];
            }
            boolean add = true;
            for (User user2 : userList) {               //determine duplicates
                if (user2.getName().equals(name)) {
                    add = false;
                }
            }
            if (add) {
                userList.add(new User(name, password)); //add user + success
                signUpSuccess();
            } else {
                signUpUnsuccessful();
                userName.setText("");
                passwordField.setText("");
            }
        }
        else if (src.getText().equals("LOGIN")) {//login attempt
                logIn.setVisible(false);
                String name = userName.getText();
                char[] password1 = passwordField.getPassword();
                String password = "";
                for (int i = 0; i < password1.length; i++) {
                    password += password1[i];
                }
                boolean add1 = false;
                for (User user2 : userList) {
                    if (user2.getName().equals(name) && user2.getPassword().equals(password)) {
                        add1 = true;
                        validUser = user2;
                    }
                }
                if (add1 == false) {
                    logInUnsuccessful();
                } else {
                    success.setText("Successful. LOADING........");
                    success.setForeground(new Color(40, 100, 10));
                    success.setFont(new Font("Serif", Font.BOLD, 20));
                    User finalValidUser = validUser;
                    Thread thread = new Thread(() -> {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        new Search(finalValidUser);
                        success.setVisible(false);
                        logIn.setVisible(true);
                        signUp.setVisible(true);
                    });
                    thread.start();
                }
        }
    }



    //add user successfully
    private void signUpSuccess(){
        success.setText("   S U C C E S S F U L");
        ImageIcon image = new ImageIcon("smiley.png");
        Image image1 = image.getImage().getScaledInstance(100, 60, 1000);
        image = new ImageIcon(image1);
        success.setIcon(image);
        success.setHorizontalTextPosition(JLabel.RIGHT);
        success.setForeground(new Color(40, 100, 10));
        success.setFont(new Font("Serif", Font.BOLD, 20));
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
    }

    //user signUp unsuccessful
    private void signUpUnsuccessful(){
        success.setText("   U N S U C C E S S F U L");     //unsuccessful
        ImageIcon image = new ImageIcon("sad.png");
        Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
        image = new ImageIcon(image1);
        success.setIcon(image);

        success.setForeground(new Color(250, 30, 50));
        success.setFont(new Font("Serif", Font.BOLD, 20));

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

    //log in unsuccessful
    private void logInUnsuccessful(){
        success.setText("   U N S U C C E S S F U L");
        ImageIcon image = new ImageIcon("sad.png");
        Image image1 = image.getImage().getScaledInstance(60, 60, 1000);
        image = new ImageIcon(image1);
        success.setIcon(image);

        success.setForeground(new Color(250, 30, 50));
        success.setFont(new Font("Serif", Font.BOLD, 20));

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
    }

    //font, style, size
    private void fontStyle(){
        user.setFont(new Font("Serif", Font.BOLD, 23));
        password.setFont(new Font("Serif", Font.BOLD, 23));
        logIn.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        signUp.setFont(new Font("Serif", Font.ITALIC|Font.BOLD, 18));
        userName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));

        user.setForeground(new Color(107, 112, 92));
        passwordField.setForeground(new Color(107, 112, 92));
        password.setForeground(new Color(107, 112, 92));
        userName.setForeground(new Color(107, 112, 92));
        signUp.setForeground(Color.WHITE);
        logIn.setForeground(Color.WHITE);

        logIn.setBackground(new Color(107, 112, 92));
        signUp.setBackground(new Color(107, 112, 92));
        Border empty = BorderFactory.createEmptyBorder();

        logIn.setBorder(BorderFactory.createEmptyBorder());
        signUp.setBorder(BorderFactory.createEmptyBorder());
        userName.setBorder(BorderFactory.createEmptyBorder(1,5,1,1));
        passwordField.setBorder(BorderFactory.createEmptyBorder(1,5,1,1));
    }

    //setBounds and setLocation
    private void sizeAndLocation(){

        //size
        user.setBounds(20, 20, 200, 30);
        password.setBounds(20, 50, 200, 30);
        passwordField.setBounds(120, 50, 300, 35);
        userName.setBounds(120, 20, 300, 35);
        logIn.setBounds(20, 20, 100, 30);
        signUp.setBounds(20, 20, 120, 30);

        //location
        user.setLocation(50,115);
        password.setLocation(50, 215);
        userName.setLocation(220, 115);
        passwordField.setLocation(220, 215);
        logIn.setLocation(100, 295);
        signUp.setLocation(350, 295);
    }

}
