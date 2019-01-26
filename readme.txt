README

Author: Fred (Pei-Jan) Chang

Philadelphia Bike Share Data and Jeopardy

******************************************************************************************

Setup
* To run program correctly, place .java files in src folder and place .csv files one level above
* Open up IndegoBikeshare.java and run
* Results will print and output file will be created in same folder as .csv files 



Structure of Program
* IndegoBikeshare.java ---> main method
* StationReader.java   ---> read indego-stations-2018-8-3.csv
* TripReader.java      ---> read indego-trips-2018-q2.csv
* Station.java         ---> Station class to construct ArrayList of stations
* Trip.java            ---> Trip class to construct ArrayList of trips
* Analyzer.java        ---> Analyzer class to analyze the data



Changes from CRC
* Originally created FilePrinter class to print file. However, final version printed file within main method as there is only one report to print.
* Removed setters as they were not needed in the program
* Added additional methods to Analyzer.java to answer additional and custom questions



Analysis of Data
1. How many Active stations were there in the second quarter of 2018?
Calculates the number of stations with Status field = "Active". User can change to "Inactive" to display number of inactive stations.

2. How many stations that had a Go-Live Date in 2017 are still Active?
User can adjust the Go-Live year parameter "2017" or status "Active" to check for i.e. "2016" "Inactive"

3. How many trips originated at Amtrak 30th Street Station?
User can adjust station by changing stationId

4. What was the most popular month for walk ups?
As walkups were replaced with day passes during the quarter, this value was calculated by counting the number of walk-ups and day-passes during each month

5. What is the average duration of a bike rental?
Calculates the average duration (minutes) of all trips during the quarter.

6. How many trips started and completed between 10am and 1pm? You can assume the input will not include part of the hour.
Determined by counting all trips that started and completed between 10:00 (inclusive) and 13:00 (not inclusive) on the same day. User can adjust the start and end hour.

7. Wild card: What is the most used bike during the quarter? 
This custom question is designed to return the bikeID of the most used bike (determined by number of trips with that bikeId) and how many times it was used. This can be useful for Indego Bikeshare as they can determine which bike should receive priority for routine maintenance or checks.



Summary of Data by Station
* BikeStationReport.txt ---> output file
* <station id> ---> station id
* <station name> ---> station name
* <total number of trips> ---> total number of trips that started from station
* <average trip duration from this station> ---> average trip duration (minutes) from station
* <max trip duration from this station> ---> average trip duration (minutes) from station
* <number of one way trips> ---> number of one way trips from station
* <difference between the total number of trips that start and end at this station>   ---> number of trips that start at station - number of trips that end at station


