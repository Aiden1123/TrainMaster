package trainMaster;

import java.util.ArrayList;

public class TrainLine {
	String name;
	ArrayList<Station> stations;
	ArrayList<TrainLine> exchanges;
	
	TrainLine(String name) {
		this.name = name;
		this.stations = new ArrayList<Station>();
		this.exchanges = new ArrayList<TrainLine>();
	}
	
	public void addStation(Station station) {
		this.stations.add(station);
		station.addLine(this);
	}
	
	public void addStation(Station station, int index) {
		
		try {
			this.stations.add(index,station);
		} 
		catch(IndexOutOfBoundsException e) {
			this.stations.add(station);
		}
		
		station.addLine(this);
	}
	
	public void deleteStation(Station station) {
		this.stations.remove(station);
		station.deleteLine(this);
	}
	
	public void deleteStation(int index) {
		
		try {
			Station a = this.stations.get(index);
			this.stations.remove(index);
			a.deleteLine(this);
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Invalid index");
		}
	}

	public void printStations() {
		int i=0;
		for(Station station: stations) {
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}
	
	public void printStations(String message) {
		int i=0;
		for(Station station: stations) {
			System.out.print(message);
			System.out.println(Integer.toString(i++) + ": " + station.getName());
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Station> getStations() {
		return stations;
	}

	public ArrayList<TrainLine> getExchanges() {
		return exchanges;
	}

}
