//Package and Price 

import java.util.*;
import java.io.*;

public class Package_Price 
{
    public static void main(String[] args) 
    {
        try
        {
            File PackPrice = new File("PackagePrice.txt");      // Path for new file
            Scanner read = new Scanner(PackPrice);

            System.out.print("\n");
            while(read.hasNextLine())
            {   
                String text = read.nextLine();  // Declare text for reading
                System.out.println(text); // Display text from PackagePrice.txt
            } 
            System.out.print("\n");

            

        }
        catch(FileNotFoundException e)
        {   
            System.out.print("File Not Found!");
        }
       
    }
}
