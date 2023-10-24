/**
 * Represent a railway station
 * @author Michal Danishevsky
 * @version 6.5.2020
 */
public class RailwayStation{
    private Train [] _station;
    private int _noOfTrs;

    final int MAX_TRAINS = 100;//in day

    /**
     * Constructor of railway station
     */
    public RailwayStation(){
        _station = new Train [MAX_TRAINS];
        _noOfTrs = 0;
    }//end of the constructor

    /**
     * Add new train to the station
     * @param newTrain The new tarin to be added to the station
     * @return True if the train is added and false if the 
     * station is full or tha train already in the station
     */
    public boolean addTrain(Train newTrain){
        for(int i=0 ; i < _station.length ; i++){
            if(_station[i] == null){
                _station[i] = new Train(newTrain);//add the new train
                _noOfTrs++;//there is one more train
                return true;//the train added to the array
            }//end of if

            else if( newTrain.equals( _station[i] ) )
                return false;//the train already in the array
        }//end of for

        return false;//the array is full

    }//end of addTrain

    /**
     * Remove the train from station
     * @param delete Train to remove from the station
     * @return True if the train removed and false if the 
     * station is empty or the train not in the station
     */
    public boolean removeTrain(Train delete){

        for(int i=0 ; i < _station.length && _station[i] != null; i++){

            if( _station[i].equals(delete)){
                _station[i] = null;//delete this train
                noHoles(i);//make no holes in the array
                return true;//the train erased
            }//end of if

        }//end of for

        return false;//the array is empty or the train not in the array

    }//end of removeTrain

    /*Get hole's place in the array and remove the 
    final train in the array to the hole */
    private void noHoles (int place){
        boolean isHole = true;
        for(int i = (_station.length - 1) ; i >= 0 && isHole ; i--)
            if(_station[i] != null){//the final train in the array
                //remove the final train to the hole
                _station[place] = new Train(_station[i]);
                _station[i] = null;
                isHole = false;
            }//end of if
    }//end of noHoles

    /**
     * Return the time which first train to destination
     * @param place The destination to look for the sooner 
     * time that train come to it
     * @return The time which first train to destination and
     * null if there isn't train to this destination (or if the station is empty)
     */
    public Time1 firstDepartureToDestination (String place){
        if(emptyStation())
            return null;//the station is empty

        Time1 sooner = new Time1(_station[0].getDeparture());
        boolean isNotTarainsToDestination = true;

        for(int i=0 ; i < _station.length && _station[i] != null ; i++){

            if(place.equals(_station[i].getDestination())){
                if(isNotTarainsToDestination){
                    //found the first train to this destination
                    isNotTarainsToDestination = false;
                    sooner = new Time1(_station[i].getDeparture());
                }//end of if

                else if(sooner.after(_station[i].getDeparture()))
                //there is train to this destination that sooner
                    sooner = new Time1(_station[i].getDeparture());
            }
        }//end of for
        if(isNotTarainsToDestination)
            return null;//there isn't trains to this destination
        else
            return sooner;//the sooner time to this destination
    }//end of firstTrainToDestination

    //return true if the station is empty this day
    private boolean emptyStation(){
        if(_noOfTrs == 0)//the trains are sequence in the array of station
            return true;//the station is empte this day
        return false;//the station not empt this day
    }//end of emptyStation

    /**
     * Return a String representation of all the trains today
     * @return String representation of all the trains today
     */
    public String toString(){
        String output = "The trains today are:";
        output += "\n";

        if(emptyStation())//the station is empty today
            return "There are no trains today";

        for(int i=0 ; i < _station.length && _station[i] != null ; i++){
            output += _station[i].toString();//add every train that in the station today
            output += "\n";
        }//end of for
        return output;//the String of all trains
    }//end of toString

    /**
     * Return how many full trains there are in the station in this day
     * @return How many full trains there are in the station in this day
     */
    public int howManyFullTrains(){
        int fullTrains = 0;

        for(int i=0 ; i < _station.length && _station[i] != null ; i++){
            if(_station[i].isFull())
            //there is one more full train
                fullTrains ++ ;
        }//end of for

        return fullTrains;
    }//end of howManyFullTrains

    /**
     * Return what the most popular destination from the train that in the station
     * @return What the most popular destination from the train that in the station
     */
    public String mostPopularDestination(){
        String popularDestination = new String("");
        String destination;
        int trainsToDestination;
        int trainsToPopularDestination = 0;

        if(emptyStation())
            return null;//there aren't train in the station

        for(int i=0 ; i < _station.length && _station[i] != null ; i++){

            //sum how many trains to any destination
            destination = new String(_station[i].getDestination());
            trainsToDestination = 0;
            for(int j=0 ; j < _station.length && _station[j] != null ; j++)
                if(_station[j].getDestination().equals(destination))
                    trainsToDestination ++;//one more train to this destination

            /*check if there is more popular destination
             * if there is more popular destination it change the 
             * popular destination to the more puplar destination
             */
            if(trainsToDestination > trainsToPopularDestination){
                popularDestination = new String(destination);
                trainsToPopularDestination = trainsToDestination;
            }//end of "if"
        }//end of "for"

        return popularDestination;//the most popular destination
    }//end of mostPopularDestination

    /**
     * Return the train that have the most expensive ticket
     * @return The train that have the most expensive ticket
     */
    public Train mostExpensiveTicket(){
        if(emptyStation())
            return null;//the station is empty

        Train expensiveTicket = new Train(_station[0]);

        for(int i=0 ; i < _station.length && _station[i] !=null ; i++)
            if(expensiveTicket.isCheaper(_station[i]))
            //there is more expensive train
                expensiveTicket = new Train(_station[i]);

        return expensiveTicket;//the most expensive train
    }//end of mostExpensiveTicket

    /**
     * Return the train with the longest drive time
     * @return The train with the longest drive time
     */
    public Train longestTrain(){
        if(emptyStation())
            return null;

        Train longest = new Train(_station[0]);

        for(int i=0 ; i < _station.length && _station[i] !=null ; i++)
            if(longest.getDuration() < _station[i].getDuration())
            //there is train with most longer drive time
                longest = new Train(_station[i]);

        return longest;//the train with the longest drive time
    }//end of longestTrain
}//end of class 