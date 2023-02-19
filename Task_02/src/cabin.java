public class cabin {
    String availability;

    private final Passenger[] passengers = new Passenger[3];

    public Passenger[] getPassengers(){
        return passengers;
    }

    public cabin (String availability) {
        this.availability = availability;
    }

    public void addPassengersToArray(Passenger passenger){
        for (int i = 0; i < passengers.length; i++){
            if (passengers[i] == null){
                passengers[i] = passenger;
                break;
            }
        }
    }

    public boolean isEmpty(){
        return passengers [0] == null && passengers [1] == null && passengers [2] == null;
    }

    public boolean isFull() {
        return passengers [0] != null && passengers [1] != null && passengers [2] != null;
    }
    public boolean isPassengerFull(){
        return passengers[0] != null  && passengers [1] == null && passengers[2] == null;
    }


}