import java.util.ArrayList;

/**
 * Created by Branden on 2/28/2016.
 */
public class Tester
{
    public static void main( String args[] )
    {
        testPlaneClass();

    }

    /**
     * test the aiplane class for working constructor
     */
    public static void testPlaneClass()
    {

        Plane airplane = new Plane( "First 1-2, Left: A-B, Right: C-D", "Economy 10-29, Left: A-C, Right: D-F" );
        // all seats should be open and there should be 8 first class seats, and 20 economy seats

        System.out.println("Testing if we have the correct number of seats in first class");
        ArrayList<Seat> openFirstClassSeats = airplane.getOpenFirstClassSeats();
        int numFirstClassSeats = 0;
        if ( openFirstClassSeats == null || (numFirstClassSeats = openFirstClassSeats.size()) != 8){
            System.out.println("\tTest FAILED");
            System.out.println("\tCreated " + numFirstClassSeats + " seats and should have created 8 seats.");
        }
        else
        {
            System.out.println("\tTest PASSED");
        }

        System.out.println("Testing if we have the correct number of seats in Econ Class");
        ArrayList<Seat> openEconSeats = airplane.getOpenEconClassSeats();
        int numEconSeats = 0;
        if( openEconSeats == null || (numEconSeats = openEconSeats.size()) != 120 )
        {
            System.out.println("\tTest FAILED");
            System.out.println("\tCreated " + numEconSeats + " seats and should have created 20 seats");
        }
        else
        {
            System.out.println("\tTest PASSED");
        }



    }

    public static void testReservationSystem()
    {
        String filename = "sample_input.txt";
        String filepath = "C:\\Users\\Branden\\Git\\ReservationSystem\\sample_CL34.txt";
        String someArgs[] = {filename+"\\"+filepath};
        ReservationSystem.main(someArgs);
    }
}
