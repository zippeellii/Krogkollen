package se.chalmers.krogkollen.utils;

import java.sql.Time;

/**
 * Created with IntelliJ IDEA.
 * User: filipcarlen
 * Date: 2013-09-27
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 *
 * Class handling the opening and closing hours of a pub
 */
public class OpeningHours {

    private int openingHour;
    private int closingHour;

    public OpeningHours(int openingHour, int closingHour){
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public int getOpeningHour(){
        return this.openingHour;
    }

    public int getClosingHour(){
        return this.closingHour;
    }

    public String toString(){
        return "Opening hour: "+getOpeningHour()+" Closing hour: "+getClosingHour();
    }
}
