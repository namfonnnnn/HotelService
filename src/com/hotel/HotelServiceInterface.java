package com.hotel;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface HotelServiceInterface {

	@WebMethod
	public List<Room> allRoom();
}
