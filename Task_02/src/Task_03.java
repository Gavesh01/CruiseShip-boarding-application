import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Task_03 {
    static CircularQueue queue = new CircularQueue();
    static int fullCabins = 0;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        String option = " ";

        cabin[] Ship = new cabin[12];

        for (int i = 0; i < Ship.length; i++){
            Ship[i] = new cabin("empty");
        }

        int cabinNum = 0;

        while(!option.equals("Q")){

            //**The program's menu The user has the ability to choose which option to run.The menu runs until the user enters "Q"to exit*/

            System.out.println("\nA: Add a customer to a cabin");
            System.out.println("E: Display Empty cabins");
            System.out.println("D: Delete customer from cabin");
            System.out.println("F: Find cabin from customer name");
            System.out.println("S: Store program data into file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View passengers Ordered alphabetically by name");
            System.out.println("V: view all cabins");
            System.out.println("T: Expenses");
            System.out.println();

            System.out.print("Enter your Option : ");
            option = input.nextLine().toUpperCase();

            switch (option) {
                case "A" -> Add_a_customer(Ship);
                case "E" -> Empty_cabins(Ship);
                case "D" -> Delete_customer(Ship);
                case "F" -> Find_cabin(Ship);
                case "O" -> Alphabetically_ordered(Ship);
                case "V" -> view_all(Ship);
                case "S" -> Store_data(Ship);
                case "L" -> Load_data(Ship);
                case "T" -> expenses(Ship);
                case "Q" -> System.out.println("Thank You!");
                default -> System.out.println("Invalid Input!! Please choose an option from the options listed below..");//when the user enters an invalid option
            }
        }
    }

    /**Asks the user for the cabin number to which they wish to add customers. And add a name for the cabin
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void  Add_a_customer(cabin[] A){
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("please select an empty cabin that you would like to reserve : ");
            int cabinNum = input.nextInt();

            if(cabinNum >= 0 && cabinNum < 12){  //check if the suggested cabin number is inside the cabin range
                if (fullCabins == 12) {
                    queue();
                }
                else if (!A[cabinNum].isFull()){
                    System.out.println("\nHow many people do you want to add to the cabin  ?");
                    int passengerCount = input.nextInt();

                    for (int p=0; p < passengerCount; p++){

                        System.out.print("\nplease enter the customer's first name : ");
                        String firstName = input.next();

                        System.out.print("\nplease enter the customer's last name : ");
                        String lastName = input.next();

                        System.out.print("\nplease enter the customer's expenses : ");
                        int expenses = input.nextInt();

                        Passenger details = new Passenger(firstName, lastName, expenses);

                        A[cabinNum].addPassengersToArray(details);
                        A[cabinNum].availability = "Occupied";

                        if ( A[cabinNum].isPassengerFull()){
                            fullCabins += 1;
                        }
                    }
                }else {
                    System.out.println("There is currently no available space. Cabin " + cabinNum + " is fully reserved!!");
                }
            }else{
                System.out.println("The cabin number is invalid!! Enter a number  of this range (0 - 11)");     //Validating cabin number
            }
        }catch (InputMismatchException e){
            System.out.println("The input value must be an integer");
            input.nextLine();
        }

    }

    /**Empty cabins are displayed. parameters- An array of (12) Cabin Objects
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void Empty_cabins(cabin[] E){
        System.out.println();

        for (int i = 0; i < 12; i++) {
            if (E[i].availability.equals("empty"))
                System.out.println("Cabin " + i + " is empty");
        }
        System.out.println();
    }

    /**Requests the user's first name and removes the passenger from the cabin
     parameters- an of Twelve Cabin Objects
     Return- None*/
    public static void Delete_customer(cabin[] D){
        Scanner input = new Scanner(System.in);
        int found = 0;
        System.out.print("Please enter the customer's name: ");
        String cusName = input.nextLine();
        for (int i = 0; i < D.length; i++ ) {
            for (int j = 0; j < 3; j++) {
                if (D[i].getPassengers()[j] != null) {
                    if (D[i].getPassengers()[j].getFirstName().equalsIgnoreCase(cusName)) {
                        found = 1;
                        D[i].getPassengers()[j] = null; //Passenger removal
                        System.out.println("\nPassenger " + cusName + " got successfully removed!");
                        if (D[i].isEmpty()){             //Checking to see if the cabin is empty and, if so, changing the availability to "Empty"
                            D[i].availability = "empty";
                            fullCabins -= 1;
                        }
                        if (!queue.isEmpty()){             //Checking if queue is not empty
                            D[i].addPassengersToArray(queue.dequeue());         //Adding passenger from queue to cabin
                            D[(i)].availability = "Occupied";       //Changing initialized Empty status
                            System.out.println("The empty slot in cabin " + (i) + "got successfully filled");
                        }
                    }
                }
            }
        }
        if (found == 0) { //if an invalid input
            System.out.println("Sorry ! The passenger you are looking for is not in the list");
        }
    }

    /**Find the cabin number by asking the user for their first name.
     parameters- First name,an of Twelve Cabin Objects
     Return- Array of cabin and passenger numbers*/
    public static void Find_cabin(cabin[] F){
        Scanner input = new Scanner(System.in);
        int found = 0;

        System.out.print("Enter customer name: ");
        String cusName = input.nextLine();
        for (int i = 0; i < F.length; i++ ) {
            for (int j = 0; j < 3; j++) {
                if (F[i].getPassengers()[j] != null) {
                    if (F[i].getPassengers()[j].getFirstName().equalsIgnoreCase(cusName)){
                        found = 1;
                        System.out.println("The customer is in cabin. " + (i));
                    }
                }
            }
        }
        if (found == 0){  //if an invalid input
            System.out.println("A cabin in your name is not been reserve");
        }
    }

    /**Creating a file with all the cabin & passenger information
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void Store_data(cabin S[]){
        int i = 0;
        try {
            FileWriter myWriter = new FileWriter("ShipCabinDetails.txt"); //Make a FileWriter class object and enter the filename
            for ( i = 0; i < S.length; i++ ) {
                myWriter.write("cabin " + i + "\n");
                for ( int j = 0; j < 3; j++ ) {
                    if (S[i].getPassengers()[j] != null) {
                        myWriter.write("     Passenger " + (j) + "- " + S[i].getPassengers()[j].toString() + "\n" );//Write to file
                    }
                    else{
                        myWriter.write("     Passenger " + (j) + "- Empty\n");
                    }
                }
            }
            myWriter.close();       //File closing
            System.out.println("Congratulations!! Your information has been successfully written to the ShipCabinDetails.txt file.");
        }
        catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**Loads and displays the user's written file
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void Load_data(cabin[] L){
        int lineCount = 1;
        try {
            File inputFile = new File("ShipCabinDetails.txt"); //Specifies the file name
            Scanner rf = new Scanner(inputFile); //Reads data from a file
            String fileLine;

            while (rf.hasNext()) { //Reads a single line at a time.
                fileLine = rf.nextLine();
                System.out.println(fileLine);
                lineCount++;
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
    }

    /**Sorts and shows passengers alphabetically by their first name.
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void Alphabetically_ordered(cabin[] O){
        System.out.println("Alphabetically Orders Passenger List");
        String firstNames = "";
        for (int i = 0; i < O.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (O[i].getPassengers()[j] != null) {
                    firstNames += O[i].getPassengers()[j].getFirstName() + ",";
                }
            }
        }

        String[] tempPassengers = firstNames.split(","); //Add the first name to an array and separate them with commas
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
    public static void view_all(cabin[] V){
        Scanner input = new Scanner(System.in);

        System.out.println();
        for (int i = 0; i < 12; i++ ) {
            System.out.println();
            System.out.println("cabin " + i);
            for (int j = 0; j < 3; j++){
                if (V[i].getPassengers()[j] != null){
                    System.out.println("\n   Cabin " + j + "received by " + V[i].getPassengers()[j].toString());
                }
                else {
                    System.out.println("\n   Cabin " + j + " received by");
                }
            }

        }
        System.out.println();
    }

    /**Displays expenses by the customer as well as total expenses.
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static void expenses (cabin [] CabinNum) {
        System.out.println("Passenger Expenses: ");
        for (int i = 0; i < CabinNum.length; i++ ) {
            if (!CabinNum[i].availability.equals("Empty")){
                for ( int j = 0; j < 3; j++ ) {
                    if (CabinNum[i].getPassengers()[j] != null) {
                        System.out.println("       " + CabinNum[i].getPassengers()[j].toString() );
                    }
                }
            }
        }
        System.out.println("\n\nTotal Passenger Expenses: " + totalExpenses(CabinNum));     //referring to a method for calculating total costs
    }

    /** Calculates the total expenses
     parameters- Array of 12 Cabin Objects
     Return- None
     */
    public static double totalExpenses (cabin[] CabinNum) {
        double total = 0;
        for (int i = 0; i < CabinNum.length; i++ ) {
            if (!CabinNum[i].availability.equals("Empty")) {
                for (int j = 0; j < 3; j++) {
                    if (CabinNum[i].getPassengers()[j] != null) {
                        total += CabinNum[i].getPassengers()[j].getExpenses();
                    }
                }
            }
        }
        return total;
    }


    /** creating the queue*/
    public static void queue () {
        Scanner input = new Scanner(System.in);
        if (!queue.isFull()){           //Check if the queue is full
            System.out.println("All Cabins are full. You will be added to the waiting list");
            System.out.print("\nplease enter the customer's first name : ");
            String firstName = input.next();

            System.out.print("\nplease enter the customer's last name : ");
            String lastName = input.next();

            System.out.print("\nplease enter the customer's expenses : ");
            int expenses = input.nextInt();

            Passenger details = new Passenger(firstName, lastName, expenses);


            queue.enqueue(details);       //Add passenger to queue

            System.out.println("\nYour information has been added to the waiting list.");
        }
        else {      //If queue is full
            System.out.println("Sorry!! The waiting list is full.");
        }
    }
}
