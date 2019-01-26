import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main method for Indego Bikeshare analysis
 * @author fredchang
 *
 */
public class IndegoBikeshare {

	public static void main(String[] args) throws ParseException, FileNotFoundException {
		
		//Reads in stations csv file
		StationReader stationReader = new StationReader("indego-stations-2018-8-3.csv");
		ArrayList<Station> stations = new ArrayList<>();
		try {
			stations = stationReader.readStationFile();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Reads in trips csv file
		TripReader tripReader = new TripReader("indego-trips-2018-q2.csv");
		ArrayList<Trip>trips = new ArrayList<>();
		try {
			trips = tripReader.readTripFile();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		
		Analyzer analyzer = new Analyzer(stations, trips);	
		
		//ANALYSIS OF DATA ANSWERS
		System.out.println("------------Analysis of Data------------");
		
		//How many Active stations were there in the second quarter of 2018?	
		System.out.print("1. Active Stations Q2 2018: ");
		System.out.println(analyzer.analyzeStationStatus("Active").size());
		
		//How many stations that had a Go-Live Date in 2017 are still Active?
		System.out.print("2. Stations that had go-live date in 2017 that are still active: ");
		System.out.println(analyzer.analyzeGoLive("2017", "Active").size());
		
		//How many trips originated at Amtrak 30th Street Station?
		System.out.print("3. Trips originating at Amtrak 30th Street Station: ");
		System.out.println(analyzer.analyzeStationOriginatingTrips(3022).size());
		
		//What was the most popular month for walk ups?
		System.out.print("4. Most popular month for walkups: ");
		System.out.println(analyzer.analyzeMostPopularWalkupMonth());
		
		//What is the average duration of a bike rental?
		System.out.print("5. Average duration of bike rental (minutes): ");
		System.out.println(analyzer.analyzeAverageDuration());
		
		//How many trips started and completed between 10am and 1pm? You can assume the input will not include part of the hour.
		System.out.print("6. Trips started and completed between 10am and 1pm: ");
		System.out.println(analyzer.analyzeTimePeriodTrips(10, 13));
		
		//What is the most used bike during the quarter?
		System.out.print("7. Most used bike during Q2 (wild card): ");		
		System.out.println(analyzer.analyzeMostUsedBike());
		
		System.out.println("");
		System.out.println("------------Extra Credit------------");
		//EXTRA CREDIT: Find all pairs of stations close to each other
		System.out.println("All pairs of stations that are considered close to each other: ");
		System.out.println(analyzer.analyzeCloseProximity());
		
		
		//EXTRA CREDIT: What is the least popular end station?
		System.out.print("* Least popular end station: ");
		String tempLeastPopular = analyzer.analyzeLeastPopularEndStation();
		System.out.println(analyzer.analyzeStationName(Integer.parseInt(tempLeastPopular)));
		
		//EXTRA CREDIT CUSTOM: 
		System.out.println("* What is the distribution of passholder types?");
		System.out.println(analyzer.analyzePassholderDistribution());
		
		//Create stationsOutput arrayList for file output
		ArrayList<StationOutput> stationsOutput = new ArrayList<>();
		int i = 0;
		for (i=0; i<stations.size(); i++) {
			int stationId = stations.get(i).getStationId();
			String stationName = analyzer.analyzeStationName(stationId);
			int stationTrips = analyzer.analyzeTotalTripsByStation(stationId);
			String averageTripDuration = analyzer.analyzeAverageTripDurationByStation(stationId);
			int maxTripDuration = analyzer.analyzeMaxDurationByStation(stationId);
			int numOneWay = analyzer.analyzeNumOneWayTrips(stationId);
			int startEndDifference = analyzer.analyzeNumStartEndTripDiff(stationId);
			
			StationOutput sOut = new StationOutput(stationId, stationName, stationTrips, averageTripDuration, maxTripDuration, numOneWay, startEndDifference);
			stationsOutput.add(sOut);
		}
		
		PrintWriter out = new PrintWriter("BikeStationReport.txt");
		try {
			out.println("Station ID, Station Name, Station Trips, Average Trip Duration, Max Trip Duration, One Way Trips, Total Start Trip End Trip Diff");
			int c = 0;
			for (c=0; c<stationsOutput.size(); c++) {
				out.print(stationsOutput.get(c).getStationId() + ",");
				out.print("\"" + stationsOutput.get(c).getStationName() + "\"" + ",");
				out.print(stationsOutput.get(c).getStationTrips() + ",");
				out.print(stationsOutput.get(c).getAverageTripDuration() + ",");
				out.print(stationsOutput.get(c).getMaxTripDuration() + ",");
				out.print(stationsOutput.get(c).getNumOneWay() + ",");
				out.println(stationsOutput.get(c).getStartEndDifference());
			}
		} finally {
			out.close();
		}	
		
		
	}
	
}
