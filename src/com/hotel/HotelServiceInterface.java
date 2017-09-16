package com.hotel;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.model.Room;

@WebService
public interface HotelServiceInterface {

	@WebMethod
	public List<Room> allRoom();
	
	@WebMethod
	public List<Room> findRoomBusy();
	
	@WebMethod
	public List<Room> createUser();
}
