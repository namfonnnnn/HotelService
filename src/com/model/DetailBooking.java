package com.model;

import java.util.List;

import com.connect.Connect;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DetailBooking {
	private int status;
	private String startDate;
	private String endDate;
	
	public int [] room (String startDate, String endDate){
		DB db = new Connect().mongo();
		DBCollection collection = db.getCollection("detailBooking");
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("status",com.Enum.Enum.statusBookingConfirm);
		searchQuery.put("startDate", new BasicDBObject("$gte", startDate));
		searchQuery.put("endDate", new BasicDBObject("$lte", endDate));
		
		System.out.println(searchQuery);
		DBCursor cursor = collection.find(searchQuery);
		
		List<DBObject> myList = null;
		myList = cursor.toArray();
		
		// ห้องที่ไม่ว่าง
		int[] roomNumber = new int[myList.size()];
		int index = 0;
		for (DBObject dbObject : myList) {
			roomNumber[index] = (int) dbObject.get("roomID");
			index++;
		}
		return roomNumber;
	}
}
