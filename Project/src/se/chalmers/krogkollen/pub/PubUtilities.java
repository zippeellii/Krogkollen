package se.chalmers.krogkollen.pub;

import java.util.LinkedList;
import java.util.List;

/**
 * A singleton holding a list of all the pubs, and is used to load data from the server.
 *
 * For now this class only contains hardcoded values, since we don't have support for a server.
 *
 * @author Albin Garpetun
 * Created 2013-09-22
 */
public class PubUtilities {

    private List<IPub> pubList = new LinkedList<IPub>();
    private static PubUtilities instance = null;

    protected PubUtilities() {
        // Exists only to defeat instantiation.
    }

    /**
     * Creates an instance of this object if there is none, otherwise it simply returns the old one.
     *
     * @return The instance of the object.
     */
    public static PubUtilities getInstance() {
        if(instance == null) {
            instance = new PubUtilities();
        }
        return instance;
    }

    /**
     * Loads the pubs from the server and puts them in the list of pubs.
     *
     * For now this method only adds hardcoded pubs into the list.
     */
    public void loadPubList() {
        pubList.add(new Pub("Hubben 2.0", "IT's pub", "22:00-05:00", 18, 4, 57.688221, 11.979539,100, 1));
        pubList.add(new Pub("Kajsabaren", "E", "18:00-01:00", 18, 0, 57.688248, 11.978506,100, 2));
        pubList.add(new Pub("Databasen", "D", "19:00-01:00", 18, 0, 57.687502, 11.978667,100, 3));
        pubList.add(new Pub("Zaloonen", "Z", "19:00-01:00", 18, 0, 57.689094,11.979308,100, 4));
        pubList.add(new Pub("Bulten", "-", "18:00-01:00", 18, 0, 57.68924,11.978487,100, 5));
        pubList.add(new Pub("Winden", "M", "18:00-01:00", 18, 0, 57.689685,11.978004,100, 6));
        pubList.add(new Pub("Focus", "TF", "17:00-03:00", 18, 0, 57.691316,11.975523,100, 7));
        pubList.add(new Pub("Golden I", "I", "19:00-03:00", 18, 0, 57.693277,11.975391,100, 8));
        pubList.add(new Pub("Sigurd", "A", "19:00-03:00", 18, 0, 57.687818,11.976682,100, 9));
        pubList.add(new Pub("Röda rummet", "?", "19:00-03:00", 18, 0, 57.687783,11.976274,100, 10));
        pubList.add(new Pub("Järnvägspub", "?", "18:00 - 01:00", 18, 0, 57.688483,11.975867,100, 11));
        pubList.add(new Pub("Gasquen", "-", "20:00 - 03:00", 18, 0, 57.688862,11.975223,100, 12));
        pubList.add(new Pub("J.A. Pripps", "-", "17:00 - 02:00", 18, 0, 57.688988,11.974579,100, 13));
        pubList.add(new Pub("GasTown", "?", "19:00 - 01:00", 18, 0, 57.691144,11.978077,100, 14));
        pubList.add(new Pub("FortNOx", "?", "19:00 - 01:00", 18, 0, 57.691144,11.978077,100, 15));
        pubList.add(new Pub("Spritköket", "?", "19:00 - 01:00", 18, 0, 57.691144,11.978077,100, 16));
        pubList.add(new Pub("Club Avancez", "-", "19:00 - 03:00", 18, 0, 57.693289,11.976017,100, 17));
        pubList.add(new Pub("Pub P", "?", "18:00 - 01:00", 18, 0, 57.706922,11.939643,100, 18));
        pubList.add(new Pub("11:an", "?", "18:00 - 01:00", 18, 0, 57.706555,11.936639,100, 19));
    }

    /**
     * Returns the list of pubs.
     *
     * @return The list of pubs.
     */
    public List<IPub> getPubList() {
        return pubList;
    }

    public IPub getPub(int id){
        return pubList.get(id);
    }

}
