import java.io.*;
import java.util.*;

public class Task_01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] CrShip = new String[12];   // Create an array of 12 cabins.

        String option = " ";


        while(!option.equals("Q")) {

            /**The program's menu The user has the ability to choose which option to run.The menu runs until the user enters "Q"to exit*/

            System.out.println("\nA: Add a customer to a cabin");
            System.out.println("E: Display Empty cabins");
            System.out.println("D: Delete customer from cabin");
            System.out.println("F: Find cabin from customer name");
            System.out.println("S: Store program data into file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View passengers Ordered alphabetically by name");
            System.out.println("V: View all cabins");
            System.out.println("Q:Exit");

            System.out.print("\nEnter your Option:");
            option = input.nextLine().toUpperCase();


            switch (option) {
                case "A" -> Add_a_customer(CrShip);
                case "E" -> Empty_cabins(CrShip);
                case "D" -> Delete_customer(CrShip);
                case "F" -> Find_cabin(CrShip);
                case "O" -> Alphabetically_ordered(CrShip);
                case "V" -> view_all(CrShip);
                case "S" -> Store_data(CrShip);
                case "L" -> Load_data(CrShip);
                case "Q" -> System.out.println("Thank You!");
                default -> {
                    System.out.println("Invalid Input!! Please choose an option from the options listed below..\n"); //when the user enters an invalid option

                }
            }
        }

    }
    public static void empty(String emptycabin[]){
        for (int i = 0; i < 12; i++){
            emptycabin[i] = "none";
        }
    }


    /**Asks the user for the cabin number to which they wish to add customers. And add a name for the cabin
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void Add_a_customer(String[] A){
        Scanner input = new Scanner(System.in);

        System.out.print("Please select an empty cabin that you would like to reserve : ");
        int CabiNum = input.nextInt();

        System.out.print("Add a name for the cabin " + CabiNum + " :  ");
        String customerName = input.next();

        A[CabiNum] = customerName; //Adding passengers to the array

        System.out.println("\n Customer " + customerName + " has been added to the cabin number " + CabiNum +" .");

    }

    /**Empty cabins are displayed. parameters- An array of (12) Cabin Objects
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void Empty_cabins(String E[]){
        System.out.println();

        for (int i = 0; i < 12; i++) {
            if (E[i] == null)
                System.out.println("Cabin " + i + " is empty");
        }
        System.out.println();
    }

    /**Requests the user's first name and removes the passenger from the cabin
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void Delete_customer(String[] D){
        Scanner input = new Scanner(System.in);
        boolean isFound = false;

        System.out.print("\nEnter passenger name you want to remove: ");
        String cusName = input.nextLine();

        for ( int i = 0; i < D.length; i++ ) {
            if (D[i] != null){
                if (D[i].equalsIgnoreCase(cusName)){
                    D[i] = null;
                    System.out.println("Passenger " + cusName + "got successfully removed!");
                    isFound = true;
                }
            }
        }
        if (!isFound){
            System.out.println("Sorry! Passenger you are looking for is not in the list!!");
        }
    }

    /**Find the cabin number by asking the user for their first name.
     parameters- First name,an of Twelve Cabin Objects
     Return- Array of cabin and passenger numbers*/
    public static void Find_cabin(String[] F){
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the customer's name. : ");
        String cabiName = input.next();

        int position = 0;
        boolean element = false;

        for(int i=0; i<12; i++){
            if(cabiName.equals(F[i])){
                position = i;
                element = true;
                break;
            }
        }
        if (element){

            System.out.println("\nYou reserve "+position+" cabin.");

        }

        else{
            System.out.println("\nA cabin in your name is not been reserve");

        }
    }

    /**Creating a file with all the cabin & passenger information
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    private static void Store_data (String[] CabiNum) {
        int i = 0;
        try {
            FileWriter myWriter = new FileWriter("ShipCabinDetails.txt"); //Make a FileWriter class object and enter the filename
            for ( i = 0; i < CabiNum.length; i++ ) {
                myWriter.write("Cabin Number "+ i + ": " + CabiNum[i] + "\n");//Write to file
            }
            myWriter.close();
            System.out.println("Congratulations!! Your information has been successfully written to the ShipCabinDetails.txt file.");
            System.out.println();
        }
        catch (IOException e) {
            System.out.println("An error has occurred");
        }
    }

    /**Loads and displays the user's written file
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    private static void Load_data (String CabinNum[]) {
        int line_Count = 1;
        try {
            File inputFile = new File("ShipCabinDetails.txt"); //Specifies the file name
            Scanner rf = new Scanner(inputFile);//Reads data from a file
            String file_Line;

            while (rf.hasNext()) { //Reads a single line at a time.
                file_Line = rf.nextLine();
                System.out.println(line_Count + " " + file_Line);
                line_Count++;
            }
            rf.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**Sorts and shows passengers alphabetically by their first name
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void Alphabetically_ordered(String[] O){
        String names = "";
        for (int i = 0; i < O.length; i++) {
            if (O[i] != null) {
                names += O[i] + ",";
            }

        }
        String[] tempPassengers = names.split(",");
        for (int i = 0; i < tempPassengers.length; i++) {
            for (int j = 0; j < tempPassengers.length - 1; j++) {
                if (tempPassengers[j].compareTo(tempPassengers[j+1]) > 0) {
                    String temp = tempPassengers[j];
                    tempPassengers[j] = tempPassengers[j+1];
                    tempPassengers[j+1] = temp;
                }
            }
        }
        for (int i = 0; i < tempPassengers.length; i++) {
            System.out.println("   Customer Name: " + tempPassengers[i]);
        }
    }

    /**display all cabins in the ship
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void view_all(String[] V){

        System.out.println();
        for (int i = 0; i < 12; i++ ) {
            System.out.println("Cabin " + (i) + " received by " + V[i]);
        }
        System.out.println();
    }
}