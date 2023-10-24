/**
 * Represents time - hours:minutes.
 * @author Michal Danishevsky
 * @vertion 20.04.2020
 */
public class Time1{

    private int _hour;
    private int _minute;

    final int MAXIMUM_MINUTES=60;
    final int MINIMUM_MINUTES=0;
    final int MAXIMUM_HOURS=24;
    final int MINIMUM_HOURS=0;

    /**
     * Constructs a Time1 object
     * @param h Time's hour
     * @param m Time's minute
     */
    public Time1(int h,int  m){
        /* _hour get the given hour if given hour in the
         * range of hour and get the value 0 if not
         */
        if(h < MAXIMUM_HOURS && h > MINIMUM_HOURS)
            _hour=h;
        else
            _hour=0;

        /* _minute get the given minute if given minute in the
         * range of minute and get 0 if not
         */
        if(m < MAXIMUM_MINUTES && m > MINIMUM_MINUTES)
            _minute=m;
        else
            _minute=0;

    }//end of Time1

    /**
     * Copy the hour and minutes from other time to this time
     * @param other The time object from which to construct the new time
     */
    public Time1(Time1 other){

        _hour=other._hour;
        _minute=other._minute;

    }//end of Time1

    /**
     * Return the time's hour
     * @return Time's hour
     */
    public int getHour(){ 

        return _hour;

    }//end of getHour

    /**
     * Return the time's minute
     * @return Time's minute
     */
    public int getMinute(){

        return _minute;

    }//end of getMinute

    /**
     * Sets the time's hour to new given hour if the new given hour 
     * in the range of hour if not the hour stay the same hour
     * @param num New hour
     */
    public void setHour(int num){

        if(num < MAXIMUM_HOURS && num > MINIMUM_HOURS)
            _hour = num;

    }//end of setHour

    /**
     * Sets the time's minute to new given minute if the new given minute 
     * in the range of minute if not the minute stay the same minute
     * @param num New minute
     */
    public void setMinute(int num){

        if(num < MAXIMUM_MINUTES && num > MINIMUM_MINUTES)
            _minute = num;

    }//end of setMinute

    /**
     * Returns a string representation of this time ("hh:mm")
     * @return This time in pattern hh:mm
     */
    public String toString(){

        if(_hour < 10 && _minute < 10)
            return "0" + _hour + ":" + 0 + _minute;
        //the minute and the hour are smaller than ten 

        else if(_hour < 10) 
            return "0" + _hour + ":" + _minute;
        //only the minute is bigger than ten

        else if(_minute < 10) 
            return  _hour + ":" + 0 + _minute;
        //only the hour is bigger than ten

        else 
            return _hour + ":" + _minute;
        //the minute and the hour are bigger than ten

    }//end of toString

    /**
     * Return how much minutes passed from the midnight (00:00)
     * @return The minutes from midnight
     */
    public int minFromMidnight(){

        return (_hour * MAXIMUM_MINUTES + _minute);

    }//end of minFromMidnight

    /**
     * Checks if the received time is equal to this time
     * @param other The time to be compared with this time
     * @return True if they are the same time and 
     * false if they are differents times
     */
    public boolean equals(Time1 other){

        if(_minute == other._minute && _hour == other._hour)
            return true;//there is the same time
        else
            return false;//they are differents times

    }//end of equals

    /**
     * Check if this time before the other time
     * @param other The time to be compared with this time
     * @return True if this time before the other time else it is return false
     */
    public boolean before(Time1 other){ 

        if(_hour == other._hour)
            if(_minute < other._minute)
                return true;//this time before the other time
        if(_hour < other._hour)
            return true;//this time before the other time
        else
            return false;/* they are the same time or
        the other time before this time */

    }//end of before

    /**
     * Check if this time after the other time
     * @param other The time to be compared with this time
     * @return True if this time before the other time else it is return false
     */
    public boolean after(Time1 other){

        if(other.before(this))
            return true;/*other time is before this time so 
        this time after the other time*/
        else
            return false;/*the times are equals or other
        time is after this time*/

    }//end of after

    /**
     * Calculates the difference (in minutes) between two times.
     * Assumption: this time is after other time
     * @param other The time to check the difference
     * @return The difference in minute
     */
    public int difference(Time1 other){

        int totalMinuteDifference;
        int minuteDifference = _minute - other._minute;//minutes difference
        int hourDifference = _hour - other._hour;//hour difference

        /*minutes can't to be negetive so if it
        happend need convert hour to minutes*/
        if(minuteDifference < MINIMUM_MINUTES){
            hourDifference--;
            minuteDifference = minuteDifference + MAXIMUM_MINUTES;
        }//end of if

        //total the difference in minutes
        totalMinuteDifference = minuteDifference + MAXIMUM_MINUTES * hourDifference;
        return totalMinuteDifference;

    }//end of difference

    /**
     * Add minutes to this time to make new time
     * @param num Minutes to add
     * @return The new time
     */
    public Time1 addMinutes(int num){

        Time1 newTime = new Time1(this);//make new time
        newTime._minute += num;//add the minutes
        num = newTime._minute / MAXIMUM_MINUTES;//only hours stay to add
        newTime._minute %= MAXIMUM_MINUTES;//they are only 60 minuts in hour
        newTime._hour += num;//add the hours
        newTime._hour %= MAXIMUM_HOURS;//they are only 24 hours in day

        /*minutes can't to be negetive so if it
        happend need convert hour to minutes*/
        if(newTime._minute < MINIMUM_MINUTES){
            newTime._hour--;
            newTime._minute += MAXIMUM_MINUTES;
        }//end of if
        /*hours can't to be negetive so if it
        happend need convert day to hours*/
        if(newTime._hour < MINIMUM_HOURS)
            newTime._hour += MAXIMUM_HOURS;

        return new Time1(newTime);

    }//end of addMinutes
}//end of class 