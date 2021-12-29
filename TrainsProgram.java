package trainMaster;

//import java.util.ArrayList;
import java.util.Scanner;

public class TrainsProgram {
	public static void executeScript(String filename, TrainLineDatabase lines, StationDatabase stations) {
		
	}
	
	public static void main(String[] args) {

		TrainLineDatabase lines = new TrainLineDatabase();
		StationDatabase stations = new StationDatabase();
		Scanner sc=new Scanner(System.in);  
		String[] instruction;
		Boolean exit_program = false;
		
		System.out.println("TrainMaster");
		
		while(!exit_program) {
			
			
			instruction = sc.nextLine().split(" ");
			
			try {
			
			switch(instruction[0]) {
			
				case "add": 
					switch(instruction[1]) {
						case "line":
							for(int i=2;i<instruction.length;i++) {
								if (lines.nameExists(instruction[i])) {
									System.out.println(instruction[i] + " Name already taken!");
									break;
								}
								lines.add(new TrainLine(instruction[i]));
							}
							break;
						case "station":
							for(int i=2;i<instruction.length;i++) {
								if (stations.nameExists(instruction[i])) {
									System.out.println(instruction[i] + " Name already taken!");
									break;
								}
								stations.add(new Station(instruction[i]));
							}
							break;							
					}
					break;
				
				case "append":
					if (!lines.nameExists(instruction[1])) {
						System.out.println("Invalid line name");
						break;
					}
					
					for(int i=2;i<instruction.length;i++) {
					
						if (stations.nameExists(instruction[i])) {
							lines.find(instruction[1]).addStation(stations.find(instruction[i]));
						}
						
						else {
							Station aux = new Station(instruction[i]); 
							stations.add(aux);
							lines.find(instruction[1]).addStation(aux);
						}

					}
					break;
					
				case "insert":
					if (lines.nameExists(instruction[1]) && stations.nameExists(instruction[2])) {
						lines.find(instruction[1]).addStation(stations.find(instruction[2]),Integer.parseInt(instruction[3]));
					}
					
					else if (lines.nameExists(instruction[1]) && !stations.nameExists(instruction[2])) {
						Station aux = new Station(instruction[2]); 
						stations.add(aux);
						lines.find(instruction[1]).addStation(aux,Integer.parseInt(instruction[3]));
					}
					
					else {
						System.out.println("Incorrect station or line name");
					}
					break;
					
				case "print":
					switch(instruction[1]) {
						case "line":
							lines.find(instruction[2]).printStations();
							break;
							
						case "station":
							stations.find(instruction[2]).printLines();
							break;
					
						case "lines":
							lines.printNames();
							break;
						
						case "linesinfo":
							lines.printNamesStations();
							break;
							
						case "stations":
							stations.printNames();
							break;
						
						case "stationsinfo":
							stations.printNamesLines();
							break;
						
						case "linesExchanges":
							lines.printNamesExchanges();
							break;
					}
					break;
				
				case "quit":
					exit_program = true;
					break;
					
				case "save": 
					{
						SaveDatabases save = new SaveDatabases(lines, stations);
						save.writeToFile(instruction[1]);
					}
					break;
					
				case "load":
					SaveDatabases load = SaveDatabases.readFromFile(instruction[1]);
					lines = load.getLines();
					stations = load.getStations();
					break;
					
				default:
					System.out.println("add line, print lines, print linesinfo, print stations, print stationsinfo, quit");
					break;
			}
			
			
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Incorrect instruction");
			}
			
		}
	
		sc.close();
	}
}
