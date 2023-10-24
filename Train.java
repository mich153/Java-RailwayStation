/**
 * Represent a train
 * @author Michal Danishevski
 * @version 23.4.2020
 */
public class Train{

    private String _destination;
    private Time1 _departure;
    private int _duration;//in minutes
    private int _passengers;
    private int _seats;
    private int _price;

    final int MINIMUM_SEATS = 0;
    final int MINIMUM_PASSENGERS = 0;
    final int MINIMUM_DURATION = 0;
    final int MINIMUM_PRICE = 0;

    /**
     * Constructor of train
     * @param dest the destination of the train
     * @param h the hour of departure
     * @param m the minute of departue
     * @param duration the duration of the travel
     * @param pass the number of passeners
     * @param seats the number of seats in the train
     * @param price the price of the travel
     */
    public Train(String dest,int h,int m,int duration,
    int pass,int seats,int price){
        if(seats < MINIMUM_SEATS)//seats need to be in seats' range
            _seats = 0;
        else
            _seats = seats;

        if(pass > _seats)
            _passengers = _seats;//can't be more passengers from seats
        else if(pass < MINIMUM_PASSENGERS)/*passengers need to be 
        in passengers' range*/
            _passengers = MINIMUM_PASSENGERS;
        else
            _passengers = pass;

        if(duration < MINIMUM_DURATION)//duration need to be in passengers' range
            _duration = MINIMUM_DURATION;
        else 
            _duration = duration;

        if(price < MINIMUM_PRICE)//price need to be in passengers' range
            _price = MINIMUM_PRICE;
        else
            _price = price;

        _departure = new Time1(h,m);
        _destination = new String(dest);
    }//end of the constructor

    /**
     * Copy constructor of train
     * @param other The train object from which to construct the new train
     */
    public Train(Train other){

        _destination = new String(other._destination);
        _departure = new Time1(other._departure);
        _duration = other._duration;
        _passengers = other._passengers;
        _seats = other._seats;
        _price = other._price;

    }//ens of copy constructor

    /**
     * Returns the arrival time
     * @return The arrival time
     */
    public Time1 getArrivalTime(){
        return new Time1(_departure.addMinutes(_duration));
    }//end of getArrivalTime

    /**
     * Return the departure time
     * @return The departure time
     */
    public Time1 getDeparture(){
        return new Time1(_departure);
    }//end of getDeparture

    /**
     * Return the destination
     * @return The destination
     */
    public String getDestination(){
        return new String(_destination);
    }//end of getDestination

    /**
     * Return the duration
     * @return The duration
     */
    public int getDuration(){
        return _duration;
    }//end of getDuration

    /**
     * Return the number of passengers
     * @return The number of passengers
     */
    public int getPassengers(){
        return _passengers;
    }//end of getPassengers

    /**
     * Return the price for one ticket to train
     * @return The price for one ticket to train
     */
    public int getPrice(){
        return _price;
    }//end of getPrice

    /**
     * Return the number of seats
     * @return The number of seats
     */
    public int getSeats(){
        return _seats;
    }//end of getSeats

    /**
     * Updates the departure time of the train.
     * @param t The new departure time of the train
     */
    public void setDeparture(Time1 t){
        _departure = new Time1(t);//Time1 chage the time
    }//end of setDeparture

    /**
     * updates the destination of the train
     * @param d The new detination of the train.
     */
    public void setDestination(String d){
        _destination = new String(d);
    }//end of setDestination

    /**
     * Updates the duration of the train
     * @param d The new duration of the train
     */
    public void setDuration(int d){
        if(d >= MINIMUM_DURATION)//duration can't be smaller from 0
            _duration = d;
    }

    /**
     * Updates the number of passengers.
     * @param p The new number of passengers
     */
    public void setPassengers(int p){
        if(p > _seats)//number passengers should be less than seats 
            _passengers = _seats;
        else if(p >= MINIMUM_PASSENGERS)//passengers should to be in passengers' range
            _passengers = p;
    }//end of setPassengers

    /**
     * Updates the price
     * @param p The new price
     */
    public void setPrice(int p){
        if(p >= MINIMUM_PRICE)//passengers should to be positive or zero
            _price = p;
    }//end of setPrice

    /**
     * Updates the number of seats
     * @param s The new number of seats
     */
    public void setSeats(int s){
        if(s >= MINIMUM_SEATS && s > _passengers)/*s should be positive or zero 
        and larger tha passenger*/
            _seats = s;
    }//end of setSeats

    /**
     * Check if the received train is equal to this train
     * @param other The train to be compared with this train
     * @return True if the received train is equal to this train
     */
    public boolean equals(Train other){
        if(other._destination.equals(_destination) &&
        other._departure.equals(_departure) && other._passengers == _passengers)
            return true;/*trains equals if destination,departure and 
        number of passengers equals*/
        return false;
    }//end of equals

    /**
     * Add num passengers to the train
     * @param num The number of passengers to add
     * @return True if there is empty space to add the passengers
     */
    public boolean addPassengers(int num){
        if(_passengers + num <= _seats){
            _passengers += num;//add the passengers
            return true;//the train have empty space
        }
        return false;//the train full
    }//end of addPassengers

    /**
     * Returns true if train is full
     * @return True if train is full
     */
    public boolean isFull(){
        return (_passengers == _seats);//if there is empty space in the train
    }//end of isFull

    /**
     * Returns true if the price for this train is cheaper than the other train
     * @param other The train which price compared to this price
     * @return True if the price for this train is cheaper than the other train
     */
    public boolean isCheaper(Train other){
        return (_price < other._price);
    }//end of isCheaper

    /**
     * Returns the total price for all passengers
     * @return The total price for all passengers.
     */
    public int totalPrice(){
        return (_passengers * _price);//the total price
    }//end of totalPrice

    /**
     * Returns the arrival time
     * @param other The train which arrival time
     * compared to this train's arrival time
     * @return True if this train's arrival time before 
     * the other train's arrival time
     */
    public boolean arrivesEarlier(Train other){
        return (this.getArrivalTime().before(other.getArrivalTime()));
    }//end of arrivesEarlier

    /**
     * Return a String representation of the train
     * @return String representation of the train
     */
    public String toString(){
        if(this.isFull())
            return "Train to " + _destination + " departs at " 
            + _departure + ". " + "Train is full";//the train is full

        return "Train to " + _destination + " departs at " 
        + _departure + ". " + "Train is not full";//the train is not full
    }//end of toString
}//end of class 