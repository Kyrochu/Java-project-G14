import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PackageGUI extends JFrame {

    private JLabel packageLabel;
    private JLabel priceLabel;
    private JLabel selectedPackageLabel;
    private JComboBox<String> packageComboBox;
    private JButton selectButton;
    private JTextArea selectedPackageTextArea;

    private String[] packageNames;
    private double[] packagePrices;

    public PackageGUI() {

        setTitle("Travel Packages");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        packageLabel = new JLabel("Package:");
        priceLabel = new JLabel("Price (MYR):");
        selectedPackageLabel = new JLabel("Selected Package:");
        packageComboBox = new JComboBox<>();
        selectButton = new JButton("Select");
        selectedPackageTextArea = new JTextArea(8, 30);
        selectedPackageTextArea.setEditable(false);

        loadPackages();

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = packageComboBox.getSelectedIndex();
                if (selectedIndex >= 0) {
                    String selectedPackage = packageNames[selectedIndex];
                    double selectedPrice = packagePrices[selectedIndex];
                    selectedPackageTextArea.setText(
                            "Package: " + selectedPackage + "\nPrice: " + selectedPrice + " MYR\nDuration: 4D3N");
                }
            }
        });

        add(packageLabel);
        add(packageComboBox);
        add(priceLabel);
        add(selectButton);
        add(selectedPackageLabel);
        add(selectedPackageTextArea);

        setVisible(true);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PackageGUI();
            }
        });
    }
}
