import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Using an ArrayList of Station and Trip objects, this class does analysis of Indego Bikeshare
 * @author fredchang
 *
 */
public class Analyzer {

	ArrayList<Station> stations;
	ArrayList<Trip> trips;
	
	/**
	 * Takes an ArrayList of Station objects
	 * This ArrayList is created by the StationReader object
	 * @param stations
	 */
	Analyzer(ArrayList<Station> stations, ArrayList<Trip> trips){
		this.stations = stations;
		this.trips = trips;
	}
	
	
	/**
	 * Creates a new ArrayList with only Stations that are active
	 * @return ArrayList of Station objects
	 */
	public ArrayList<Station> analyzeStationStatus(String status){
		ArrayList<Station> statusStations = new ArrayList<>();
		for(Station s: stations) {
			if(s.getStationStatus().equals(status)) {
				statusStations.add(s);
			}
		}
		return statusStations;
	}
	
	/**
	 * Creates a new ArrayList with only Stations with Go-Live Date in 2017 and are still active
	 * @param year
	 * @param status
	 * @return ArrayList of Station objects
	 * @throws ParseException
	 */
	public ArrayList<Station> analyzeGoLive(String year, String status) throws ParseException{
		ArrayList<Station> activeStationsGoLive2017 = new ArrayList<>();
		for(Station s: stations) {
			if(s.getStationStatus().equals(status) && s.getGoLiveYear().equals(year)) {
				activeStationsGoLive2017.add(s);
			}
		}
		return activeStationsGoLive2017;
	}

	/**
	 * Creates a new ArrayList with only Trips that originated at Amtrak 30th Street Station
	 * @return ArrayList of Trip objects
	 */
	public ArrayList<Trip> analyzeStationOriginatingTrips(int stationId){
		ArrayList<Trip> amtrakStationTrips = new ArrayList<>();
		for(Trip t: trips) {
			if(t.getTripStartStation() == stationId) {
				amtrakStationTrips.add(t);
			}
		}
		return amtrakStationTrips;
	}

	/**
	 * Creates a variable that counts the number of walk-up per month and returns most popular month
	 * @return mostPopularMonth
	 * @throws ParseException 
	 */
	public String analyzeMostPopularWalkupMonth() throws ParseException {
		String mostPopularMonth = "N/A";
		int walkupCounter4 = 0;
		int walkupCounter5 = 0;
		int walkupCounter6 = 0;
		for (Trip t: trips) {
			if(t.getPassHolderType().equals("Walk-up") || t.getPassHolderType().equals("Day Pass")) {
				if(t.getTripStartMonth().equals("4")) {
					walkupCounter4++;
				}
				if(t.getTripStartMonth().equals("5")) {
					walkupCounter5++;
				}
				if(t.getTripStartMonth().equals("6")) {
					walkupCounter6++;
				}
			}
		}
		if (walkupCounter4 > walkupCounter5 && walkupCounter4 > walkupCounter6) {
			mostPopularMonth = "April";
		} else if (walkupCounter5 > walkupCounter6) {
			mostPopularMonth = "May";
		}
		else {
			mostPopularMonth = "June";
		}
		return mostPopularMonth;
	}
	
	/**
	 * Creates a variable that calculates the average duration of bike rentals
	 * @return averageDurationInt
	 */
	public int analyzeAverageDuration() {
		int averageDurationInt = 0;
		float averageDuration = 0.0f;
		float sum = 0.0f;
		for (Trip t: trips) {
			sum = sum + t.getTripDuration();
		}
		if (trips.size() > 0) {
			averageDuration = sum / trips.size();
			averageDurationInt = Math.round(averageDuration);
		} else {
			averageDurationInt = 0;
		}
		return averageDurationInt;	
	}
	
	/**
	 * Creates a variable that counts the number of trips from 10am to 1pm on the same day
	 * @return numTripsTenToOne
	 * @throws ParseException 
	 */
	public int analyzeTimePeriodTrips(int startHour, int endHour) throws ParseException {
		SimpleDateFormat fullFormatter = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		int numTrips = 0;
		for (Trip t: trips) {
		
			Date tStartDate = fullFormatter.parse(t.getTripStartTime());
			Date tEndDate = fullFormatter.parse(t.getTripEndTime());
			
			SimpleDateFormat outputFormatter = new SimpleDateFormat("MM-dd-yyyy");
			String tStartDateParsed = outputFormatter.format(tStartDate);
			String tEndDateParsed = outputFormatter.format(tEndDate);
			
			SimpleDateFormat hourFormatter = new SimpleDateFormat("HH");
			int tStartDateHours = Integer.parseInt(hourFormatter.format(tStartDate));
			int tEndDateHours = Integer.parseInt(hourFormatter.format(tEndDate));
			
			if (tStartDateParsed.equals(tEndDateParsed)) {
				if (tStartDateHours>=startHour && tEndDateHours<endHour) {
					numTrips++;
				}
			}
		
		}
		return numTrips;
	}
	
	/**
	 * Calculates most used bike 
	 * @return bikeID
	 */
	public String analyzeMostUsedBike(){
		ArrayList<String> allBikeId = new ArrayList<>();
		for (Trip t: trips) {
			allBikeId.add(Integer.toString(t.getBikeId()));
		}
		int max = 0;
		int current = 0;
		String currentBikeId = null;
		Set<String> unique = new HashSet<String>(allBikeId);
			
			for (String id : unique) {
				current = Collections.frequency(allBikeId, id);
				if (max < current) {
					max = current;
					currentBikeId = id;
				}
			}
		return "bikeId " + currentBikeId + " used " + max + " times";
	}
	
	//FilePrinter Methods
	
	/**
	 * 
	 * Returns name of Station
	 * @param stationId
	 * @return stationName
	 */
	public String analyzeStationName(int stationId) {
		String stationName = "n/a";
		for (Station s: stations) {
			if (s.getStationId() == stationId) {
				stationName = s.getStationName();
			}
		}
		return stationName;
	}
	
	/**
	 * 
	 * Calculates total number of trips by station
	 * @param stationId
	 * @return numTrips
	 */
	public int analyzeTotalTripsByStation(int stationId) {
		int numTrips = 0;
		for (Trip t: trips) {
			if (t.getTripStartStation() == stationId) {
				numTrips++;
			}
			//ENABLE IF CALCULATING TRIPS BY NUM TRIPS ORIGINATING AND TERMINATING AT STATION
//			if (t.getTripEndStation() == stationId) {
//				numTrips++;
//			}
		}
		return numTrips;
	}
	
	/**
	 * 
	 * Calculates average trip duration by station
	 * @param stationId
	 * @return averageDuration (in minutes)
	 */
	public String analyzeAverageTripDurationByStation(int stationId) {
		float averageDuration = 0.0f;
		float sumDuration = 0.0f;
		int numTrips = 0;
		for (Trip t: trips) {
			if (t.getTripStartStation() == stationId) {
				sumDuration = sumDuration + t.getTripDuration();
				numTrips ++;
			}
		}
		averageDuration = sumDuration / numTrips;
		DecimalFormat df = new DecimalFormat("#.00");	
		return df.format(averageDuration);	
	}
	
	/**
	 * 
	 * Calculates max trip duration by station
	 * @param stationId
	 * @return maxDuration (in minutes)
	 */
	public int analyzeMaxDurationByStation(int stationId) {
		int maxDuration = 0;
		for (Trip t: trips) {
			if (t.getTripStartStation() == stationId) {
				if (maxDuration < t.getTripDuration()) {
					maxDuration = t.getTripDuration();
				}
			}
		}
		return maxDuration;
	}
	
	/**
	 * 
	 * Calculates number of one way trips by station
	 * @param stationId
	 * @return numTrips
	 */
	public int analyzeNumOneWayTrips(int stationId) {
		int numTrips = 0;
		for (Trip t: trips) {
			if (t.getTripStartStation() == stationId) {
				if (t.getTripRouteCategory().equals("One Way")) {
					numTrips++;
				}
			}
		}
		return numTrips;
	}
	
	/**
	 * 
	 * Calculates difference in number of start and end trips by station
	 * @param stationId
	 * @return numDifference
	 */
	public int analyzeNumStartEndTripDiff(int stationId) {
		int numDifference = 0;
		int numStart = 0;
		int numEnd = 0;
		for (Trip t: trips) {
			if (t.getTripStartStation() == stationId) {
				numStart++;
			}
			if (t.getTripEndStation() == stationId) {
				numEnd++;
			}
		}
		numDifference = numStart - numEnd;
		
		return numDifference;
	}
	
	
	// EXTRA CREDIT
	/**
	 * 
	 * Calculates all the stations that are in close proximity to each other
	 * @return 
	 */
	public String analyzeCloseProximity(){	
		String closeProximity = "";
		
		Map<Integer, HashSet<Integer>> proximity = new HashMap<>();
		
		//For all stations
		for(Station a: stations) {
			HashSet<Integer> hset = new HashSet<>();
			int stationA = a.getStationId();
			double stationALat = 0;
			double stationALong = 0;
			
			//Check if trip stationId = station
			for (Trip t: trips) {
				if (t.getTripStartStation() == stationA) {
					stationALat = t.getTripStartLat();
					stationALong = t.getTripStartLong();
				}
			}

			//For all trips
			for (Trip b: trips) {
				int stationB = b.getTripStartStation();
				
				double stationBLat = b.getTripStartLat();
				double stationBLong = b.getTripStartLong();

				double diffStartLat = Math.abs(stationALat - stationBLat);
				
				double diffStartLong = Math.abs(stationALong - stationBLong);
				
				double two = 2.000000;
				double difference = (diffStartLat + diffStartLong) / two;
				float diff = (float)difference;
				
				double compValue = 0.020000;
				if (diff < compValue) {
					hset.add(stationB);
				}
			}	
			proximity.put(stationA, hset);
			}
			//Print out result
			for (Map.Entry<Integer, HashSet<Integer>> entry : proximity.entrySet()) {
				System.out.println(entry.getKey() + "---" + entry.getValue());
		}


		return closeProximity;
	}
	
	
	
	
	// EXTRA CREDIT
	
	/**
	 * Calculates least popular end station
	 * @return currentStationId
	 */
	public String analyzeLeastPopularEndStation(){
		  ArrayList<String> allStationId = new ArrayList<>();
		  for (Trip t: trips) {
		    allStationId.add(Integer.toString(t.getTripEndStation()));
		  }
		  int min = 10000;
		  int current = 0;
		  String currentStationId = null;
		  Set<String> unique = new HashSet<String>(allStationId);
		    
		    for (String id : unique) {
		      current = Collections.frequency(allStationId, id);
		      if (min > current) {
		        min = current;
		        currentStationId = id;
		      }
		    }
		  return currentStationId;
	}
	
	public String analyzePassholderDistribution(){
		String passholderDistribution = "";
		int daypassWalkupCounter = 0;
		int indego30Counter = 0;
		int indego365Counter = 0;
		int indegoflexCounter = 0;
		for (Trip t: trips) {
			if(t.getPassHolderType().equals("\"Walk-up\"") || t.getPassHolderType().equals("\"Day Pass\"")) {
				daypassWalkupCounter++;
			
			}
			if(t.getPassHolderType().equals("\"Indego30\"")) {
				indego30Counter++;
			}
			if(t.getPassHolderType().equals("\"Indego365\"")) {
				indego365Counter++;
			}
			if(t.getPassHolderType().equals("\"IndegoFlex\"")) {
				indegoflexCounter++;
			}
		}
		
		System.out.println("Day Pass / Walk-Ups: " + daypassWalkupCounter);
		System.out.println("Indego30: " + indego30Counter);
		System.out.println("Indego365: " + indego365Counter);
		System.out.println("Indego Flex: " + indegoflexCounter);
		
		return passholderDistribution;
	}
	
	
	
	
}

