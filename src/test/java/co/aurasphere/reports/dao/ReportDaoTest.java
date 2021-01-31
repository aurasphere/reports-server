package co.aurasphere.reports.dao;

import java.time.Month;
import java.time.YearMonth;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.aurasphere.reports.dao.ReportDao;

@ContextConfiguration("file:src/main/webapp/WEB-INF/reports-servlet.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ReportDaoTest {
	
	@Autowired
	private ReportDao dao;
	
	@Test
	@Ignore
	public void testReportSummaryAggregation() {
		YearMonth date = YearMonth.of(2018, Month.SEPTEMBER);
		System.out.println(dao.tmp(date));
	}
	
	@Test
	public void testUpdateReportQuery() {
		System.out.println(dao.tmp(YearMonth.of(2018, 9)));
	}

}
