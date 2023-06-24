import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class calculate extends JFrame 
{
    private JLabel packageLabel ,Atext,Ctext;
    private JLabel priceLabel;
    private JLabel selectedPackageLabel;
    private JComboBox<String> packageComboBox;
    private JButton selectButton;
    private JTextArea selectedPackageTextArea;
    private JTextField numa , numc ;

    private String[] packageNames;
    private double[] packagePrices;

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            public void run() 
            {
                new calculate();
            }
        });
    }

    public calculate() {

        setTitle("Travel Packages");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        packageLabel = new JLabel("Package:");
        priceLabel = new JLabel("Price (MYR):");
        selectedPackageLabel = new JLabel("Selected Package:");
        Atext = new JLabel("Adult Price:");
        Ctext = new JLabel("Child Price:");
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
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);
        
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        
        setJMenuBar(menuBar);

        

        add(packageLabel);
        add(packageComboBox);
        add(Atext);
        add(numa);
        add(Ctext);
        add(numc);
        add(priceLabel);
        add(selectButton);
        add(selectedPackageLabel);
        add(selectedPackageTextArea);

        setVisible(true);

        loadPackages();

        selectButton.addActionListener(new ActionListener() {
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

                    selectedPackageTextArea.setText
                    (
                        "Package: " + selectedPackage + 
                        "\nPrice for Adult: " + total_a + 
                        "MYR\nPrice for Child: " + total_c + 
                        "MYR\nDuration: 4D3N" + 
                        "\nThe total Price: " + total_num

                    );
                }

            }
        });

    }


    private void loadPackages() {
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

    
}
