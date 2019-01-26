import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Constructor for Station object
 * @author fredchang
 *
 */
public class Station {
	
	//define instance variables
	private int stationId;
	private String stationName;
	private String goLiveDate;
	private String stationStatus;
	
	/**
	 * 
	 * Constructs Station object that takes in:
	 * @param stationId
	 * @param stationName
	 * @param goLiveDate
	 * @param stationStatus
	 */
	Station(int stationId, String stationName, String goLiveDate, String stationStatus){
		this.stationId = stationId;
		this.stationName = stationName;
		this.goLiveDate = goLiveDate;
		this.stationStatus = stationStatus;
	}
	
	//GETTERS 
	
	public int getStationId() {
		return stationId;
	}

	public String getStationName() {
		return stationName;
	}

	public String getGoLiveDate() {
		return goLiveDate;
	}
	
	/**
	 * 
	 * Reads in goLiveDate as String and returns the year as String
	 * @return
	 * @throws ParseException
	 */
	public String getGoLiveYear() throws ParseException {
		SimpleDateFormat parseFormatter = new SimpleDateFormat("MM/dd/yyyy");
        String dateInString = goLiveDate;
        Date date = parseFormatter.parse(dateInString);
        SimpleDateFormat outputFormatter = new SimpleDateFormat("yyyy");
		String goLiveYear = outputFormatter.format(date);
		return goLiveYear;
	}

	public String getStationStatus() {
		return stationStatus;
	}
	
	
}

