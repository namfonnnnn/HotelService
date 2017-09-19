package com.hotel;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.model.Room;
import com.model.User;

@WebService
public interface HotelServiceInterface {

	@WebMethod
	public List<Room> allRoom();
	@WebMethod
	public List<User> allUser();
	
	@WebMethod  //หาห้องว่าง ใส่เป็น yyyy-mm-dd
	public List<Room> findRoomBusy(@WebParam(name = "startDate") String startDate,@WebParam(name = "endDate") String endDate);
	
	
	@WebMethod
	public boolean createRoom(@WebParam(name = "roomID") String roomID, 
			@WebParam(name = "type") String type,@WebParam(name = "price") String price);
	
	public boolean updateRoom(@WebParam(name = "id") String id ,@WebParam(name = "roomID") String roomID, 
			@WebParam(name = "type") String type, @WebParam(name = "price") String price) ;
	
	
	@WebMethod
	public Room getupdateRoom(@WebParam(name = "id") String id);
	
	
	@WebMethod
	public Boolean deleteRoom(@WebParam(name = "id") String id);
	
	@WebMethod
	public Boolean deleteUser(@WebParam(name = "id") String id);
	@WebMethod
	
	public boolean createUser(@WebParam(name = "lastName") String lastName, 
			@WebParam(name = "firstName") String firstName, @WebParam(name = "identity") String identity,
			@WebParam(name = "phone") String phone, @WebParam(name = "email") String email,
			@WebParam(name = "address") String address, @WebParam(name = "username") String username,
			@WebParam(name = "password") String password, @WebParam(name = "type") String type);
	
	
	@WebMethod
	public boolean updateUser(@WebParam(name = "id") String id ,@WebParam(name = "lastName") String lastName, 
			@WebParam(name = "firstName") String firstName, @WebParam(name = "identity") String identity,
			@WebParam(name = "phone") String phone, @WebParam(name = "email") String email,
			@WebParam(name = "address") String address, @WebParam(name = "username") String username,
			@WebParam(name = "password") String password, @WebParam(name = "type") String type);
	
	
	@WebMethod
	public User getupdateUser(@WebParam(name = "id") String id);
	
}
