package com.hotel;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.connect.Connect;
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
}
