package com.booking.ws.validator;

import java.util.StringJoiner;

import org.springframework.stereotype.Component;

import com.booking.ws.ticket.Errors;
import com.booking.ws.ticket.GetBookingRequest;
import com.booking.ws.ticket.GetBookingResponse;
import com.booking.ws.util.StringUtils;

@Component
public class BookingValidator {

	boolean hasErrors = false;
	Errors errors = null;

	public void validate(final GetBookingRequest request) {

		StringJoiner stringJoiner = new StringJoiner("||");

		if (!StringUtils.isNotEmpty(request.getPassangerDetails().getFirstName())) {
			stringJoiner.add("First name must not be empty.");
			hasErrors = true;
		}

		if (!StringUtils.isNotEmpty(request.getPassangerDetails().getLastName())) {
			stringJoiner.add("Last name must not be empty.");
			hasErrors = true;
		}

		if (!StringUtils.isNotEmpty(request.getPassangerDetails().getPassportNumber())) {
			stringJoiner.add("Passport number must not be empty.");
			hasErrors = true;
		}

		if (request.getPassangerDetails().getDateOfJourney() == null) {
			stringJoiner.add("Journey date must not be empty.");
			hasErrors = true;
		}

		if (hasErrors) {
			errors = new Errors();
			errors.setErrorCode(404);
			errors.setErrorMessage(stringJoiner.toString());
		}

	}

	public boolean hasErrors() {
		return hasErrors;
	}

	public GetBookingResponse getErrors() {
		GetBookingResponse response = null;
		if (hasErrors) {
			response = new GetBookingResponse();
			response.setErrors(errors);
		}
		return response;
	}

}
