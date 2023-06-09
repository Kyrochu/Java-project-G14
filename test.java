import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class test extends JFrame {
    private JLabel packageLabel, Atext, Ctext;
    private JLabel priceLabel;
    private JLabel selectedPackageLabel;
    private JComboBox<String> packageComboBox;
    private JButton selectButton;
    private JTextArea selectedPackageTextArea, cardetail;
    private JTextField numa, numc;
    private JRadioButton bt1, bt2, bt3;

    private String[] packageNames;
    private double[] packagePrices;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new test();
            }
        });
    }

    public test() {
        setTitle("Travel Packages");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        packageLabel = new JLabel("Package:");
        priceLabel = new JLabel("Price (MYR):");
        selectedPackageLabel = new JLabel("Selected Package:");
        Atext = new JLabel("Adult Number:");
        Ctext = new JLabel("Child Number:");
        numa = new JTextField(6);
        numc = new JTextField(6);
        cardetail = new JTextArea(4, 10);
        packageComboBox = new JComboBox<>();
        selectButton = new JButton("Select");
        selectedPackageTextArea = new JTextArea(8, 30);
        selectedPackageTextArea.setEditable(false);

        bt1 = new JRadioButton("Van");
        bt2 = new JRadioButton("MPV Car");
        bt3 = new JRadioButton("None");

        ButtonGroup bg = new ButtonGroup();
        bg.add(bt1);
        bg.add(bt2);
        bg.add(bt3);

        // Add components to the frame
        add(packageLabel);
        add(packageComboBox);
        add(priceLabel);
        add(selectButton);
        add(selectedPackageLabel);
        add(selectedPackageTextArea);
        add(Atext);
        add(numa);
        add(Ctext);
        add(numc);
        add(bt1);
        add(bt2);
        add(bt3);
        add(cardetail);

        setVisible(true);

        loadPackages();

        // ActionListener for the packageComboBox
        packageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculatePackage();
            }
        });

        // ActionListener for the selectButton
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });
    }

    private void loadPackages() {
        try {
            BufferedReader packageReader = new BufferedReader(new FileReader("package.txt"));
            BufferedReader priceReader = new BufferedReader(new FileReader("packagePrice.txt"));

            String packageLine;
            String priceLine;
            int packageCount = 0;

            while ((packageLine = packageReader.readLine()) != null && (priceLine = priceReader.readLine()) != null) {
                String[] packageData = packageLine.split("\\s+");
                String[] priceData = priceLine.split("\\s+");

                if (packageData.length > 0 && priceData.length > 0) {
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
            while ((packageLine = packageReader.readLine()) != null && (priceLine = priceReader.readLine()) != null) {
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
                    "\nPrice for Adult: " + total_a + " MYR" +
                    "\nPrice for Child: " + total_c + " MYR" +
                    "\nDuration: 4D3N"
            );
        }
    }

    private void calculateTotal() {
        int selectedIndex = packageComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            String selectedPackage = packageNames[selectedIndex];
            double selectedPrice = packagePrices[selectedIndex];
            double total_a = selectedPrice;
            double total_c = selectedPrice * 0.70;

            if (bt1.isSelected()) {
                total_a += 100; // Additional cost for Van
            } else if (bt2.isSelected()) {
                total_a += 200; // Additional cost for MPV Car
            }

            int adultCount = Integer.parseInt(numa.getText());
            int childCount = Integer.parseInt(numc.getText());

            double totalPrice = (total_a * adultCount) + (total_c * childCount);

            selectedPackageTextArea.append(
                    "\nSelected Vehicle: " + (bt1.isSelected() ? "Van" : (bt2.isSelected() ? "MPV Car" : "None")) +
                    "\nTotal Price: " + totalPrice + " MYR"
            );
        }
    }
}
