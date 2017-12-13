package data;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class CommandController {
	private TrainController tc = new TrainController();
	//COMMAND INTERFACE MAIN METHOD
			public String executeCommand(String command){
			String commandresult="";
		if (command.length()>10){
		if (command.substring(0,3).equals("new")) {
			if (command.substring(4,9).equals("train") && tc.idcontrol(command) == true) {
				String execution = command.replace("new train ", "");
				//check if train exists already
				if (tc.TrainControl(execution) == false) {
					tc.createTrain(execution);
					commandresult= ("train " + execution + " created.\n");
				} else {
					commandresult= ("This train already exists.\n");
				}
			}
			// create a wagon
			else if (command.toLowerCase().contains("wagon") && tc.idcontrol(command) == true) {
				String id = "";
				//create passengerwagon
				if (command.substring(4,18).equals("passengerwagon")){
					id += command.substring(19,22);
					//check if wagon exists already
					if (tc.WagonControl(id)==false){
					//check for optional numseats input
					if (command.length()>22){
					if (command.substring(23,31).equals("numseats")) {
						String value = command.substring(32, command.length());
						tc.createPassengerWagon(id, Integer.parseInt(value));
						commandresult= ("passengerwagon " + id + " created with " + value + " seats.\n");
					}else{
						commandresult=("extension format isn't correct; use numseats 01.\n");
					}} else {
						tc.createPassengerWagon(id, 20);
						commandresult= ("passengerwagon " + id + " created with 20 seats.\n");
					}}
					else{
						commandresult= ("this wagon already exists.\n");
					}
				}
				//create a liquidcargowagon
				if (command.substring(4,20).contains("liquidcargowagon")) {
					id += command.substring(21,24);
					//check if wagon exists already
					if (tc.WagonControl(id)==false){
					//check for required contentlit input
					if (command.length()>24){
					if (command.substring(25,35).equals("contentlit")) {
						String value = command.substring(36, command.length());
						tc.createLiquidCargoWagon(id, Integer.parseInt(value));
						commandresult= (
								"liquidcargowagon " + id + " created with " + value + " liter content.\n");
					} else{
						commandresult=("extension format isn't correct; use contentlit 01.\n");
					}}else {
						commandresult= ("no content given; try again.\n");
					}}
					else{
						commandresult= ("this wagon already exists.\n");
					}
				}
				//create solidcargowagon
				if (command.substring(4,19).contains("solidcargowagon")) {
					id += command.substring(20,23);
					//check if wagon exists already
					if (tc.WagonControl(id)==false){
					//check for required contentcub input
					if (command.length()>23){
					if (command.substring(24,34).equals("contentcub")) {
						String value = command.substring(35, command.length());
						tc.createSolidCargoWagon(id, Integer.parseInt(value));
						commandresult= ("solidcargowagon " + id + " created with " + value
								+ " cubic meters content.\n");
					}else{
						commandresult=("extension format isn't correct; use contentcub 01.\n");
					}} else {
						commandresult= ("no content given; try again.\n");
					}
				}
				else{
					commandresult= ("this wagon already exists.\n");
				}
			} }else {
				commandresult= (
						"no viable type given; use train, passengerwagon, liquidcargowagon or solid cargowagon.\n");
			}
		}
		// get numseats from passengerwagons and trains
		else if (command.substring(0,11).equals("getnumseats")) {
			if (command.length()>17){
			//get seats from train
			if (command.substring(12,17).equals("train")) {
				String execution = command.replace("getnumseats train ", "");
				int result = tc.getNumseatsTrain(execution);
				//check if train exists
				if (result > 0) {
					commandresult= ("train " + execution + " has " + result + " total seats.\n");
				} else {
					commandresult= ("train " + execution + " does not exist.\n");
				}
			}
			// get seats from passengerswagon
			else if (command.substring(12,26).equals("passengerwagon")) {
				String id = command.replace("getnumseats passengerwagon ", "");
				Wagon wag=tc.getWagon(id);
				//check if wagon is a passengerwagon
				if (wag.getClass()==Passengerswagon.class){
					Passengerswagon pw=(Passengerswagon)wag;
					commandresult= ("passengerwagon "+id+" has "+pw.getSeats()+" seats.\n");
				}
				else{
					commandresult= ("wagon "+id+" is not a passengerwagon.\n");
				}

			} else {
				commandresult= ("No passengerwagon or train with the given id exists.\n");
			}}else{
				commandresult=("incorrect format; use getnumseats train/passengerwagon wID.\n");
			}
		}
		// get contentlit from liquidcargowagons and trains
		else if (command.substring(0,13).equals("getcontentlit")) {
			if (command.length()>19){
			if (command.substring(14,19).equals("train")) {
				String execution = command.replace("getcontentlit train ", "");
				//check if train exists
				if (tc.TrainControl(execution)==true){
				int result = tc.getContentlitTrain(execution);
				if (result > 0) {
					commandresult= ("train " + execution + " has " + result + " liters total content.\n");
				} else {
					commandresult= ("train " + execution + " does not have liquidcargowagons.\n");
				}
				}
				else{
					commandresult= ("train "+execution+" does not exist.\n");
				}
			}
			//get contentlit of liquidcargowagon
			else if (command.substring(14,30).equals("liquidcargowagon")) {
				String id = command.replace("getcontentlit liquidcargowagon ", "");
				Wagon wag=tc.getWagon(id);
				if (wag.getClass()==LiquidCargowagon.class){
					LiquidCargowagon lcw=(LiquidCargowagon)wag;
					commandresult= ("liquidcargowagon "+id+" has a content of "+lcw.getContentliters()+" liters.\n");
				}
				else{
					commandresult= ("wagon "+id+" is not a liquidcargowagon.\n");
				}

			} else {
				commandresult= ("No liquidcargowagon or train with the given id exists.\n");
			}}
			else{
				commandresult=("incorrect format; use getcontentlit train/liquidcargowagon wID.\n");
			}
		}
		//get contentcub of train
		else if (command.substring(0,13).equals("getcontentcub")) {
			if (command.length()>19){
			if (command.substring(14,19).equals("train")) {
				String execution = command.replace("getcontentcub train ", "");
				if (tc.TrainControl(execution)==true){
				int result = tc.getContentcubTrain(execution);
				if (result > 0) {
					commandresult= ("train " + execution + " has " + result + " cubic meters total content.\n");
				} else {
					commandresult= ("train " + execution + " does not have solidcargowagons.\n");
				}
				}
				else{
					commandresult= ("train "+execution+" does not exist.\n");
				}
			}
			//get contentcub of solidcargowagon
			else if (command.substring(14,29).equals("solidcargowagon")) {
				String id = command.replace("getcontentcub solidcargowagon ", "");
				Wagon wag=tc.getWagon(id);
				if (wag.getClass()==SolidCargowagon.class){
					SolidCargowagon scw=(SolidCargowagon)wag;
					commandresult= ("solidcargowagon "+id+" has a content of "+scw.getContentcubic()+" cubic meters.\n");
				}
				else{
					commandresult= ("wagon "+id+" is not a solidcargowagon.\n");
				}

			} else {
				commandresult= ("No solidcargowagon or train with the given id exists.\n");
			}}
			else{
				commandresult=("incorrect format; use getcontentcub train/solidcargowagon wID.\n");
			}
		}
		// add wagon to train
		else if (command.substring(0,3).equals("add")) {
			String wagon_id = command.substring(4,7);
			String train_id = command.substring(11, 14);
			
			Wagon wag = tc.getWagon(wagon_id);
			Train tr = tc.getTrain(train_id);
			//individual errors
			if (wag==null){
				commandresult= ("The given wagon doesn't exist or isn't a wagon.\n");
			}
			if (tr==null){
				commandresult= ("The given train doesn't exist or isn't a train.\n");
			}
			if (tc.getWagon(wagon_id).getClass().equals(Locomotive.class)) {
				commandresult= ("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
			}
			if (tc.addWagonControl(wag, tr) == true) {
				commandresult= ("wagon " + wag.getWagonid() + " is already attached to train " + tr.getTrainid() + ".\n");
			}
			//final control
			if (wag!=null && tr!=null && !wag.getClass().equals(Locomotive.class)&&tc.addWagonControl(wag,tr)==false){
			tc.addWagon(wag, tr);
			if (tc.getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
				Passengerswagon paswag = (Passengerswagon) wag;
				//add seats of wagon to totalseats of train
				tr.setTotalseats(tr.getTotalseats() + paswag.getSeats());
				tc.updateTrain(tr);
				commandresult= ("passengerwagon " + wag.getWagonid() + " has been added to train "
						+ tr.getTrainid() + ".\n");
			}
			if (tc.getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
				commandresult= ("solidcargowagon " + wag.getWagonid() + " has been added to train "
						+ tr.getTrainid() + ".\n");
			}
			if (tc.getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
				commandresult= ("liquidcargowagon " + wag.getWagonid() + " has been added to train "
						+ tr.getTrainid() + ".\n");
			}
			
		}}
		// remove wagon from train
		else if (command.substring(0,6).equals("remove")) {
			String wagon_id = command.substring(7,10);
			String train_id = command.substring(16,19);

			Wagon wag = tc.getWagon(wagon_id);
			Train tr = tc.getTrain(train_id);
			//individual errors
			if (wag==null){
				commandresult= ("The given wagon doesn't exist or isn't a wagon.\n");
			}
			if (tr==null){
				commandresult= ("The given train doesn't exist or isn't a train.\n");
			}
			if (tc.getWagon(wagon_id).getClass().equals(Locomotive.class)) {
				commandresult= ("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
			}
			if (tc.addWagonControl(wag, tr) == false) {
				commandresult= ("wagon " + wag.getWagonid() + " is not attached to train " + tr.getTrainid() + ".\n");
			}
			//final control
			if (wag!=null && tr!=null && !wag.getClass().equals(Locomotive.class)&&tc.addWagonControl(wag,tr)==true){
				tc.removeWagon(wag,tr);
				if (tc.getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
					Passengerswagon paswag = (Passengerswagon) wag;
					//remove seats of wagon from totalseats of train
					tr.setTotalseats(tr.getTotalseats() - paswag.getSeats());
					tc.updateTrain(tr);
					commandresult= ("passengerwagon " + wag.getWagonid() + " has been removed from train "
							+ tr.getTrainid() + ".\n");
				}
				if (tc.getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
					commandresult= ("solidcargowagon " + wag.getWagonid() + " has been removed from train "
							+ tr.getTrainid() + ".\n");
				}
				if (tc.getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
					commandresult= ("liquidcargowagon " + wag.getWagonid() + " has been removed from train "
							+ tr.getTrainid() + ".\n");
				}
			}
		}
		// delete train or wagon
		else if (command.substring(0,6).equals("delete")) {
			if (command.length()>10){
			String target = command.substring(13,16);
			//delete train
			if (command.substring(7,12).equals("train")) {
				if (tc.getTrain(target) != null) {
					tc.deleteTrain(tc.getTrain(target));
					commandresult= ("train " + target + " deleted.\n");
				} else {
					commandresult= ("train " + target + " does not exist.\n");
				}

			}
			//delete wagon
			else if (command.substring(7,12).equals("wagon")) {
				if (tc.getWagon(target) != null) {
					if (tc.getWagon(target).getClass().equals(Locomotive.class)) {
						commandresult= ("The given wagon is a locomotive; locomotives can't removed.\n");
					}
					else{
					tc.deleteWagon(tc.getWagon(target));
					commandresult= ("wagon " + target + " deleted.\n");
				}} else {
					commandresult= ("wagon " + target + " does not exist.\n");
				}

			}
			else{
				commandresult= ("No correct type given; use train or wagon.\n");
			}}else{
				commandresult=("incorrect format; use delete train/wagon oID.\n");
			}
		}
		// command doesn't exist
		else {
			commandresult= (
					"given command is illegal; try new, add, remove, getnumseats, getcontentcub, getcontentlit or delete\n");
		}
}else{
		commandresult=("command too short; use add/remove wID to/from tID or getnumseats/getcontentlit/getcontentcub/delete/new type ID.\n");
}
		return commandresult;
}}
