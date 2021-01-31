package co.aurasphere.reports.utilities;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor.HSSFColorPredefined;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class ExcelFormatter {
	
	private CellStyle cell;

	private ExcelFormatter(Workbook workbook) {
		cell = workbook.createCellStyle();
	}
	
	public static ExcelFormatter newInstance(Workbook workbook) {
		return new ExcelFormatter(workbook);
	}
	
	public ExcelFormatter fullBorder(BorderStyle border) {
		cell.setBorderBottom(border);
		cell.setBorderLeft(border);
		cell.setBorderRight(border);
		cell.setBorderTop(border);
		return this;
	}
	
	public ExcelFormatter centerText() {
		cell.setAlignment(HorizontalAlignment.CENTER);
		cell.setVerticalAlignment(VerticalAlignment.CENTER);
		return this;
	}
	
	public ExcelFormatter setFont(Font font) {
		cell.setFont(font);
		return this;
	}
	
	public ExcelFormatter setBackgroundColor(HSSFColorPredefined color) {
		cell.setFillForegroundColor(color.getIndex());
		cell.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		return this;
	}
	
	public CellStyle build() {
		return cell;
	}
	
	public static void createRegionWithBorders(BorderStyle border, CellRangeAddress range, Sheet sheet) {
		sheet.addMergedRegion(range);
		RegionUtil.setBorderTop(border, range, sheet);
		RegionUtil.setBorderLeft(border, range, sheet);
		RegionUtil.setBorderRight(border, range, sheet);
		RegionUtil.setBorderBottom(border, range, sheet);
	}
	
	public static void createStyledCell(Row row, int column, String value, CellStyle style) {
		Cell customerNameCell = row.createCell(column);
		customerNameCell.setCellValue(new HSSFRichTextString(value));
		customerNameCell.setCellStyle(style);
	}

}
