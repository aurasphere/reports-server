package co.aurasphere.reports.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.aurasphere.reports.model.Order;
import co.aurasphere.reports.model.Report;
import co.aurasphere.reports.model.Timesheet;
import co.aurasphere.reports.model.TimesheetRow;

public class ExcelGenerator {
	
	private static final Logger LOG = LoggerFactory.getLogger(ExcelGenerator.class);

	private static final DateTimeFormatter FIRST_LETTER_OF_DAY_FORMATTER = DateTimeFormatter.ofPattern("EEEEE",
			Locale.ITALIAN);
	
	private static final DateTimeFormatter YEAR_MONTH_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMMM yyyy",
			Locale.ITALIAN);
	/**
	 * Left padding for the report.
	 */
	private static final int CUSTOMER_NAME_COLUMN = 1;
	private static final int ROW_NAME_COLUMN = CUSTOMER_NAME_COLUMN + 1;
	private static final int HEADER_LOGO_COLUMN = ROW_NAME_COLUMN;
	private static final int DAYS_STARTING_COLUMN = ROW_NAME_COLUMN + 1;
	private static final int HEADER_ROW = 2;	
	
	public static void createExcelForReports(List<Report> reports, YearMonth reportDate, OutputStream output) {
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet("Rapportini");

		// Variables common between reports.
		int daysInMonth = reportDate.lengthOfMonth();
		boolean[] holidayArray = getHolidayArray(reportDate);
		int firstColumnAfterDays = daysInMonth + DAYS_STARTING_COLUMN;

		// Fonts used.
		Font bold = workbook.createFont();
		bold.setBold(true);

		Font boldRed = workbook.createFont();
		boldRed.setBold(true);
		boldRed.setColor(HSSFColorPredefined.RED.getIndex());
		
		Font boldWhite = workbook.createFont();
		boldWhite.setBold(true);
		boldWhite.setFontHeightInPoints((short) 30);
		boldWhite.setColor(HSSFColorPredefined.WHITE.getIndex());

		// Styles used.
		CellStyle borderedBoldRed = ExcelFormatter.newInstance(workbook).centerText().setFont(boldRed)
				.fullBorder(BorderStyle.MEDIUM).build();

		CellStyle borderedBoldRedGrey = ExcelFormatter.newInstance(workbook).centerText().setFont(boldRed)
				.fullBorder(BorderStyle.THIN).setBackgroundColor(HSSFColorPredefined.GREY_25_PERCENT).build();

		CellStyle borderedBold = ExcelFormatter.newInstance(workbook).centerText().setFont(bold)
				.fullBorder(BorderStyle.MEDIUM).build();

		CellStyle bordered = ExcelFormatter.newInstance(workbook).centerText().fullBorder(BorderStyle.THIN).build();

		CellStyle headerStyle = ExcelFormatter.newInstance(workbook).centerText()
				.setBackgroundColor(HSSFColorPredefined.GREY_80_PERCENT).setFont(boldWhite).build();

		// Report header logo.
		try (InputStream inputStream = ExcelGenerator.class.getClassLoader().getResourceAsStream("logo.png");) {
			byte[] imageBytes = IOUtils.toByteArray(inputStream);
			int pictureureIdx = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);
			CreationHelper helper = workbook.getCreationHelper();
			Drawing drawing = sheet.createDrawingPatriarch();
			ClientAnchor anchor = helper.createClientAnchor();
			anchor.setCol1(HEADER_LOGO_COLUMN);
			anchor.setCol2(HEADER_LOGO_COLUMN + 2);
			anchor.setRow1(HEADER_ROW);
			anchor.setRow2(HEADER_ROW + 4);
			drawing.createPicture(anchor, pictureureIdx);
		} catch (IOException e) {
			// Doesn't really matter so log only a warn.
			LOG.warn("Error while adding header logo.", e);
		}

		// Report header
		Row headerRow = sheet.createRow(HEADER_ROW);
		ExcelFormatter.createStyledCell(headerRow, CUSTOMER_NAME_COLUMN, "Reports Drill down " + YEAR_MONTH_DATE_FORMATTER.format(reportDate),
				headerStyle);
		CellRangeAddress headerCellRange = new CellRangeAddress(HEADER_ROW, HEADER_ROW + 3, CUSTOMER_NAME_COLUMN,
				firstColumnAfterDays);
		sheet.addMergedRegion(headerCellRange);

		// Starting rows for reports.
		int currentRow = 8;

		// Prints single reports.
		for (Report report : reports) {
			String user = report.getUserDisplayName();
			Timesheet timesheet = report.getTimesheet();
			List<TimesheetRow> timesheetRows = timesheet.getRows();

			// Employee row.
			Row userNameRow = sheet.createRow(currentRow);

			// Employee title.
			ExcelFormatter.createStyledCell(userNameRow, CUSTOMER_NAME_COLUMN, "Employee:", borderedBoldRed);
			CellRangeAddress employeeTitleCellRange = new CellRangeAddress(currentRow, currentRow, CUSTOMER_NAME_COLUMN,
					ROW_NAME_COLUMN);
			ExcelFormatter.createRegionWithBorders(BorderStyle.MEDIUM, employeeTitleCellRange, sheet);

			// Employee name.
			ExcelFormatter.createStyledCell(userNameRow, DAYS_STARTING_COLUMN, user, borderedBold);
			CellRangeAddress employeeNameCellRange = new CellRangeAddress(currentRow, currentRow, DAYS_STARTING_COLUMN,
					firstColumnAfterDays);
			ExcelFormatter.createRegionWithBorders(BorderStyle.MEDIUM, employeeNameCellRange, sheet);

			// Day numbers and names.
			Row dayNumberRow = sheet.createRow(++currentRow);
			Row dayNamesRow = sheet.createRow(++currentRow);

			// Customer column.
			ExcelFormatter.createStyledCell(dayNumberRow, CUSTOMER_NAME_COLUMN, "Customer", borderedBoldRed);
			CellRangeAddress customerNameCellRange = new CellRangeAddress(currentRow - 1, currentRow,
					CUSTOMER_NAME_COLUMN, CUSTOMER_NAME_COLUMN);
			ExcelFormatter.createRegionWithBorders(BorderStyle.MEDIUM, customerNameCellRange, sheet);

			// Row name column.
			ExcelFormatter.createStyledCell(dayNumberRow, ROW_NAME_COLUMN, "Type", borderedBoldRed);
			CellRangeAddress rowNameCellRange = new CellRangeAddress(currentRow - 1, currentRow, ROW_NAME_COLUMN,
					ROW_NAME_COLUMN);
			ExcelFormatter.createRegionWithBorders(BorderStyle.MEDIUM, rowNameCellRange, sheet);

			// Day numbers and names values.
			for (int i = 0; i < daysInMonth; i++) {

				// Cell used.
				Cell currentDayNumberCell = dayNumberRow.createCell(DAYS_STARTING_COLUMN + i);
				Cell currentDayNameCell = dayNamesRow.createCell(DAYS_STARTING_COLUMN + i);

				// Cell values.
				currentDayNumberCell.setCellValue(i + 1);
				currentDayNameCell.setCellValue(
						new HSSFRichTextString(reportDate.atDay(i + 1).format(FIRST_LETTER_OF_DAY_FORMATTER)));

				// Sets the cell styles.
				if (holidayArray[i]) {
					currentDayNumberCell.setCellStyle(borderedBoldRed);
					currentDayNameCell.setCellStyle(borderedBoldRed);
				} else {
					currentDayNumberCell.setCellStyle(borderedBold);
					currentDayNameCell.setCellStyle(borderedBold);
				}
			}

			// Total column.
			ExcelFormatter.createStyledCell(dayNumberRow, firstColumnAfterDays, "Total", borderedBoldRed);
			CellRangeAddress totalCellRange = new CellRangeAddress(currentRow - 1, currentRow, firstColumnAfterDays,
					firstColumnAfterDays);
			ExcelFormatter.createRegionWithBorders(BorderStyle.MEDIUM, totalCellRange, sheet);

			// Timesheet rows.
			for (int i = 0; i < timesheetRows.size(); i++) {
				TimesheetRow timesheetRow = timesheetRows.get(i);
				String customer = "-";

				// Sets the customer for the row if present.
				Order currentOrder = timesheetRow.getOrder();
				if (currentOrder != null) {
					customer = currentOrder.getCustomer();
				}

				// Prints the customer name.
				Row currentHourRow = sheet.createRow(++currentRow);
				ExcelFormatter.createStyledCell(currentHourRow, CUSTOMER_NAME_COLUMN, customer, borderedBold);
				// Prints the row name.
				ExcelFormatter.createStyledCell(currentHourRow, ROW_NAME_COLUMN, timesheetRow.getName(), borderedBold);

				// Prints the hours for each column.
				int[] hours = timesheetRow.getHours();
				
				if (hours == null) {
					hours = new int[daysInMonth];
				}
				
				int rowTotal = 0;
				for (int j = 0; j < hours.length; j++) {
					rowTotal += hours[j];
					Cell currentHourCell = currentHourRow.createCell(j + DAYS_STARTING_COLUMN);
					
					// Prints nothing instead to avoid an Excel filled with 0s.
					if (hours[j] != 0) {
						currentHourCell.setCellValue(hours[j]);
					}

					// Sets the style for the hours.
					if (holidayArray[j]) {
						currentHourCell.setCellStyle(borderedBoldRedGrey);
					} else {
						currentHourCell.setCellStyle(bordered);
					}

				}

				// Prints the total.
				Cell currentTotal = currentHourRow.createCell(firstColumnAfterDays);
				currentTotal.setCellValue(rowTotal);
				currentTotal.setCellStyle(bordered);
			}
			
			// Adds row spaces between each report.
			currentRow += 3;
		}

		// Autosizes column to fit the text.
		sheet.autoSizeColumn(CUSTOMER_NAME_COLUMN);
		sheet.autoSizeColumn(ROW_NAME_COLUMN);

		// Prints the report.
		try {
			workbook.write(output);
		} catch (IOException e) {
			LOG.error("Error during Excel file generation", e);
		}
	}

	private static boolean[] getHolidayArray(YearMonth date) {
		int daysInMonth = date.lengthOfMonth();
		boolean[] holidayArray = new boolean[daysInMonth];
		for (int i = 0; i < daysInMonth; i++) {
			Calendar currentCalendar = new GregorianCalendar(date.getYear(), date.getMonthValue() - 1, i + 1);
			holidayArray[i] = ItalianHolidays.getInstance().isWeekendOrHoliday(currentCalendar);
		}
		return holidayArray;
	}

}