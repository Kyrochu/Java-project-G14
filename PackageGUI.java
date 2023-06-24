import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PackageGUI extends JFrame 
{
    // ## DECLARATION ## //

    // Main Title Declaration
    JLabel MainTitle, MainIcon;
    ImageIcon MI = new ImageIcon(getClass().getResource("MainIcon.png"));

    // Packages Declaration
    JLabel packageLabel, Atext, Ctext;
    JLabel priceLabel;
    JLabel selectedPackageLabel;
    JComboBox<String> packageComboBox;
    JButton selectButton;
    JTextArea selectedPackageTextArea;
    JTextField numa, numc;

    String[] packageNames;
    double[] packagePrices;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new PackageGUI();
            }
        });
    }

    public PackageGUI() 
    {
        Font f1 = new Font("Dialog", Font.BOLD | Font.ITALIC, 30); // SET FONT

        setTitle("Travel Agency Package Calculator");
        setSize(700, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        MainIcon = new JLabel(MI);
        MainTitle = new JLabel("Travelling Packages Calculator For You");
        MainTitle.setFont(f1);

        packageLabel = new JLabel("Packages:");
        priceLabel = new JLabel("Price (MYR):");
        selectedPackageLabel = new JLabel("Selected Package:");
        Atext = new JLabel("No. of Adult/s:");
        Ctext = new JLabel("No. of Child/s:");
        numa = new JTextField(6);
        numc = new JTextField(6);
        packageComboBox = new JComboBox<>();
        selectButton = new JButton("Select");
        selectedPackageTextArea = new JTextArea(8, 30);
        selectedPackageTextArea.setEditable(false);

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
        packagePanel.add(priceLabel, post);

        post.gridx = 1;
        post.gridy = 1;
        packagePanel.add(selectButton,post);

        post.gridx = 0;
        post.gridy = 2;
        packagePanel.add(selectedPackageLabel,post);

        post.gridx = 1;
        post.gridy = 2;
        packagePanel.add(selectedPackageTextArea,post);

        post.gridx = 0;
        post.gridy = 3;
        packagePanel.add(Atext, post);

        post.gridx = 1;
        post.gridy = 3;
        packagePanel.add(numa, post);

        post.gridx = 0;
        post.gridy = 4;
        packagePanel.add(Ctext, post);

        post.gridx = 1;
        post.gridy = 4;
        packagePanel.add(numc,post);
        
        // -------------------- DISPLAY MAIN PACKAGE BORDER --------------------------- //

        JPanel display1 = new JPanel();
        display1.setLayout(new BorderLayout());
        display1.add(packagePanel,BorderLayout.NORTH);
        add(display1, BorderLayout.WEST);   

        setVisible(true);

        loadPackages(); // Calling Function

        selectButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int selectedIndex = packageComboBox.getSelectedIndex();

                if (selectedIndex >= 0) 
                {
                    String selectedPackage = packageNames[selectedIndex];
                    double selectedPrice = packagePrices[selectedIndex];
                    Double enter_a = Double.parseDouble(numa.getText());
                    Double enter_c = Double.parseDouble(numc.getText());
                    double total_a = enter_a * selectedPrice;
                    double total_c = enter_c * (selectedPrice * 0.70);
                    Double total_num = total_a + total_c;

                    selectedPackageTextArea.setText("Package: " + selectedPackage + "\n\nPrice for Adult: RM" + total_a
                            + "\n\nPrice for Child: RM" + total_c + "\n\nDuration: 4D3N" + "\n\nTotal Price: RM"
                            + total_num);
                }

            }
        });
    }

    public void loadPackages() 
    {
        try 
        {
            BufferedReader packageReader = new BufferedReader(new FileReader("package.txt"));
            BufferedReader priceReader = new BufferedReader(new FileReader("packagePrice.txt"));

            String packageLine;
            String priceLine;
            int packageCount = 0;

            while ((packageLine = packageReader.readLine()) != null
                    && (priceLine = priceReader.readLine()) != null) {
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
            while ((packageLine = packageReader.readLine()) != null
                    && (priceLine = priceReader.readLine()) != null) {
                String[] packageData = packageLine.split("\\s+");
                String[] priceData = priceLine.split("\\s+");

                if (packageData.length > 0 && priceData.length > 0) {
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
}