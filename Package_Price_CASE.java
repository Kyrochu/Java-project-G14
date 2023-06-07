import java.util.*;
import java.io.*;

public class Package_Price_CASE 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        String Package,Duration;
        double Aprice,Cprice;

        try 
        {
            File packPrice = new File("PackagePrice.txt"); 
            Scanner read = new Scanner(packPrice);
        
            System.out.println();
            
            while (read.hasNextLine()) 
            {
                String text = read.nextLine(); 
                System.out.println(text);
            }

            System.out.println();
        
            System.out.print("Please enter your choice('1' or '2' or '3' or '4'): ");
            int choice = input.nextInt();
            
            switch(choice)
            {
                case '1':Package="Pulau Langkawi";
                         Duration="3D2N";
                         Aprice=150.00;
                         Cprice=105.00;
                         break;
                
                case '2':Package="Pulau Redang";
                         Duration="3D2N";
                         Aprice=120.00;
                         Cprice=84.00;
                         break;

                case '3':Package="Pulau Ketam";
                         Duration="3D2N";
                         Aprice=100.00;
                         Cprice=70.00;
                         break;

                case '4':Package="Genting Highlands";
                         Duration="4D3N";
                         Aprice=350.00;
                         Cprice=245.00;
                         break;

                case '5':Package="Cameron Highlands";
                         Duration="4D3N";
                         Aprice=300.00;
                         Cprice=210.00;
                         break;

                default:Package="Unknown Package";
                        Duration="Unknown Duration";
                        Aprice=0;
                        Cprice=0;
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.print("FILE NOT FOUND!");
        }
    }

}




    
