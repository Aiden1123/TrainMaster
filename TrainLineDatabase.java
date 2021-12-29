package trainMaster;

import java.util.ArrayList;

public class TrainLineDatabase {
	
	ArrayList<TrainLine> lines;
	int count;
	
	TrainLineDatabase() {
		this.count=0;
		this.lines = new ArrayList<TrainLine>();
	}
	
	public Boolean nameExists(String name) {
		for(TrainLine i: lines) {
			if(i.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void add(TrainLine new_line) {
		
		if (this.nameExists(new_line.getName())) {
			System.out.println("Name already taken!");
			return;
		}
		
		this.count++;
		this.lines.add(new_line);
	}
	
	public TrainLine find(String target) {
		for(TrainLine i: lines) {
			if (i.getName().equals(target))
				return i;
		}
		return null;
	}
	
	public void delete(TrainLine line) {
		this.lines.remove(line);
		this.count--;
	}
	
	public void printNames() {
		for(TrainLine i: lines) {
			System.out.println(i.getName());
		}
	}
	
	public void printNamesStations() {
		for(TrainLine i: lines) {
			System.out.println(i.getName());
			i.printStations("\t");
		}
	}


}
