package data;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainController {
		private TrainDAO dao = new TrainDAO();
		private static TrainUI ui=new TrainUI();

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
		public void commandExecute(String command){
			System.out.println(command.substring(0,3));
	if (command.substring(0,3).equals("new")) {
		if (command.substring(4,9).equals("train") && idcontrol(command) == true) {
			String execution = command.replace("new train ", "");
			//check if train exists already
			if (TrainControl(execution) == false) {
				createTrain(execution);
				ui.output.append("train " + execution + " created.\n");
			} else {
				ui.output.append("This train already exists.\n");
			}
		}
		// create a wagon
		else if (command.toLowerCase().contains("wagon") && idcontrol(command) == true) {
			String id = "";
			//create passengerwagon
			if (command.substring(4,18).equals("passengerwagon")){
				id += command.substring(19,22);
				//check if wagon exists already
				if (WagonControl(id)==false){
				//check for optional numseats input
				if (command.substring(23,31).equals("numseats")) {
					String value = command.substring(32, command.length());
					createPassengerWagon(id, Integer.parseInt(value));
					ui.output.append("passengerwagon " + id + " created with " + value + " seats.\n");
				} else {
					createPassengerWagon(id, 20);
					ui.output.append("passengerwagon " + id + " created with 20 seats.\n");
				}}
				else{
					ui.output.append("this wagon already exists.\n");
				}
			}
			//create a liquidcargowagon
			if (command.substring(4,20).contains("liquidcargowagon")) {
				id += command.substring(21,24);
				//check if wagon exists already
				if (WagonControl(id)==false){
				//check for required contentlit input
				if (command.substring(25,35).equals("contentlit")) {
					String value = command.substring(36, command.length());
					createLiquidCargoWagon(id, Integer.parseInt(value));
					ui.output.append(
							"liquidcargowagon " + id + " created with " + value + " liter content.\n");
				} else {
					ui.output.append("no content given; try again.\n");
				}}
				else{
					ui.output.append("this wagon already exists.\n");
				}
			}
			//create solidcargowagon
			if (command.substring(4,19).contains("solidcargowagon")) {
				id += command.substring(20,23);
				//check if wagon exists already
				if (WagonControl(id)==false){
				//check for required contentcub input
				if (command.substring(24,34).equals("contentcub")) {
					String value = command.substring(35, command.length());
					createSolidCargoWagon(id, Integer.parseInt(value));
					ui.output.append("solidcargowagon " + id + " created with " + value
							+ " cubic meters content.\n");
				} else {
					ui.output.append("no content given; try again.\n");
				}
			}
			else{
				ui.output.append("this wagon already exists.\n");
			}
		} }else {
			ui.output.append(
					"no viable type given; use train, passengerwagon, liquidcargowagon or solid cargowagon.\n");
		}
	}
	// get numseats from passengerwagons and trains
	else if (command.substring(0,11).equals("getnumseats")) {
		//get seats from train
		if (command.substring(12,17).equals("train")) {
			String execution = command.replace("getnumseats train ", "");
			int result = getNumseatsTrain(execution);
			//check if train exists
			if (result > 0) {
				ui.output.append("train " + execution + " has " + result + " total seats.\n");
			} else {
				ui.output.append("train " + execution + " does not exist.\n");
			}
		}
		// get seats from passengerswagon
		else if (command.substring(12,26).equals("passengerwagon")) {
			String id = command.replace("getnumseats passengerwagon ", "");
			Wagon wag=getWagon(id);
			//check if wagon is a passengerwagon
			if (wag.getClass()==Passengerswagon.class){
				Passengerswagon pw=(Passengerswagon)wag;
				ui.output.append("passengerwagon "+id+" has "+pw.getSeats()+" seats.\n");
			}
			else{
				ui.output.append("wagon "+id+" is not a passengerwagon.\n");
			}

		} else {
			ui.output.append("No passengerwagon or train with the given id exists.\n");
		}
	}
	// get contentlit from liquidcargowagons and trains
	else if (command.substring(0,13).equals("getcontentlit")) {
		if (command.substring(14,19).equals("train")) {
			String execution = command.replace("getcontentlit train ", "");
			//check if train exists
			if (TrainControl(execution)==true){
			int result = getContentlitTrain(execution);
			if (result > 0) {
				ui.output.append("train " + execution + " has " + result + " liters total content.\n");
			} else {
				ui.output.append("train " + execution + " does not have liquidcargowagons.\n");
			}
			}
			else{
				ui.output.append("train "+execution+" does not exist.\n");
			}
		}
		//get contentlit of liquidcargowagon
		else if (command.substring(14,30).equals("liquidcargowagon")) {
			String id = command.replace("getcontentlit liquidcargowagon ", "");
			Wagon wag=getWagon(id);
			if (wag.getClass()==LiquidCargowagon.class){
				LiquidCargowagon lcw=(LiquidCargowagon)wag;
				ui.output.append("liquidcargowagon "+id+" has a content of "+lcw.getContentliters()+" liters.\n");
			}
			else{
				ui.output.append("wagon "+id+" is not a liquidcargowagon.\n");
			}

		} else {
			ui.output.append("No liquidcargowagon or train with the given id exists.\n");
		}
	}
	//get contentcub of solidcargowagon
	else if (command.substring(0,13).equals("getcontentcub")) {
		if (command.substring(14,19).equals("train")) {
			String execution = command.replace("getcontentcub train ", "");
			if (TrainControl(execution)==true){
			int result = getContentcubTrain(execution);
			if (result > 0) {
				ui.output.append("train " + execution + " has " + result + " cubic meters total content.\n");
			} else {
				ui.output.append("train " + execution + " does not have solidcargowagons.\n");
			}
			}
			else{
				ui.output.append("train "+execution+" does not exist.\n");
			}
		}
		//get contentcub of solidcargowagon
		else if (command.substring(14,29).equals("solidcargowagon")) {
			String id = command.replace("getcontentcub solidcargowagon ", "");
			Wagon wag=getWagon(id);
			if (wag.getClass()==SolidCargowagon.class){
				SolidCargowagon scw=(SolidCargowagon)wag;
				ui.output.append("solidcargowagon "+id+" has a content of "+scw.getContentcubic()+" cubic meters.\n");
			}
			else{
				ui.output.append("wagon "+id+" is not a solidcargowagon.\n");
			}

		} else {
			ui.output.append("No solidcargowagon or train with the given id exists.\n");
		}
	}
	// add wagon to train
	else if (command.substring(0,3).equals("add")) {
		String wagon_id = command.substring(4,7);
		String train_id = command.substring(11, 14);
		
		Wagon wag = getWagon(wagon_id);
		Train tr = getTrain(train_id);
		//individual errors
		if (wag==null){
			ui.output.append("The given wagon doesn't exist or isn't a wagon.\n");
		}
		if (tr==null){
			ui.output.append("The given train doesn't exist or isn't a train.\n");
		}
		if (getWagon(wagon_id).getClass().equals(Locomotive.class)) {
			ui.output.append("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
		}
		if (addWagonControl(wag, tr) == true) {
			ui.output.append("wagon " + wag.getWagonid() + " is already attached to train " + tr.getTrainid() + ".\n");
		}
		//final control
		if (wag!=null && tr!=null && !wag.getClass().equals(Locomotive.class)&&addWagonControl(wag,tr)==false){
		addWagon(wag, tr);
		if (getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
			Passengerswagon paswag = (Passengerswagon) wag;
			//add seats of wagon to totalseats of train
			tr.setTotalseats(tr.getTotalseats() + paswag.getSeats());
			updateTrain(tr);
			ui.output.append("passengerwagon " + wag.getWagonid() + " has been added to train "
					+ tr.getTrainid() + "\n");
		}
		if (getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
			ui.output.append("solidcargowagon " + wag.getWagonid() + " has been added to train "
					+ tr.getTrainid() + "\n");
		}
		if (getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
			ui.output.append("liquidcargowagon " + wag.getWagonid() + " has been added to train "
					+ tr.getTrainid() + "\n");
		}
		
	}}
	// remove wagon from train
	else if (command.substring(0,6).equals("remove")) {
		String wagon_id = command.substring(7,10);
		String train_id = command.substring(16,19);

		Wagon wag = getWagon(wagon_id);
		Train tr = getTrain(train_id);
		//individual errors
		if (wag==null){
			ui.output.append("The given wagon doesn't exist or isn't a wagon.\n");
		}
		if (tr==null){
			ui.output.append("The given train doesn't exist or isn't a train.\n");
		}
		if (getWagon(wagon_id).getClass().equals(Locomotive.class)) {
			ui.output.append("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
		}
		if (addWagonControl(wag, tr) == false) {
			ui.output.append("wagon " + wag.getWagonid() + " is not attached to train " + tr.getTrainid() + ".\n");
		}
		//final control
		if (wag!=null && tr!=null && !wag.getClass().equals(Locomotive.class)&&addWagonControl(wag,tr)==true){
			removeWagon(wag,tr);
			if (getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
				Passengerswagon paswag = (Passengerswagon) wag;
				//remove seats of wagon from totalseats of train
				tr.setTotalseats(tr.getTotalseats() - paswag.getSeats());
				updateTrain(tr);
				ui.output.append("passengerwagon " + wag.getWagonid() + " has been removed from train "
						+ tr.getTrainid() + "\n");
			}
			if (getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
				ui.output.append("solidcargowagon " + wag.getWagonid() + " has been removed from train "
						+ tr.getTrainid() + "\n");
			}
			if (getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
				ui.output.append("liquidcargowagon " + wag.getWagonid() + " has been removed from train "
						+ tr.getTrainid() + "\n");
			}
		}
	}
	// delete train or wagon
	else if (command.substring(0,6).equals("delete")) {
		String target = command.substring(13,16);
		//delete train
		if (command.substring(7,12).equals("train")) {
			if (getTrain(target) != null) {
				deleteTrain(getTrain(target));
				ui.output.append("train " + target + " deleted.\n");
			} else {
				ui.output.append("train " + target + " does not exist.\n");
			}

		}
		//delete wagon
		else if (command.substring(7,12).equals("wagon")) {
			if (getWagon(target) != null) {
				if (getWagon(target).getClass().equals(Locomotive.class)) {
					ui.output.append("The given wagon is a locomotive; locomotives can't removed.\n");
				}
				else{
				deleteWagon(getWagon(target));
				ui.output.append("wagon " + target + " deleted.\n");
			}} else {
				ui.output.append("wagon " + target + " does not exist.\n");
			}

		}
		else{
			ui.output.append("No correct type given; use train or wagon.\n");
		}
	}
	// command doesn't exist
	else {
		ui.output.append(
				"given command is illegal; try new, add, remove, getnumseats, getcontentcub, getcontentlit or delete\n");
	}
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
