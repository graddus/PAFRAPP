package data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import design.LiquidCargowagon;
import design.Locomotive;
import design.Passengerswagon;
import design.SolidCargowagon;
import design.Train;
import design.Wagon;

public class TrainDAO extends BaseDAO {
	private ArrayList<Train> trainlist;
	private ArrayList<Wagon> wagonlist;

	public TrainDAO() {
	}

	// Lijst van alle Customers ontvangen
	public ArrayList<Train> getTrains() {
		trainlist = new ArrayList<Train>();
		// Leg de connectie met de database
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "SELECT * FROM Trains order by id";

			// Een tweede statement uitvoeren
			ResultSet rs = stmt.executeQuery(queryText);

			String code;
			int totalseats;
			Train train;

			while (rs.next()) {

				code = rs.getString("ID");
				totalseats = rs.getInt("totalseats");
				train = new Train(code);
				train.setTotalseats(totalseats);
				train.setWagonlist(getWagons(train));
				trainlist.add(train);
			}
			// De resultset, het statement en de verbinding sluiten
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return trainlist;
	}

	public Train getTrain(String id) {
		trainlist = new ArrayList<Train>();
		// Leg de connectie met de database
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "SELECT * FROM Trains where ID='" + id + "'";

			// Een tweede statement uitvoeren
			ResultSet rs = stmt.executeQuery(queryText);

			String code;
			int totalseats;
			Train train2;

			while (rs.next()) {

				code = rs.getString("ID");
				totalseats = rs.getInt("totalseats");
				train2 = new Train(code);
				train2.setTotalseats(totalseats);
				train2.setWagonlist(getWagons(train2));
				trainlist.add(train2);
			}
			// De resultset, het statement en de verbinding sluiten
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			return null;
		}
		if (trainlist.isEmpty() == true) {
			return null;
		} else {
			return trainlist.get(0);
		}
	}

	public void createTrain(String id) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "INSERT INTO Trains values('" + id + "', 1)";
			String queryText2 = "INSERT INTO Wagons values('" +id+ "', 'Locomotive', 1, NULL, NULL, '" + id + "')";
			// changed wagon id to be train id again
			// TODO wagon id shouldnt be the trains id as this can give errors
			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);
			stmt.executeQuery(queryText2);

			// De resultset, het statement en de verbinding sluiten
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Wagon> getWagons(Train train) {
		wagonlist = new ArrayList<Wagon>();
		// Leg de connectie met de database
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "SELECT * FROM Wagons WHERE train_ID='" + train.getTrainid() + "' order by id";

			// Een tweede statement uitvoeren
			ResultSet rs = stmt.executeQuery(queryText);

			String code;
			int seats;
			double contentcubic;
			double contentliters;
			String wagontype;
			Passengerswagon pw;
			SolidCargowagon scw;
			LiquidCargowagon lcw;
			Locomotive loco;

			while (rs.next()) {
				wagontype = rs.getString("wagontype");
				code = rs.getString("id");
				if (wagontype.equals("Passenger")) {
					seats = rs.getInt("seats");
					pw = new Passengerswagon(code, seats);
					wagonlist.add(pw);
				}
				if (wagontype.equals("Locomotive")) {
					loco = new Locomotive(code, 1);
					wagonlist.add(loco);
				}
				if (wagontype.equals("SolidCargo")) {
					contentcubic = rs.getDouble("ContentCubic");
					scw = new SolidCargowagon(code, contentcubic);
					wagonlist.add(scw);
				}
				if (wagontype.equals("LiquidCargo")) {
					contentliters = rs.getDouble("ContentLiters");
					lcw = new LiquidCargowagon(code, contentliters);
					wagonlist.add(lcw);
				}
			}
			// De resultset, het statement en de verbinding sluiten
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wagonlist;
	}

	public Wagon getWagon(String id) {
		wagonlist = new ArrayList<Wagon>();
		// Leg de connectie met de database
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "SELECT * FROM Wagons WHERE id='" + id + "'";

			// Een tweede statement uitvoeren
			ResultSet rs = stmt.executeQuery(queryText);

			String code;
			int seats;
			double contentcubic;
			double contentliters;
			String wagontype;
			Passengerswagon pw;
			SolidCargowagon scw;
			LiquidCargowagon lcw;
			Locomotive loco;

			while (rs.next()) {
				wagontype = rs.getString("wagontype");
				code = rs.getString("id");
				if (wagontype.equals("Passenger")) {
					seats = rs.getInt("seats");
					pw = new Passengerswagon(code, seats);
					wagonlist.add(pw);
				}
				if (wagontype.equals("Locomotive")) {
					loco = new Locomotive(code, 1);
					wagonlist.add(loco);
				}
				if (wagontype.equals("SolidCargo")) {
					contentcubic = rs.getDouble("ContentCubic");
					scw = new SolidCargowagon(code, contentcubic);
					wagonlist.add(scw);
				}
				if (wagontype.equals("LiquidCargo")) {
					contentliters = rs.getDouble("ContentLiters");
					lcw = new LiquidCargowagon(code, contentliters);
					wagonlist.add(lcw);
				}
			}
			// De resultset, het statement en de verbinding sluiten
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (wagonlist.isEmpty() == true) {
			return null;
		}
		return wagonlist.get(0);
	}

	public void deleteTrain(Train train) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "UPDATE WAGONS set train_id=NULL where train_id='" + train.getTrainid() + "'";
			String queryText2 = "DELETE from Trains where id='" + train.getTrainid() + "'";
			String queryText3 = "DELETE from Wagons where id='" + train.getTrainid() + "' AND wagontype='Locomotive'";

			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);
			stmt.executeQuery(queryText2);
			stmt.executeQuery(queryText3);

			// De resultset, het statement en de verbinding sluiten
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createWagon(String id, int value, String wagontype) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			//System.out.println(value);
			Statement stmt = conn.createStatement();
			String queryText = "";
			// Een tweede statement maken dat een resultaat oplevert
			if (wagontype.equals("Passenger")) {
				queryText = "INSERT INTO WAGONS(id,wagontype,seats) VALUES('" + id + "','Passenger'," + value + ")";
			}
			if (wagontype.equals("LiquidCargo")) {
				queryText = "INSERT INTO WAGONS(id,wagontype,contentliters) VALUES('" + id + "','LiquidCargo'," + value
						+ ")";
			}
			if (wagontype.equals("SolidCargo")) {
				queryText = "INSERT INTO WAGONS(id,wagontype,contentcubic) VALUES('" + id + "','SolidCargo'," + value
						+ ")";
			}
			if (wagontype.equals("Locomotive")) {
				queryText = "INSERT INTO WAGONS(id,wagontype,seats) VALUES('" + id + "','Locomotive'," + value + ")";
			}
			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);

			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addWagon(Wagon wagon, Train train) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();
			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "UPDATE WAGONS SET TRAIN_ID='" + train.getTrainid() + "' WHERE ID='" + wagon.getWagonid()
					+ "'";
			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);

			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removeWagon(Wagon wagon, Train train) {
		try {
			Connection conn = super.getConnection();

			System.out.println(wagon.getWagonid());
			System.out.println(train.getTrainid());
			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();
			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "UPDATE WAGONS SET TRAIN_ID=NULL WHERE ID='" + wagon.getWagonid() + "' AND TRAIN_ID='"
					+ train.getTrainid() + "'";
			;
			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);

			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteWagon(Wagon wagon) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();

			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "DELETE from Wagons where id='" + wagon.getWagonid() + "'";

			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);

			// De resultset, het statement en de verbinding sluiten
			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateTrain(Train train) {
		try {
			Connection conn = super.getConnection();

			// Een eerste SQL statement maken
			Statement stmt = conn.createStatement();
			// Een tweede statement maken dat een resultaat oplevert
			String queryText = "UPDATE Trains SET totalseats=" + train.getTotalseats() + " WHERE ID='"
					+ train.getTrainid() + "'";
			// Een tweede statement uitvoeren
			stmt.executeQuery(queryText);

			conn.commit();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
