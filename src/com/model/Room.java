package com.model;

import javax.jws.WebParam;

import org.bson.types.ObjectId;

import com.connect.Connect;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Room {
	private String id;
	private int roomID;
	private String type;
	private double price;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRoomID() {
		return roomID;
	}
	public void setRoomID(int roomid) {
		this.roomID = roomid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean create(int roomID,String type,double price) {
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("room");
		
		
		BasicDBObject document = new BasicDBObject();
		document.put("roomID", roomID);
		document.put("type", type);
		document.put("price", price);

		collection.insert(document);
		
		return true;
	}
	public boolean update(String id, int roomID,String type,double price ){
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("course");
		
		BasicDBObject document = new BasicDBObject();
		document.put("roomID", roomID);
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
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("room");
		
		DBObject document = collection.findOne(new ObjectId(id));
		collection.remove(document);
		
		return true;  
	}
	
	
	public Room getUpdate(@WebParam(name = "Room") String id) {
		DB db = new Connect().mongo();
		DBCollection table = db.getCollection("Room");
		
		DBObject object = table.findOne(new ObjectId(id));
		
		Room course = new Room();
		course.setId(object.get("_id").toString());
		course.setRoomID((int) object.get("roomID"));
		course.setType(object.get("type").toString());
		course.setPrice((double) object.get("price"));
		
		return course;  
	}
	
}