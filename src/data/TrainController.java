package data;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import design.LiquidCargowagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainController {
		private static TrainDAO dao = new TrainDAO();
		private static TrainUI ui=new TrainUI();
		private static CommandController cc=  new CommandController();

		public ArrayList<Train> getTrains() {
			return dao.getTrains();
		}
		public Train getTrain(String id){
				return dao.getTrain(id);
			}

		public void createTrain(String id) {
			dao.createTrain(id);
		}

		public ArrayList<Wagon> getWagons(Train train) {
			return dao.getWagons(train);
		}
		public Wagon getWagon(String id) {
			return dao.getWagon(id);
		}

		public void deleteTrain(Train train){
			dao.deleteTrain(train);
		}

		public void createWagon(String id, int value, String wagontype) {
				dao.createWagon(id, value, wagontype);
		}
		public void addWagon(Wagon wagon, Train train) {
			dao.addWagon(wagon, train);
	}
		public void removeWagon(Wagon wagon, Train train) {
			dao.removeWagon(wagon, train);
	}
		public void deleteWagon(Wagon wagon) {
			dao.deleteWagon(wagon);	
		}
		public void updateTrain(Train train) {
			dao.updateTrain(train);
			
		}
		public int getContentcubTrain(String execution) {
			Train t = dao.getTrain(execution);
			int result = 0;
			if (t != null) {
				for (Wagon w : t.getWagonlist()) {
					if (w.getClass().equals(SolidCargowagon.class)) {
						SolidCargowagon scw = (SolidCargowagon) w;
						result += scw.getContentcubic();
					}
				}
			}
			return result;

		}
		//get sum of the content all attached liquidcargowagons
		public int getContentlitTrain(String execution) {
			Train t = dao.getTrain(execution);
			int result = 0;
			if (t != null) {
				for (Wagon w : t.getWagonlist()) {
					if (w.getClass().equals(LiquidCargowagon.class)) {
						LiquidCargowagon lcw = (LiquidCargowagon) w;
						result += lcw.getContentliters();
					}
				}
			}
			return result;

		}
		//get sum of the seats all attached passengerwagons
		public int getNumseatsTrain(String execution) {
			try {
				Train t = dao.getTrain(execution);
				return t.getTotalseats();
			} catch (NullPointerException e) {
				return 0;
			}
		}
		//check if given train exists
		public boolean TrainControl(String command) {
			boolean result = true;
			String id = "";
			id += command.charAt(0);
			id += command.charAt(1);
			id += command.charAt(2);
			if (dao.getTrain(id) == null) {
				result = false;
			}
			return result;
		}
		//check if given wagon exists
		public boolean WagonControl(String command) {
			boolean result = true;
			String id = "";
			id += command.charAt(0);
			id += command.charAt(1);
			id += command.charAt(2);
			if (dao.getWagon(id) == null) {
				result = false;
			}
			return result;
		}
		//check if given id follows the required format
		public boolean idcontrol(String command) {
			String id = command.replace("new train ", "");
			id = id.replace("new passengerwagon ", "");
			id = id.replace("new liquidcargowagon ", "");
			id = id.replace("new solidcargowagon ", "");
			boolean result = false;
			if (Character.isLetter(id.charAt(0)) && Character.isLetter(id.charAt(1)) && Character.isDigit(id.charAt(2))) {
				result = true;
			}
			return result;
		}

		public void createPassengerWagon(String id, int numseats) {
			dao.createWagon(id, numseats, "Passenger");

		}

		public void createPassengerWagon(String id) {
			dao.createWagon(id, 20, "Passenger");

		}

		public void createSolidCargoWagon(String id, int content) {
			dao.createWagon(id, content, "SolidCargo");

		}

		public void createLiquidCargoWagon(String id, int content) {
			dao.createWagon(id, content, "LiquidCargo");

		}
		//check if given wagon is already attached to given train
		public boolean addWagonControl(Wagon w, Train t) {
			boolean result = false;
			for (Wagon wag : dao.getWagons(t)) {
				if (wag.getWagonid().equals(w.getWagonid()))
					result = true;
			}
			return result;
		}
		
//COMMAND INTERFACE MAIN METHOD
		public void executeCommand(String command){
			String result=cc.executeCommand(command);
		ui.output.append(result);
}
		
//display UI
public static void main(String... args) {
	SwingUtilities.invokeLater(new Runnable() {
		public void run() {
			ui.displayGUI();
		}
	});
}
}
