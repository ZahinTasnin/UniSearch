import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

public class Search implements MouseListener, FocusListener {
    private JButton search;
    private JButton add;
    private JButton remove;
    private JButton viewList;
    private JTextField searchField;
    private ImageIcon imageIcon;
    private JFrame frame;
    private JPanel panel;
    private JButton school;
    private JPanel panel1;
    private JScrollPane scrollPane;
    private ArrayList<Unis> topSchools;

    public Search(){

        //building frame
        frame = new JFrame("University Search");
        frame.setSize(600, 600);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());


        //menu field initialization
        panel1 = new JPanel();
     //   panel1.set
        search = new JButton("Search");
        add = new JButton (" Add");
        remove = new JButton(" Remove");
        viewList = new JButton(" View List");
        searchField = new JTextField("Enter number");
        imageIcon = new ImageIcon("search.png.png");
        Image image1 = imageIcon.getImage().getScaledInstance(20, 20, 100);
        imageIcon = new ImageIcon(image1);



        //menu field button design
        search.setFont(new Font("Serif", Font.PLAIN, 18));
        add.setFont(new Font("Serif", Font.PLAIN, 18));
        remove.setFont(new Font("Serif", Font.PLAIN, 18));
        viewList.setFont(new Font("Serif", Font.PLAIN, 18));
        searchField.setFont(new Font("Serif", Font.PLAIN, 18));

        search.setForeground(Color.white);
        add.setForeground(Color.white);
        remove.setForeground(Color.white);
        viewList.setForeground(Color.white);
        searchField.setForeground(Color.GRAY);

        search.setBackground(new Color(212, 163, 115));
        remove.setBackground(new Color(212, 163, 115));
        viewList.setBackground(new Color(212, 163, 115));
        add.setBackground(new Color(212, 163, 115));

        search.setIcon(imageIcon);
        search.setFocusable(false);
        add.setFocusable(false);
        remove.setFocusable(false);
        viewList.setFocusable(false);

        //menu field button size and location
        panel1.setBounds(100,50,400,50);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        search.setBorder(emptyBorder);
        add.setBorder(emptyBorder);
        remove.setBorder(emptyBorder);
        viewList.setBorder(emptyBorder);
        searchField.setBorder(emptyBorder);

        search.setPreferredSize(new Dimension(90,30));
        add.setPreferredSize(new Dimension(55,30));
        remove.setPreferredSize(new Dimension(80,30));
        viewList.setPreferredSize(new Dimension(85,30));
        searchField.setColumns(9);
        searchField.setPreferredSize(new Dimension(150,30));

        panel1.setLayout(new FlowLayout());

        JLabel empty = new JLabel();
        panel1.add(searchField);
        panel1.add(empty);
        panel1.add(search);
        panel1.add(empty);
        panel1.add(add);
        panel1.add(empty);
        panel1.add(remove);
        panel1.add(empty);
        panel1.add(viewList);
        panel1.add(empty);

        //actionListener
        searchField.addFocusListener( this);
        panel1.addMouseListener(this);

        //search panel
        panel = new JPanel(new GridLayout(98,1));
        topSchools = top98();

        for(int i= 0; i<topSchools.size(); i++){
            school = new JButton(topSchools.get(i).getName());
            school.setPreferredSize(new Dimension(400,40));
            school.setHorizontalAlignment(JButton.LEFT);
            school.setFocusable(false);
            school.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
            school.setVisible(true);
            if(i%2==0){
                school.setBackground(new Color(204, 213, 174));
            } else{
                school.setBackground(new Color(233, 237, 201));

            }
            panel.add(school);
            school.addMouseListener(this);
        }
        scrollPane = new JScrollPane(panel);
        JPanel contentPane = new JPanel(null);
        scrollPane.setBounds(75,20, 450, 450);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.setPreferredSize(new Dimension(500,500));
        contentPane.add(scrollPane);

        frame.add(new JPanel(), BorderLayout.NORTH);
        frame.add(panel1, BorderLayout.CENTER);
        frame.add(contentPane, BorderLayout.SOUTH);
        contentPane.setBackground(new Color(254, 250, 224));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.requestFocusInWindow();

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

    private String findURL(String name){
        for(int i = 0; i<top98().size(); i++){
            if(top98().get(i).getName().equals(name)){
                return top98().get(i).getURL();
            }
        } return "";
    }

    public void mousePressed(MouseEvent e) {    //connect link
        if(e.getSource() == panel1){
            panel1.requestFocusInWindow();
        } else{
            JButton src = (JButton) e.getSource();
            try{
                Desktop.getDesktop().browse(new URL(findURL(src.getText())).toURI());
            }
            catch (Exception f)
            {}
        }
    }

    public void focusGained(FocusEvent e)
    {
        if (searchField.getText().equals("Enter number")) {
            searchField.setForeground(Color.black);
            searchField.setText("");

        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (searchField.getText().isEmpty()) {
            searchField.setText("Enter number");
            searchField.setForeground(Color.GRAY);
        }
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
    @Override
    public void mouseClicked(MouseEvent e) {

    }

}
