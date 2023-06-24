
    import javax.swing.*;

public class test extends JFrame {
    public test() {
        setTitle("JDialog Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton showDialogButton = new JButton("Show Dialog");
        showDialogButton.addActionListener(e -> {
            // Create a new JDialog
            JDialog dialog = new JDialog(this, "Dialog", true);
            
            // Set the content of the dialog
            JLabel label = new JLabel("This is a dialog!");
            dialog.add(label);
            
            // Set the size and position of the dialog
            dialog.setSize(200, 100);
            dialog.setLocationRelativeTo(this);
            
            // Make the dialog visible
            dialog.setVisible(true);
        });
        
        getContentPane().add(showDialogButton);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new test());
    }
}

