import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Branden on 2/24/2016.
 */
public class ReservationSystem {

    String file;
    private static final String DEFAULTFIRSTCLASS = "First 1-2, Left: A-B, Right: C-D";
    private static final String DEFAULTECONCLASS = "Economy 10-29, Left: A-C, Right: D-F";
    Plane airplane;


    public static void main(String args[]) {


        ReservationSystem reservation = new ReservationSystem( args[0] );
        char choice = reservation.mainPrompt();
        while( choice != 'q') {
            switch (choice) {
                case ('p'):
                    reservation.singleReservationPrompt();
                    break;
                case ('g'):
                    reservation.groupReservationPrompt();
                    break;
                case ('c'):
                    reservation.cancelReservation();
                    break;
                case ('a'):
                    reservation.printSeatingAvailability();
                    break;
                case ('m'):
                    reservation.printManifest();
                    break;
                case ('Q'):
                    break;
                default:
                    choice = reservation.mainPrompt();
            }
        }

    }

    /* properties for ReservationSystem */


    /**
     *
     * @param file filename and path of the file to be open
     */
    public ReservationSystem( String file)
    {
        this.file = file;
        ArrayList<String> parsedFileData = openFile();
        if(parsedFileData != null )
        {
            String[] airplaneInfo = parsedFileData.get(0).split(";");
            airplane = new Plane(airplaneInfo[0], airplaneInfo[1] );

            for( int i = 1; i < parsedFileData.size(); i++ )
            {
                String passengerInfo = parsedFileData.get(i);
                parsePassengerInfo( passengerInfo, airplane );
            }
        }
        else
        {
            airplane = new Plane(DEFAULTFIRSTCLASS, DEFAULTECONCLASS);

        }

    }

    /**
     * display main prompt to user and get choice
     * @return the character they chose as their choice
     */
    public char mainPrompt()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Add [P]assenger, Add [G]roup, [C]ancel Reservations, " +
                "Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
        String choice = in.nextLine();
        choice = choice.toLowerCase();
        return choice.toCharArray()[0];

    }

    public void singleReservationPrompt()
    {

    }

    public void groupReservationPrompt()
    {

    }

    public void cancelReservation()
    {

    }

    public void printSeatingAvailability()
    {

    }

    public void printManifest()
    {

    }

    /**
     * Parse the passenger info from a single string line that we got
     * from opening the file. Then assign passengers into the plane
     * when the info had been parsed.
     * @param passengerInfo  a line of passenger info from the file in the form of "1D, G, cssjsu, Birdie Ross"
     */
    private void parsePassengerInfo( String passengerInfo, Plane aeroplane )
    {
        String[] fields = passengerInfo.split(",");

        //split the row and letter up in the seat
        String seat = fields[0].split("\\D")[0];

        int seatRow = Integer.parseInt(seat);
        char seatletter = fields[0].charAt(fields[0].length()-1);

        //are we dealing with a group reservation or individual reservation
        if( fields[1].compareTo(" I") == 0 ) //single
        {
            char reservationDesignation = 'I';
            String name = fields[fields.length-1];
            Passenger p = new Passenger( reservationDesignation, null, name, fields[0] );
            aeroplane.setSeat(seatRow, seatletter, p);

        }
        else //group
        {
            char reservatinoDesignation = 'G'; //yes hardcoded and bad practice
            String groupName = fields[2];
            String name = fields[3];
            Passenger p = new Passenger( reservatinoDesignation, groupName, name, fields[0]);
            aeroplane.setSeat(seatRow, seatletter, p);

        }

    }

    /**
     * opens the file containing info about the plane and current reservations
     */
    private ArrayList<String> openFile()
    {
        ArrayList<String> parsedFileData = new ArrayList<>();


        File fl = new File(file);
        if( fl.exists() )
        {

            try{
                FileInputStream fis = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fis) );
                String strLine;
                while( (strLine = br.readLine()) != null)
                {
                    parsedFileData.add(strLine);
                }
            }
            catch (Exception e)
            {
                //this should never happen since we check for this error
                System.err.println("File not found exceptin " + e);
                e.printStackTrace();
                System.exit(2);
            }

            return parsedFileData;

        }
        else
        {
            try
            {
                fl.createNewFile();

            } catch (IOException e)
            {
                System.err.println("IO exception, check that a legal file name was used");
                e.printStackTrace();
                System.exit(2);
            }
            return null;
        }


    }

    /**
     * Save the current reservations and plane info into the file.
     */
    public void saveFile()
    {

        File fl = new File(file);
        try
        {
            FileWriter fw = new FileWriter(fl);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(airplane.planeInfo());
            ArrayList<Seat> fcSeats = airplane.getFilledFirstClassSeats();
            ArrayList<Seat> econSeats = airplane.getFilledEconSeats();

            for ( Seat s : fcSeats)
            {
                bw.write(s.getPassenger().getPassengerInfo() + "\n");
            }

            for( Seat s: econSeats)
            {
                bw.write(s.getPassenger().getPassengerInfo() + "\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }



    }

}
