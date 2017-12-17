package data;

import design.*;

public class CommandController {
	private TrainController tc = new TrainController();

	// COMMAND INTERFACE MAIN METHOD
	public String executeCommand(String command) {
		String commandresult = "";
		if (command.length() > 10) {
			if (command.substring(0, 3).equals("new")) {
				String value = "";
				String id = "";
				String execute = "";
				if (command.substring(4, 9).equals("train") && tc.idcontrol(command) == true) {
					id = command.replace("new train ", "");
					// check if train exists already
					if (tc.TrainControl(id) == false) {
						tc.createTrain(id);
						// commandresult = ("train " + id + " created.\n");
					} else {
						commandresult = ("This train already exists.\n");
					}
				}
				// create a wagon
				else if (command.toLowerCase().contains("wagon") && tc.idcontrol(command) == true) {
					// create passengerwagon
					if (command.substring(4, 18).equals("passengerwagon")) {
						execute = command.replace("new passengerwagon ", "");
						if (execute.contains("numseats")) {
							id = execute.substring(0, execute.indexOf(" "));
						} else {
							id = execute.substring(0, execute.length());
						}
						// System.out.println(id);
						// check if wagon exists already
						if (!id.contains(" ")) {
							if (tc.WagonControl(id) == false) {
								// check for optional numseats input
								if (command.length() > 22) {
									if (execute.contains("numseats")) {
										execute = execute.replace("numseats ", "");
										value = execute.substring(execute.indexOf(" ") + 1, execute.length());
										tc.createPassengerWagon(id, Integer.parseInt(value));
										// commandresult = ("passengerwagon " +
										// id + " created with " + value + "
										// seats.\n");
									} else {
										tc.createPassengerWagon(id, 20);
										// commandresult = ("passengerwagon " +
										// id + " created with 20 seats.\n");
									}
								} else {
									commandresult = ("extension format isn't correct; use numseats 01.\n");
								}
							} else {
								commandresult = ("this wagon already exists.\n");
							}
						} else {
							commandresult = ("either the given id contains spaces or the wrong extension was used.\n");
						}
					}
					// create a liquidcargowagon
					if (command.substring(4, 20).equals("liquidcargowagon")) {
						execute = command.replace("new liquidcargowagon ", "");
						if (execute.contains("contentlit")) {
							id = execute.substring(0, execute.indexOf(" "));
						} else {
							id = execute.substring(0, execute.length());
						}
						System.out.println(id);
						// check if wagon exists already
						if (!id.contains(" ")) {
							if (tc.WagonControl(id) == false) {
								// check for optional numseats input
								if (command.length() > 22) {
									if (execute.contains("contentlit")) {
										execute = execute.replace("contentlit ", "");
										value = execute.substring(execute.indexOf(" ") + 1, execute.length());
										tc.createLiquidCargoWagon(id, Integer.parseInt(value));
										// commandresult = ("liquidcargowagon "
										// + id + " created with " + value + "
										// liters content.\n");
									} else {
										tc.createLiquidCargoWagon(id, 100);
										// commandresult = ("liquidcargowagon "
										// + id + " created with 100 liters
										// content.\n");
									}
								} else {
									commandresult = ("extension format isn't correct; use contentlit 01.\n");
								}
							} else {
								commandresult = ("this wagon already exists.\n");
							}
						} else {
							commandresult = ("either the given id contains spaces or the wrong extension was used.\n");
						}
					}
					// create solidcargowagon
					if (command.substring(4, 19).equals("solidcargowagon")) {
						execute = command.replace("new solidcargowagon ", "");
						if (execute.contains("contentcub")) {
							id = execute.substring(0, execute.indexOf(" "));
						} else {
							id = execute.substring(0, execute.length());
						}
						System.out.println(id);
						// check if wagon exists already
						if (!id.contains(" ")) {
							if (tc.WagonControl(id) == false) {
								// check for optional numseats input
								if (command.length() > 22) {
									if (execute.contains("contentcub")) {
										execute = execute.replace("contentcub ", "");
										value = execute.substring(execute.indexOf(" ") + 1, execute.length());
										tc.createSolidCargoWagon(id, Integer.parseInt(value));
										// commandresult = ("solidcargowagon " +
										// id + " created with " + value + "
										// cubic meters content.\n");
									} else {
										tc.createSolidCargoWagon(id, 100);
										// commandresult = ("solidcargowagon " +
										// id + " created with 100 cubic meters
										// content.\n");
									}
								} else {
									commandresult = ("extension format isn't correct; use contentcub 01.\n");
								}
							} else {
								commandresult = ("this wagon already exists.\n");
							}
						} else {
							commandresult = ("either the given id contains spaces or the wrong extension was used.\n");
						}
					}
				} else {
					commandresult = ("no viable type given; use train, passengerwagon, liquidcargowagon or solid cargowagon.\n");
				}
			}
			// get numseats from passengerwagons and trains
			else if (command.substring(0, 11).equals("getnumseats")) {
				if (command.length() > 17) {
					// get seats from train
					if (command.substring(12, 17).equals("train")) {
						String execution = command.replace("getnumseats train ", "");
						int result = tc.getNumseatsTrain(execution);
						// check if train exists
						if (result > 0) {
							commandresult = ("train " + execution + " has " + result + " total seats.\n");
						} else {
							commandresult = ("train " + execution + " does not exist.\n");
						}
					}
					// get seats from passengerswagon
					else if (command.substring(12, 26).equals("passengerwagon")) {
						String id = command.replace("getnumseats passengerwagon ", "");
						Wagon wag = tc.getWagon(id);
						// check if wagon is a passengerwagon
						if (wag.getClass() == Passengerswagon.class) {
							Passengerswagon pw = (Passengerswagon) wag;
							commandresult = ("passengerwagon " + id + " has " + pw.getSeats() + " seats.\n");
						} else {
							commandresult = ("wagon " + id + " is not a passengerwagon.\n");
						}

					} else {
						commandresult = ("No passengerwagon or train with the given id exists.\n");
					}
				} else {
					commandresult = ("incorrect format; use getnumseats train/passengerwagon wID.\n");
				}
			}
			// get contentlit from liquidcargowagons and trains
			else if (command.substring(0, 13).equals("getcontentlit")) {
				if (command.length() > 19) {
					if (command.substring(14, 19).equals("train")) {
						String execution = command.replace("getcontentlit train ", "");
						// check if train exists
						if (tc.TrainControl(execution) == true) {
							int result = tc.getContentlitTrain(execution);
							if (result > 0) {
								commandresult = ("train " + execution + " has " + result + " liters total content.\n");
							} else {
								commandresult = ("train " + execution + " does not have liquidcargowagons.\n");
							}
						} else {
							commandresult = ("train " + execution + " does not exist.\n");
						}
					}
					// get contentlit of liquidcargowagon
					else if (command.substring(14, 30).equals("liquidcargowagon")) {
						String id = command.replace("getcontentlit liquidcargowagon ", "");
						Wagon wag = tc.getWagon(id);
						if (wag.getClass() == LiquidCargowagon.class) {
							LiquidCargowagon lcw = (LiquidCargowagon) wag;
							commandresult = ("liquidcargowagon " + id + " has a content of " + lcw.getContentliters()
									+ " liters.\n");
						} else {
							commandresult = ("wagon " + id + " is not a liquidcargowagon.\n");
						}

					} else {
						commandresult = ("No liquidcargowagon or train with the given id exists.\n");
					}
				} else {
					commandresult = ("incorrect format; use getcontentlit train/liquidcargowagon wID.\n");
				}
			}
			// get contentcub of train
			else if (command.substring(0, 13).equals("getcontentcub")) {
				if (command.length() > 19) {
					if (command.substring(14, 19).equals("train")) {
						String execution = command.replace("getcontentcub train ", "");
						if (tc.TrainControl(execution) == true) {
							int result = tc.getContentcubTrain(execution);
							if (result > 0) {
								commandresult = ("train " + execution + " has " + result
										+ " cubic meters total content.\n");
							} else {
								commandresult = ("train " + execution + " does not have solidcargowagons.\n");
							}
						} else {
							commandresult = ("train " + execution + " does not exist.\n");
						}
					}
					// get contentcub of solidcargowagon
					else if (command.substring(14, 29).equals("solidcargowagon")) {
						String id = command.replace("getcontentcub solidcargowagon ", "");
						Wagon wag = tc.getWagon(id);
						if (wag.getClass() == SolidCargowagon.class) {
							SolidCargowagon scw = (SolidCargowagon) wag;
							commandresult = ("solidcargowagon " + id + " has a content of " + scw.getContentcubic()
									+ " cubic meters.\n");
						} else {
							commandresult = ("wagon " + id + " is not a solidcargowagon.\n");
						}

					} else {
						commandresult = ("No solidcargowagon or train with the given id exists.\n");
					}
				} else {
					commandresult = ("incorrect format; use getcontentcub train/solidcargowagon wID.\n");
				}
			}
			// add wagon to train
			else if (command.substring(0, 3).equals("add")) {
				String execute = "";
				String wagon_id = "";
				String train_id = "";
				execute = command.replace("add ", "");
				execute = execute.replace("to ", "");
				wagon_id = execute.substring(0, execute.indexOf(" "));
				train_id = execute.substring(execute.indexOf(" ") + 1, execute.length());

				Wagon wag = tc.getWagon(wagon_id);
				Train tr = tc.getTrain(train_id);
				// individual errors
				if (wag == null) {
					commandresult = ("The given wagon doesn't exist or isn't a wagon.\n");
				} else if (tc.getWagon(wagon_id).getClass().equals(Locomotive.class)) {
					commandresult = ("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
				}
				if (tr == null) {
					commandresult = ("The given train doesn't exist or isn't a train.\n");
				} else if (tc.addWagonControl(wag, tr) == true) {
					commandresult = ("wagon " + wag.getWagonid() + " is already attached to train " + tr.getTrainid()
							+ ".\n");
				}
				// final control
				if (wag != null && tr != null && !wag.getClass().equals(Locomotive.class)
						&& tc.addWagonControl(wag, tr) == false) {
					tc.addWagon(wag, tr);
					if (tc.getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
						Passengerswagon paswag = (Passengerswagon) wag;
						// add seats of wagon to totalseats of train
						tr.setTotalseats(tr.getTotalseats() + paswag.getSeats());
						tc.updateTrain(tr);
						// commandresult = ("passengerwagon " + wag.getWagonid()
						// + " has been added to train " + tr.getTrainid() +
						// ".\n");
					}
					if (tc.getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
						// commandresult = ("solidcargowagon " +
						// wag.getWagonid() + " has been added to train " +
						// tr.getTrainid() + ".\n");
					}
					if (tc.getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
						// commandresult = ("liquidcargowagon " +
						// wag.getWagonid() + " has been added to train " +
						// tr.getTrainid() + ".\n");
					}

				}
			}
			// remove wagon from train
			else if (command.substring(0, 6).equals("remove")) {
				String execute = "";
				String wagon_id = "";
				String train_id = "";
				execute = command.replace("remove ", "");
				execute = execute.replace("from ", "");
				wagon_id = execute.substring(0, execute.indexOf(" "));
				train_id = execute.substring(execute.indexOf(" ") + 1, execute.length());

				Wagon wag = tc.getWagon(wagon_id);
				Train tr = tc.getTrain(train_id);
				// individual errors
				if (wag == null) {
					commandresult = ("The given wagon doesn't exist or isn't a wagon.\n");
				} else if (tc.getWagon(wagon_id).getClass().equals(Locomotive.class)) {
					commandresult = ("The given wagon is a locomotive; locomotives can't be moved to other trains.\n");
				}
				if (tr == null) {
					commandresult = ("The given train doesn't exist or isn't a train.\n");
				} else if (tc.addWagonControl(wag, tr) == false) {
					commandresult = ("wagon " + wag.getWagonid() + " is not attached to train " + tr.getTrainid()
							+ ".\n");
				}
				// final control
				if (wag != null && tr != null && !wag.getClass().equals(Locomotive.class)
						&& tc.addWagonControl(wag, tr) == true) {
					tc.removeWagon(wag, tr);
					if (tc.getWagon(wagon_id).getClass().equals(Passengerswagon.class)) {
						Passengerswagon paswag = (Passengerswagon) wag;
						// remove seats of wagon from totalseats of train
						tr.setTotalseats(tr.getTotalseats() - paswag.getSeats());
						tc.updateTrain(tr);
						// commandresult = ("passengerwagon " + wag.getWagonid()
						// + " has been removed from train " + tr.getTrainid() +
						// ".\n");
					}
					if (tc.getWagon(wagon_id).getClass().equals(SolidCargowagon.class)) {
						// commandresult = ("solidcargowagon " +
						// wag.getWagonid() + " has been removed from train "+
						// tr.getTrainid() + ".\n");
					}
					if (tc.getWagon(wagon_id).getClass().equals(LiquidCargowagon.class)) {
						// commandresult = ("liquidcargowagon " +
						// wag.getWagonid() + " has been removed from train "+
						// tr.getTrainid() + ".\n");
					}
				}
			}
			// delete train or wagon
			else if (command.substring(0, 6).equals("delete")) {
				if (command.length() > 10) {
					String target = command.substring(13, command.length());
					// delete train
					if (command.substring(7, 12).equals("train")) {
						if (tc.getTrain(target) != null) {
							tc.deleteTrain(tc.getTrain(target));
							// commandresult = ("train " + target + "
							// deleted.\n");
						} else {
							commandresult = ("train " + target + " does not exist.\n");
						}

					}
					// delete wagon
					else if (command.substring(7, 12).equals("wagon")) {
						if (tc.getWagon(target) != null) {
							if (tc.getWagon(target).getClass().equals(Locomotive.class)) {
								commandresult = ("The given wagon is a locomotive; locomotives can't removed.\n");
							} else {
								tc.deleteWagon(tc.getWagon(target));
								// commandresult = ("wagon " + target + "
								// deleted.\n");
							}
						} else {
							commandresult = ("wagon " + target + " does not exist.\n");
						}

					} else {
						commandresult = ("No correct type given; use train or wagon.\n");
					}
				} else {
					commandresult = ("incorrect format; use delete train/wagon oID.\n");
				}
			}
			// command doesn't exist
			else {
				commandresult = ("given command is illegal; try new, add, remove, getnumseats, getcontentcub, getcontentlit or delete\n");
			}
		} else {
			commandresult = ("command too short; use add/remove wID to/from tID or getnumseats/getcontentlit/getcontentcub/delete/new type ID.\n");
		}
		return commandresult;
	}
}
