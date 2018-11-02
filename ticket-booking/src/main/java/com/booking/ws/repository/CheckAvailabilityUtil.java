package com.booking.ws.repository;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CheckAvailabilityUtil {

	private static Map<String, Map<Date, Boolean>> availability = new TreeMap<>();

	@PostConstruct
	public void initData() {

		Map<Date, Boolean> booking1 = new TreeMap<>();
		Map<Date, Boolean> booking2 = new TreeMap<>();

		availability.put("AB12345", booking1);
		booking1.put(new GregorianCalendar(2018, 0, 1).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 2).getTime(), false);
		booking1.put(new GregorianCalendar(2018, 0, 3).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 4).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 5).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 6).getTime(), false);
		booking1.put(new GregorianCalendar(2018, 0, 7).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 8).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 9).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 10).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 11).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 12).getTime(), false);
		booking1.put(new GregorianCalendar(2018, 0, 13).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 14).getTime(), true);
		booking1.put(new GregorianCalendar(2018, 0, 15).getTime(), true);

		availability.put("MSG98765", booking2);
		booking2.put(new GregorianCalendar(2018, 0, 1).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 2).getTime(), false);
		booking2.put(new GregorianCalendar(2018, 0, 3).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 4).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 5).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 6).getTime(), false);
		booking2.put(new GregorianCalendar(2018, 0, 7).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 8).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 9).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 10).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 11).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 12).getTime(), false);
		booking2.put(new GregorianCalendar(2018, 0, 13).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 14).getTime(), true);
		booking2.put(new GregorianCalendar(2018, 0, 15).getTime(), true);
	}

	public Map<Date, Boolean> findTicketBooking(String passportNumber) {
		Assert.notNull(passportNumber, "The passport number must not be null");
		return availability.get(passportNumber);
	}

	public Map<String, Map<Date, Boolean>> getAvailability() {
		return availability;
	}

	public void addAvailability(Map<String, Map<Date, Boolean>> avalabilitiesForAllPassport) {
		CheckAvailabilityUtil.availability.putAll(avalabilitiesForAllPassport);
	}

}
