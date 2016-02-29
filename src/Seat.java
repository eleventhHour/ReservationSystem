/**
 * Created by Branden on 2/24/2016.
 */
public class Seat {

    /* some properties of the seat class */
    private int row;
    private char seatletter;
    private char seatDesignation;
    private Passenger pssngr;

    public Seat()
    {
        row = -1;
        seatletter = '\0';
        pssngr = null;
    }

    public Seat(int row, char seatLetter, char seatDesignation)
    {
        this.row = row;
        this.seatletter = seatLetter;
        this.seatDesignation = seatDesignation;
        pssngr = null;
    }

    public void setPassenger(Passenger p)
    {
        pssngr = p;
    }

    public Passenger getPassenger()
    {
        return pssngr;
    }

}
