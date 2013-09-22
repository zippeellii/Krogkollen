package se.chalmers.krogkollen.pub;

import com.google.android.gms.maps.model.LatLng;

/**
 * A standard pub object.
 *
 * @author Albin Garpetun
 * Created 2013-09-22
 */
public class Pub implements IPub {

    private String name;
    private String description;
    private String openingHours;
    private int ageRestriction;
    private int queueTime;
    private int queueTimeLastUpdatedTimestamp;
    private LatLng coordinates;
    private int id;

    /**
     * Instantiates the object, creating a dummy pub object.
     *
     * More constructors should be added, in case there is information missing.
     */
    public Pub () {
        this("Name", "Description", "00:00 - 00:00", 18, 10, 15, new LatLng(0,0), 0);
    }

    /**
     * Instantiates the object.
     *
     * @param name The name of the pub.
     * @param description The description of the pub.
     * @param openingHours The hours which the pub is open.
     * @param ageRestriction The age restriction of the pub.
     * @param queueTime The estimated time of the queue at the pub.
     * @param queueTimeLastUpdatedTimestamp The time since the last updating of the queue time occured.
     * @param coordinates The coordinates of the pub.
     * @param id The unique id for this pub.
     */
    public Pub (String name, String description, String openingHours, int ageRestriction, int queueTime,
                                            int queueTimeLastUpdatedTimestamp, LatLng coordinates, int id) {
        this.name = name;
        this.description = description;
        this.openingHours = openingHours;
        this.ageRestriction = ageRestriction;
        this.queueTime = queueTime;
        this.queueTimeLastUpdatedTimestamp = queueTimeLastUpdatedTimestamp;
        this.coordinates = coordinates;
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getOpeningHours() {
        return openingHours;
    }

    @Override
    public int getAgeRestriction() {
        return ageRestriction;
    }

    @Override
    public int getQueueTime() {
        return queueTime;
    }

    @Override
    public void setQueueTime(int queueTime) {
        // Not yet implemented
    }

    @Override
    public int getQueueTimeLastUpdatedTimestamp() {
        return queueTimeLastUpdatedTimestamp;
    }

    @Override
    public LatLng getCoordinates() {
        return coordinates;
    }

    @Override
    public int getID() {
        return id;
    }
}
