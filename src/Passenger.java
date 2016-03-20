/**
 * Created by Branden on 2/24/2016.
 */
public class Passenger
{
    private char reservationDesignation;
    private String groupName;
    private String name;
    private String assignedSeat;

    public Passenger(char reservationDesignation, String groupName, String passengerName)
    {
        this.reservationDesignation = reservationDesignation;
        this.groupName = groupName;
        this.name = passengerName;
        this.assignedSeat = null;
    }

    public Passenger(char reservationType, String groupName, String passengerName, String assignedSeat)
    {
        this.reservationDesignation = reservationType;
        this.groupName = groupName;
        this.name = passengerName;
        this.assignedSeat = assignedSeat;

    }

    public void setAssignedSeat(String assignedSeat)
    {
        this.assignedSeat = assignedSeat;
    }

    public String getPassengerSeatAndName()
    {
        return assignedSeat + ": " + name;
    }

    public String getPassengerInfo()
    {
        if( groupName != null)
        {
            return assignedSeat + ", " + reservationDesignation + ", " + groupName + ", " + name;
        }

        return assignedSeat + ", " + reservationDesignation  + ", " + name;
    }

}
