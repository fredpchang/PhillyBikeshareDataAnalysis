import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Constructor for Trip object
 * @author fredchang
 *
 */
public class Trip {
	
	//define instance variables
	private int tripId;
	private int tripDuration;
	private String tripStartTime;
	private String tripEndTime;
	private int tripStartStation;
	private double tripStartLat;
	private double tripStartLong;
	private int tripEndStation;
	private double tripEndLat;
	private double tripEndLong;
	private int bikeId;
	private int planDuration;
	private String tripRouteCategory;
	private String passHolderType;
	
	/**
	 * 
	 * Constructs Trip object that takes in:
	 * @param tripId
	 * @param tripDuration
	 * @param tripStartTime
	 * @param tripEndTime
	 * @param tripStartStation
	 * @param tripStartLat
	 * @param tripStartLong
	 * @param tripEndStation
	 * @param tripEndLat
	 * @param tripEndLong
	 * @param bikeId
	 * @param planDuration
	 * @param tripRouteCategory
	 * @param passHolderType
	 */
	Trip( int tripId,
		  int tripDuration,
		  String tripStartTime,
		  String tripEndTime,
		  int tripStartStation,
		  double tripStartLat,
		  double tripStartLong,
		  int tripEndStation,
		  double tripEndLat,
		  double tripEndLong,
		  int bikeId,
		  int planDuration,
		  String tripRouteCategory,
		  String passHolderType	
		  )
	{
		this.tripId = tripId;
		this.tripDuration = tripDuration;
		this.tripStartTime = tripStartTime;
		this.tripEndTime = tripEndTime;
		this.tripStartStation = tripStartStation;
		this.tripStartLat = tripStartLat;
		this.tripStartLong = tripStartLong;
		this.tripEndStation = tripEndStation;
		this.tripEndLat = tripEndLat;
		this.tripEndLong = tripEndLong;
		this.bikeId = bikeId;
		this.planDuration = planDuration;
		this.tripRouteCategory = tripRouteCategory;
		this.passHolderType	= passHolderType;
	}

	//GETTERS
	
	public int getTripId() {
		return tripId;
	}


	public int getTripDuration() {
		return tripDuration;
	}


	public String getTripStartTime() {
		return tripStartTime;
	}

	public String getTripEndTime() {
		return tripEndTime;
	}

	public int getTripStartStation() {
		return tripStartStation;
	}

	public double getTripStartLat() {
		return tripStartLat;
	}

	public double getTripStartLong() {
		return tripStartLong;
	}
	
	public int getTripEndStation() {
		return tripEndStation;
	}

	public double getTripEndLat() {
		return tripEndLat;
	}

	public double getTripEndLong() {
		return tripEndLong;
	}

	public int getBikeId() {
		return bikeId;
	}

	public int getPlanDuration() {
		return planDuration;
	}

	public String getTripRouteCategory() {
		return tripRouteCategory;
	}

	public String getPassHolderType() {
		return passHolderType;
	}
	
	/**
	 * 
	 * Reads in tripStartDate and returns month
	 * @return tripStartMonth
	 * @throws ParseException
	 */
	public String getTripStartMonth() throws ParseException {
		SimpleDateFormat parseFormatter = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        String dateInString = tripStartTime;
        Date date = parseFormatter.parse(dateInString);
        SimpleDateFormat outputFormatter = new SimpleDateFormat("MM");
		String tripStartMonth = outputFormatter.format(date);
		return tripStartMonth;
	}
	
	
	
}
