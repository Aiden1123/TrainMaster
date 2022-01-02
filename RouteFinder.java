package trainMaster;

import java.util.ArrayList;

public class RouteFinder {
	public static void FewestExchanges(Station start, Station dest) {

		if(start.equals(dest)) {
			System.out.println("Start and destination is the same");
			return;
		}
		
		ArrayList<TrainLine> lines = new ArrayList<TrainLine>();
		ArrayList<TrainLine> prev_line = new ArrayList<TrainLine>();
		
		for(TrainLine i: start.getLines()) {
			lines.add(i);
			prev_line.add(null);
		}
		
		int i=0;
		
		while(i<lines.size()) {
			
			if (lines.get(i).getStations().contains(dest)) {
				String res = null;
				TrainLine previousLine = prev_line.get(i);
				TrainLine nextLine = lines.get(i);
				
				res = "Get on " + lines.get(i).getName() + "\n\t Get off at " + dest.getName() + "\n";
				while(!(previousLine == null)) {
		
					Station transfer = null;
					
					for(Exchange j: previousLine.getExchanges()) {
						if (j.getLine().equals(nextLine)) {
							transfer = j.getStation();
							break;
						}
					}
		
					if (transfer == null) {
						System.out.println("An error has occured");
						return;
					}
					
					res = "Get on " + previousLine.getName() + "\n\t Get off at " + transfer.getName() + "\n" + res;
					nextLine = previousLine;
					previousLine = prev_line.get(lines.indexOf(nextLine));
				}
				System.out.println(res);
				return;
			}
			
			else {
				for(Exchange j: lines.get(i).getExchanges()) {
					if (!lines.contains(j.getLine())) {
						lines.add(j.getLine());
						prev_line.add(lines.get(i));
					}
				}
			}
			i++;
		}
		
		
		System.out.println("Route has not been found");
		return;
		
	}
}
