package co.aurasphere.reports.test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Month;
import java.time.YearMonth;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.aurasphere.reports.dao.ReportDao;
import co.aurasphere.reports.model.Report;
import co.aurasphere.reports.utilities.ExcelGenerator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/reports-servlet.xml")
public class TestGenerationExcel {
	
	@Autowired
	private ReportDao reportDao;
	
	@Test
	public void testGenerateFileExcel() {
		YearMonth yearMonth = YearMonth.of(2018, Month.NOVEMBER);
		List<Report> reports = reportDao.getReportsSummary(yearMonth);
		ExcelGenerator excelGenerator = new ExcelGenerator();
		// Prints the report.
		try (FileOutputStream fileOut = new FileOutputStream("Rapportini-test.xls")) {
			excelGenerator.createExcelForReports(reports, yearMonth, fileOut);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}