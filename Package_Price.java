// //Package and Price 

// import java.util.*;
// import java.io.*;

// public class Package_Price 
// {
//     public static void main(String[] args) 
//     {
//         try
//         {
//             File PackPrice = new File("PackagePrice.txt");      // Path for new file
//             Scanner read = new Scanner(PackPrice);

//             System.out.print("\n");
//             while(read.hasNextLine())
//             {   
//                 String text = read.nextLine();  // Declare text for reading
//                 System.out.println(text); // Display text from PackagePrice.txt
//             } 
//             System.out.print("\n");

            

//         }
//         catch(FileNotFoundException e)
//         {   
//             System.out.print("File Not Found!");
//         }
       
//     }
// }

//Package and Price 

import java.util.*;
import java.io.*;

public class Package_Price 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

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

            System.out.print("\nChoose a package:");
            int choice = input.nextInt();

            read = new Scanner(PackPrice);
            int i=1;

            while(read.hasNextLine())
            {
                String text = read.nextLine();
                if(i == choice)
                {
                    System.out.print("Selected Package : "+text);
                    break;
                }
                i++;
            }

            

        }
        catch(FileNotFoundException e)
        {   
            System.out.print("File Not Found!");
        }
       
    }
}

