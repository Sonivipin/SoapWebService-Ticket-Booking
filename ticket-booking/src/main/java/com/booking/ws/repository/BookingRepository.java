package com.booking.ws.repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Repository;

import com.booking.ws.ticket.BookingDetails;
import com.booking.ws.ticket.Errors;
import com.booking.ws.ticket.GetBookingRequest;
import com.booking.ws.ticket.GetBookingResponse;

@Repository
public class BookingRepository {

	private final CheckAvailabilityUtil checkAvailabilityUtil;

	public BookingRepository(CheckAvailabilityUtil checkAvailabilityUtil) {
		this.checkAvailabilityUtil = checkAvailabilityUtil;
	}

	public GetBookingResponse bookTicket(GetBookingRequest request) {
		GetBookingResponse response = new GetBookingResponse();
		BookingDetails bookingDetails = null;

		try {
			if (isTravelDetailsAvailable(request)) {
				bookingDetails = new BookingDetails();
				bookingDetails.setTicketId(new Random().nextInt(3550));
				bookingDetails.setPassportNumber(request.getPassangerDetails().getPassportNumber());
				response.setBookingDetails(bookingDetails);
			} else {
				Errors errors = new Errors();
				errors.setErrorMessage("TICKETS NOT AVAILABLE FOR GIVEN DATE AND PASSPORT");
				errors.setErrorCode(200);
				response.setErrors(errors);
			}
		} catch (ParseException e) {
			Errors errors = new Errors();
			errors.setErrorMessage("DATE OF JOURNEY FORMAT NOT CORRECT . PLEASE ENTER IN FORMAT DD-MM-YYYY");
			errors.setErrorCode(404);
			response.setErrors(errors);
		}
		return response;
	}

	private boolean isTravelDetailsAvailable(GetBookingRequest request) throws ParseException {

		Map<Date, Boolean> availability = checkAvailabilityUtil
				.findTicketBooking(request.getPassangerDetails().getPassportNumber());

		boolean isAvailable = false;

		Date travelDate = null;

		travelDate = new SimpleDateFormat("yyyy-MM-dd")
				.parse(request.getPassangerDetails().getDateOfJourney().toString());

		if (availability != null) {
			isAvailable = (availability.get(travelDate) != null && availability.get(travelDate) == true);
		}

		return isAvailable;

	}

}
