import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.swing.JScrollPane;
import java.util.Comparator;

public class Search implements MouseListener {
    private JButton search;
    private JTextField searchField;
    private ImageIcon imageIcon;
    private JFrame frame;
    private JPanel panel;
    private JButton school;
    private JPanel panel1;
    private JScrollPane scrollPane;

    public Search(){

        frame = new JFrame("University Search");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        //search field
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        search = new JButton(" Search");
        search.setFont(new Font("Serif", Font.PLAIN, 18));
        search.setForeground(Color.white);
        searchField = new JTextField(20);
        imageIcon = new ImageIcon("search.png.png");
        Image image1 = imageIcon.getImage().getScaledInstance(25, 25, 100);
        imageIcon = new ImageIcon(image1);
        search.setIcon(imageIcon);
        search.setFocusable(false);
        search.setBackground(new Color(249,120,249));


        panel1.add(search);
        panel1.add(searchField);


        panel1.setBounds(100,50,400,50);



        panel = new JPanel(new GridLayout(98,1));

        ArrayList<Unis> topSchools = top98();

        for(int i= 0; i<topSchools.size(); i++){
            school = new JButton(topSchools.get(i).getName());
            school.setBounds(100, 100, 400, 50);
            school.setHorizontalAlignment(JButton.LEFT);
            school.setFocusable(false);
            school.setFont(new Font("Georgia", Font.PLAIN, 15));
            school.setVisible(true);
            if(i%2==0){
                school.setBackground(new Color(224,250,224));
            } else{
                school.setBackground(new Color(240,230,220));

            }
            panel.add(school);
            school.addMouseListener(this);
        }
        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(100,100, 400, 450);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel contentPane = new JPanel(null);
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.add(scrollPane);

        frame.add(panel1);
        frame.add(search);
        frame.add(searchField);
        frame.add(contentPane);
        contentPane.setBackground(new Color(249,230,249));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
    public static void main(String[]args){
        new Search();
    }
    private ArrayList<Unis> top98(){
        ArrayList<Unis> unisArrayList = new ArrayList<Unis>();
        String fileName = "src\\top98.csv";
        BufferedReader reader  = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(fileName));
            while((line = reader.readLine()) != null){
                String[] college = line.split(",");
                Unis uni = new Unis(college[0], college[1]);
                unisArrayList.add(uni);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return unisArrayList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

        JButton src = (JButton) e.getSource();

        try{
            Desktop.getDesktop().browse(new URL(findURL(src.getText())).toURI());
        }
        catch (Exception f)
        {}

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    private String findURL(String name){
        for(int i = 0; i<top98().size(); i++){
            if(top98().get(i).getName().equals(name)){
                return top98().get(i).getURL();
            }
        } return "";
    }


}
