//package com.zhxx.admin.server.utils;
//
//
//import org.apache.log4j.Logger;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * excel工具类
// */
//public class  ExcelParser implements Iterator<List<String>> {
//
//	public static void main(String[] args) {
//		String path = "C:\\Users\\Administrator.USERUQI-91MF28N\\Desktop\\学徒制系统\\测评问卷上传文档模板.xls";
//
//		ExcelParser ep = new ExcelParser(path);
//		while(ep.hasNext()){
//			List<String> row = ep.next();
//			System.out.println(row.get(0) + ", " + row.get(1));
//		}
//		ep.close();
//	}
////	public static void excelLocal(String path, String fileName, String[] headers, List<Object[]> datas) {
////		Workbook workbook = getWorkbook(headers, datas);
////		if (workbook != null) {
////			ByteArrayOutputStream byteArrayOutputStream = null;
////			FileOutputStream fileOutputStream = null;
////			try {
////				byteArrayOutputStream = new ByteArrayOutputStream();
////				workbook.write(byteArrayOutputStream);
////
////				String suffix = ".xls";
////				File file = new File(path + File.separator + fileName + suffix);
////				if (!file.getParentFile().exists()) {
////					file.getParentFile().mkdirs();
////				}
////
////				fileOutputStream = new FileOutputStream(file);
////				fileOutputStream.write(byteArrayOutputStream.toByteArray());
////			} catch (Exception e) {
////				e.printStackTrace();
////			} finally {
////				try {
////					if (fileOutputStream != null) {
////						fileOutputStream.close();
////					}
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////				try {
////					if (byteArrayOutputStream != null) {
////						byteArrayOutputStream.close();
////					}
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////
////				try {
////					workbook.close();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
////		}
////	}
////	/**
////	 * 导出excel
////	 *
////	 * @param fileName
////	 * @param headers
////	 * @param datas
////	 * @param response
////	 */
////	public static void excelExport(String fileName, String[] headers, List<Object[]> datas,
////			HttpServletResponse response) {
////		Workbook workbook = getWorkbook(headers, datas);
////		if (workbook != null) {
////			ByteArrayOutputStream byteArrayOutputStream = null;
////			try {
////				byteArrayOutputStream = new ByteArrayOutputStream();
////				workbook.write(byteArrayOutputStream);
////
////				String suffix = ".xls";
////				response.setContentType("application/vnd.ms-excel;charset=utf-8");
////				response.setHeader("Content-Disposition",
////						"attachment;filename=" + new String((fileName + suffix).getBytes(), "iso-8859-1"));
////
////				OutputStream outputStream = response.getOutputStream();
////				outputStream.write(byteArrayOutputStream.toByteArray());
////				outputStream.close();
////			} catch (Exception e) {
////				e.printStackTrace();
////			} finally {
////				try {
////					if (byteArrayOutputStream != null) {
////						byteArrayOutputStream.close();
////					}
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////
////				try {
////					workbook.close();
////				} catch (IOException e) {
////					e.printStackTrace();
////				}
////			}
////		}
////	}
////	/**
////	 *
////	 * @param headers
////	 *            列头
////	 * @param datas
////	 *            数据
////	 * @return
////	 */
////	public static Workbook getWorkbook(String[] headers, List<Object[]> datas) {
////		Workbook workbook = new HSSFWorkbook();
////
////		Sheet sheet = workbook.createSheet();
////		Row row = null;
////		Cell cell = null;
////		CellStyle style = workbook.createCellStyle();
////		style.setAlignment(HorizontalAlignment.CENTER_SELECTION);
////
////		Font font = workbook.createFont();
////
////		int line = 0, maxColumn = 0;
////		if (headers != null && headers.length > 0) {// 设置列头
////			row = sheet.createRow(line++);
////			row.setHeightInPoints(23);
////			font.setBold(true);
////			font.setFontHeightInPoints((short) 13);
////			style.setFont(font);
////
////			maxColumn = headers.length;
////			for (int i = 0; i < maxColumn; i++) {
////				cell = row.createCell(i);
////				cell.setCellValue(headers[i]);
////				cell.setCellStyle(style);
////			}
////		}
////
////		if (datas != null && datas.size() > 0) {// 渲染数据
////			for (int index = 0, size = datas.size(); index < size; index++) {
////				Object[] data = datas.get(index);
////				if (data != null && data.length > 0) {
////					row = sheet.createRow(line++);
////					row.setHeightInPoints(20);
////
////					int length = data.length;
////					if (length > maxColumn) {
////						maxColumn = length;
////					}
////
////					for (int i = 0; i < length; i++) {
////						cell = row.createCell(i);
////						cell.setCellValue(data[i] == null ? null : data[i].toString());
////					}
////				}
////			}
////		}
////
////		for (int i = 0; i < maxColumn; i++) {
////			sheet.autoSizeColumn(i);
////		}
////
////		return workbook;
////	}
//
//	private static final Logger logger = Logger.getLogger(ExcelParser.class);
//
//	private InputStream is = null;
//	private Iterator<?> rowIterator = null;
//
//	public ExcelParser(String excelFile){
////		HSSFWorkbook book = null;
////		HSSFSheet sheet = null;
//		try {
//			is = new FileInputStream(excelFile);
//			Workbook wb = WorkbookFactory.create(is);
////			book = new HSSFWorkbook(is);
//			Sheet sheet = wb.getSheetAt(0);
//			rowIterator = sheet.rowIterator();
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//	}
//
//	@Override
//	public boolean hasNext(){
//		return rowIterator.hasNext();
//	}
//
//	@Override
//	public List<String> next(){
//		HSSFRow row = (HSSFRow)rowIterator.next();
//		List<String> erow = new ArrayList<String>();
//		Iterator<?> cellIterator = row.cellIterator();
//		while(cellIterator.hasNext()){
//			HSSFCell cell = (HSSFCell)cellIterator.next();
//			erow.add(this.getCellValue(cell));
//		}
//		return erow;
//	}
//
//	@Override
//	public void remove(){
//		throw new UnsupportedOperationException("本EXCEL解析器是只读的.");
//	}
//
//	private String getCellValue(HSSFCell cell){
//		String value = null;
//		//简单的查检列类型
//		switch(cell.getCellType())
//		{
//			case HSSFCell.CELL_TYPE_STRING://字符串
//				value = cell.getRichStringCellValue().getString();
//				break;
//			case HSSFCell.CELL_TYPE_NUMERIC://数字
//				long dd = (long)cell.getNumericCellValue();
//				value = dd+"";
//				break;
//			case HSSFCell.CELL_TYPE_BLANK:
//				value = "";
//				break;
//			case HSSFCell.CELL_TYPE_FORMULA:
//				value = String.valueOf(cell.getCellFormula());
//				break;
//			case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
//				value = String.valueOf(cell.getBooleanCellValue());
//				break;
//			case HSSFCell.CELL_TYPE_ERROR:
//				value = String.valueOf(cell.getErrorCellValue());
//				break;
//			default:
//				break;
//		}
//		return value;
//	}
//
//	public void close(){
//		if(is != null){
//			try {
//				is.close();
//			} catch (IOException e) {
//				logger.error(e.getMessage(), e);
//			}
//		}
//	}
//}
