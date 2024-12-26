import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

class InfixToPostfixDummy {
    public static int evaluate(String expression) {
        char[] tokens = expression.toCharArray();

        Stack<Integer> values = new
                Stack<Integer>();
        Stack<Character> ops = new
                Stack<Character>();

        for (int i = 0; i < tokens.length; i++) {

            if (tokens[i] == ' ')
                continue;

            if (tokens[i] >= '0' &&
                    tokens[i] <= '9') {
                StringBuffer sbuf = new
                        StringBuffer();

                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                        tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(parseInt(sbuf.
                        toString()));
                i--;
            } else if (tokens[i] == '(')
                ops.push(tokens[i]);

            else if (tokens[i] == ')') {
                while (ops.peek() != '(')
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));
                ops.pop();
            } else if (tokens[i] == '+' ||
                    tokens[i] == '-' ||
                    tokens[i] == '*' ||
                    tokens[i] == '/') {

                while (!ops.empty() &&
                        hasPrecedence(tokens[i],
                                ops.peek()))
                    values.push(applyOp(ops.pop(),
                            values.pop(),
                            values.pop()));

                ops.push(tokens[i]);
            }
        }

        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                    values.pop(),
                    values.pop()));

        return values.pop();
    }
    public static boolean hasPrecedence(
            char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') &&
                (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }
    public static int applyOp(char op,
                              int b, int a) {
        switch (op) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new
                            UnsupportedOperationException(
                            "Cannot divide by zero");
                return a / b;
        }
        return 0;
    }
}
class Details implements ActionListener {
    public JFrame jFrame,jFrame2;
    TextArea bill;
    JLabel seachlable, login_password,login_password2,welcome_lable, welcome_lable2, welcome_lable3, each, generate, cal, detail, bill_no, customer_name, phone_no, product, sc, mc, pc, spc, quantity, stock, discount, price;
    JTextField each_val, jTextField, ibill_no, icustomer_name, iphone_no, search, quainput, stockno, discount_value, price_value;
    JPasswordField login_password_value,login_password_value2;
    JComboBox main_category, sub_category, product_category, sub_product;
    JButton btn1, btn2, btn3, btnplus;
    JButton btn4, btn5, btn6, btnminus;
    JButton btn7, btn8, btn9, btnmult;
    JButton btn00, btn0, btndeci, btndiv;
    JButton btnequal, btncut, btndel, print,clr_bill,cutbtn,srchbtn,ok,okk,resetbtn,changebtn;
    JButton  clear, total, gb, exit;
    JToggleButton add_more,add_cart,pass_info,pass_info2;
    JWindow jWindow;
    JProgressBar jProgressBar;
    ImageIcon imageIcon;
    JMenuBar menuBar;
    JPopupMenu popup;
    JMenuItem copy,cut,paste,selectall;
    JMenu file, view;
    JMenuItem save, save_as, history;
    ImageIcon imageIcon2,imageIcon3,visible,imageIcon4,imageIcon5,visible4,non_visible,imageIconn2,imageIconn3,visiblee,non_visiblee;
    Image image,image2,image4,imagee,imagee2;
    LocalDate date;
    LocalTime time;
    DateTimeFormatter format_time, format_date;
    Timer timer;
    String bn = " ";
    String cn = " ";
    String pn = " ";
    String detail_maincat = " ";
    String detail_subcat = " ";
    String detail_product = " ";
    String detail_subpro = " ";
    String quatity = " ";
    String discountt = " ";
    String pricee = " ";
    String ppp = " ";
    String change_dis=" ";
    String update_val;

    double final_price = 0;
    double final_pricee = 0;
    int id_bill_no;
    String current_time;
    String current_date;
    ArrayList<String> itemDetailsList = new ArrayList<>();
    ArrayList<String> itemDetailsCart = new ArrayList<>();
    ArrayList<String> choose_product = new ArrayList<>();
    ArrayList<String> choose_price = new ArrayList<>();
    StringBuilder finalsubproduct, finalchooseprice;


    //Login Page
   public void login_com(){

        jFrame2=new JFrame("Login page");
       imageIcon5 = new ImageIcon("D:\\Sumit\\1583402771-billing_system.jpg");
       jFrame2.setIconImage(imageIcon5.getImage());
       Container container2 = jFrame2.getContentPane();
       Color color2 = new Color(171, 149, 218);
       container2.setBackground(color2);

        login_password=new JLabel("Enter Password:- ");
        login_password.setBounds(300,450,300,30);
        login_password.setFont(new Font("Roman Times",Font.BOLD,25));

        login_password_value=new JPasswordField();
        login_password_value.setBounds(550,450,250,30);
        login_password_value.setFont(new Font("Roman Times",Font.BOLD,20));
        login_password_value.setEchoChar('*');
        login_password_value.setToolTipText("Enter Your Password");

        imageIcon2=new ImageIcon("D:\\Sumit\\visible.png");
        image = imageIcon2.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        visible = new ImageIcon(image);

        imageIcon3=new ImageIcon("D:\\Sumit\\non-visible.png");
        image2 = imageIcon3.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
        non_visible = new ImageIcon(image2);

        pass_info=new JToggleButton(non_visible);
        pass_info.setBounds(820,450,50,30);
        pass_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pass_info.isSelected()){
                    login_password_value.setEchoChar((char) 0);
                    pass_info.setIcon(visible);
                }
                else{
                    login_password_value.setEchoChar('*');
                    pass_info.setIcon(non_visible);
                }
            }
        });
        ok=new JButton("LOGIN");
        ok.setBounds(400,500,200,30);
        ok.setFont(new Font("Roman Times",Font.BOLD,20));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pass_val = new String(login_password_value.getPassword());

                //Taking Updated Value From DataBase
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                    String quaryy = "SELECT update_value FROM passwordMod WHERE pass_No = (SELECT MAX(pass_No) FROM passwordMod)";
                    PreparedStatement preparedStatement = connection.prepareStatement(quaryy);

                    ResultSet scan = preparedStatement.executeQuery();
                    if (scan.next()) {
                        update_val= scan.getString("update_value");
                    }
                    connection.close();
                }
                catch (Exception ex) {
                    System.out.println(ex);
                }
                if (update_val.equals(pass_val)) {
                    login_password.setVisible(false);
                    login_password_value.setVisible(false);
                    pass_info.setVisible(false);
                    ok.setVisible(false);
                    resetbtn.setVisible(false);
                    timer.start();
                }
                else {
                    JOptionPane.showMessageDialog(jFrame2, "Password Is Incorrect \n Try Again", "Message", JOptionPane.ERROR_MESSAGE);
                    login_password_value.setText("");
                    timer.stop();
                }
            }
        });
       //FORGOT PASSWORD
       resetbtn=new JButton("FORGOT PASSWORD");
       resetbtn.setBounds(650,500,200,30);
       resetbtn.setFont(new Font("Roman Times",Font.BOLD,15));
       resetbtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {

               login_password.setVisible(false);
               login_password_value.setVisible(false);
               pass_info.setVisible(false);
               ok.setVisible(false);
               resetbtn.setVisible(false);

               login_password2=new JLabel("New Password:- ");
               login_password2.setBounds(300,450,400,30);
               login_password2.setFont(new Font("Roman Times",Font.BOLD,25));

               login_password_value2=new JPasswordField();
               login_password_value2.setBounds(550,450,250,30);
               login_password_value2.setFont(new Font("Roman Times",Font.BOLD,20));
               login_password_value2.setEchoChar('*');
               login_password_value2.setToolTipText("Enter Your Password");

               imageIconn2=new ImageIcon("D:\\Sumit\\visible.png");
               imagee = imageIcon2.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
               visiblee = new ImageIcon(imagee);

               imageIconn3=new ImageIcon("D:\\Sumit\\non-visible.png");
               imagee2 = imageIcon3.getImage().getScaledInstance(50, 30, Image.SCALE_SMOOTH);
               non_visiblee = new ImageIcon(imagee2);

               pass_info2=new JToggleButton(non_visiblee);
               pass_info2.setBounds(820,450,50,30);
               pass_info2.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       if (pass_info2.isSelected()){
                           login_password_value2.setEchoChar((char) 0);
                           pass_info2.setIcon(visiblee);
                       }
                       else{
                           login_password_value2.setEchoChar('*');
                           pass_info2.setIcon(non_visiblee);
                       }
                   }
               });

               changebtn=new JButton("SET PASSWORD");
               changebtn.setBounds(650,500,200,30);
               changebtn.setFont(new Font("Roman Times",Font.BOLD,15));
               changebtn.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String pass_val = new String(login_password_value2.getPassword());

                       //Insert Updated Password Value In DataBase
                       try {
                           Class.forName("com.mysql.cj.jdbc.Driver");
                           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                           PreparedStatement preparedStatement = null;
                           String quary = "INSERT INTO passwordMod(update_value)" + "VALUES(?)";
                           preparedStatement = connection.prepareStatement(quary);
                           preparedStatement.setString(1, pass_val);

                           int status = preparedStatement.executeUpdate();
                           if (status > 0) {
                               JOptionPane.showMessageDialog(jFrame2, "Password Is Updated", "Message", JOptionPane.INFORMATION_MESSAGE);
                           }
                           connection.close();
                       } catch (Exception ex) {
                           JOptionPane.showMessageDialog(jFrame2, ex, "Message", JOptionPane.WARNING_MESSAGE);
                       }
                   }
               });

               okk=new JButton("LOGIN");
               okk.setBounds(400,500,200,30);
               okk.setFont(new Font("Roman Times",Font.BOLD,20));
               okk.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       String pass_val = new String(login_password_value2.getPassword());

                       //Taking Updated Value From DataBase
                       try {
                           Class.forName("com.mysql.cj.jdbc.Driver");
                           Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                           String quaryy = "SELECT update_value FROM passwordMod WHERE pass_No = (SELECT MAX(pass_No) FROM passwordMod)";
                           PreparedStatement preparedStatement = connection.prepareStatement(quaryy);

                           ResultSet scan = preparedStatement.executeQuery();
                           if (scan.next()) {
                               update_val= scan.getString("update_value");
                           }
                           connection.close();
                       }
                       catch (Exception ex) {
                           System.out.println(ex);
                       }
                       if (update_val.equals(pass_val)) {
                           login_password2.setVisible(false);
                           login_password_value2.setVisible(false);
                           pass_info2.setVisible(false);
                           okk.setVisible(false);
                           resetbtn.setVisible(false);
                           changebtn.setVisible(false);
                           timer.start();
                       }
                       else {
                           JOptionPane.showMessageDialog(jFrame2, "Password Is Incorrect \n Try Again", "Message", JOptionPane.ERROR_MESSAGE);
                           login_password_value2.setText("");
                           timer.stop();
                       }
                   }
               });

               jFrame2.add(login_password2);
               jFrame2.add(login_password_value2);
               jFrame2.add(pass_info2);
               jFrame2.add(okk);
               jFrame2.add(changebtn);
           }

       });

        welcome_lable=new JLabel("WELCOME TO ");
        welcome_lable.setBounds(480,120,1000,50);
        welcome_lable.setFont(new Font("Roman Times",Font.BOLD,50));

        welcome_lable2=new JLabel("JAVA MINI PROJECT");
        welcome_lable2.setBounds(400,220,1000,50);
        welcome_lable2.setFont(new Font("Roman Times",Font.BOLD,50));
        welcome_lable3=new JLabel("\nBILL GENERATING SYSTEM");
        welcome_lable3.setBounds(250,320,1000,50);
        welcome_lable3.setFont(new Font("Roman Times",Font.BOLD,60));

        jProgressBar=new JProgressBar(0,100);
        jProgressBar.setFont(new Font("Roman Times",Font.BOLD,20));
        jProgressBar.setBounds(0,630,1300,30);
        jProgressBar.setOrientation(SwingConstants.HORIZONTAL);
        jProgressBar.setStringPainted(false);
        Color color=new Color(50, 220, 87);
        jProgressBar.setForeground(color);
        timer=new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value=jProgressBar.getValue();
                if(value==100){
                    timer.stop();

                    //Dispose Login Page
                    jFrame2.dispose();
                    taking_Customer_Details();
                    calculator();
                    jframe_add_calulator();
                    jframeAdd();

                    JOptionPane.showMessageDialog(jFrame,"Login Is Done Successfully","Message",JOptionPane.INFORMATION_MESSAGE);

                    //Taking Bill No. From Data Base
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                        String quaryy = "SELECT MAX(Bill_No) +1 AS bill_no FROM daata";
                        PreparedStatement preparedStatement = connection.prepareStatement(quaryy);

                        ResultSet scan = preparedStatement.executeQuery();
                        if (scan.next()) {
                            id_bill_no=scan.getInt("bill_no");
                            ibill_no.setText(String.valueOf(id_bill_no));
                        }
                        else{
                            ibill_no.setText("1");
                        }
                        connection.close();
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }
                }
                else{
                    jProgressBar.setValue(value+50);
                }
            }
        });

        jFrame2.add(login_password);
        jFrame2.add(login_password_value);
        jFrame2.add(pass_info);
       jFrame2.add(ok);
        jFrame2.add(resetbtn);
        jFrame2.add(jProgressBar);
        jFrame2.add( welcome_lable);
        jFrame2.add( welcome_lable2);
        jFrame2.add( welcome_lable3);
        jFrame2.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame2.setLayout(null);
        jFrame2.setVisible(true);
        jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount()==2){
                    jFrame2.dispose();
                }
            }
        });
        jFrame2.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(jFrame2,"Are You Want To Exit");
                if(a==JOptionPane.YES_OPTION){
                    jFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                if(a==JOptionPane.NO_OPTION){
                    jFrame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                if(a==JOptionPane.CANCEL_OPTION){
                    jFrame2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }
    
    public void taking_Customer_Details() {
        jFrame = new JFrame("Billing Generating System");

        //Exit From Main page use Double click
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {

                    jFrame.dispose();

                }
            }
        });

        //Give Background color to Frame
        Container container = jFrame.getContentPane();
        Color color = new Color(206, 232, 228);
        container.setBackground(color);


        //Logo Of Our Application
        imageIcon = new ImageIcon("D:\\Sumit\\1583402771-billing_system.jpg");
        jFrame.setIconImage(imageIcon.getImage());


        //Popup Button
        popup=new JPopupMenu("Option");
        copy=new JMenuItem("Copy");
        cut=new JMenuItem("Cut");
        paste=new JMenuItem("Paste");
        selectall=new JMenuItem("SelectAll");
        popup.add(copy);
        popup.add(cut);
        popup.add(paste);
        popup.add(selectall);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        jFrame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON3){
                    int x_axis=e.getX();
                    int y_axis=e.getY();
                    popup.show(jFrame,x_axis,y_axis);
                }
            }
        });


        //Menu Bar
        menuBar = new JMenuBar();
        file = new JMenu("File");
        view = new JMenu("View");
        menuBar.add(file);
        menuBar.add(view);

        save = new JMenuItem("Save");
        save_as = new JMenuItem("Save As");
        history = new JMenuItem("History");

        file.add(save);
        file.add(save_as);
        view.add(history);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF file");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");
                fileChooser.setFileFilter(filter);

                int userSelection = fileChooser.showSaveDialog(jFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    try {
                        Document document = new Document(PageSize.A4);
                        PdfWriter.getInstance(document, new FileOutputStream(filePath));
                        document.open();


                        String[] lines = bill.getText().split(" ");
                        for (String line : lines) {
                            Paragraph paragraph = new Paragraph(line);
                            document.add(paragraph);
                        }

                        document.close();
                        JOptionPane.showMessageDialog(jFrame, "PDF generated successfully at: " + filePath);
                    } catch (DocumentException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        save_as.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF file");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");
                fileChooser.setFileFilter(filter);

                int userSelection = fileChooser.showSaveDialog(jFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    try {
                        Document document = new Document(PageSize.A4);
                        PdfWriter.getInstance(document, new FileOutputStream(filePath));
                        document.open();


                        String[] lines = bill.getText().split(" ");
                        for (String line : lines) {
                            Paragraph paragraph = new Paragraph(line);
                            document.add(paragraph);
                        }

                        document.close();
                        JOptionPane.showMessageDialog(jFrame, "PDF generated successfully at: " + filePath);
                    } catch (DocumentException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        history.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(jFrame, "History Is Empty", "Message", JOptionPane.WARNING_MESSAGE);
            }
        });

        //Heading like Customer Details
        detail = new JLabel("CUSTOMER DETAILS");
        detail.setBounds(550, 5, 300, 30);
        detail.setFont(new Font("Roman Times", Font.BOLD, 20));


        //Taking Customer Details
        bill_no = new JLabel("Bill No. : ");
        bill_no.setBounds(10, 50, 70, 30);
        bill_no.setFont(new Font("Roman Times", Font.BOLD, 15));
        ibill_no = new JTextField();
        ibill_no.setBounds(80, 50, 200, 30);
        ibill_no.setFont(new Font("Roman Times", Font.BOLD, 15));
        ibill_no.setEditable(false);

        customer_name = new JLabel("Customer Name : ");
        customer_name.setBounds(330, 50, 130, 30);
        customer_name.setFont(new Font("Roman Times", Font.BOLD, 15));
        icustomer_name = new JTextField();
        icustomer_name.setBounds(460, 50, 200, 30);
        icustomer_name.setFont(new Font("Roman Times", Font.BOLD, 15));

        phone_no = new JLabel("Phone No. : ");
        phone_no.setBounds(710, 50, 100, 30);
        phone_no.setFont(new Font("Roman Times", Font.BOLD, 15));
        iphone_no = new JTextField();
        iphone_no.setBounds(810, 50, 200, 30);
        iphone_no.setFont(new Font("Roman Times", Font.BOLD, 15));
        iphone_no.setToolTipText("Number Must Have 10 Digit");

        seachlable = new JLabel("Search");
        seachlable.setBounds(1060, 35, 200, 15);
        seachlable.setFont(new Font("Roman Times", Font.BOLD, 12));
        search = new JTextField();
        search.setBounds(1060, 50, 200, 30);
        search.setFont(new Font("Roman Times", Font.BOLD, 15));
        srchbtn=new JButton("Search");
        srchbtn.setBounds(1060, 90, 100, 30);
        srchbtn.setFont(new Font("Roman Times", Font.BOLD, 15));
        srchbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        srchbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e){
                String searchitem = search.getText();
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                    PreparedStatement preparedStatement = null;
                    String quary = "SELECT * FROM daata WHERE Customer_Name LIKE ?";
                    preparedStatement = connection.prepareStatement(quary);
                    preparedStatement.setString(1, searchitem);

                    ResultSet scan = preparedStatement.executeQuery();
                    while (scan.next()) {
                        String name = scan.getString("Customer_Name");
                        String pro = scan.getString("Product");
                        String amt= scan.getString("Amount");
                        String netamt=scan.getString("Net_Amount");
                        String t=scan.getString("Timee");
                        String d=scan.getString("Datee");
                        String ph=scan.getString("phone_no");
                        String bname=scan.getString("Bill_No");

                        String search_Details="\n\nTime : "+t+"\tDate: "+d+"\n\nBill No. : "+bname+"\n\nCustomer Name : "+name+"\n\nPhone No. : "+ph+
                                              "\n\nSub Product : "+pro+"\n\nAmount : "+amt+"\n\nNet Amount : "+netamt;

                        generate.setText("CUSTOMER DETAILS");
                        bill.setText(search_Details);
                    }
                    connection.close();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        imageIcon4=new ImageIcon("D:\\Sumit\\1600389-200.png");
        image4 = imageIcon4.getImage().getScaledInstance(40, 30, Image.SCALE_SMOOTH);
        visible4 = new ImageIcon(image4);
        cutbtn=new JButton(visible4);
        cutbtn.setBounds(1170, 90, 40, 30);
        cutbtn.setFont(new Font("Roman Times", Font.BOLD, 15));
        cutbtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        cutbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search.setText(" ");
            }
        });


        //Heading like Product
        product = new JLabel("PRODUCT SELECT AREA");
        product.setBounds(550, 100, 300, 30);
        product.setFont(new Font("Roman Times", Font.BOLD, 20));

        //List Of All Category
        String[] mp = {"Cosmetic", "Grocery", "Electronics"};

        String[] cosmeticSubCategories = {"Soaps", "Shampoos"};
        String[] grocerySubCategories = {"Vegetables", "Fruits"};
        String[] electronicsSubCategories = {"Phones", "Laptops"};

        String[] soapTypes =
                {"Bath Soaps", "Hand Soaps", "Face Soaps", "Body Soaps"};

        String[] bathSoaps = {"Dove", "Olay", "Lux", "Nivea", "Pears", "Dettol", "Cetaphil", "Irish Spring", "Yardley", "Neutrogena"};
        String[] faceSoaps = {"Cetaphil", "Neutrogena", "Dove", "Olay", "Nivea", "La Roche-Posay", "CeraVe", "Kiehl's", "Clinique", "Simple"};
        String[] bodySoaps = {"Dove", "Olay", "Nivea", "Lush", "Dial", "Irish Spring", "Aveeno", "Yardley", "Caress", "Method"};
        String[] handSoap = {"Dial", "Softsoap", "Mrs. Meyers", "Method", "Bath & Body Works", "Lysol", "Palmolive", "Dove", "Eo", "J.R. Watkins"};

        String[] shampooTypes =
                {"Hair Type Shampoos", "Conditioning Shampoos", "Therapeutic Shampoos",
                        "Natural & Organic Shampoos", "Luxury Shampoos"};

        String[] hairTypeShampoos = {"Pantene", "Head & Shoulders", "Tresemmé", "Herbal Essences", "Dove", "Garnier Fructis", "Redken", "Kerastase", "Biolage", "Paul Mitchell"};
        String[] conditioningShampoos = {"Pantene", "Tresemmé", "Dove", "Herbal Essences", "Garnier Fructis", "Redken", "Biolage", "Matrix", "Paul Mitchell", "Nexxus"};
        String[] therapeuticShampoos = {"Nizoral", "Selsun Blue", "Head & Shoulders Clinical Strength", "Neutrogena T/Gel", "Dermarest", "Sebamed", "Jason Dandruff Relief", "T/Sal Therapeutic Shampoo", "Coal Tar Shampoo", "Kiehl’s Scalp Purifying"};
        String[] natural_OrganicShampoos = {"Avalon Organics", "John Masters Organics", "Rahua", "Aveda", "100% Pure", "Burt's Bees", "SheaMoisture", "Innersense", "Acure", "Alba Botanica"};
        String[] luxuryShampoos = {"Oribe", "Kerastase", "Briogeo", "Philip Kingsley", "La Mer", "Amika", "Christophe Robin", "Rahua", "Wella Professionals", "Sachajuan"};


        String[] vegetableTypes =
                {"Leafy Vegetables", "Root Vegetables", "Fruiting Vegetables",
                        "Legumes & Pulses", "Cruciferous Vegetables",
                        "Herbs & Spices", "Gourds"};

        String[] rootVegetables = {"Potato", "Sweet Potato", "Carrot", "Radish", "Beetroot", "Onion", "Elephant Foot Yam"};
        String[] leafyVegetables = {"Spinach", "Fenugreek Leaves", "Mustard Greens", "Amaranth Leaves", "Kale", "Cabbage", "Water Spinach", "Sorrel Leaves", "Drumstick Leaves", ""};
        String[] fruitingVegetables = {"Tomato", "Eggplant/Brinjal", "Bell Pepper", "Okra", "Chili Peppers", "Pumpkin", "Bitter Gourd", "Cucumber", "Zucchini", "Bottle Gourd", "Ivy Gourd", "Snake Gourd", "Ridge Gourd"};
        String[] legumes_Pulses = {"Green Beans", "Peas", "Chickpeas", "Kidney Beans", "Black Gram", "Red Lentils", "Pigeon Peas", "Mung Beans", "Black-eyed Peas", "Horse Gram", "Lentils", "Split Bengal Gram", "Fava Beans"};
        String[] cruciferousVegetables = {"Cauliflower", "Broccoli", "Cabbage", "Kohlrabi", "Brussels Sprouts"};
        String[] herbs_Spices = {"Coriander", "Mint", "Basil", "Fennel", "Cumin", "Turmeric", "Ginger", "Fenugreek", "Curry Leaves", "Mustard Seeds", "Asafoetida", "Cloves", "Cinnamon", "Cardamom", "Bay Leaf", "Saffron", "Black Pepper", "Star Anise", "Nutmeg", "Caraway"};
        String[] gourds = {"Bottle Gourd", "Bitter Gourd", "Ridge Gourd", "Snake Gourd", "Ivy Gourd", "Sponge Gourd", "Ash Gourd", "Pumpkin", "Indian Round Gourd", "Pointed Gourd"};


        String[] fruitTypes =
                {"Citrus Fruits", "Tropical Fruits", "Stone Fruits", "Berries",
                        "Melons", "Dry Fruits and Nuts", "Exotic Fruits", "Seasonal Fruits"};

        String[] citrusFruits = {"Orange", "Lemon", "Lime", "Grapefruit", "Tangerine", "Clementine", "Mandarin", "Pomelo", "Sweet Lime (Mosambi)", "Bitter Orange"};
        String[] tropicalFruits = {"Mango", "Banana", "Pineapple", "Papaya", "Guava", "Lychee", "Jackfruit", "Coconut", "Dragon Fruit", "Passion Fruit", "Durian", "Rambutan", "Mangosteen", "Avocado", "Starfruit"};
        String[] stoneFruits = {"Cherry", "Peach", "Plum", "Apricot", "Nectarine", "Mango", "Olive", "Almond"};
        String[] berries = {"Strawberry", "Blueberry", "Raspberry", "Blackberry", "Cranberry", "Gooseberry", "Elderberry", "Boysenberry", "Mulberry", "Currant"};
        String[] melons = {"Watermelon", "Cantaloupe", "Honeydew", "Galia Melon", "Casaba Melon", "Winter Melon"};
        String[] dryFruits_Nuts = {"Almond", "Cashew", "Walnut", "Pistachio", "Raisin", "Date", "Fig", "Apricot", "Prune", "Hazelnut", "Macadamia Nut", "Brazil Nut"};
        String[] exoticFruits = {"Dragon Fruit", "Durian", "Rambutan", "Mangosteen", "Cherimoya", "Jackfruit", "Starfruit", "Buddha's Hand", "Horned Melon", "Salak (Snake Fruit)"};
        String[] seasonalFruits = {"Apple", "Orange", "Mango", "Strawberry", "Grapes", "Peach", "Plum", "Pomegranate", "Watermelon", "Kiwi"};


        String[] phonesTypes =
                {"Apple", "Samsung", "Xiaomi", "Oppo", "Vivo", "One Plus",
                        "Realme", "Motorola", "Sony", "Infinix", "Lava", "Nokia"};

        String[] apple_phone = {"iPhone 14 serie", "iPhone 15 series"};
        String[] samsung = {"Galaxy S24 series", "Galaxy Z Fold 5", "Galaxy Z Flip 5"};
        String[] xiaomi = {"Xiaomi 13T Pro", "Xiaomi Redmi Note 13 series", "Xiaomi Poco F5"};
        String[] oppo = {"Oppo Find X6 Pro", "Oppo Reno 10 series", "Oppo A78"};
        String[] vivo = {"Vivo X90 series", "Vivo V29", "Vivo Y100"};
        String[] realme = {"Realme GT 3", "Realme 12 Pro", "Realme Narzo 70"};
        String[] motorola = {"Motorola Edge 40", "Motorola Moto G73", "Motorola Razr 40"};
        String[] sony = {"Sony Xperia 1 V", "Sony Xperia 10 V"};
        String[] infinix = {"Infinix Zero Ultra", "Infinix Note 30"};
        String[] lava = {"Lava Agni 2 5G", "Lava X2"};
        String[] nokia = {"Nokia G42 5G", "Nokia X30 5G"};
        String[] oneplus = {"OnePlus 11T", "OnePlus Nord CE 3 Lite", "OnePlus 11R"};


        String[] laptopsTypes =
                {"Dell", "Apple", "Huawei", "HP", "Lenovo",
                        "Asus", "Acer", "Microsoft", "MSI", "Samsung"};

        String[] dell = {"Dell XPS 13", "Dell XPS 15", "Dell XPS 17", "Dell Inspiron 14", "Dell Inspiron 15", "Dell Alienware x17 R2", "Dell G15 Gaming"};
        String[] apple_laptop = {"MacBook Air (M2, 2022)", "MacBook Pro (M2, 2023, 14-inch and 16-inch)", "MacBook Pro (M1, 2021, 14-inch and 16-inch)"};
        String[] huawei = {"Huawei MateBook X Pro", "Huawei MateBook 14"};
        String[] hP = {"HP Spectre x360 14", "HP Envy 15", "HP Pavilion x360", "HP Omen 16", "HP Victus 16"};
        String[] lenovo = {"Lenovo ThinkPad X1 Carbon Gen 10", "Lenovo ThinkPad X1 Yoga Gen ", "Lenovo IdeaPad Flex 5", "Lenovo Legion 5 Pro", "Lenovo Yoga Slim 7i"};
        String[] asus = {"Asus ZenBook 14", "Asus ROG Zephyrus G14", "Asus TUF Gaming A15", "Asus VivoBook 15"};
        String[] acer = {"Acer Swift X", "Acer Aspire 7", "Acer Predator Helios 300", "Acer Nitro 5"};
        String[] microsoft = {"Surface Laptop 5", "Surface Pro 9", "Surface Book 3"};
        String[] mSI = {"MSI GF63 Thin", "MSI GS66 Stealth", "MSI Creator Z16"};
        String[] samsung_laptop = {"Samsung Galaxy Book 3 Pro", "Samsung Galaxy Book 3 360"};


        //Main Product
        mc = new JLabel("Select Main Category : ");
        mc.setBounds(10, 150, 180, 30);
        mc.setFont(new Font("Roman Times", Font.BOLD, 15));
        main_category = new JComboBox(mp);
        main_category.setBounds(180, 150, 120, 30);
        main_category.setFont(new Font("Roman Times", Font.BOLD, 15));
        main_category.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMainCategory = (String) main_category.getSelectedItem();
                sub_category.removeAllItems();

                if ("Cosmetic".equals(selectedMainCategory)) {
                    for (String subCategory : cosmeticSubCategories) {
                        sub_category.addItem(subCategory);
                    }
                } else if ("Grocery".equals(selectedMainCategory)) {
                    for (String subCategory : grocerySubCategories) {
                        sub_category.addItem(subCategory);
                    }
                } else if ("Electronics".equals(selectedMainCategory)) {
                    for (String subCategory : electronicsSubCategories) {
                        sub_category.addItem(subCategory);
                    }
                }

                if (sub_category.getItemCount() > 0) {
                    sub_category.setSelectedIndex(0);
                }
            }
        });

        //SubProduct Category
        sc = new JLabel("Select Sub Category : ");
        sc.setBounds(320, 150, 180, 30);
        sc.setFont(new Font("Roman Times", Font.BOLD, 15));
        sub_category = new JComboBox();
        sub_category.setBounds(480, 150, 120, 30);
        sub_category.setFont(new Font("Roman Times", Font.BOLD, 15));
        sub_category.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedSubCategory = (String) sub_category.getSelectedItem();
                product_category.removeAllItems();

                if ("Soaps".equals(selectedSubCategory)) {
                    for (String product : soapTypes) {
                        product_category.addItem(product);
                    }
                } else if ("Shampoos".equals(selectedSubCategory)) {
                    for (String product : shampooTypes) {
                        product_category.addItem(product);
                    }
                } else if ("Vegetables".equals(selectedSubCategory)) {
                    for (String product : vegetableTypes) {
                        product_category.addItem(product);
                    }
                } else if ("Fruits".equals(selectedSubCategory)) {
                    for (String product : fruitTypes) {
                        product_category.addItem(product);
                    }
                } else if ("Phones".equals(selectedSubCategory)) {
                    for (String product : phonesTypes) {
                        product_category.addItem(product);
                    }
                } else if ("Laptops".equals(selectedSubCategory)) {
                    for (String product : laptopsTypes) {
                        product_category.addItem(product);
                    }
                }
            }
        });

        //Product Category
        pc = new JLabel("Select Product : ");
        pc.setBounds(620, 150, 140, 30);
        pc.setFont(new Font("Roman Times", Font.BOLD, 15));
        product_category = new JComboBox();
        product_category.setBounds(740, 150, 170, 30);
        product_category.setFont(new Font("Roman Times", Font.BOLD, 15));
        product_category.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedProductCategory = (String) product_category.getSelectedItem();
                sub_product.removeAllItems();

                if ("Apple".equals(selectedProductCategory)) {
                    for (String subproduct : apple_phone) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Samsung".equals(selectedProductCategory)) {
                    for (String subproduct : samsung) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Xiaomi".equals(selectedProductCategory)) {
                    for (String subproduct : xiaomi) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Oppo".equals(selectedProductCategory)) {
                    for (String subproduct : oppo) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Vivo".equals(selectedProductCategory)) {
                    for (String subproduct : vivo) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Realme".equals(selectedProductCategory)) {
                    for (String subproduct : realme) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Motorola".equals(selectedProductCategory)) {
                    for (String subproduct : motorola) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Sony".equals(selectedProductCategory)) {
                    for (String subproduct : sony) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Infinix".equals(selectedProductCategory)) {
                    for (String subproduct : infinix) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Lava".equals(selectedProductCategory)) {
                    for (String subproduct : lava) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Nokia".equals(selectedProductCategory)) {
                    for (String subproduct : nokia) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("One Plus".equals(selectedProductCategory)) {
                    for (String subproduct : oneplus) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Dell".equals(selectedProductCategory)) {
                    for (String subproduct : dell) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Apple".equals(selectedProductCategory)) {
                    for (String subproduct : apple_laptop) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Huawei".equals(selectedProductCategory)) {
                    for (String subproduct : huawei) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("HP".equals(selectedProductCategory)) {
                    for (String subproduct : hP) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Lenovo".equals(selectedProductCategory)) {
                    for (String subproduct : lenovo) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Asus".equals(selectedProductCategory)) {
                    for (String subproduct : asus) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Acer".equals(selectedProductCategory)) {
                    for (String subproduct : acer) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Microsoft".equals(selectedProductCategory)) {
                    for (String subproduct : microsoft) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("MSI".equals(selectedProductCategory)) {
                    for (String subproduct : mSI) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Samsung".equals(selectedProductCategory)) {
                    for (String subproduct : samsung_laptop) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Bath Soaps".equals(selectedProductCategory)) {
                    for (String subproduct : bathSoaps) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Body Soaps".equals(selectedProductCategory)) {
                    for (String subproduct : bodySoaps) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Hand Soaps".equals(selectedProductCategory)) {
                    for (String subproduct : handSoap) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Face Soaps".equals(selectedProductCategory)) {
                    for (String subproduct : faceSoaps) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Hair Type Shampoos".equals(selectedProductCategory)) {
                    for (String subproduct : hairTypeShampoos) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Conditioning Shampoos".equals(selectedProductCategory)) {
                    for (String subproduct : conditioningShampoos) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Therapeutic Shampoos".equals(selectedProductCategory)) {
                    for (String subproduct : therapeuticShampoos) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Natural & Organic Shampoos".equals(selectedProductCategory)) {
                    for (String subproduct : natural_OrganicShampoos) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Luxury Shampoos".equals(selectedProductCategory)) {
                    for (String subproduct : luxuryShampoos) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Leafy Vegetables".equals(selectedProductCategory)) {
                    for (String subproduct : leafyVegetables) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Root Vegetables".equals(selectedProductCategory)) {
                    for (String subproduct : rootVegetables) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Fruiting Vegetables".equals(selectedProductCategory)) {
                    for (String subproduct : fruitingVegetables) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Legumes & Pulses".equals(selectedProductCategory)) {
                    for (String subproduct : legumes_Pulses) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Cruciferous Vegetables".equals(selectedProductCategory)) {
                    for (String subproduct : cruciferousVegetables) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Herbs & Spices".equals(selectedProductCategory)) {
                    for (String subproduct : herbs_Spices) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Gourds".equals(selectedProductCategory)) {
                    for (String subproduct : gourds) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Citrus Fruits".equals(selectedProductCategory)) {
                    for (String subproduct : citrusFruits) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Tropical Fruits".equals(selectedProductCategory)) {
                    for (String subproduct : tropicalFruits) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Stone Fruits".equals(selectedProductCategory)) {
                    for (String subproduct : stoneFruits) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Berries".equals(selectedProductCategory)) {
                    for (String subproduct : berries) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Melons".equals(selectedProductCategory)) {
                    for (String subproduct : melons) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Dry Fruits and Nuts".equals(selectedProductCategory)) {
                    for (String subproduct : dryFruits_Nuts) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Exotic Fruits".equals(selectedProductCategory)) {
                    for (String subproduct : exoticFruits) {
                        sub_product.addItem(subproduct);
                    }
                } else if ("Seasonal Fruits".equals(selectedProductCategory)) {
                    for (String subproduct : seasonalFruits) {
                        sub_product.addItem(subproduct);
                    }
                }
            }
        });

        //SubProduct Category
        spc = new JLabel("Select Sub Product : ");
        spc.setBounds(930, 150, 170, 30);
        spc.setFont(new Font("Roman Times", Font.BOLD, 15));
        sub_product = new JComboBox();
        sub_product.setBounds(1090, 150, 170, 30);
        sub_product.setFont(new Font("Roman Times", Font.BOLD, 15));

        // Price PerProduct
        each = new JLabel("Price Per Product : ");
        each.setBounds(10, 200, 150, 30);
        each.setFont(new Font("Roman Times", Font.BOLD, 15));
        each_val = new JTextField();
        each_val.setBounds(160, 200, 100, 30);
        each_val.setFont(new Font("Roman Times", Font.BOLD, 15));

        //Stock Options
        stock = new JLabel("In Stock : ");
        stock.setBounds(10, 250, 100, 30);
        stock.setFont(new Font("Roman Times", Font.BOLD, 15));
        stockno = new JTextField("500");
        stockno.setBounds(90, 250, 100, 30);
        stockno.setFont(new Font("Roman Times", Font.BOLD, 15));
        stockno.setEditable(false);

        //Quantity  Options
        quantity = new JLabel("Quantity : ");
        quantity.setBounds(250, 250, 100, 30);
        quantity.setFont(new Font("Roman Times", Font.BOLD, 15));
        quainput = new JTextField();
        quainput.setBounds(340, 250, 200, 30);
        quainput.setFont(new Font("Roman Times", Font.BOLD, 15));

        //Discount Options
        discount = new JLabel("Discount : ");
        discount.setBounds(10, 300, 100, 30);
        discount.setFont(new Font("Roman Times", Font.BOLD, 15));

        discount_value = new JTextField();
        discount_value.setBounds(90, 300, 100, 30);
        discount_value.setFont(new Font("Roman Times", Font.BOLD, 15));
        discount_value.setEditable(false);


        //Price Options
        price = new JLabel("Price : ");
        price.setBounds(250, 300, 100, 30);
        price.setFont(new Font("Roman Times", Font.BOLD, 15));
        price_value = new JTextField();
        price_value.setBounds(340, 300, 200, 30);
        price_value.setFont(new Font("Roman Times", Font.BOLD, 15));
        price_value.setEditable(false);

        //Heading Calculator
        cal = new JLabel("CALCULATOR");
        cal.setBounds(50, 350, 150, 30);
        cal.setFont(new Font("Roman Times", Font.BOLD, 20));

        //Calculation Product Price
        total = new JButton("Total");
        total.setBounds(340, 350, 200, 30);
        total.setFont(new Font("Roman Times", Font.BOLD, 15));
        total.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        total.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Change Value Of Stack And Calculating Value of Price
                String s1 = quainput.getText();
                double b = parseDouble(s1);
                if (b >= 500) {
                    JOptionPane.showMessageDialog(jFrame, "This Is Warning Message\n Quantity Is Always less and Equal 500\nDiscount Box Never Empty", "Message", JOptionPane.WARNING_MESSAGE);
                } else {
                    double c = 500;
                    double resultt = c - b;
                    stockno.setText(String.valueOf(resultt));

                    //Changes In Discount Value ("Cosmetic" "Grocery" "Electronics")
                    change_dis= String.valueOf(main_category.getItemAt(main_category.getSelectedIndex()));
                    if("Cosmetic".equals(change_dis)){
                        discount_value.setText("10");
                    }
                    else if ("Grocery".equals(change_dis)) {
                        discount_value.setText("15");
                    }
                    else if ("Electronics".equals(change_dis)) {
                        discount_value.setText("20");
                    }
                    String s2 = discount_value.getText();
                    double d = parseDouble(s2);

                    String s3 = each_val.getText();
                    double p = parseDouble(s3);
                    Double price_val = (b * p) * (1 - (d / 100));
                    price_value.setText(String.valueOf(price_val));
                }
            }
        });

        //Add More Item In List/bill
        add_more = new JToggleButton("Add Item");
        add_more.setBounds(340, 400, 200, 30);
        add_more.setFont(new Font("Roman Times", Font.BOLD, 15));
        add_more.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add_more.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (add_more.isSelected()) {
                    quainput.setText("");
                    discount_value.setText("");
                    price_value.setText("");
                    stockno.setText("500");
                    each_val.setText("");
                    sub_category.removeAllItems();
                    product_category.removeAllItems();
                    sub_product.removeAllItems();
                }
                else {
                    detail_maincat = String.valueOf(main_category.getItemAt(main_category.getSelectedIndex()));
                    detail_subcat = String.valueOf(sub_category.getItemAt(sub_category.getSelectedIndex()));
                    detail_product = String.valueOf(product_category.getItemAt(product_category.getSelectedIndex()));
                    detail_subpro = String.valueOf(sub_product.getItemAt(sub_product.getSelectedIndex()));
                    quatity = String.valueOf(quainput.getText());
                    discountt = String.valueOf(discount_value.getText());
                    ppp = String.valueOf(each_val.getText());
                    pricee = String.valueOf(price_value.getText());

                    String itemDetails = "\nCategory: " + detail_maincat + "\t\tSub Category: " + detail_subcat +
                            "\nProduct : " + detail_product + "\t\tSubProduct: " + detail_subpro +
                            "\nQuantity : " + quatity + "\t\t\t" +
                            "Discount : " + discountt + "%" +
                            "\nPrice Per Product : " + ppp +
                            "\n\t\t\tAmount : " + pricee + "\n\n";

                    itemDetailsList.add(itemDetails);
                    choose_product.add(detail_subpro);
                    choose_price.add(pricee);

                    //Add In Cart
                    itemDetailsCart.add(itemDetails);
                    StringBuilder cart = new StringBuilder();
                    for (String cartList:itemDetailsCart) {
                        cart.append(cartList);
                        String[] lines = cartList.split("\n");
                        for (String line : lines) {
                            if (line.contains("Amount :")) {
                                String priceStr = line.split(":")[1].trim();
                                double priceee = Double.parseDouble(priceStr);
                                final_pricee += priceee;
                                break;
                            }
                        }
                    }
                    cart.append("\n----------------------------------------------------------------------------\n" +
                            "\t\t\tNet Amount :" + final_pricee +
                            "\n----------------------------------------------------------------------------\n");

                    generate.setText("ADD IN CART");
                    bill.setText(cart.toString());

                }
            }
        });



        //Clear Entered Items
        clear = new JButton("Clear");
        clear.setBounds(340, 450, 200, 30);
        clear.setFont(new Font("Roman Times", Font.BOLD, 15));
        clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                icustomer_name.setText("");
                iphone_no.setText("");
                quainput.setText(" ");
                discount_value.setText("");
                price_value.setText(" ");
                stockno.setText("500");
                each_val.setText(" ");
                sub_category.removeAllItems();
                product_category.removeAllItems();
                sub_product.removeAllItems();
                itemDetailsList.clear();
                choose_price.clear();
                choose_product.clear();
                itemDetailsCart.clear();
                final_price=0;
                final_pricee=0;

            }
        });

        //Generate Bill
        generate = new JLabel("CUSTOMER BILL");
        generate.setBounds(850, 200, 300, 30);
        generate.setFont(new Font("Roman Times", Font.BOLD, 20));

        bill = new TextArea();
        bill.setBounds(700, 230, 430, 340);
        bill.setFont(new Font("Courier New", Font.BOLD, 15));
        bill.setEditable(false);
        bill.setBackground(Color.white);


        gb = new JButton("Genrate Bill");
        gb.setBounds(340, 500, 200, 30);
        gb.setFont(new Font("Roman Times", Font.BOLD, 15));
        gb.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        gb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bn = ibill_no.getText();
                cn = icustomer_name.getText();
                pn = iphone_no.getText();

                date = LocalDate.now();
                format_date = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                time = LocalTime.now();
                format_time = DateTimeFormatter.ofPattern("HH:mm:ss");
                current_time = time.format(format_time);
                current_date = date.format(format_date);
                String storeInfo = "\n\t\t\tBig Mall Super Mart\n" +
                        "\tPhone No.:1234567890\tMumbai,Maharashtra\n\n" +
                        "\tTime : " + current_time + "\t\tDate : " + current_date + "\n" +
                        "----------------------------------------------------------------------------\n";

                String customerInfo = "Bill No. : " + bn + "\nCustomer Name : " + cn + "\nPhone No. : " + pn + "\n";

                StringBuilder finalBill = new StringBuilder();
                finalBill.append(storeInfo).append(customerInfo);

                for (String itemDetail : itemDetailsList) {
                    finalBill.append(itemDetail);
                    String[] lines = itemDetail.split("\n");
                    for (String line : lines) {
                        if (line.contains("Amount :")) {
                            String priceStr = line.split(":")[1].trim();
                            double priceee = Double.parseDouble(priceStr);
                            final_price += priceee;
                            break;
                        }
                    }
                }

                finalBill.append("\n----------------------------------------------------------------------------\n" +
                        "\t\t\tNet Amount :" + final_price +
                        "\n----------------------------------------------------------------------------\n");

                generate.setText("CUSTOMER BILL");
                bill.setText(finalBill.toString());


                // Saprate Value  taking  for data base
                finalsubproduct = new StringBuilder();
                for (String allproduct : choose_product) {
                    finalsubproduct.append(allproduct + " ,");
                }
                finalchooseprice = new StringBuilder();
                for (String allprice : choose_price) {
                    finalchooseprice.append(allprice + " ,");
                }
            }
        });

        add_cart = new JToggleButton("Add In Data Base");
        add_cart.setBounds(340, 550, 200, 30);
        add_cart.setFont(new Font("Roman Times", Font.BOLD, 15));
        add_cart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add_cart.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(add_cart.isSelected()){
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                        PreparedStatement preparedStatement = null;
                        String quary = "INSERT INTO daata(Customer_Name, phone_no, Product, Amount, Net_Amount, Timee,Datee)" +
                                "VALUES(?,?,?,?,?,?,?)";
                        preparedStatement = connection.prepareStatement(quary);
                        preparedStatement.setString(1, cn);
                        preparedStatement.setString(2, pn);
                        preparedStatement.setString(3, String.valueOf(finalsubproduct));
                        preparedStatement.setString(4, String.valueOf(finalchooseprice));
                        preparedStatement.setString(5, String.valueOf(final_price));
                        preparedStatement.setString(6, current_time);
                        preparedStatement.setString(7, current_date);

                        int status = preparedStatement.executeUpdate();
                        if (status > 0) {
                            JOptionPane.showMessageDialog(jFrame, "Bill Data Inserted In DataBase Successfully", "Message", JOptionPane.INFORMATION_MESSAGE);
                            System.out.println("\n*****Bill Data Inserted In DataBase Successfully*****");
                        }
                        connection.close();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(jFrame, ex, "Message", JOptionPane.WARNING_MESSAGE);
                    }
                }
                else {

                    //Taking Bill No. From Data Base
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/dummy", "root", "12345678");
                        String quaryy = "SELECT MAX(Bill_No) +1 AS bill_no FROM daata";
                        PreparedStatement preparedStatement = connection.prepareStatement(quaryy);

                        ResultSet scan = preparedStatement.executeQuery();
                        if (scan.next()) {
                            id_bill_no=scan.getInt("bill_no");
                            ibill_no.setText(String.valueOf(id_bill_no));
                            System.out.println("ID Of Bill = "+id_bill_no);
                        }
                        else{
                            ibill_no.setText("1");
                        }
                        connection.close();
                    }
                    catch (Exception ex) {
                        System.out.println(ex);
                    }

                }
            }
        });
        exit = new JButton("Exit");
        exit.setBounds(340, 600, 200, 30);
        exit.setFont(new Font("Roman Times", Font.BOLD, 15));
        exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });


        //Clear Bill
        clr_bill = new JButton("Clear Bill");
        clr_bill.setBounds(750, 580, 150, 30);
        clr_bill.setFont(new Font("Roman Times", Font.BOLD, 15));
        clr_bill.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clr_bill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bill.setText(" ");
            }
        });

        //Print Bill
        print = new JButton("Print Bill");
        print.setBounds(950, 580, 150, 30);
        print.setFont(new Font("Roman Times", Font.BOLD, 15));
        print.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        print.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save PDF file");
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files (*.pdf)", "pdf");
                fileChooser.setFileFilter(filter);

                int userSelection = fileChooser.showSaveDialog(jFrame);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    String filePath = fileChooser.getSelectedFile().getAbsolutePath();

                    if (!filePath.toLowerCase().endsWith(".pdf")) {
                        filePath += ".pdf";
                    }

                    try {
                        Document document = new Document(PageSize.A4);
                        PdfWriter.getInstance(document, new FileOutputStream(filePath));
                        document.open();


                        String[] lines = bill.getText().split("\\n");
                        for (String line : lines) {
                            Paragraph paragraph = new Paragraph(line);
                            document.add(paragraph);
                        }

                        document.close();
                        JOptionPane.showMessageDialog(jFrame, "PDF generated successfully at: " + filePath);
                    } catch (DocumentException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error generating PDF: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(jFrame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

}


    public  void calculator(){

        //Result Display
        jTextField=new JTextField();
        jTextField.setBounds(10,390,230,30);
        jTextField.setFont(new Font("Roman Times", Font. BOLD,15));
        jTextField.setBackground(Color.gray);
        jTextField.setForeground(Color.white);
        jTextField.setEditable(false);

        //First Row
        btn1=new JButton("1");
        btn1.setBounds(10,430,50,30);
        btn1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn1.addActionListener(this);
        btn2=new JButton("2");
        btn2.setBounds(70,430,50,30);
        btn2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn2.addActionListener(this);
        btn3=new JButton("3");
        btn3.setBounds(130,430,50,30);
        btn3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn3.addActionListener(this);
        btnplus=new JButton("+");
        btnplus.setBounds(190,430,50,30);
        btnplus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnplus.addActionListener(this);

        //Second Row
        btn4=new JButton("4");
        btn4.setBounds(10,470,50,30);
        btn4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn4.addActionListener(this);
        btn5=new JButton("5");
        btn5.setBounds(70,470,50,30);
        btn5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn5.addActionListener(this);
        btn6=new JButton("6");
        btn6.setBounds(130,470,50,30);
        btn6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn6.addActionListener(this);
        btnminus=new JButton("-");
        btnminus.setBounds(190,470,50,30);
        btnminus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnminus.addActionListener(this);

        //Third Row
        btn7=new JButton("7");
        btn7.setBounds(10,510,50,30);
        btn7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn7.addActionListener(this);
        btn8=new JButton("8");
        btn8.setBounds(70,510,50,30);
        btn8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn8.addActionListener(this);
        btn9=new JButton("9");
        btn9.setBounds(130,510,50,30);
        btn9.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn9.addActionListener(this);
        btnmult=new JButton("*");
        btnmult.setBounds(190,510,50,30);
        btnmult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnmult.addActionListener(this);

        //Fourth Row
        btn00=new JButton("00");
        btn00.setBounds(10,550,50,30);
        btn00.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn00.addActionListener(this);
        btn0=new JButton("0");
        btn0.setBounds(70,550,50,30);
        btn0.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn0.addActionListener(this);
        btndeci=new JButton(".");
        btndeci.setBounds(130,550,50,30);
        btndeci.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btndeci.addActionListener(this);
        btndiv=new JButton("/");
        btndiv.setBounds(190,550,50,30);
        btndiv.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btndiv.addActionListener(this);

        //Fifth Row
        btnequal=new JButton("=");
        btnequal.setBounds(10,590,50,30);
        btnequal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnequal.addActionListener(this);
        btncut=new JButton("X");
        btncut.setBounds(70,590,50,30);
        btncut.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btncut.addActionListener(this);
        btndel=new JButton("DEL");
        btndel.setBounds(130,590,110,30);
        btndel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btndel.addActionListener(this);

    }
    public void jframe_add_calulator(){
        jFrame.add(jTextField);
        jFrame.add(btn1);
        jFrame.add(btn2);
        jFrame.add(btn3);
        jFrame.add(btnplus);
        jFrame.add(btn4);
        jFrame.add(btn5);
        jFrame.add(btn6);
        jFrame.add(btnminus);
        jFrame.add(btn7);
        jFrame.add(btn8);
        jFrame.add(btn9);
        jFrame.add(btnmult);
        jFrame.add(btn00);
        jFrame.add(btn0);
        jFrame.add(btndeci);
        jFrame.add(btndiv);
        jFrame.add(btnequal);
        jFrame.add(btncut);
        jFrame.add(btndel);
    }
    public  void jframeAdd() {
        jFrame.add(cutbtn);
        jFrame.add(popup);
        jFrame.add(menuBar);
        jFrame.setJMenuBar(menuBar);
        jFrame.add(clr_bill);
        jFrame.add(print);
        jFrame.add(each);
        jFrame.add(each_val);
        jFrame.add(generate);
        jFrame.add(bill);
        jFrame.add(add_more);
        jFrame.add(add_cart);
        jFrame.add(clear);
        jFrame.add(total);
        jFrame.add(gb);
        jFrame.add(exit);
        jFrame.add(cal);
        jFrame.add(discount);
        jFrame.add(discount_value);
        jFrame.add(price);
        jFrame.add(price_value);
        jFrame.add(stock);
        jFrame.add(stockno);
        jFrame.add(quantity);
        jFrame.add(quainput);
        jFrame.add(mc);
        jFrame.add(sc);
        jFrame.add(pc);
        jFrame.add(spc);
        jFrame.add(main_category);
        jFrame.add(sub_category);
        jFrame.add(product_category);
        jFrame.add(sub_product);
        jFrame.add(product);
        jFrame.add(search);
        jFrame.add(seachlable);
        jFrame.add(srchbtn);
        jFrame.add(ibill_no);
        jFrame.add(icustomer_name);
        jFrame.add(iphone_no);
        jFrame.add(bill_no);
        jFrame.add(customer_name);
        jFrame.add(phone_no);
        jFrame.add(detail);
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int a=JOptionPane.showConfirmDialog(jFrame,"Are You Want To Exit");
                if(a==JOptionPane.YES_OPTION){
                    jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                if(a==JOptionPane.NO_OPTION){
                    jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
                if(a==JOptionPane.CANCEL_OPTION){
                    jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });

        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    String exp=" ";
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==cut){
            icustomer_name.cut();
        }
        if(e.getSource()==copy){
            icustomer_name.copy();
        }
        if(e.getSource()==paste){
            search.paste();
        }
        if(e.getSource()==selectall){
            icustomer_name.selectAll();
        }

        if(e.getSource()==btn1) {
            exp+=btn1.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn2) {
            exp+=btn2.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn3) {
            exp+=btn3.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btnplus) {
            exp+=btnplus.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn4) {
            exp+=btn4.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn5) {
            exp+=btn5.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn6) {
            exp+=btn6.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btnminus) {
            exp+=btnminus.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn7) {
            exp+=btn7.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn8) {
            exp+=btn8.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn9) {
            exp+=btn9.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btnmult) {
            exp+=btnmult.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn00) {
            exp+=btn00.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btn0) {
            exp+=btn0.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btndeci) {
            exp+=btndel.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btndiv) {
            exp+=btndiv.getText();
            jTextField.setText(exp);
        }
        if(e.getSource()==btnequal) {
            int ans=solve(exp);
            exp= String.valueOf(ans);
            jTextField.setText(String.valueOf(ans));
        }
        if(e.getSource()==btncut) {
            exp=exp.substring(0,exp.length()-1);
            jTextField.setText(exp);
        }
        if(e.getSource()==btndel) {
            exp=" ";
            jTextField.setText(exp);
        }
    }
      public int solve(String exp) {

        return InfixToPostfixDummy.evaluate(exp);
    }
}

public class Billing_Generating_System {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Details::new);
        Details details=new Details();
//        details.taking_Customer_Details();
//        details.calculator();
//        details.jframe_add_calulator();
//        details.jframeAdd();
        details.login_com();
    }
}