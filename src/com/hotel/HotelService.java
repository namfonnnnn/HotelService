package com.hotel;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.bson.types.ObjectId;

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
			c.setRoomID(dbObject.get("roomID").toString());
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
		int a = emptyRoom.size();
		return emptyRoom;	
	}
	
	@WebMethod
	public boolean createRoom(@WebParam(name = "roomID") String roomID, 
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
	public boolean updateRoom(@WebParam(name = "id") String id ,@WebParam(name = "roomID") String roomID, 
			@WebParam(name = "type") String type, @WebParam(name = "price") String price) {
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("room");
		
		BasicDBObject document = new BasicDBObject();
		document.put("roomID", roomID);
		document.put("type", type);
		document.put("price", price);
		
		System.out.println(roomID);
		System.out.println(type);
		System.out.println(price);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(id));

		collection.update(searchQuery, setQuery);
		
		return true; 
	}
	
	@WebMethod
	public Room getupdateRoom(@WebParam(name = "id") String id) {
		
		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("room");
		System.out.println(id);
		DBObject object = table.findOne(new ObjectId(id));
		
		Room room = new Room();
		room.setId(object.get("_id").toString());
		room.setRoomID(object.get("roomID").toString());
		room.setType(object.get("type").toString());
		room.setPrice(object.get("price").toString());
		
		return room;
	}

	@WebMethod
	public boolean deleteRoom(@WebParam(name = "id") String id) {
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("room");
		System.out.println(id);
		DBObject document = collection.findOne(new ObjectId(id));
		collection.remove(document);
		
		return true; 
	}
	public boolean deleteUser(String id) {
		System.out.println(id);
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("user");
		DBObject document = collection.findOne(new ObjectId(id));
		collection.remove(document);
		
		return true;  
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


		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("user");
		System.out.println(id);
		DBObject object = table.findOne(new ObjectId(id));
		
		User course = new User();
		course.setId(object.get("_id").toString());
		course.setLastName(object.get("LastName").toString());
		course.setFirstName(object.get("FirstName").toString());
		course.setIdentity (object.get("identity").toString());
		course.setPhone    (object.get("phone").toString());
		course.setEmail    (object.get("email").toString());
		course.setAddress  (object.get("address").toString());
		course.setUsername (object.get("username").toString());
		course.setPassword (object.get("password").toString());
		course.setType     (object.get("type").toString());
		return course;  
	}
	
	
	@WebMethod
	public List<User> allUser(){
		
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("user");
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DBCursor cursor = collection.find(searchQuery);
		
		List<DBObject> myList = null;
		myList = cursor.toArray();
		
		List<User> list = new ArrayList<User>();
		
		for (DBObject dbObject : myList) {
			User u = new User();
			u.setId(dbObject.get("_id").toString());
			u.setFirstName(dbObject.get("FirstName").toString());
			u.setLastName(dbObject.get("LastName").toString());
			u.setIdentity(dbObject.get("identity").toString());
			u.setPhone(dbObject.get("phone").toString());
			u.setEmail(dbObject.get("email").toString());
			u.setAddress(dbObject.get("address").toString());
			u.setUsername(dbObject.get("username").toString());
			u.setPassword(dbObject.get("password").toString());
			u.setType(dbObject.get("type").toString());

			list.add(u);
		}
		return list;
	}
	
	
}
