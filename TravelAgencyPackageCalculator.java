// GROUP 14
// TRAVEL AGENCY PACKAGE CALCULATOR
// 1211205395 BRIAN LEE CHONG MING
// 1211205392 CHU WEI WANG
// 1211205394 JAMES LEW MING REN 
// 1211205588 YU BUI XUAN

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TravelAgencyPackageCalculator extends JFrame 
{
    // =================== DECLARATION ======================= //

    // Main Title Declaration
    JLabel MainTitle, MainIcon;
    ImageIcon MI = new ImageIcon(getClass().getResource("MainIcon.png"));

    // Packages Declaration
    JLabel packageLabel, Atext, Ctext,vehicleLabel,TransPriceLabel;
    JLabel SpecialOfferLabel;
    JLabel selectedPackageLabel;
    JComboBox<String> packageComboBox;
    JButton selectButton;
    JTextArea selectedPackageTextArea, cardetail , resit;
    JTextField numa, numc;
    JRadioButton bt1 ,bt2 , bt3;
    JLabel lb1 , lb2 ,lb3 ,lb4,lb5,lb6;
    JTextArea ta1 , ta2 ,ta3 ,ta4 ,ta5,ta6;
    double carprice;

    String[] packageNames;
    double[] packagePrices;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new TravelAgencyPackageCalculator();
            }
        });
    }

    public TravelAgencyPackageCalculator() 
    {
        Font f1 = new Font("Dialog", Font.BOLD | Font.ITALIC, 29); // SET FONT
        Font f2 = new Font("Serif", Font.BOLD, 26);
        Font f3 = new Font("Arial",Font.BOLD,10);

        
        setTitle("Travel Agency Package Calculator");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // =================== SET TEXT AND ICON ======================= //

        MainIcon = new JLabel(MI);
        MainTitle = new JLabel("Travelling Packages Calculator For You");
        MainTitle.setFont(f1);

        SpecialOfferLabel = new JLabel("<html><u>SPECIAL OFFERS</u></html>");
        SpecialOfferLabel.setFont(f2);
        SpecialOfferLabel.setForeground(Color.BLUE);

        packageLabel = new JLabel("Packages:");
        selectedPackageLabel = new JLabel("Selected Package:");
        Atext = new JLabel("No. of Adult/s:");
        Ctext = new JLabel("No. of Child/s:");
        numa = new JTextField(6);
        numc = new JTextField(6);
        vehicleLabel = new JLabel("Transportation for rent:");
        cardetail = new JTextArea(6, 30);
        TransPriceLabel = new JLabel("Transportation price:");
        cardetail.setText("1.Comfort Van (8-Seater) -  RM 1800\n\n2.MPV Car -   RM 1200\n\n3.None");
        cardetail.setEditable(false);
        packageComboBox = new JComboBox<>();
        selectButton = new JButton("Calculate");
        selectedPackageTextArea = new JTextArea(6, 30);
        selectedPackageTextArea.setEditable(false);
        resit = new JTextArea(10, 40);
        resit.setEditable(false);
        
        lb1 = new JLabel("Selected Package :");
        ta1 = new JTextArea(1, 15);
        ta1.setFont(f3);
        ta1.setEditable(false);

        lb2 = new JLabel("Total Adult Price :");
        ta2 = new JTextArea(1, 15);
        ta2.setFont(f3);
        ta2.setEditable(false);

        lb3 = new JLabel("Total Child Price :");
        ta3 = new JTextArea(1, 15);
        ta3.setFont(f3);
        ta3.setEditable(false);

        lb4 = new JLabel("Duration :");
        ta4 = new JTextArea(1,15);
        ta4.setFont(f3);
        ta4.setEditable(false);

        lb5 = new JLabel("Selected Vehicle :");
        ta5 = new JTextArea(1, 15);
        ta5.setFont(f3);
        ta5.setEditable(false);

        lb6 = new JLabel("Total Price :");
        ta6 = new JTextArea(1, 15);
        ta6.setFont(f3);
        ta6.setEditable(false);

        bt1 = new JRadioButton("Comfort Van (8-Seater)");
        bt2 = new JRadioButton("MPV Car");
        bt3 = new JRadioButton("None");

        ButtonGroup bg = new ButtonGroup();
        bg.add(bt1);
        bg.add(bt2);
        bg.add(bt3);


        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        // =================== DISPLAY MENUBAR AND MAIN TITLE ======================= //

        // MenuBar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        setJMenuBar(menuBar);

        // Main Title
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Border line for main title
        topPanel.add(MainIcon);
        topPanel.add(MainTitle);

        // -------------------- DISPLAY MAIN TITLE BORDER --------------------------- //
        
        add(topPanel, BorderLayout.NORTH);   
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Special Offers");
        titledBorder.setTitleJustification(TitledBorder.LEFT);
        titledBorder.setTitlePosition(TitledBorder.TOP);
        JPanel specialOfferPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        specialOfferPanel.add(SpecialOfferLabel);

        // Add both panels to a common panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(topPanel);
        titlePanel.add(specialOfferPanel);

        add(titlePanel, BorderLayout.NORTH);

        // =================== DISPLAY PACKAGES , NO OF ADULT & CHILD and MORE ======================= //

        JPanel packagePanel = new JPanel();
        packagePanel.setLayout(new GridBagLayout());

        GridBagConstraints post = new GridBagConstraints();
        post.anchor = GridBagConstraints.WEST;
        post.insets = new Insets(10, 10, 0, 10); // Spacing around components

        // Positioning components in PackagePanel
        post.gridx = 0;
        post.gridy = 0;
        packagePanel.add(packageLabel, post);

        post.gridx = 1;
        post.gridy = 0;
        packagePanel.add(packageComboBox, post);

        post.gridx = 0;
        post.gridy = 1;
        packagePanel.add(selectedPackageLabel, post);

        post.gridx = 1;
        post.gridy = 1;
        packagePanel.add(selectedPackageTextArea,post);

        post.gridx = 0;
        post.gridy = 2;
        packagePanel.add(Atext,post);

        post.gridx = 1;
        post.gridy = 2;
        packagePanel.add(numa,post);

        post.gridx = 0;
        post.gridy = 3;
        packagePanel.add(Ctext, post);

        post.gridx = 1;
        post.gridy = 3;
        packagePanel.add(numc, post);

        post.gridx = 0;
        post.gridy = 4;
        packagePanel.add(vehicleLabel, post);

        JPanel radiobtn = new JPanel(new FlowLayout(FlowLayout.LEFT));

        ImageIcon Van = new ImageIcon(getClass().getResource("van.png"));
        JLabel VanIcon = new JLabel(Van);
        radiobtn.add(bt1);
        radiobtn.add(VanIcon);

        ImageIcon Mpv = new ImageIcon(getClass().getResource("mpv.png"));
        JLabel MpvIcon = new JLabel(Mpv);
        radiobtn.add(bt2);
        radiobtn.add(MpvIcon);
        
        ImageIcon Na = new ImageIcon(getClass().getResource("None.png"));
        JLabel NoneIcon = new JLabel(Na);
        radiobtn.add(bt3);
        radiobtn.add(NoneIcon);
        
        post.gridx = 1;
        post.gridy = 4;
        packagePanel.add(radiobtn, post);

        post.gridx = 0;
        post.gridy = 5;
        packagePanel.add(TransPriceLabel,post);

        post.gridx = 1;
        post.gridy = 5;
        packagePanel.add(cardetail,post);

        post.gridx = 2;
        post.gridy = 5;
        packagePanel.add(selectButton,post);

        JPanel display1 = new JPanel();
        display1.setLayout(new BorderLayout());
        display1.add(packagePanel,BorderLayout.NORTH);
        add(display1, BorderLayout.WEST);   
        
        // =================== DISPLAY SUMMARY ======================= //

        GridBagConstraints place = new GridBagConstraints();
        place.anchor = GridBagConstraints.WEST;
        place.insets = new Insets(10, 10, 10, 10); // Spacing around components

        // sumPanel with GridBagLayout
        JPanel sumPanel = new JPanel(new GridBagLayout());
        TitledBorder sumtitle = BorderFactory.createTitledBorder("SUMMARY");
        sumtitle.setTitleFont(f2);
        sumtitle.setTitleColor(Color.BLUE);
        sumPanel.setBorder(sumtitle);

        place.gridx = 0;
        place.gridy = 0;
        sumPanel.add(lb1, place);

        place.gridx = 1;
        place.gridy = 0;
        sumPanel.add(ta1, place);

        place.gridx = 0;
        place.gridy = 1;
        sumPanel.add(lb4, place);

        place.gridx = 1;
        place.gridy = 1;
        sumPanel.add(ta4, place);

        place.gridx = 0;
        place.gridy = 2;
        sumPanel.add(lb2, place);

        place.gridx = 1;
        place.gridy = 2;
        sumPanel.add(ta2, place);

        place.gridx = 0;
        place.gridy = 3;
        sumPanel.add(lb3, place);

        place.gridx = 1;
        place.gridy = 3;
        sumPanel.add(ta3, place);

        place.gridx = 0;
        place.gridy = 4;
        sumPanel.add(lb5, place);

        place.gridx = 1;
        place.gridy = 4;
        sumPanel.add(ta5, place);

        place.gridx = 0;
        place.gridy = 5;
        sumPanel.add(lb6, place);

        place.gridx = 1;
        place.gridy = 5;
        sumPanel.add(ta6, place);

        add(sumPanel, BorderLayout.SOUTH);

        setVisible(true);

       loadPackages(); // Calling Function

        // ActionListener for the packageComboBox
        packageComboBox.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                calculatePackage();
            }
        });

        // ActionListener for the selectButton
        selectButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                calculateTotal();
            }
        });
        
    }

    private void loadPackages() 
    {
        try
        {
            BufferedReader packageReader = new BufferedReader(new FileReader("package.txt"));
            BufferedReader priceReader = new BufferedReader(new FileReader("packagePrice.txt"));

            String packageLine;
            String priceLine;
            int packageCount = 0;

            while ((packageLine = packageReader.readLine()) != null && (priceLine = priceReader.readLine()) != null) 
            {
                String[] packageData = packageLine.split("\\s+");
                String[] priceData = priceLine.split("\\s+");

                if (packageData.length > 0 && priceData.length > 0) 
                {
                    packageComboBox.addItem(packageData[0]);
                    packageCount++;
                }
            }

            packageReader.close();
            priceReader.close();

            packageNames = new String[packageCount];
            packagePrices = new double[packageCount];

            packageReader = new BufferedReader(new FileReader("package.txt"));
            priceReader = new BufferedReader(new FileReader("packagePrice.txt"));

            int index = 0;
            while ((packageLine = packageReader.readLine()) != null && (priceLine = priceReader.readLine()) != null) 
            {
                String[] packageData = packageLine.split("\\s+");
                String[] priceData = priceLine.split("\\s+");

                if (packageData.length > 0 && priceData.length > 0) 
                {
                    packageNames[index] = packageData[0];
                    if (packageData.length > 0 && priceData.length > 0)
                        packagePrices[index] = Double.parseDouble(priceData[0]);
                    index++;
                }
            }

            packageReader.close();
            priceReader.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    private void showErrorDialog(String message) 
    {
        JOptionPane.showMessageDialog(this, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    private void calculatePackage() 
    {
        int selectedIndex = packageComboBox.getSelectedIndex();
        if (selectedIndex >= 0) 
        {
            String selectedPackage = packageNames[selectedIndex];
            double selectedPrice = packagePrices[selectedIndex];
            try 
            {
                double total_a = selectedPrice;
                double total_c = selectedPrice * 0.70;
                
                selectedPackageTextArea.setText(
                    "Package: " + selectedPackage +
                    "\n\nAdult Price/person: RM" + String.format("%.2f", total_a) +
                    "\n\nChild Price/person: RM" + String.format("%.2f", total_c) +
                    "\n\nDuration: 4D3N"
                );
            } 
            catch (NumberFormatException e) 
            {
                // Exception handling when User does not enter input / wrong input
                JOptionPane.showMessageDialog(this, "Please enter only numeric values for Adult and Child! DONT LEAVE BLANK!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void calculateTotal() 
    {
        int selectedIndex = packageComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedPackage = packageNames[selectedIndex];
            double selectedPrice = packagePrices[selectedIndex];
            
            try 
            {
                double enter_a = Double.parseDouble(numa.getText());
                double enter_c = Double.parseDouble(numc.getText());
                
                if (!bt1.isSelected() && !bt2.isSelected() && !bt3.isSelected()) 
                {
                    // Error dialog will be displayed if Transportation option is not choosen
                    JOptionPane.showMessageDialog(this, "Please select a transportation option!", "Transportation Selection", JOptionPane.ERROR_MESSAGE);
                    return; // Stop execution
                }
                
                double total_a = selectedPrice * enter_a;
                double total_c = enter_c * (selectedPrice * 0.70);
                double total = total_a + total_c;
                
                if (bt1.isSelected()) 
                {
                    total += 1800; // Van Price
                } 
                else if (bt2.isSelected()) 
                {
                    total += 1200; // MPV car Price
                }
                
                // Summary Text Area
                ta1.setText(selectedPackage);
                ta2.setText("RM" + String.format("%.2f", total_a));
                ta3.setText("RM" + String.format("%.2f", total_c));
                ta4.setText("4D3N");
                ta5.setText(bt1.isSelected() ? "Comfort Van (8-Seater)" : (bt2.isSelected() ? "MPV Car" : "None"));
                ta6.setText("RM" + String.format("%.2f", total));
                
            } 
            catch (NumberFormatException e) 
            {
                // Exception handling when User does not enter input / wrong input
                JOptionPane.showMessageDialog(this, "Please enter only numeric values for Adult and Child! DONT LEAVE BLANK!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}