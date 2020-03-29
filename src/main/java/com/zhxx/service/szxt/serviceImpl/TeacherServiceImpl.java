package com.zhxx.service.szxt.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.zhxx.admin.server.dao.UserDao;
import com.zhxx.admin.server.dto.UserDto;
import com.zhxx.admin.server.model.Permission;
import com.zhxx.admin.server.model.User;
import com.zhxx.admin.server.service.UserService;
import com.zhxx.admin.server.utils.UserUtil;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.entity.TeacherAchievementsDictEntity;
import com.zhxx.service.szxt.entity.TeacherConcludingEntity;
import com.zhxx.service.szxt.entity.TeacherHorizontalEntity;
import com.zhxx.service.szxt.entity.TeacherOutputPicRelateEntity;
import com.zhxx.service.szxt.entity.TeacherPaperEntity;
import com.zhxx.service.szxt.entity.TeacherPatentEntity;
import com.zhxx.service.szxt.entity.TeacherPrizeEntity;
import com.zhxx.service.szxt.entity.TeacherProjectEntity;
import com.zhxx.service.szxt.entity.TeacherResearchSectionEntity;
import com.zhxx.service.szxt.entity.TeacherStudentRelateEntity;
import com.zhxx.service.szxt.entity.TeacherUserEntity;
import com.zhxx.service.szxt.entity.TeacherWritingsEntity;
import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherAchievementsDictEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherConcludingEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherHorizontalEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherOutputPicRelateEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherPaperEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherPatentEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherPrizeEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherProjectEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherResearchSectionEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherStudentRelateEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherUserEntityMapper;
import com.zhxx.service.szxt.mapper.TeacherWritingsEntityMapper;
import com.zhxx.service.szxt.service.TeacherService;
import com.zhxx.service.szxt.utils.DownExcel;
import com.zhxx.service.szxt.utils.UUIDUtil;
@Service
public class TeacherServiceImpl implements TeacherService{
    @Autowired
    private TeacherUserEntityMapper teacherUserEntityMapper;
    @Autowired
    private TeacherStudentRelateEntityMapper teacherStudentRelateEntityMapper; 
    @Autowired
    private TeacherPaperEntityMapper teacherPaperEntityMapper;
    @Autowired
    private TeacherWritingsEntityMapper teacherWritingsEntityMapper;
    @Autowired
    private TeacherProjectEntityMapper teacherProjectEntityMapper;
    @Autowired
    private TeacherConcludingEntityMapper teacherConcludingEntityMapper;
    @Autowired
    private TeacherHorizontalEntityMapper teacherHorizontalEntityMapper;
    @Autowired
    private TeacherPrizeEntityMapper teacherPrizeEntityMapper;
    @Autowired
    private TeacherPatentEntityMapper teacherPatentEntityMapper;
    @Autowired
    private TeacherAchievementsDictEntityMapper teacherAchievementsDictEntityMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private TeacherResearchSectionEntityMapper teacherResearchSectionEntityMapper;
    @Autowired
    private TeacherOutputPicRelateEntityMapper teacherOutputPicRelateEntityMapper;
    @Autowired
    private StudentEntityMapper studentEntityMapper;
    @Autowired
	private RemoteProperties remoteProperties;
    @Override
	public List<TeacherAchievementsDictEntity> achievementsDict(
			TeacherAchievementsDictEntity teacherAchievementsDictEntity) {
    	teacherAchievementsDictEntityMapper.queryAchievementsDict(teacherAchievementsDictEntity);
		return null;
	}

	
	@Override
	public HttpResponseEntity addTeacher(TeacherUserEntity teacherUserEntity) {
		//添加用户表
		UserDto userDto = new UserDto();
		userDto.setUsername(teacherUserEntity.getTeacherNo());
		userDto.setPassword(teacherUserEntity.getPassword());
		User u = userService.getUser(userDto.getUsername());
	        if (u != null) {
	            throw new IllegalArgumentException(userDto.getUsername() + "已存在");
	        }
	    User user = userService.saveTeacherUser(userDto);
	    //添加教师表
		int i=0;
		try{
			i = teacherUserEntityMapper.insertSelective(teacherUserEntity);
			if(i>0){
				return HttpResponseEntity.seccuss("注册教师成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
		
	}

	@Override
	public HttpResponseEntity modifyTeacher(TeacherUserEntity teacherUserEntity) {
		int i=0;
		teacherUserEntity.setModifyTime(new Date());
		try{
			i = teacherUserEntityMapper.updateByPrimaryKeySelective(teacherUserEntity);
			if(i>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}


	@Override
	public TeacherUserEntity selTeacher(TeacherUserEntity teacherUserEntity) {
		return teacherUserEntityMapper.selectByPrimaryKey(teacherUserEntity.getTeacherId());
	}

	@Override
	public HttpResponseEntity delTeacher(TeacherUserEntity teacherUserEntity) {
		int i=0;
		try{
			userService.deleteUser(Integer.parseInt(teacherUserEntity.getId()));
			i = teacherUserEntityMapper.deleteByPrimaryKey(teacherUserEntity.getUserName());
			if(i>0){
				return HttpResponseEntity.seccuss("教师删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity bindStudent(TeacherStudentRelateEntity teacherStudentRelateEntity) {
		int i=0;
		try{
			i = teacherStudentRelateEntityMapper.insertSelective(teacherStudentRelateEntity);
			if(i>0){
				return HttpResponseEntity.seccuss("绑定成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
		
	}
	@Override
	public HttpResponseEntity unbindStudent(TeacherStudentRelateEntity teacherStudentRelateEntity) {
		int i=0;
		try{
			i = teacherStudentRelateEntityMapper.deleteByPrimaryKey(teacherStudentRelateEntity);
			if(i>0){
				return HttpResponseEntity.seccuss("解除绑定");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<StudentEntity> bindStudentList(TeacherStudentRelateEntity teacherStudentRelateEntity) {
		return teacherStudentRelateEntityMapper.bindStudentList(teacherStudentRelateEntity);	
	}
	@Override
	public HttpResponseEntity addPaper(TeacherPaperEntity teacherPaperEntity) {
		int i=0;
		teacherPaperEntity.setId(UUIDUtil.getOneUUID());
		teacherPaperEntity.setCreateTime(new Date());
		try{
			i = teacherPaperEntityMapper.insertSelective(teacherPaperEntity);
			int j= addPic(teacherPaperEntity.getId(),teacherPaperEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			e.printStackTrace();
			return HttpResponseEntity.error("请求失败");
		}
	}


	@Override
	public List<StudentEntity> queryStudentMsg(StudentEntity studentEntity) {
		
		return studentEntityMapper.selectByStudent(studentEntity);
	}
	@Override
	public HttpResponseEntity modifyPaper(TeacherPaperEntity teacherPaperEntity) {
		int i=0;
		teacherPaperEntity.setModifyTime(new Date());
		try{
			i = teacherPaperEntityMapper.updateByPrimaryKeySelective(teacherPaperEntity);
			int j= addPic(teacherPaperEntity.getId(),teacherPaperEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delPaper(TeacherPaperEntity teacherPaperEntity) {
		int i=0;
		try{
			i = teacherPaperEntityMapper.deleteByPrimaryKey(teacherPaperEntity.getId());
			int j= addPic(teacherPaperEntity.getId(),teacherPaperEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherPaperEntity> selPaper(TeacherPaperEntity teacherPaperEntity) {
		List<TeacherPaperEntity> list = teacherPaperEntityMapper.selectList(teacherPaperEntity);
		if(teacherPaperEntity.getId()!= null && teacherPaperEntity.getId()!= "" ){
			String[] str = selPic(teacherPaperEntity.getId());
			list.get(0).setPicId(str);
		}
		if (list.size() != 0 && list.get(0).getJournalLevel() != null) {
			for (int i = 0; i < list.size(); i++) {
				TeacherPaperEntity teacherPaperEntity1 = new TeacherPaperEntity();
				teacherPaperEntity1.setId(list.get(i).getId());
				teacherPaperEntity1.setJournalLevel(list.get(i).getJournalLevel());
				teacherPaperEntity1 = teacherPaperEntityMapper.selectJou(teacherPaperEntity1);
				list.get(i).setJournalLevel(teacherPaperEntity1.getJournalLevel());
			}
		}
		return list;
	}
	@Override
	public HttpResponseEntity addWritings(TeacherWritingsEntity teacherWritingsEntity) {
		int i=0;
		teacherWritingsEntity.setId(UUIDUtil.getOneUUID());
		teacherWritingsEntity.setCreateTime(new Date());
		try{
			i = teacherWritingsEntityMapper.insertSelective(teacherWritingsEntity);
			int j= addPic(teacherWritingsEntity.getId(),teacherWritingsEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyWritings(TeacherWritingsEntity teacherWritingsEntity) {
		int i=0;
		teacherWritingsEntity.setModifyTime(new Date());
		try{
			i = teacherWritingsEntityMapper.updateByPrimaryKeySelective(teacherWritingsEntity);
			int j= addPic(teacherWritingsEntity.getId(),teacherWritingsEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delWritings(TeacherWritingsEntity teacherWritingsEntity) {
		int i=0;
		try{
			i = teacherWritingsEntityMapper.deleteByPrimaryKey(teacherWritingsEntity.getId());
			int j= addPic(teacherWritingsEntity.getId(),teacherWritingsEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherWritingsEntity> selWritings(TeacherWritingsEntity teacherWritingsEntity) {
    	List<TeacherWritingsEntity> list = teacherWritingsEntityMapper.selectList(teacherWritingsEntity);
		if(teacherWritingsEntity.getId()!= null && teacherWritingsEntity.getId()!= "" ){
			String[] str = selPic(teacherWritingsEntity.getId());
			list.get(0).setPicId(str);
		}
		if (list.size() != 0 && list.get(0).getCompilationForm() != null) {
			for (int i = 0; i < list.size(); i++) {
				TeacherWritingsEntity teacherWritingsEntity1 = new TeacherWritingsEntity();
				teacherWritingsEntity1.setId(list.get(i).getId());
				teacherWritingsEntity1.setCompilationForm(list.get(i).getCompilationForm());
				teacherWritingsEntity1 = teacherWritingsEntityMapper.selectCom(teacherWritingsEntity1);
				list.get(i).setCompilationForm(teacherWritingsEntity1.getCompilationForm());
			}
		}
		return list;
	}
	@Override
	public HttpResponseEntity addProject(TeacherProjectEntity teacherProjectEntity) {
		int i=0;
		teacherProjectEntity.setId(UUIDUtil.getOneUUID());
		teacherProjectEntity.setCreateTime(new Date());;
		try{
			i = teacherProjectEntityMapper.insertSelective(teacherProjectEntity);
			int j= addPic(teacherProjectEntity.getId(),teacherProjectEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyProject(TeacherProjectEntity teacherProjectEntity) {
		int i=0;
		teacherProjectEntity.setModifyTime(new Date());
		try{
			i = teacherProjectEntityMapper.updateByPrimaryKeySelective(teacherProjectEntity);
			int j= addPic(teacherProjectEntity.getId(),teacherProjectEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delProject(TeacherProjectEntity teacherProjectEntity) {
		int i=0;
		try{
			i = teacherProjectEntityMapper.deleteByPrimaryKey(teacherProjectEntity.getId());
			int j= addPic(teacherProjectEntity.getId(),teacherProjectEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherProjectEntity> selProject(TeacherProjectEntity teacherProjectEntity) {
		List<TeacherProjectEntity> list =  teacherProjectEntityMapper.selectList(teacherProjectEntity);
		if(teacherProjectEntity.getId()!= null && teacherProjectEntity.getId()!= "" ){
			String[] str = selPic(teacherProjectEntity.getId());
			list.get(0).setPicId(str);
		}
		return list;
	}
	@Override
	public HttpResponseEntity addConcluding(TeacherConcludingEntity teacherConcludingEntity) {
		int i=0;
		teacherConcludingEntity.setId(UUIDUtil.getOneUUID());
		teacherConcludingEntity.setCreateTime(new Date());;
		try{
			i = teacherConcludingEntityMapper.insertSelective(teacherConcludingEntity);
			int j= addPic(teacherConcludingEntity.getId(),teacherConcludingEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyConcluding(TeacherConcludingEntity teacherConcludingEntity) {
		int i=0;
		teacherConcludingEntity.setModifyTime(new Date());
		try{
			i = teacherConcludingEntityMapper.updateByPrimaryKeySelective(teacherConcludingEntity);
			int j= addPic(teacherConcludingEntity.getId(),teacherConcludingEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delConcluding(TeacherConcludingEntity teacherConcludingEntity) {
		int i=0;
		try{
			i = teacherConcludingEntityMapper.deleteByPrimaryKey(teacherConcludingEntity.getId());
			int j= addPic(teacherConcludingEntity.getId(),teacherConcludingEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherConcludingEntity> selConcluding(TeacherConcludingEntity teacherConcludingEntity) {
		List<TeacherConcludingEntity> list = teacherConcludingEntityMapper.selectList(teacherConcludingEntity);
		if(teacherConcludingEntity.getId()!= null && teacherConcludingEntity.getId()!= "" ){
			String[] str = selPic(teacherConcludingEntity.getId());
			list.get(0).setPicId(str);
		}
		return list;
	}
	@Override
	public HttpResponseEntity addHorizontal(TeacherHorizontalEntity teacherHorizontalEntity) {
		int i=0;
		teacherHorizontalEntity.setId(UUIDUtil.getOneUUID());
		teacherHorizontalEntity.setCreateTime(new Date());;
		try{
			i = teacherHorizontalEntityMapper.insertSelective(teacherHorizontalEntity);
			int j= addPic(teacherHorizontalEntity.getId(),teacherHorizontalEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyHorizontal(TeacherHorizontalEntity teacherHorizontalEntity) {
		int i=0;
		teacherHorizontalEntity.setModifyTime(new Date());
		try{
			i = teacherHorizontalEntityMapper.updateByPrimaryKeySelective(teacherHorizontalEntity);
			int j= addPic(teacherHorizontalEntity.getId(),teacherHorizontalEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delHorizontal(TeacherHorizontalEntity teacherHorizontalEntity) {
		int i=0;
		try{
			i = teacherHorizontalEntityMapper.deleteByPrimaryKey(teacherHorizontalEntity.getId());
			int j= addPic(teacherHorizontalEntity.getId(),teacherHorizontalEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherHorizontalEntity> selHorizontal(TeacherHorizontalEntity teacherHorizontalEntity) {
		List<TeacherHorizontalEntity> list = teacherHorizontalEntityMapper.selectList(teacherHorizontalEntity);
		if(teacherHorizontalEntity.getId()!= null && teacherHorizontalEntity.getId()!= "" ){
			String[] str = selPic(teacherHorizontalEntity.getId());
			list.get(0).setPicId(str);
		}
		return list;
	}

	@Override
	public HttpResponseEntity addPrize(TeacherPrizeEntity teacherPrizeEntity) {
		int i=0;
		teacherPrizeEntity.setId(UUIDUtil.getOneUUID());
		teacherPrizeEntity.setCreateTime(new Date());;
		try{
			i = teacherPrizeEntityMapper.insertSelective(teacherPrizeEntity);
			int j= addPic(teacherPrizeEntity.getId(),teacherPrizeEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyPrize(TeacherPrizeEntity teacherPrizeEntity) {
		int i=0;
		teacherPrizeEntity.setModifyTime(new Date());
		try{
			i = teacherPrizeEntityMapper.updateByPrimaryKeySelective(teacherPrizeEntity);
			int j= addPic(teacherPrizeEntity.getId(),teacherPrizeEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delPrize(TeacherPrizeEntity teacherPrizeEntity) {
		int i=0;
		try{
			i = teacherPrizeEntityMapper.deleteByPrimaryKey(teacherPrizeEntity.getId());
			int j= addPic(teacherPrizeEntity.getId(),teacherPrizeEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public List<TeacherPrizeEntity> selPrize(TeacherPrizeEntity teacherPrizeEntity) {
		List<TeacherPrizeEntity> list = teacherPrizeEntityMapper.selectList(teacherPrizeEntity);
		if(teacherPrizeEntity.getId()!= null && teacherPrizeEntity.getId()!= "" ){
			String[] str = selPic(teacherPrizeEntity.getId());
			list.get(0).setPicId(str);
		}
		return list;
	}
	@Override
	public HttpResponseEntity addPatent(TeacherPatentEntity teacherPatentEntity) {
		int i=0;
		teacherPatentEntity.setId(UUIDUtil.getOneUUID());
		teacherPatentEntity.setCreateTime(new Date());;
		try{
			i = teacherPatentEntityMapper.insertSelective(teacherPatentEntity);
			int j= addPic(teacherPatentEntity.getId(),teacherPatentEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("添加成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity modifyPatent(TeacherPatentEntity teacherPatentEntity) {
		int i=0;
		teacherPatentEntity.setModifyTime(new Date());
		try{
			i = teacherPatentEntityMapper.updateByPrimaryKeySelective(teacherPatentEntity);
			int j= addPic(teacherPatentEntity.getId(),teacherPatentEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("修改成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}
	@Override
	public HttpResponseEntity delPatent(TeacherPatentEntity teacherPatentEntity) {
		
		int i=0;
		try{
			i = teacherPatentEntityMapper.deleteByPrimaryKey(teacherPatentEntity.getId());
			int j= addPic(teacherPatentEntity.getId(),teacherPatentEntity.getPicId());
			if(i>0&&j>0){
				return HttpResponseEntity.seccuss("删除成功");
			}else{
				return HttpResponseEntity.error("请求失败");
			}			
		}catch(Exception e){
			return HttpResponseEntity.error("请求失败");
		}
	}

	@Override
	public List<TeacherPatentEntity> selPatent(TeacherPatentEntity teacherPatentEntity) {
		List<TeacherPatentEntity> list = teacherPatentEntityMapper.selectList(teacherPatentEntity);
		if(teacherPatentEntity.getId()!= null && teacherPatentEntity.getId()!= "" ){
			String[] str = selPic(teacherPatentEntity.getId());
			list.get(0).setPicId(str);
		}
		return list;
	}


	@Override
	public HttpResponseEntity setPermission(TeacherUserEntity teacherUserEntity) {
		//判断传值set为设定院领导，noset 为解除院领导 院领导角色id为25
		List<Long> roleIds = new  ArrayList<Long>();
		List<Long> roleIds1 = new  ArrayList<Long>();
		String returnStr = new String();
		if("set".equals(teacherUserEntity.getPermissionId())){
			roleIds.add((long) 25);
			try{
			User user = new User();
			user.setId(Long.valueOf(teacherUserEntity.getId()));
			user.setIsNoleader("1");
			userDao.saveUserRoles(Long.valueOf(teacherUserEntity.getId()), roleIds);
			userDao.update(user);
			returnStr = "设定成功";
			}catch(Exception e){
				e.printStackTrace();
				return HttpResponseEntity.error("请求失败");
			}
		}else{
			try{
				roleIds1.add((long) 26);
				userDao.deleteUserRole(Long.valueOf(teacherUserEntity.getId()));
				User user = new User();
				user.setId(Long.valueOf(teacherUserEntity.getId()));
				user.setIsNoleader("");
				userDao.update(user);
				userDao.saveUserRoles(Long.valueOf(teacherUserEntity.getId()),roleIds1);
				returnStr = "解除设定成功";
			}catch(Exception e){
				e.printStackTrace();
				return HttpResponseEntity.error("请求失败");
			}
					
		}
		return HttpResponseEntity.seccuss(returnStr);
	}


	@Override
	public HttpResponseEntity addResearch(TeacherResearchSectionEntity teacherResearchSectionEntity) {
		try{
			teacherResearchSectionEntityMapper.insertSelective(teacherResearchSectionEntity);
			return HttpResponseEntity.seccuss("添加成功");
		}catch(Exception e){
			e.printStackTrace();
			return HttpResponseEntity.error("请求失败");
		}
	}


	@Override
	public HttpResponseEntity delResearch(TeacherResearchSectionEntity teacherResearchSectionEntity) {
		try{
			teacherResearchSectionEntityMapper.deleteByPrimaryKey(teacherResearchSectionEntity.getId());
			teacherResearchSectionEntityMapper.deleteByparentId(teacherResearchSectionEntity.getId());
			return HttpResponseEntity.seccuss("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			return HttpResponseEntity.error("请求失败");
		}
	
	}


	@Override
	public List<TeacherResearchSectionEntity> selResearch() {
		List<TeacherResearchSectionEntity> teacherResearchAll = teacherResearchSectionEntityMapper.listAll();

        List<TeacherResearchSectionEntity> list = Lists.newArrayList();
        setTeacherResearchSectionList(0L, teacherResearchAll, list);

        return list;
	}
	 private void setTeacherResearchSectionList(Long pId, List<TeacherResearchSectionEntity> teacherResearchAll, List<TeacherResearchSectionEntity> list) {
	        for (TeacherResearchSectionEntity tea : teacherResearchAll) {
	            if (tea.getParentid().equals(pId)) {
	                list.add(tea);
	                if (teacherResearchAll.stream().filter(p -> p.getParentid().equals(tea.getId())).findAny() != null) {
	                	setTeacherResearchSectionList(tea.getId().longValue(), teacherResearchAll, list);
	                }
	            }
	        }
	    }


	@Override
	public List<TeacherResearchSectionEntity> selResearchParent() {
		List<TeacherResearchSectionEntity> teacherResearchAll = teacherResearchSectionEntityMapper.listParentAll();
		return teacherResearchAll;
	}
	/**
	 * 添加图片
	 */
	public int addPic(String outputId,String[] picId ){
		
		try{
			//根据成果物id，删除所有图片关联
			teacherOutputPicRelateEntityMapper.deleteByPrimaryKey(outputId);
			//重新添加图片关联
			if(picId == null){
				return 1;
			}else{
				for(int i=0;i<picId.length;i++){
					TeacherOutputPicRelateEntity teacherOutputPicRelateEntity = new TeacherOutputPicRelateEntity();
					teacherOutputPicRelateEntity.setOutputId(outputId);
					teacherOutputPicRelateEntity.setPicId(picId[i]);
					teacherOutputPicRelateEntityMapper.insertSelective(teacherOutputPicRelateEntity);
				}
			}	
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}
		return 1;		
	}
	/**
	 * 查询图片
	 */
	public String[] selPic(String outputId ) {
		TeacherOutputPicRelateEntity teacherOutputPicRelateEntity = new TeacherOutputPicRelateEntity();
		teacherOutputPicRelateEntity.setOutputId(outputId);
		List<TeacherOutputPicRelateEntity> list = teacherOutputPicRelateEntityMapper.selPic(teacherOutputPicRelateEntity);
		String[] str =  new String[list.size()];
		for(int i=0;i<list.size();i++){
			str[i] = remoteProperties.getHttpUrl()+list.get(i).getPicId();
		}
		return str;
	}


	@Override
	public List<TeacherUserEntity> selTeacherList(TeacherUserEntity teacherUserEntity) {
		//获取当前用户
		User user = UserUtil.getCurrentUser();
		TeacherUserEntity teacher = teacherUserEntityMapper.selectByTeacherNo(user.getUsername());
		TeacherUserEntity userTeacher = new TeacherUserEntity();
		userTeacher.setCollege(teacher.getCollege());
		userTeacher.setTeacherNo(teacherUserEntity.getTeacherNo());
		userTeacher.setUserName(teacherUserEntity.getUserName());
		List<TeacherUserEntity> list = teacherUserEntityMapper.selectTeacherList(userTeacher);
		return list;
	}
	@Override
	public List<TeacherUserEntity> selTeacherOutputList(TeacherUserEntity teacherUserEntity) {
		//获取当前用户
		User user = UserUtil.getCurrentUser();
		TeacherUserEntity teacher = teacherUserEntityMapper.selectByTeacherNo(user.getUsername());	
		Date date = new Date();
		//如果角色是院领导，则可以查看该院全部老师信息
		if("1".equals(user.getIsNoleader())){
			teacherUserEntity.setCollege(teacher.getCollege());
		}else if(teacher.getAllowCheckTime().after(date)){	//若不是院领导，对比教师允许查看时间，根据教研室查询
			teacherUserEntity.setMajor(teacher.getMajor());
		}else{//如果都不是只能查看自己
			teacherUserEntity.setTeacherId(teacher.getTeacherId());
		}			
		List<TeacherUserEntity> list = teacherUserEntityMapper.selectTeacherOutput(teacherUserEntity);		
		return list;
	}
	
	@Override
	public List<TeacherResearchSectionEntity> miniselResearch(
			TeacherResearchSectionEntity teacherResearchSectionEntity) {	
		return teacherResearchSectionEntityMapper.queryAll(teacherResearchSectionEntity);
		 
	}
	
	@Override
	public HttpResponseEntity downloadTeacherOutput(TeacherUserEntity teacherUserEntity,HttpServletResponse response) {
		//获取当前用户
		User user = UserUtil.getCurrentUser();
		TeacherUserEntity teacher = teacherUserEntityMapper.selectByTeacherNo(user.getUsername());	
		Date date = new Date();
		//如果角色是院领导，则可以查看该院全部老师信息
		if("1".equals(user.getIsNoleader())){
			teacherUserEntity.setCollege(teacher.getCollege());
		}else if(teacher.getAllowCheckTime().after(date)){	//若不是院领导，对比教师允许查看时间，根据教研室查询		
			teacherUserEntity.setMajor(teacher.getMajor());
		}else{//如果都不是只能查看自己
			teacherUserEntity.setTeacherId(teacher.getTeacherId());
		}
		Map<String,List<?>> map = new HashMap<String,List<?>>();
		//根据条件分别查询成果物
		List<TeacherPaperEntity> paperList = teacherPaperEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherWritingsEntity> writingList = teacherWritingsEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherProjectEntity> projectList = teacherProjectEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherConcludingEntity> concludingList = teacherConcludingEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherHorizontalEntity> horizontalList = teacherHorizontalEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherPrizeEntity> prizeList = teacherPrizeEntityMapper.queryForExcel(teacherUserEntity);
		List<TeacherPatentEntity> patentList = teacherPatentEntityMapper.queryForExcel(teacherUserEntity);
		map.put("paperList", paperList);
		map.put("writingList", writingList);
		map.put("projectList", projectList);
		map.put("concludingList", concludingList);
		map.put("horizontalList", horizontalList);
		map.put("prizeList", prizeList);
		map.put("patentList", patentList);
		
		//下载excel
//		DownExcel.downloadExcel(map);
		String filePath = new String();
		try {
			filePath = DownExcel.downloadExcel(map,response);
		} catch (IOException e) {
			e.printStackTrace();
			return HttpResponseEntity.error("导出失败");
		}
		return HttpResponseEntity.seccuss(filePath);
		
	}


}
