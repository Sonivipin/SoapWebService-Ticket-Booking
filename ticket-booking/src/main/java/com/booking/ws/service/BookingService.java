package com.booking.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booking.ws.repository.BookingRepository;
import com.booking.ws.ticket.GetBookingRequest;
import com.booking.ws.ticket.GetBookingResponse;
import com.booking.ws.validator.BookingValidator;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final BookingValidator validator;

	@Autowired
	public BookingService(final BookingRepository bookingRepository, final BookingValidator bookingValidator) {
		this.bookingRepository = bookingRepository;
		this.validator = bookingValidator;
	}

	public GetBookingResponse bookTicket(GetBookingRequest request) {
		GetBookingResponse response = null;

		// validate request
		validator.validate(request);

		if (validator.hasErrors()) {
			return validator.getErrors();
		}

		// call repository to fetch booking or create new booking
		response = bookingRepository.bookTicket(request);

		return response;
	}

}
