package se.chalmers.krogkollen.utils;

/**
 *
 * Class handling the opening and closing hours of a pub
 */
public class OpeningHours {

    private int openingHour;
    private int closingHour;

    // TODO javadoc
    public OpeningHours(int openingHour, int closingHour){
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    // TODO javadoc
    public int getOpeningHour(){
        return this.openingHour;
    }

    // TODO javadoc
    public int getClosingHour(){
        return this.closingHour;
    }

    @Override
    public String toString(){
        return getOpeningHour()+" - "+getClosingHour();
    }
}
