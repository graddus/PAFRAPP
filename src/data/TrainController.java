package data;

import java.util.ArrayList;

import design.Train;
import design.Wagon;

public class TrainController {
	private TrainDAO dao = new TrainDAO();

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

}
