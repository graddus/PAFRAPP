package data;

import java.util.ArrayList;

import design.Train;
import design.Wagon;

public class TrainController {
	private TrainDAO dao = new TrainDAO();

	public ArrayList<Train> getTrains() {
		return dao.getTrains();
	}
	public Train getTrainbyID(int id){
			return dao.getTrainbyID(id);
		}

	public void createTrain(Train train) {
		dao.createTrain(train);
	}

	public ArrayList<Wagon> getWagons(Train train) {
		return dao.getWagons(train);
	}

	public void deleteTrain(Train train){
		dao.deleteTrain(train);
	}

	public void createWagon(Wagon wagon, Train train) {
			dao.createWagon(wagon, train);
	}

}
