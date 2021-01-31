package co.aurasphere.reports.test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import co.aurasphere.reports.utilities.ItalianHolidays;

public class HolidayTest {
	
	@Test
	public void test() {
//		boolean holiday = ItalianHolidays.getInstance().isHoliday(new GregorianCalendar(2018, 9, 2));
//		System.out.println(holiday);
		Calendar c = Calendar.getInstance();
		System.out.println(LocalDate.now().getMonthValue());
	}

}
