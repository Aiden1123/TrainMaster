package trainMaster;

import java.util.ArrayList;

public class StationDatabase {
	
	ArrayList<Station> stations;
	int count;
	
	StationDatabase() {
		this.count=0;
		this.stations = new ArrayList<Station>();
	}
	
	public Boolean nameExists(String name) {
		for(Station i: stations) {
			if(i.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void add(Station new_station) {
		
		if (this.nameExists(new_station.getName())) {
			System.out.println("Name already taken!");
			return;
		}
		
		this.count++;
		this.stations.add(new_station);
	}
	
	public Station find(String target) {
		for(Station i: stations) {
			if (i.getName().equals(target))
				return i;
		}
		return null;
	}
	
	public void delete(Station station) {
		this.stations.remove(station);
		this.count--;
	}
	
	public void printNames() {
		for(Station i: stations) {
			System.out.println(i.getName());
		}
	}
	
	public void printNamesLines() {
		for(Station i: stations) {
			System.out.println(i.getName());
			i.printLines("\t");
		}
	}
	
}
