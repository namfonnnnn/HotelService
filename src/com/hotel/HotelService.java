package com.hotel;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.connect.Connect;
import com.model.DetailBooking;
import com.model.Room;
import com.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;



@WebService(endpointInterface = "com.hotel.HotelServiceInterface")
public class HotelService {
		
	
	@WebMethod
	public List<Room> allRoom() {
		
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("room");
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DBCursor cursor = collection.find(searchQuery);
		
		List<DBObject> myList = null;
		myList = cursor.toArray();
		
		List<Room> list = new ArrayList<Room>();
		
		for (DBObject dbObject : myList) {
			Room c = new Room();
			c.setRoomID((int) dbObject.get("roomID"));
			c.setId(dbObject.get("_id").toString());
			c.setType(dbObject.get("type").toString());
			c.setPrice(dbObject.get("price").toString());
			list.add(c);
		}
		return list;
	}
	@WebMethod
	public List<Room> findRoomBusy(@WebParam(name = "startDate") String startDate,@WebParam(name = "endDate") String endDate){
		DetailBooking room = new DetailBooking();
		int[] fullRoom = room.room(startDate, endDate);
		
		Room room2 = new Room();
		
		List<Room> emptyRoom = new ArrayList<Room>();
		
		emptyRoom = room2.emptyRoom(fullRoom);
		return emptyRoom;	
	}
	
	@WebMethod
	public boolean createRoom(@WebParam(name = "roomID") int roomID, 
			@WebParam(name = "type") String type,@WebParam(name = "price") String price) {
		
	DB db = new Connect().mongo();
	DBCollection collection = db.getCollection("room");
	
	System.out.println(roomID);
	System.out.println(type);
	System.out.println(price);
	
	BasicDBObject document = new BasicDBObject();
	document.put("roomID", roomID);
	document.put("type", type);
	document.put("price", price);

	collection.insert(document);
	
	return true; 
	}
	
	@WebMethod
	public boolean updateRoom(@WebParam(name = "id") String id ,@WebParam(name = "roomID") int roomID, 
			@WebParam(name = "type") String type, @WebParam(name = "price") String price) {
		Room room = new Room();
		double p = Double.parseDouble(price);
		room.update(id, roomID, type, p);
	return true;
	}
	
	@WebMethod
	public Room getupdateRoom(@WebParam(name = "id") String id) {
		Room room = new Room();
		room.delete(id);
	return room;
	}

	@WebMethod
	public Room deleteRoom(@WebParam(name = "id") String id) {
		Room room = new Room();
		room.getUpdate(id);
	return room;
	}
	
	@WebMethod
	public boolean createUser(@WebParam(name = "lastName") String lastName, 
			@WebParam(name = "firstName") String firstName, @WebParam(name = "identity") String identity,
			@WebParam(name = "phone") String phone, @WebParam(name = "email") String email,
			@WebParam(name = "address") String address, @WebParam(name = "username") String username,
			@WebParam(name = "password") String password, @WebParam(name = "type") String type) 			
	{
		User user= new User();
		user.create(lastName, firstName, identity, phone, email, address, username, password, type);
	return true;
	}
	
	@WebMethod
	public boolean updateUser(@WebParam(name = "id") String id ,@WebParam(name = "lastName") String lastName, 
			@WebParam(name = "firstName") String firstName, @WebParam(name = "identity") String identity,
			@WebParam(name = "phone") String phone, @WebParam(name = "email") String email,
			@WebParam(name = "address") String address, @WebParam(name = "username") String username,
			@WebParam(name = "password") String password, @WebParam(name = "type") String type) 
	{
		User user = new User();
		user.update(id, lastName, firstName, identity, phone, email, address, username, password, type);
	return true;
	}
	
	@WebMethod
	public User getupdateUser(@WebParam(name = "id") String id) {
		User user = new User ();
		user.getUpdate(id);
	return user;
	}
	
	
}
