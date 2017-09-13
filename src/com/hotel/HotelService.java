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



@WebService(endpointInterface = "com.demo.HotelServiceInterface")
public class HotelService {
		
	
	@WebMethod
	public List<Room> allCourse() {
		
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("course");
		
		BasicDBObject searchQuery = new BasicDBObject();
		
		DBCursor cursor = collection.find(searchQuery);
		
		List<DBObject> myList = null;
		myList = cursor.toArray();
		
		System.out.println(myList);
		
		List<Room> list = new ArrayList<Room>();
		
		for (DBObject dbObject : myList) {
			Room c = new Room();
			c.setRoomID((int)dbObject.get("roomID"));
			c.setId(dbObject.get("_id").toString());
			c.setType(dbObject.get("type").toString());
			c.setPrice((double)dbObject.get("price"));
			list.add(c);
		}
		return list;
	
	}
}
