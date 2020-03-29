package com.zhxx.service.szxt.utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.zhxx.service.szxt.entity.TeacherConcludingEntity;
import com.zhxx.service.szxt.entity.TeacherHorizontalEntity;
import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherPatentEntity;
import com.zhxx.service.szxt.entity.TeacherPrizeEntity;
import com.zhxx.service.szxt.entity.TeacherProjectEntity;
import com.zhxx.service.szxt.entity.TeacherWritingsEntity;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.xssf.usermodel.*;

public class DownExcel {

	private static String ScientificResearchResults="科研成果汇总统计表_";

	/**
	 *
	 *(2007 xlsx后缀 导出)
	 * @param
	 * @return void 导出Excel多表
	 * @author 王传营
	 * @2020-02-20
	 */
	public static String downloadExcel(Map<String,List<?>> map,HttpServletResponse response) throws IOException {
		//excel模板路径
		File fi=new File("D:\\X\\2019年科研成果汇总统计表.xlsx");
		InputStream in = new FileInputStream(fi);
		//读取excel模板
		XSSFWorkbook wb = new XSSFWorkbook(in);
		//读取了模板内所有sheet内容
		XSSFSheet sheet = wb.getSheetAt(0);
		XSSFSheet sheet1 = wb.getSheetAt(1);
		XSSFSheet sheet2 = wb.getSheetAt(2);
		XSSFSheet sheet3 = wb.getSheetAt(3);
		XSSFSheet sheet4 = wb.getSheetAt(4);
		XSSFSheet sheet5 = wb.getSheetAt(5);
		XSSFSheet sheet6 = wb.getSheetAt(6);
		//如果这行没有了，整个公式都不会有自动计算的效果的
//        sheet.setForceFormulaRecalculation(true);

		List<TeacherPaperEntity> paperList = (List<TeacherPaperEntity>) map.get("paperList");
		List<TeacherWritingsEntity> writingList = (List<TeacherWritingsEntity>) map.get("writingList");
		List<TeacherProjectEntity> projectList = (List<TeacherProjectEntity>) map.get("projectList");
		List<TeacherConcludingEntity> concludingList = (List<TeacherConcludingEntity>) map.get("concludingList");
		List<TeacherHorizontalEntity> horizontalList = (List<TeacherHorizontalEntity>) map.get("horizontalList");
		List<TeacherPrizeEntity> prizeList = (List<TeacherPrizeEntity>) map.get("prizeList");
		List<TeacherPatentEntity> patentList = (List<TeacherPatentEntity>) map.get("patentList");

		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM");//设置日期格式
		SimpleDateFormat dfs66 = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式

		//论文
		for (int i=0;i<paperList.size();i++){
			//行高500
			sheet.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(paperList.get(i).getTitle());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(paperList.get(i).getAuthor());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(paperList.get(i).getJournalName());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);cell4.setCellValue(paperList.get(i).getJournalNo());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(paperList.get(i).getOrganizer());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(paperList.get(i).getPublishDate());
			cell6.setCellStyle(style6);cell6.setCellValue(data);

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			cell7.setCellStyle(style7);cell7.setCellValue(paperList.get(i).getJournalLevel());

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			cell8.setCellStyle(style8);cell8.setCellValue(paperList.get(i).getPage());

			XSSFCell cell9 = row1.createCell(9);
			XSSFCellStyle style9 =getstyle(wb);
			style9.setBorderLeft(BorderStyle.THIN);//左边框
			style9.setBorderRight(BorderStyle.THIN);//右边框
			cell9.setCellStyle(style9);cell9.setCellValue(paperList.get(i).getAuthorRank());

			XSSFCell cell10 = row1.createCell(10);
			XSSFCellStyle style10 =getstyle(wb);
			style10.setBorderLeft(BorderStyle.THIN);//左边框
			style10.setBorderRight(BorderStyle.THIN);//右边框
			cell10.setCellStyle(style10);cell10.setCellValue(paperList.get(i).getCategories());

			XSSFCell cell11 = row1.createCell(11);
			XSSFCellStyle style11 =getstyle(wb);
			style11.setBorderLeft(BorderStyle.THIN);//左边框
			style11.setBorderRight(BorderStyle.THIN);//右边框
			cell11.setCellStyle(style11);cell11.setCellValue(paperList.get(i).getRemarks());
		}
		removeRow(sheet,3+paperList.size());
		//著作
		for (int i=0;i<writingList.size();i++){
			//行高500
			sheet1.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet1, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(writingList.get(i).getTitle());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(writingList.get(i).getAuthor());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(writingList.get(i).getPressName());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(writingList.get(i).getPressDate());
			cell4.setCellStyle(style4);cell4.setCellValue(data);

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(writingList.get(i).getCompilationForm());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			cell6.setCellStyle(style6);cell6.setCellValue(writingList.get(i).getAuthorRank());

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			cell7.setCellStyle(style7);cell7.setCellValue(writingList.get(i).getCategories());

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			cell8.setCellStyle(style8);cell8.setCellValue(writingList.get(i).getBookNo());

			XSSFCell cell9 = row1.createCell(9);
			XSSFCellStyle style9 =getstyle(wb);
			style9.setBorderLeft(BorderStyle.THIN);//左边框
			style9.setBorderRight(BorderStyle.THIN);//右边框
			cell9.setCellStyle(style9);cell9.setCellValue(writingList.get(i).getTotalWords());

			XSSFCell cell10 = row1.createCell(10);
			XSSFCellStyle style10 =getstyle(wb);
			style10.setBorderLeft(BorderStyle.THIN);//左边框
			style10.setBorderRight(BorderStyle.THICK);//右边框
			cell10.setCellStyle(style10);cell10.setCellValue(writingList.get(i).getRemarks());
		}
		removeRow(sheet1,3+writingList.size());
		//立项课题
		for (int i=0;i<projectList.size();i++){
			//行高500
			sheet2.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet2, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(projectList.get(i).getProjectNo());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(projectList.get(i).getProjectName());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(projectList.get(i).getHost());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);cell4.setCellValue(projectList.get(i).getParticipant());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(projectList.get(i).getSource());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			cell6.setCellStyle(style6);cell6.setCellValue(projectList.get(i).getProjectCategory());

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			cell7.setCellStyle(style7);cell7.setCellValue(projectList.get(i).getProjectLevel());

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(projectList.get(i).getProjectTime());
			cell8.setCellStyle(style8);cell8.setCellValue(data);

			XSSFCell cell9 = row1.createCell(9);
			XSSFCellStyle style9 =getstyle(wb);
			style9.setBorderLeft(BorderStyle.THIN);//左边框
			style9.setBorderRight(BorderStyle.THIN);//右边框
			cell9.setCellStyle(style9);cell9.setCellValue(projectList.get(i).getResearchFunding());

			XSSFCell cell10 = row1.createCell(10);
			XSSFCellStyle style10 =getstyle(wb);
			style10.setBorderLeft(BorderStyle.THIN);//左边框
			style10.setBorderRight(BorderStyle.THIN);//右边框
			cell10.setCellStyle(style10);cell10.setCellValue(projectList.get(i).getRemarks());
		}
		removeRow(sheet2,3+projectList.size());
		//结题课题
		for (int i=0;i<concludingList.size();i++){
			//行高500
			sheet3.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet3, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(concludingList.get(i).getProjectNo());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(concludingList.get(i).getProjectName());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(concludingList.get(i).getHost());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);cell4.setCellValue(concludingList.get(i).getParticipant());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(concludingList.get(i).getSource());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			cell6.setCellStyle(style6);cell6.setCellValue(concludingList.get(i).getProjectCategory());

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			cell7.setCellStyle(style7);cell7.setCellValue(concludingList.get(i).getProjectLevel());

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(concludingList.get(i).getProjectTime());
			cell8.setCellStyle(style8);cell8.setCellValue(data);

			XSSFCell cell9 = row1.createCell(9);
			XSSFCellStyle style9 =getstyle(wb);
			style9.setBorderLeft(BorderStyle.THIN);//左边框
			style9.setBorderRight(BorderStyle.THIN);//右边框
			String data1 = dfs.format(concludingList.get(i).getConcludingTime());
			cell9.setCellStyle(style9);cell9.setCellValue(data1);

			XSSFCell cell10 = row1.createCell(10);
			XSSFCellStyle style10 =getstyle(wb);
			style10.setBorderLeft(BorderStyle.THIN);//左边框
			style10.setBorderRight(BorderStyle.THIN);//右边框
			cell10.setCellStyle(style10);cell10.setCellValue(concludingList.get(i).getResearchFunding());


			XSSFCell cell11 = row1.createCell(11);
			XSSFCellStyle style11 =getstyle(wb);
			style11.setBorderLeft(BorderStyle.THIN);//左边框
			style11.setBorderRight(BorderStyle.THIN);//右边框
			cell11.setCellStyle(style11);cell11.setCellValue(concludingList.get(i).getRemarks());
		}
		removeRow(sheet3,3+concludingList.size());
		//横向课题
		for (int i=0;i<horizontalList.size();i++){
			//行高500
			sheet4.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet4, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(horizontalList.get(i).getProjectNo());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(horizontalList.get(i).getProjectName());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(horizontalList.get(i).getHost());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);cell4.setCellValue(horizontalList.get(i).getParticipant());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(horizontalList.get(i).getApprovalUnit());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			cell6.setCellStyle(style6);cell6.setCellValue(horizontalList.get(i).getCooperationUnit());

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(horizontalList.get(i).getProjectTime());
			cell7.setCellStyle(style7);cell7.setCellValue(data);

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			String data1 = dfs.format(horizontalList.get(i).getConcludingTime());
			cell8.setCellStyle(style8);cell8.setCellValue(data1);

			XSSFCell cell9 = row1.createCell(9);
			XSSFCellStyle style9 =getstyle(wb);
			style9.setBorderLeft(BorderStyle.THIN);//左边框
			style9.setBorderRight(BorderStyle.THIN);//右边框
			cell9.setCellStyle(style9);cell9.setCellValue(horizontalList.get(i).getAmount());

			XSSFCell cell10 = row1.createCell(10);
			XSSFCellStyle style10 =getstyle(wb);
			style10.setBorderLeft(BorderStyle.THIN);//左边框
			style10.setBorderRight(BorderStyle.THIN);//右边框
			cell10.setCellStyle(style10);cell10.setCellValue(horizontalList.get(i).getActuallyTask());


			XSSFCell cell11 = row1.createCell(11);
			XSSFCellStyle style11 =getstyle(wb);
			style11.setBorderLeft(BorderStyle.THIN);//左边框
			style11.setBorderRight(BorderStyle.THIN);//右边框
			cell11.setCellStyle(style11);cell11.setCellValue(horizontalList.get(i).getRemarks());
		}
		removeRow(sheet4,3+horizontalList.size());
		//获奖
		for (int i=0;i<prizeList.size();i++) {
			//行高500
			sheet5.createRow(3 + i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet5, 3 + i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 = getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);
			cell0.setCellValue(i + 1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 = getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);
			cell1.setCellValue(prizeList.get(i).getProductName());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 = getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);
			cell2.setCellValue(prizeList.get(i).getLeading());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 = getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);
			cell3.setCellValue(prizeList.get(i).getParticipant());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 = getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);
			cell4.setCellValue(prizeList.get(i).getAwardsReceived());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 = getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);
			cell5.setCellValue(prizeList.get(i).getAwardingUnit());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 = getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			cell6.setCellStyle(style6);
			cell6.setCellValue(prizeList.get(i).getLevel());

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 = getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs.format(prizeList.get(i).getCertificateDate());
			cell7.setCellStyle(style7);
			cell7.setCellValue(data);
		}
		removeRow(sheet5,3+prizeList.size());
		//专利
		for (int i=0;i<patentList.size();i++){
			//行高500
			sheet6.createRow(3+i).setHeight((short) 500);
			XSSFRow row1 = createRow(sheet6, 3+i);

			XSSFCell cell0 = row1.createCell(0);
			XSSFCellStyle style0 =getstyle(wb);
			style0.setBorderLeft(BorderStyle.THIN);//左边框
			style0.setBorderRight(BorderStyle.THIN);//右边框
			cell0.setCellStyle(style0);cell0.setCellValue(i+1);

			XSSFCell cell1 = row1.createCell(1);
			XSSFCellStyle style1 =getstyle(wb);
			style1.setBorderLeft(BorderStyle.THIN);//左边框
			style1.setBorderRight(BorderStyle.THIN);//右边框
			cell1.setCellStyle(style1);cell1.setCellValue(patentList.get(i).getDesigner());

			XSSFCell cell2 = row1.createCell(2);
			XSSFCellStyle style2 =getstyle(wb);
			style2.setBorderLeft(BorderStyle.THIN);//左边框
			style2.setBorderRight(BorderStyle.THIN);//右边框
			cell2.setCellStyle(style2);cell2.setCellValue(patentList.get(i).getRanking());

			XSSFCell cell3 = row1.createCell(3);
			XSSFCellStyle style3 =getstyle(wb);
			style3.setBorderLeft(BorderStyle.THIN);//左边框
			style3.setBorderRight(BorderStyle.THIN);//右边框
			cell3.setCellStyle(style3);cell3.setCellValue(patentList.get(i).getPatentPerson());

			XSSFCell cell4 = row1.createCell(4);
			XSSFCellStyle style4 =getstyle(wb);
			style4.setBorderLeft(BorderStyle.THIN);//左边框
			style4.setBorderRight(BorderStyle.THIN);//右边框
			cell4.setCellStyle(style4);cell4.setCellValue(patentList.get(i).getPatentName());

			XSSFCell cell5 = row1.createCell(5);
			XSSFCellStyle style5 =getstyle(wb);
			style5.setBorderLeft(BorderStyle.THIN);//左边框
			style5.setBorderRight(BorderStyle.THIN);//右边框
			cell5.setCellStyle(style5);cell5.setCellValue(patentList.get(i).getRightCategories());

			XSSFCell cell6 = row1.createCell(6);
			XSSFCellStyle style6 =getstyle(wb);
			style6.setBorderLeft(BorderStyle.THIN);//左边框
			style6.setBorderRight(BorderStyle.THIN);//右边框
			String data = dfs66.format(patentList.get(i).getApplyingDate());
			cell6.setCellStyle(style6);cell6.setCellValue(data);

			XSSFCell cell7 = row1.createCell(7);
			XSSFCellStyle style7 =getstyle(wb);
			style7.setBorderLeft(BorderStyle.THIN);//左边框
			style7.setBorderRight(BorderStyle.THIN);//右边框
			String data1 = dfs66.format(patentList.get(i).getAnnoDate());
			cell7.setCellStyle(style7);cell7.setCellValue(data1);

			XSSFCell cell8 = row1.createCell(8);
			XSSFCellStyle style8 =getstyle(wb);
			style8.setBorderLeft(BorderStyle.THIN);//左边框
			style8.setBorderRight(BorderStyle.THIN);//右边框
			cell8.setCellStyle(style8);cell8.setCellValue(patentList.get(i).getPatentNumber());
		}
		removeRow(sheet6,3+patentList.size());

		String filepath = new String();
		//修改模板内容导出新模板
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
		String path = buildFolder(getExcelpath(ScientificResearchResults)+df.format(new Date()));
		String fileName = ScientificResearchResults+df.format(new Date())+".xlsx";
		filepath = path +File.separator+ScientificResearchResults+df.format(new Date())+".xlsx";
		FileOutputStream out = new FileOutputStream(path +File.separator+ScientificResearchResults+df.format(new Date())+".xlsx");
		wb.write(out);
		out.close();
		
		response.reset();        
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
        response.addHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes("GBK"),"ISO8859-1"));
        response.setContentType("application/octet-stream");
        
        //获取文件输入流
        InputStream inClient = new FileInputStream(filepath);
        int len = 0;
        byte[] buffer = new byte[1024];
        OutputStream toClient = response.getOutputStream();
        while ((len = inClient.read(buffer)) > 0) {
            //将缓冲区的数据输出到客户端浏览器
        	toClient.write(buffer,0,len);
        }
        
        inClient.close();
        toClient.flush();
        toClient.close();

		
		return filepath;
	}

	private static XSSFCellStyle getstyle(XSSFWorkbook wb){

		XSSFCellStyle style = wb.createCellStyle();
		style.setBorderTop(BorderStyle.THIN);//上边框
		style.setBorderBottom(BorderStyle.THIN);//下边框

		//设置字体
		XSSFFont font = wb.createFont();
		font.setFontName("仿宋_GB2312");
		font.setFontHeightInPoints((short) 12);
		style.setFont(font);

		return style;
	}

	/**
	 * 找到需要插入的行数，并新建一个POI的row对象
	 * @param sheet
	 * @param rowIndex
	 * @return
	 */
	private static XSSFRow createRow(XSSFSheet sheet, Integer rowIndex) {
		XSSFRow row = null;
		if (sheet.getRow(rowIndex) != null) {
			int lastRowNo = sheet.getLastRowNum();
			sheet.shiftRows(rowIndex, lastRowNo, 1);
		}
		row = sheet.createRow(rowIndex);
		return row;
	}
	/**
	 * 删除行，也就是将想要删除的行的位置下面的所有全部上移，做到删除行的效果
	 * @param sheet
	 * @param rowIndex
	 */
	public static void removeRow(XSSFSheet sheet, int rowIndex) {
		int lastRowNum=sheet.getLastRowNum();
		if(rowIndex>=0&&rowIndex<lastRowNum) {
			sheet.shiftRows(rowIndex + 1, lastRowNum, -1);//将行号为rowIndex+1一直到行号为lastRowNum的单元格全部上移一行，以便删除rowIndex行
		}
		if(rowIndex==lastRowNum){
			XSSFRow removingRow=sheet.getRow(rowIndex);
			if(removingRow!=null) {
				sheet.removeRow(removingRow);
			}
		}
	}


	public static String buildFolder(String path)
	{
		//读取目录路径
		File file = new File(path);
		//推断是否存在
		if (!file.exists() && !file.isDirectory())
		{
			try
			{
				//生成目录
				file.mkdir();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return path;
	}

	//获取工程盘符
	public static String gethkdsk(){
		String userDir = System.getProperties().getProperty("user.dir");
		String[] temp = userDir.split("\\\\");
		String jarPaht = temp[0]+File.separator+"temp";
		return jarPaht;
	}
	//获取加密到处根路径
	public static String getExcelpath(String val){
		String excelpath = gethkdsk()+ val;
		return excelpath;
	}

	//获取工程盘符
	public static String gethkdsk1(){
		String userDir = System.getProperties().getProperty("user.dir");
		String[] temp = userDir.split("\\\\");
		String jarPaht = buildFolder(temp[0]+File.separator+"material/");
		return jarPaht;
	}
	//获取加密到处根路径
	public static String getExcelpath1(String val){
		String excelpath = gethkdsk1()+ val;
		return excelpath;
	}
}
