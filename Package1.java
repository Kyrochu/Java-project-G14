import java.util.Scanner;

public class Package1 {

    public static void main(String[] args) {
        String[] packages = {
                "Pulau Langkawi\t3D2N\tRM 150.00 RM 105.00",
                "Pulau Redang\t\t3D2N\tRM 120.00 RM 84.00",
                "Pulau Ketam\t\t3D2N\tRM 100.00 RM 70.00",
                "Genting Highlands\t4D3N\tRM 350.00 RM 245.00",
                "Cameron Highlands\t4D3N\tRM 300.00 RM 210.00"
        };

        displayPackages(packages);

        int choice = getPackageChoice(packages.length);
        if (choice != -1) {
            String selectedPackage = packages[choice - 1];
            System.out.println("Your Choice :" + selectedPackage);
        } else {
            System.out.println("Invaild Choice");
        }
    }

    public static void displayPackages(String[] packages) {
        System.out.println("\t\tPACKAGE PRICE\n");
        for (int i = 0; i < packages.length; i++) {
            System.out.println((i + 1) + ". " + packages[i]);
        }
    }

    public static int getPackageChoice(int maxChoice) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nPlease Enter Your Choice :");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > maxChoice) {
            return -1; 
        }
        return choice;
    }
}
