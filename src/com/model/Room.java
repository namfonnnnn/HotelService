package com.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;

import com.connect.Connect;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Room {
	private String id;
	private String roomID;
	private String type;
	private String price;
	DB db = new Connect().mongo();
	DBCollection collection = db.getCollection("room");
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRoomID() {
		return roomID;
	}
	public void setRoomID(String i) {
		this.roomID = i;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public boolean create(int roomID,String type,double price) {
			
		BasicDBObject document = new BasicDBObject();
		document.put("roomID", roomID);
		document.put("type", type);
		document.put("price", price);
		
		collection.insert(document);
		
		return true;
	}
	
	public boolean update(String id, String roomID2,String type,double price ){
	
		BasicDBObject document = new BasicDBObject();
		document.put("roomID", roomID2);
		document.put("type", type);
		document.put("price", price);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(id));
		collection.update(searchQuery, setQuery);
		
		return true;  
	}
	public boolean delete(String id) {
		
		DBObject document = collection.findOne(new ObjectId(id));
		collection.remove(document);
		
		return true;  
	}
	
	public Room getUpdate(String id) {
		DBObject object = collection.findOne(new ObjectId(id));
		
		Room course = new Room();
		course.setId(object.get("_id").toString());
		course.setRoomID(object.get("roomID").toString());
		course.setType(object.get("type").toString());
		course.setPrice(object.get("price").toString());
		
		return course;  
	}
	
	public List<Room> emptyRoom(int[] roomNumber)    {
		DBCollection collection = db.getCollection("room");
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("roomID", new BasicDBObject("$nin",roomNumber));
		DBCursor cursor = collection.find(searchQuery);
		
		List<DBObject> myList = cursor.toArray();
		
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
	
}