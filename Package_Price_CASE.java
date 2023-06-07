//Package and Price - Case Statement

import java.util.*;
import java.io.*;

public class Package_Price_CASE extends Package_Price
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        
        System.out.print("\nChoose a package (ENTER '1' or '2' or '3' or '4')");
        int choice = input.nextInt();

        String Package,duration;
        double Aprice,Cprice;
        switch(choice)
        {
            case '1':Package="Pulau Langkawi";
                     duration="3D2N";
                     Aprice=150.00;
                     Cprice=105.00;
                     break;
            
            case '2':Package="Pulau Redang";
                     duration="3D2N";
                     Aprice=120.00;
                     Cprice=84.00;
                     break;
            
            case '3':Package="Pulau Langkawi";
                     duration="3D2N";
                     Aprice=150.00;
                     Cprice=105.00;
                     
        }
        

        
    }
}
