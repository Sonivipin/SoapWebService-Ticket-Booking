package com.booking.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.booking.ws.ticket.GetBookingRequest;
import com.booking.ws.ticket.GetBookingResponse;

@Endpoint
public class BookingEndPoint {

	private static final String NAMESPACE_URI = "http://booking.com/ws/ticket";
	private BookingService bookingService;
	
	@Autowired
	public BookingEndPoint(final BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookingRequest")
	@ResponsePayload
	public GetBookingResponse processCourseDetailsRequest(@RequestPayload GetBookingRequest request) {
		GetBookingResponse response = bookingService.bookTicket(request);
		return response;
	}

}
