package com.model;

import javax.jws.WebParam;                                       
import org.bson.types.ObjectId;
import com.connect.Connect;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import com.connect.Connect;

public class User {
	private String id;
	private String lastName;
	private String firstName;
	private String identity;
	private String phone;
	private String email;
	private String address;
	private String username;
	private String password;
	private String type;
	
	DB db = new Connect().mongo();
	DBCollection collection = db.getCollection("user");
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public boolean create(String lastName,String firstName,String identity,String phone,String email,String address,String username,String password,String type) {
	
		BasicDBObject document = new BasicDBObject();
		document.put("LastName", lastName);
		document.put("FirstName", firstName);
		document.put("identity", identity);
		document.put("phone", phone);
		document.put("email", email);
		document.put("address", address);
		document.put("username", username);
		document.put("password", password);
		document.put("type", type);

		collection.insert(document);
		
		return true;
	}
	public boolean update(String id,String lastName,String firstName,String identity,String phone,String email,String address,String username,String password,String type){
	
		BasicDBObject document = new BasicDBObject();
		document.put("LastName", lastName);
		document.put("FirstName", firstName);
		document.put("identity", identity);
		document.put("phone", phone);
		document.put("email", email);
		document.put("address", address);
		document.put("username", username);
		document.put("password", password);
		document.put("type", type);
		
		BasicDBObject setQuery = new BasicDBObject();
        setQuery.put("$set", document);
		
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", new ObjectId(id));

		collection.update(searchQuery, setQuery);
		
		return true;  
	}

	
	public User getUpdate(@WebParam(name = "User") String id) {
		System.out.println(id);
		DBObject object = collection.findOne(new ObjectId(id));
		
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
}
