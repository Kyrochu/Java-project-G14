import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TravelAgencyPackageCalculator extends JFrame 
{
    // ## DECLARATION ## //

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
        Font f1 = new Font("Dialog", Font.BOLD | Font.ITALIC, 30); // SET FONT
        Font f2 = new Font("Serif", Font.BOLD, 26);

        setTitle("Travel Agency Package Calculator");
        setSize(700, 850);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

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

        // ## DISPLAYING  ## //

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
        
        JPanel specialOfferPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        specialOfferPanel.add(SpecialOfferLabel);

        // Add both panels to a common panel
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(topPanel);
        titlePanel.add(specialOfferPanel);

        add(titlePanel, BorderLayout.NORTH);

        // Packages
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
        radiobtn.add(bt1);
        radiobtn.add(bt2);
        radiobtn.add(bt3);

        post.gridx = 1;
        post.gridy = 4;
        
        packagePanel.add(radiobtn, post);

        post.gridx = 0;
        post.gridy = 5;
        packagePanel.add(TransPriceLabel,post);

        post.gridx = 1;
        post.gridy = 5;
        packagePanel.add(cardetail,post);
        
        // -------------------- DISPLAY PACKAGE BORDER --------------------------- //

        JPanel display1 = new JPanel();
        display1.setLayout(new BorderLayout());
        display1.add(packagePanel,BorderLayout.NORTH);
        add(display1, BorderLayout.WEST);   
        
        // -------------------- DISPLAY SUMMARY --------------------------- //

        JPanel summary = new JPanel(new FlowLayout(FlowLayout.LEFT));
        summary.setBorder(BorderFactory.createLineBorder(Color.BLACK));  // Border line for main title
        summary.add(resit);
        summary.add(selectButton);
        
        add(summary, BorderLayout.SOUTH);

        setVisible(true);

        loadPackages(); // Calling Function

        // ActionListener for the packageComboBox
        packageComboBox.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePackage();
            }
        });

        // ActionListener for the selectButton
        selectButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculatePackage() {
        int selectedIndex = packageComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedPackage = packageNames[selectedIndex];
            double selectedPrice = packagePrices[selectedIndex];
            double total_a = selectedPrice;
            double total_c = selectedPrice * 0.70;

            selectedPackageTextArea.setText(
                    "Package: " + selectedPackage +
                    "\nAdult Price/person: RM" + total_a +
                    "\nChild Price/person: RM" + total_c +
                    "\nDuration: 4D3N"
            );
        }
    }

    private void calculateTotal() {
        int selectedIndex = packageComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedPackage = packageNames[selectedIndex];
            double selectedPrice = packagePrices[selectedIndex];
            Double enter_a = Double.parseDouble(numa.getText());
            Double enter_c = Double.parseDouble(numc.getText());
            double total_a = selectedPrice * enter_a;
            double total_c = enter_c * (selectedPrice * 0.70);
            double total = total_a + total_c;

            if (bt1.isSelected()) {
                total += 1800; // Additional cost for Van
            } else if (bt2.isSelected()) {
                total += 1200; // Additional cost for MPV Car
            }

            resit.setText("");

            resit.append(
                    "\nPackage: " + selectedPackage +
                    "\nTotal Adult Price: RM" + total_a +
                    "\nTotal Child Price: RM" + total_c +
                    "\nDuration: 4D3N"+
                    "\nSelected Vehicle: " + (bt1.isSelected() ? "Comfort Van (8-Seater)" : (bt2.isSelected() ? "MPV Car" : "None")) +
                    "\nTotal Price: RM" + total
            );
        }
    }
        
}