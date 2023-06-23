import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TravelAgencyPackageCalculator extends JFrame
{
    JLabel MainTitle,MainIcon;
    
    ImageIcon MI = new ImageIcon(getClass().getResource("MainIcon.png"));  // MI = MainIcon 
    public static void main(String[] args) 
    {
        TravelAgencyPackageCalculator frame = new TravelAgencyPackageCalculator();
        frame.pack();
        frame.setTitle("Travel Agency Package Calculator");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public TravelAgencyPackageCalculator()
    {

        Font f1 = new Font("Dialog",Font.BOLD | Font.ITALIC,30);  // SET FONT

        MainTitle = new JLabel("Travelling Packages Calculator For You"); // SET MainTitle
        MainTitle.setFont(f1);                                                 // SET font for MainTitle

        JPanel p1 = new JPanel();                                             
        p1.setLayout(new GridLayout(1,2));
        MainIcon = new JLabel(MI);
        p1.add(MainIcon);
        p1.add(MainTitle);

        setLayout(new BorderLayout());
        add(p1,BorderLayout.NORTH);
    }

    // public void actionPerformed(ActionEvent e)
    // {

    // }
}
