package com.zhxx.service.szxt.serviceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.GrowthCommentOutputEntity;
import com.zhxx.service.szxt.entity.GrowthOutputEntity;
import com.zhxx.service.szxt.entity.GrowthPlanCommonEntity;
import com.zhxx.service.szxt.entity.GrowthPlanningCommentEntity;
import com.zhxx.service.szxt.entity.GrowthPlanningEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.mapper.GrowthPlanningCommentEntityMapper;
import com.zhxx.service.szxt.mapper.GrowthPlanningEntityMapper;
import com.zhxx.service.szxt.mapper.GrowthCommentOutputEntityMapper;
import com.zhxx.service.szxt.mapper.GrowthOutputEntityMapper;
import com.zhxx.service.szxt.mapper.GrowthPlannEntityMapper;
import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.service.GrowthPlanService;
import com.zhxx.service.szxt.utils.UUIDUtil;
@Service
public class GrowthPlanServiceImpl implements GrowthPlanService {
    
	@Autowired
	private GrowthPlanningEntityMapper growthPlanningEntityMapper;
	@Autowired
	private GrowthPlanningCommentEntityMapper growthPlanningCommentEntityMapper;
	@Autowired
	private GrowthPlannEntityMapper growthPlannEntityMapper;
	@Autowired
    private StudentEntityMapper studentEntityMapper;
	@Autowired
	private GrowthOutputEntityMapper growthOutputEntityMapper;
	@Autowired
	private GrowthCommentOutputEntityMapper growthCommentOutputEntityMapper;
	@Autowired
	private RemoteProperties remoteProperties;
	@Override
	//增加规划轨迹
	public HttpResponseEntity addGrowthPlannSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		GrowthPlanningEntity growthPlanningEntity = new GrowthPlanningEntity();
		try{
		//插入成长规划总表
		growthPlanningEntity.setPlanningId(UUIDUtil.getOneUUID());
		growthPlanningEntity.setStudenId(growthPlanCommonEntity.getStudentId());
		//获取学生年纪
		StudentEntity studentEntity = studentEntityMapper.selectByPrimaryKey(growthPlanCommonEntity.getStudentId());
		growthPlanCommonEntity.setStudenGrade('5'+studentEntity.getStudentGrade().toString());
		
		growthPlanningEntity.setStudenGrade(growthPlanCommonEntity.getStudenGrade());
		growthPlanningEntityMapper.insert(growthPlanningEntity);

		//插入成长规划表
		for(int i=0;i<growthPlanCommonEntity.getGrowthPlanningCommentEntity().size();i++){
			GrowthPlanningCommentEntity growthPlanningCommentEntity = new GrowthPlanningCommentEntity();
			growthPlanningCommentEntity.setId(UUIDUtil.getOneUUID());
			growthPlanningCommentEntity.setPlanningId(growthPlanningEntity.getPlanningId());
			growthPlanningCommentEntity.setModelId(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getModelId());
			growthPlanningCommentEntity.setOptionId(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getOptionId());
			
			growthPlanningCommentEntity.setModelTitle(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getModelTitle());
			growthPlanningCommentEntity.setModelDesc(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getModelDesc());		
			growthPlanningCommentEntity.setModelModellevel(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getModelModellevel());
			growthPlanningCommentEntity.setModelCategory(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getModelCategory());
			
			growthPlanningCommentEntity.setOptionOrder(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getOptionOrder());
			growthPlanningCommentEntity.setOptionComment(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getOptionComment());
			growthPlanningCommentEntity.setOptionScore(growthPlanCommonEntity.getGrowthPlanningCommentEntity().get(i).getOptionScore());
			
			growthPlanningCommentEntity.setFinishState("0");//0-未完成 1-完成
			
			
			growthPlanningCommentEntity.setSubmitState("0");//0-未提交 1-提交
			growthPlanningCommentEntity.setUpdatetime(new Date());
			
			growthPlanningCommentEntityMapper.insert(growthPlanningCommentEntity);
			
		}
	}catch(Exception e){
		e.printStackTrace();
		TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		return HttpResponseEntity.error("新增失败");
	}
		
		return HttpResponseEntity.seccuss("新增成功");
	}
    //查询轨迹
	@Override
	public HttpResponseEntity queryGrowthPlannSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		StudentEntity studentEntity = studentEntityMapper.selectByPrimaryKey(growthPlanCommonEntity.getStudentId());
		growthPlanCommonEntity.setStudenGrade('5'+studentEntity.getStudentGrade().toString());
		List<GrowthPlanCommonEntity> growthPlanList = growthPlannEntityMapper.querygrowthList(growthPlanCommonEntity);
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("studentGrade", studentEntity.getStudentGrade().toString());
		resultMap.put("growthPlanList", growthPlanList);
		return HttpResponseEntity.seccuss(resultMap);
		
	}

	@Override
	public HttpResponseEntity planningbeService(GrowthPlanCommonEntity growthPlanCommonEntity) {
		
		return null;
	}
    //提交成果物
	@Override
	public HttpResponseEntity finishoutputSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		try{
		for(int i=0;i<growthPlanCommonEntity.getGrowthOutputEntityList().size();i++){
			//插入成果物表
			GrowthOutputEntity growthOutputEntity = new GrowthOutputEntity();
			growthOutputEntity.setId(UUIDUtil.getOneUUID());
			growthOutputEntity.setOutputType(growthPlanCommonEntity.getGrowthOutputEntityList().get(i).getOutputType());
			growthOutputEntity.setOutputUrl(growthPlanCommonEntity.getGrowthOutputEntityList().get(i).getOutputUrl());
			growthOutputEntity.setOutputUrlComtent(growthPlanCommonEntity.getGrowthOutputEntityList().get(i).getOutputUrlComtent());
			growthOutputEntity.setOutputUploadTime(new Date());
			growthOutputEntity.setUpdatetime(new Date());
			growthOutputEntityMapper.insert(growthOutputEntity);
			
			//插入成果物关联表
			GrowthCommentOutputEntity growthCommentOutputEntity = new GrowthCommentOutputEntity();
			growthCommentOutputEntity.setReleteId(UUIDUtil.getOneUUID());
			growthCommentOutputEntity.setGrowthCommentId(growthPlanCommonEntity.getGrowthPlanningId());
			growthCommentOutputEntity.setOutputId(growthOutputEntity.getId());
			growthCommentOutputEntityMapper.insert(growthCommentOutputEntity);
			
		}	
		//更新规划表的完成状态
		GrowthPlanningCommentEntity growthPlanningCommentEntity = new GrowthPlanningCommentEntity();
		growthPlanningCommentEntity.setId(growthPlanCommonEntity.getGrowthPlanningId());
		growthPlanningCommentEntity.setFinishState("1");
		growthPlanningCommentEntityMapper.updateByPrimaryKeySelective(growthPlanningCommentEntity);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return HttpResponseEntity.error("上传成果物失败");
		}
		return HttpResponseEntity.seccuss("上传成果物成功");
	}
	//提交成果物
	@Override
	public HttpResponseEntity submitoutputSerivce(List<GrowthPlanCommonEntity> growthPlanCommonEntityList) {
		try{
			for(GrowthPlanCommonEntity list:growthPlanCommonEntityList){
				GrowthPlanningCommentEntity growthPlanningCommentEntity = new GrowthPlanningCommentEntity();
				growthPlanningCommentEntity.setId(list.getGrowthPlanningId());
				growthPlanningCommentEntity.setSubmitState("1");
				growthPlanningCommentEntityMapper.updateByPrimaryKeySelective(growthPlanningCommentEntity);
	        }		
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return HttpResponseEntity.error("提交失败");
		}
		return HttpResponseEntity.seccuss("提交成功");
	}
	//查询成绩单
	@Override
	public HttpResponseEntity queryreportSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		List<GrowthPlanCommonEntity> growthPlanList = growthPlannEntityMapper.querygrowthReportList(growthPlanCommonEntity);
		//计算学年
		if(growthPlanList.size() >0){
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
			Calendar ca = Calendar.getInstance();
			ca.setTime(growthPlanList.get(0).getCreatetime());
			int month = ca.get(Calendar.MONTH);//第几个月
			int year = ca.get(Calendar.YEAR);//年份数值
			String gradeYear =new String();
			try {
				Date dateFirst = dateFormat.parse(dateFormat.format(growthPlanList.get(0).getCreatetime()));
				Date dateLast = dateFormat.parse("7-15");
				if (dateFirst.after(dateLast)) {
					gradeYear = year +"-"+(year+1);
		        } else if (dateFirst.before(dateLast)) {
		        	gradeYear = (year-1) +"-"+(year);
		        }
				growthPlanList.get(0).setGradeYear(gradeYear);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		return HttpResponseEntity.seccuss(growthPlanList);
	}
	//查询成果物
	public HttpResponseEntity queryoutputSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		List<GrowthPlanCommonEntity> growthPlanList = growthPlannEntityMapper.querygrowthOutputList(growthPlanCommonEntity);
		growthPlanList.forEach(
				outputEntity -> outputEntity.getGrowthOutputEntityList().forEach(
						GrowthOutputEntity -> GrowthOutputEntity.setOutputUrl(remoteProperties.getHttpUrl() + GrowthOutputEntity.getOutputUrl())));  //添加服务器路径

		return HttpResponseEntity.seccuss(growthPlanList);
	}
	 //按指定年纪查询轨迹
	@Override
	public HttpResponseEntity queryGrowthPlannAppointGradeSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		List<GrowthPlanCommonEntity> growthPlanList = growthPlannEntityMapper.querygrowthList(growthPlanCommonEntity);
		return HttpResponseEntity.seccuss(growthPlanList);
	}
	@Override
	public HttpResponseEntity addreportcommentSerivce(GrowthPlanCommonEntity growthPlanCommonEntity) {
		try{
			GrowthPlanningEntity growthPlanningEntity = new GrowthPlanningEntity();
			growthPlanningEntity.setPlanningId(growthPlanCommonEntity.getPlanningId());
			growthPlanningEntity.setCommentComtent(growthPlanCommonEntity.getCommentComtent());
			growthPlanningEntity.setUpdatetime(new Date());
			growthPlanningEntityMapper.updateByPrimaryKeySelective(growthPlanningEntity);
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return HttpResponseEntity.error("提交失败");
		}
		return HttpResponseEntity.seccuss("提交成功");
	}
}
