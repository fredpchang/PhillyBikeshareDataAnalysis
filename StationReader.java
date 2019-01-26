import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reads in the station csv file, parses the file, creates Station objects and 
 * adds them to an ArrayList
 * @author fredchang
 *
 */
public class StationReader {

	File stationFile;

	/**
	 * 
	 * @param fileName the stationFile csv file
	 */
	StationReader(String stationFile) {
	
		this.stationFile = new File(stationFile);
					
	}
	
	/**
	 * Reads and parses stationFile csv file
	 * Creates Station objects
	 * @return ArrayList of Station objects
	 */
	public ArrayList<Station> readStationFile() throws FileNotFoundException{
		ArrayList<Station> stations = new ArrayList<>();		
		
		try  {
			Scanner scan= new Scanner(this.stationFile);
			//read in first line with column headers
			scan.nextLine();			
		    
		    while (scan.hasNext()) {
		    	//read in each token of the file	    	
		    	scan.useDelimiter(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
		    	String stationTemp = scan.next();
		    	int stationId = Integer.parseInt(stationTemp);
		    	String stationName = scan.next();
		    	stationName = stationName.replaceAll("^\"|\"$", "");
		    	String goLiveDate = scan.next();
		    	scan.useDelimiter(",|\n|\r");
		    	String stationStatus = scan.next();
		    	  	
		    	Station s = new Station(stationId, stationName, goLiveDate, stationStatus.trim());
		    	stations.add(s);
		    	scan.nextLine();
		    }
		    scan.close();
		 } catch(IOException e) {
			  e.printStackTrace();
		 }		
		return stations;
	}
	


	
}
