import java.util.ArrayList;

/**
 * Created by Branden on 2/24/2016.
 */
public class Plane {

    private Seat[][] firstClassSeats;
    private Seat[][] econClassSeats;
    private int numFirstClassSeats;
    private int numEconSeats;

    public Plane(String firstClassSeatInfo, String econClassSeatInfo )
    {
        createFirstClassSeats(firstClassSeatInfo);
        createEconSeats(econClassSeatInfo);
    }

    /**
     * Used for the constructor to create the two dimensional array for the first class seats
     * @param seatingInfo
     */
    private void createFirstClassSeats( String seatingInfo)
    {
        seatingInfo = seatingInfo.replace(" ", "");
        String[] fields = seatingInfo.split(",");
        String checkVal = fields[0]; //do no change the order of this statement due to pass by reference issues in parser

        Parser parseSeatingInfo = new Parser(fields);

        // convert the field demarking first class

        if ( checkVal.contains("First") )
        {
            int startRow = parseSeatingInfo.startRow;
            int endRow = parseSeatingInfo.endRow;
            char leftWindowSeat = parseSeatingInfo.leftWindowSeat;
            char leftAisleSeat = parseSeatingInfo.leftAisleSeat;
            char rightAisleSeat = parseSeatingInfo.rightAisleSeat;
            char rightWindowSeat = parseSeatingInfo.rightWindowSeat;

            int numRows = endRow - startRow + 1;
            int numSeatsPerRow = rightWindowSeat - leftWindowSeat + 1;
            numFirstClassSeats = numRows * numSeatsPerRow;
            //initialize our array
            firstClassSeats = new Seat[ numRows ][ numSeatsPerRow ];
            for( int i =  startRow; i <= endRow; i++)
            {
                for( int j = leftWindowSeat; j <= rightWindowSeat; j++)
                {
                    if( j == leftWindowSeat)
                    {
                        firstClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, leftWindowSeat, 'W');
                    }
                    else if( j == leftAisleSeat)
                    {
                        firstClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, leftAisleSeat, 'A');
                    }
                    else if( j == rightAisleSeat)
                    {
                        firstClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, rightAisleSeat, 'A');
                    }
                    else if( j == rightWindowSeat)
                    {
                        firstClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, rightWindowSeat, 'W');
                    }
                    else
                    {
                        firstClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, (char)j, 'C');
                    }


                }
            }

        }
        else
        {
            firstClassSeats = null;
        }

    }

    /**
     * Used for the constructor to create the two dimensial array for the economy class seats
     * @param seatingInfo
     */
    private void createEconSeats( String seatingInfo )
    {
        seatingInfo = seatingInfo.replace(" ", "");
        String[] fields = seatingInfo.split(",");
        String checkVal = fields[0]; //don't move the order of this state due to Parser manipulation

        Parser parseSeatingInfo = new Parser(fields);

        // convert the field demarking first class

        if ( checkVal.contains("Economy") )
        {
            int startRow = parseSeatingInfo.startRow;
            int endRow = parseSeatingInfo.endRow;
            char leftWindowSeat = parseSeatingInfo.leftWindowSeat;
            char leftAisleSeat = parseSeatingInfo.leftAisleSeat;
            char rightAisleSeat = parseSeatingInfo.rightAisleSeat;
            char rightWindowSeat = parseSeatingInfo.rightWindowSeat;

            int numRows = endRow - startRow + 1;
            int numSeatsPerRow = rightWindowSeat - leftWindowSeat + 1;
            numEconSeats = numRows * numSeatsPerRow;
            //initialize our array
            econClassSeats = new Seat[ numRows ][ numSeatsPerRow ];
            for( int i =  startRow; i <= endRow; i++)
            {
                for( int j = leftWindowSeat; j <= rightWindowSeat; j++)
                {
                    if( j == leftWindowSeat)
                    {
                        econClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, leftWindowSeat, 'W');
                    }
                    else if( j == leftAisleSeat)
                    {
                        econClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, leftAisleSeat, 'A');
                    }
                    else if( j == rightAisleSeat)
                    {
                        econClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, rightAisleSeat, 'A');
                    }
                    else if( j == rightWindowSeat)
                    {
                        econClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, rightWindowSeat, 'W');
                    }
                    else
                    {
                        econClassSeats[i-startRow][j-leftWindowSeat] = new Seat( i, (char)j, 'C');
                    }


                }
            }

        }
        else
        {
            econClassSeats = null;
        }

    }

    /**
     * private class to parser through the string defining an airplane
     */
    private class Parser
    {
        int startRow;
        int endRow;
        char leftWindowSeat;
        char leftAisleSeat;
        char rightAisleSeat;
        char rightWindowSeat;

        public Parser(String[] fields)
        {
            parseSeatDefs(fields);
        }

        /**
         * function to do the actual parsing for the class
         * @param fields a split up version of the string defining the seats
         */
        public void parseSeatDefs(String[] fields)
        {
            // convert "First 1-2" to "1" and "2"
            fields[0] = fields[0].replaceAll("[a-zA-Z]", "");
            String[] row = fields[0].split("-");
            startRow = Integer.valueOf(row[0]); // convert "1-2" to "1"
            endRow = Integer.valueOf(row[1]);

            /*
             * convert the value "Left: A-B" to "A" and "B"
             */
            String[] leftSeats = (fields[1].split(":"));
            String[] seatLettersLeft = leftSeats[1].split("-");
            leftWindowSeat = seatLettersLeft[0].charAt(0);
            leftAisleSeat = seatLettersLeft[1].charAt(0);

            //convert "Right: C-D" to "C" and "D"
            String rightSeats = fields[2].split(":")[1];
            String[] seatLetterRight = rightSeats.split("-");
            rightAisleSeat = seatLetterRight[0].charAt(0);
            rightWindowSeat = seatLetterRight[1].charAt(0);
        }

    }

    /**
     * find the largest row of open seats and place passengers from a group in those seats
     *
     * @return the seats with passengers assigned, null if unable to find enough seats for everyone
     */
    public Seat[] assignPassengersToSeats(Passenger p[], String classPreference)
    {
        return null;
    }

    /**
     * find open first class seats
     * @return the open seats as an array
     */
    public ArrayList<Seat> getOpenFirstClassSeats()
    {
        ArrayList<Seat> openSeats = new ArrayList<>();

        // search for open seats
        for(int i = 0; i < firstClassSeats.length; i++)
        {
            for( int j = 0; j < firstClassSeats[0].length; j++)
            {
                if( firstClassSeats[i][j].getPassenger() == null )
                {
                    openSeats.add(firstClassSeats[i][j]);
                }
            }
        }
        return openSeats;
    }

    /**
     * find open economy class seats
     * @return an arrayList with found open economy class seats
     */
    public ArrayList<Seat> getOpenEconClassSeats()
    {
        ArrayList<Seat> openSeats = new ArrayList<>();
        for( int i = 0; i < econClassSeats.length; i++)
        {
            for( int j = 0; j < econClassSeats[0].length; j++)
            {
                if( econClassSeats[i][j].getPassenger() == null )
                {
                    openSeats.add(econClassSeats[i][j]);
                }
            }
        }
        return openSeats;
    }

    /**
     * find seats that are filled
     * @return the seats that are filled as an array
     */
    public Seat[] getFilledSeats()
    {
        Seat p[] = null;
        return p;
    }




}
