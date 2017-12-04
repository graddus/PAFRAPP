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

	//Lijst van alle Customers ontvangen
	public ArrayList<Train> getTrains(){
		trainlist = new ArrayList<Train>();
			// Leg de connectie met de database
		try{			
		Connection conn=super.getConnection();
					System.out.println("Connection made");
				
					// Een eerste SQL statement maken
					Statement stmt = conn.createStatement();
					
					// Een tweede statement maken dat een resultaat oplevert
	 				String queryText = "SELECT * FROM Trains";
	 				
	 				// Een tweede statement uitvoeren
	 				ResultSet rs = stmt.executeQuery(queryText);
	 				
	 				int code;
	 				int totalseats;
	 				Train train;
	 				
	 				while (rs.next()) {
	 					
	 					code = rs.getInt("ID");	
	 					totalseats = rs.getInt("totalseats");
	 					train=new Train(code);
	 					train.setTotalseats(totalseats);
	 					train.setWagonlist(getWagons(train));
	 					trainlist.add(train);
	 					}
	 				// De resultset, het statement en de verbinding sluiten
	 				rs.close();
	 				stmt.close();
	 				conn.close();
		}
	 				catch (SQLException e){
	 					e.printStackTrace();
	 				}
	 				return trainlist;
	}
	public Train getTrainbyID(int id){
		trainlist = new ArrayList<Train>();
			// Leg de connectie met de database
		try{			
		Connection conn=super.getConnection();
					System.out.println("Connection made");
				
					// Een eerste SQL statement maken
					Statement stmt = conn.createStatement();
					
					// Een tweede statement maken dat een resultaat oplevert
	 				String queryText = "SELECT * FROM Trains where ID="+id;
	 				
	 				// Een tweede statement uitvoeren
	 				ResultSet rs = stmt.executeQuery(queryText);
	 				
	 				int code;
	 				int totalseats;
	 				Train train2;
	 				
	 				while (rs.next()) {
	 					
	 					code = rs.getInt("ID");	
	 					totalseats = rs.getInt("totalseats");
	 					train2=new Train(code);
	 					train2.setTotalseats(totalseats);
	 					train2.setWagonlist(getWagons(train2));
	 					trainlist.add(train2);
	 					}
	 				// De resultset, het statement en de verbinding sluiten
	 				rs.close();
	 				stmt.close();
	 				conn.close();
		}
	 				catch (SQLException e){
	 					e.printStackTrace();
	 				}
	 				return trainlist.get(0);
	}
	public void createTrain(String id){
		try{			
			Connection conn=super.getConnection();
						System.out.println("Connection made");
					
						// Een eerste SQL statement maken
						Statement stmt = conn.createStatement();
						
						// Een tweede statement maken dat een resultaat oplevert
		 				String queryText = "INSERT INTO Trains values('"+id+"', 0)";
		 				
		 				// Een tweede statement uitvoeren
		 				stmt.executeQuery(queryText);
		 				
		 				// De resultset, het statement en de verbinding sluiten
		 				conn.commit();
		 				stmt.close();
		 				conn.close();
			}
		 				catch (SQLException e){
		 					e.printStackTrace();
		 				}
		}
	public ArrayList<Wagon> getWagons(Train train){
		wagonlist = new ArrayList<Wagon>();
		// Leg de connectie met de database
	try{			
	Connection conn=super.getConnection();
				System.out.println("Connection made");
			
				// Een eerste SQL statement maken
				Statement stmt = conn.createStatement();
				
				// Een tweede statement maken dat een resultaat oplevert
 				String queryText = "SELECT * FROM Wagons WHERE train_ID="+train.getTrainid();
 				
 				// Een tweede statement uitvoeren
 				ResultSet rs = stmt.executeQuery(queryText);
 				
 				int code;
 				int seats;
 				int length;
 				double contentcubic;
 				double contentliters;
 				String wagontype;
 				Passengerswagon pw;
 				SolidCargowagon scw;
 				LiquidCargowagon lcw;
 				Locomotive loco;
 				
 				while (rs.next()) {
 					wagontype=rs.getString("wagontype");
 					code=rs.getInt("id");
 					length=rs.getInt("length");
 					if (wagontype.equals("Passenger")){
 						seats=rs.getInt("seats");
 						pw=new Passengerswagon(code, length, seats);
 						wagonlist.add(pw);
 					}
 					if (wagontype.equals("Locomotive")){
 						loco=new Locomotive(code, length, 1);
 						wagonlist.add(loco);
 					}
 					if (wagontype.equals("SolidCargo")){
 						contentcubic=rs.getDouble("ContentCubic");
 						scw=new SolidCargowagon(code, length, contentcubic);
 						wagonlist.add(scw);
 					}
 					if (wagontype.equals("LiquidCargo")){
 						contentliters=rs.getDouble("ContentLiters");
 						lcw=new LiquidCargowagon(code, length, contentliters);
 						wagonlist.add(lcw);
 					}
 				}
 				// De resultset, het statement en de verbinding sluiten
 				rs.close();
 				stmt.close();
 				conn.close();
	}
 				catch (SQLException e){
 					e.printStackTrace();
 				}
 				return wagonlist;
}
	public void deleteTrain(Train train){
		try{			
			Connection conn=super.getConnection();
						System.out.println("Connection made");
					
						// Een eerste SQL statement maken
						Statement stmt = conn.createStatement();
						
						// Een tweede statement maken dat een resultaat oplevert
						String queryText = "DELETE from Wagons where train_id="+train.getTrainid();
		 				String queryText2 = "DELETE from Train where id="+train.getTrainid();
		 				
		 				// Een tweede statement uitvoeren
		 				stmt.executeQuery(queryText);
		 				stmt.executeQuery(queryText2);
		 	
		 				// De resultset, het statement en de verbinding sluiten
		 				conn.commit();
		 				stmt.close();
		 				conn.close();
			}
		 				catch (SQLException e){
		 					e.printStackTrace();
		 				}
		}
public void createWagon(String id, int value, String wagontype){
	try{			
		Connection conn=super.getConnection();
		
					System.out.println("Connection made");
				
					// Een eerste SQL statement maken
					Statement stmt = conn.createStatement();
					String queryText="";
					// Een tweede statement maken dat een resultaat oplevert
	 				if (wagontype.equals("Passenger")){
	 					queryText="INSERT INTO WAGONS(id,wagontype,seats) VALUES('"+id+"','Passenger',"+value+")";
	 				}
	 				if (wagontype.equals("LiquidCargo")){
	 					queryText="INSERT INTO WAGONS(id,wagontype,contentliters) VALUES('"+id+",'LiquidCargo',"+value+")";
	 				}
	 				if (wagontype.equals("SolidCargo")){
	 					queryText="INSERT INTO WAGONS(id,wagontype,contentcubic) VALUES('"+id+"','SolidCargo',"+value+")";
	 				}
	 				if (wagontype.equals("Locomotive")){
	 					queryText="INSERT INTO WAGONS(id,wagontype,seats) VALUES('"+id+"','Locomotive',"+value+")";
	 				}
	 				// Een tweede statement uitvoeren
	 				stmt.executeQuery(queryText);

	 				conn.commit();
	 				stmt.close();
	 				conn.close();
		}
	 				catch (SQLException e){
	 					e.printStackTrace();
	 				}
	}
}