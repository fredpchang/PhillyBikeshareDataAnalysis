import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads in the trip csv file, parses the file, creates Trip objects and 
 * adds them to an ArrayList
 * @author fredchang
 *
 */
public class TripReader {
	
	File tripFile;
	
	/**
	 * 
	 * @param fileName the tripFile csv file
	 */
	TripReader(String tripFile) {
	
		this.tripFile = new File(tripFile);
				
	}
	
	/**
	 * Reads and parses tripFile csv file
	 * Creates Trip objects
	 * @return ArrayList of Trip objects
	 */
	
	public ArrayList<Trip> readTripFile() throws FileNotFoundException {
		ArrayList<Trip> trips = new ArrayList<>();		
		
		try  {
			Scanner scan= new Scanner(this.tripFile);
			//read in first line with column headers
			scan.nextLine();			
		    
		    while (scan.hasNext()) {
		    	//read in each token of the file
		    	String oneLine = scan.nextLine();
		    	String[] oneLineTemp = oneLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		    	
		    	//tripID
		    	String tripIdTemp = oneLineTemp[0];
		    	int tripId = Integer.parseInt(tripIdTemp);
		    	
		    	//tripDuration
				String tripDurationTemp = oneLineTemp[1];
				int tripDuration = Integer.parseInt(tripDurationTemp);
				
				//tripStartTime
				String tripStartTimeTemp = oneLineTemp[2];
				String tripStartTime = tripStartTimeTemp.replaceAll("^\"|\"$", "");
				
				//tripEndTime
				String tripEndTimeTemp = oneLineTemp[3];
				String tripEndTime = tripEndTimeTemp.replaceAll("^\"|\"$", "");
				
				//tripStartStation
				String tripStartStationTemp = oneLineTemp[4];
				int tripStartStation = Integer.parseInt(tripStartStationTemp);			
				
				//tripStartLat
				String tripStartLatTemp = oneLineTemp[5];
				tripStartLatTemp = checkNull(tripStartLatTemp);
				double tripStartLat = Double.parseDouble(tripStartLatTemp);
				
				//tripStartLong
				String tripStartLongTemp = oneLineTemp[6];
				tripStartLongTemp = checkNull(tripStartLongTemp);
				double tripStartLong = Double.parseDouble(tripStartLongTemp);
				
				//tripEndStation
				String tripEndStationTemp = oneLineTemp[7];
				int tripEndStation = Integer.parseInt(tripEndStationTemp);
		
				//tripEndLat
				String tripEndLatTemp = oneLineTemp[8];
				tripEndLatTemp = checkNull(tripEndLatTemp);
				double tripEndLat = Double.parseDouble(tripEndLatTemp);
				
				//tripEndLong
				String tripEndLongTemp = oneLineTemp[9];
				tripEndLongTemp = checkNull(tripEndLongTemp);
				double tripEndLong = Double.parseDouble(tripEndLongTemp);
				
				//bikeId
				String bikeIdTemp = oneLineTemp[10];
				bikeIdTemp = bikeIdTemp.replaceAll("^\"|\"$", "");
				int bikeId = Integer.parseInt(bikeIdTemp);
				
				//planDuration
				String planDurationTemp = oneLineTemp[11];
				int planDuration = Integer.parseInt(planDurationTemp);
				
				//tripRouteCategory
				String tripRouteCategory = oneLineTemp[12].replaceAll("^\"|\"$", "");
				
				//passHolderType
				String passHolderType = oneLineTemp[13];
		    	
		    	
		    	Trip t = new Trip(tripId, 
		    					  tripDuration,
		    					  tripStartTime,
		    					  tripEndTime,
		    					  tripStartStation,
		    					  tripStartLat,
		    					  tripStartLong,
		    					  tripEndStation,
		    					  tripEndLat,
		    					  tripEndLong,
		    					  bikeId,
		    					  planDuration,
		    					  tripRouteCategory,
		    					  passHolderType
		    					  );
		    	trips.add(t);
		    }
		    scan.close();
		 } catch(IOException e) {
			  e.printStackTrace();
		 }		
		return trips;
	}
	
	//Method that replaces empty cell with 0
	public String checkNull(String s) {
		if (s.equals("\"\"")) {
			return "0";
		}
		else if (s.equals(null)) {
			return "0";
		}
		
		return s;
	}
	
	
}
