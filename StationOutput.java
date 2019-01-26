/**
 * Constructor for StationOutput object with fields for file output
 * @author fredchang
 *
 */
public class StationOutput {

	//define instance variables
	private int stationId;
	private String stationName;
	private int stationTrips;
	private String averageTripDuration;
	private int maxTripDuration;
	private int numOneWay;
	private int startEndDifference;
	
	/**
	 * 
	 * Constructs StationOutput that takes in:
	 * @param stationId
	 * @param stationName
	 * @param stationTrips
	 * @param averageTripDuration
	 * @param maxTripDuration
	 * @param numOneWay
	 * @param startEndDifference
	 */
	StationOutput(int stationId,
				  String stationName,	
			      int stationTrips,
			      String averageTripDuration,
			      int maxTripDuration,
			      int numOneWay,
			      int startEndDifference
			     )
	{
		this.stationId = stationId;
		this.stationName = stationName;	
		this.stationTrips = stationTrips;
		this.averageTripDuration = averageTripDuration;
		this.maxTripDuration = maxTripDuration;
		this.numOneWay = numOneWay;
		this.startEndDifference = startEndDifference;
	}

	//GETTERS
	
	public int getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public int getStationTrips() {
		return stationTrips;
	}

	public String getAverageTripDuration() {
		return averageTripDuration;
	}

	public int getMaxTripDuration() {
		return maxTripDuration;
	}

	public int getNumOneWay() {
		return numOneWay;
	}

	public int getStartEndDifference() {
		return startEndDifference;
	}
	
}
