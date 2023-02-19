public class Passenger {
    private String FirstName;
    private String LastName;
    private double Expenses;

    public Passenger(){


    }

    public Passenger(String FirstName, String LastName, double Expenses){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Expenses = Expenses;
    }
    public String getFirstName(){return FirstName;}
    public String getLastName(){return LastName;}
    public double getExpenses(){return Expenses;}

    @Override
    public String toString(){
        return "• First name : " + FirstName +
                " , • Last name : " + LastName +
                " , • Expenses : " + Expenses;

    }

}
