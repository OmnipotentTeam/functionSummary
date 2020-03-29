package com.zhxx.service.szxt.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.admin.server.dao.DictDao;
import com.zhxx.admin.server.model.Dict;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.AccessModelEntity;
import com.zhxx.service.szxt.entity.AccessModelOptionCommonEntity;
import com.zhxx.service.szxt.entity.AccessModelOptionEntity;
import com.zhxx.service.szxt.entity.AccessOptionEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.mapper.AccessModelEntityMapper;
import com.zhxx.service.szxt.mapper.AccessModelOptionEntityMapper;
import com.zhxx.service.szxt.mapper.AccessOptionEntityMapper;
import com.zhxx.service.szxt.mapper.AssessFunMapper;
import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.service.AssessFunService;
import com.zhxx.service.szxt.utils.UUIDUtil;
@Service
public class AssessFunServiceImpl implements AssessFunService{
    @Autowired
    private AccessModelEntityMapper accessModelEntityMapper;
    @Autowired
    private AccessOptionEntityMapper accessOptionEntityMapper;
    @Autowired
    private AccessModelOptionEntityMapper accessModelOptionEntityMapper;
    @Autowired
    private DictDao dictDao;
    @Autowired
    private AssessFunMapper assessFunMapper;
    @Autowired
    private StudentEntityMapper studentEntityMapper;
    /*
     *增加模型
     */
	@Override
	@Transactional
	public HttpResponseEntity addAssessModel(AccessModelOptionCommonEntity accessModelOptionCommonEntity) {
		AccessModelEntity accessModelEntity = new AccessModelEntity();
		accessModelEntity.setId(UUIDUtil.getOneUUID());
		accessModelEntity.setTitle(accessModelOptionCommonEntity.getTitle());
		accessModelEntity.setDescription(accessModelOptionCommonEntity.getDescription());
		
		accessModelEntity.setApplicablegrade(accessModelOptionCommonEntity.getApplicablegrade());
		accessModelEntity.setCategory(accessModelOptionCommonEntity.getCategory());
		accessModelEntity.setModellevel(accessModelOptionCommonEntity.getModelLevel());
		accessModelEntity.setRequired(accessModelOptionCommonEntity.getRequired());
		accessModelEntity.setOpenCloseState(accessModelOptionCommonEntity.getOpenCloseState());
		
		accessModelEntity.setState("1");//1-有效 0-无效
		accessModelEntity.setOperator(accessModelOptionCommonEntity.getUserId());
		accessModelEntity.setLastoperator(accessModelOptionCommonEntity.getUserId());
		accessModelEntity.setCreatetime(new Date());
		accessModelEntity.setUpdatetime(new Date());		
		try{
			//插入模型表
			accessModelEntityMapper.insert(accessModelEntity);
		    
		    //遍历选项,插入选项表和关联表
		    for(int i=0;i<accessModelOptionCommonEntity.getOptionList().size();i++){
		    	AccessOptionEntity  accessOptionEntity = accessModelOptionCommonEntity.getOptionList().get(i);
		    	accessOptionEntity.setId(UUIDUtil.getOneUUID());
		    	 
		    	AccessModelOptionEntity accessModelOptionEntity = new AccessModelOptionEntity();
		    	accessModelOptionEntity.setRelateId(UUIDUtil.getOneUUID());
		    	accessModelOptionEntity.setModelId(accessModelEntity.getId());
		    	accessModelOptionEntity.setOptionId(accessOptionEntity.getId());
		    	accessOptionEntityMapper.insert(accessOptionEntity);
		    	accessModelOptionEntityMapper.insert(accessModelOptionEntity);
		    	 
		    }
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();	
			return HttpResponseEntity.error("模型新增失败");
		}
		   return HttpResponseEntity.seccuss("模型新增成功");
	}
	 /*
     *修改
     */
	@Override
	@Transactional
	public HttpResponseEntity updateAssessModel(AccessModelOptionCommonEntity accessModelOptionCommonEntity) {
		
		AccessModelEntity accessModelEntity = new AccessModelEntity();
		accessModelEntity.setId(accessModelOptionCommonEntity.getId());
		accessModelEntity.setTitle(accessModelOptionCommonEntity.getTitle());
		accessModelEntity.setDescription(accessModelOptionCommonEntity.getDescription());
		
		accessModelEntity.setApplicablegrade(accessModelOptionCommonEntity.getApplicablegrade());
		accessModelEntity.setCategory(accessModelOptionCommonEntity.getCategory());
		accessModelEntity.setModellevel(accessModelOptionCommonEntity.getModelLevel());
		accessModelEntity.setRequired(accessModelOptionCommonEntity.getRequired());
		accessModelEntity.setOpenCloseState(accessModelOptionCommonEntity.getOpenCloseState());
		
		accessModelEntity.setOperator(accessModelOptionCommonEntity.getUserId());
		accessModelEntity.setLastoperator(accessModelOptionCommonEntity.getUserId());
		accessModelEntity.setUpdatetime(new Date());
		
		//修改模型
		try{
			accessModelEntityMapper.updateByPrimaryKeySelective(accessModelEntity);
		//删除选项表和关联表中的数据
		//查询所有选项id
		Map<String,String> relateMap = new HashMap<String,String>();
		relateMap.put("modelId", accessModelOptionCommonEntity.getId());
		List<AccessModelOptionEntity> relateList = accessModelOptionEntityMapper.queryall(relateMap);
		//删除选项表
		for(int i=0 ; i<relateList.size();i++){
			//删除选项表
			accessOptionEntityMapper.deleteByPrimaryKey(relateList.get(i).getOptionId());
			//删除关联表
			accessModelOptionEntityMapper.deleteByPrimaryKey(relateList.get(i).getRelateId());
		}
		
		  //遍历选项,插入选项表和关联表
	    for(int i=0;i<accessModelOptionCommonEntity.getOptionList().size();i++){
	    	AccessOptionEntity  accessOptionEntity = accessModelOptionCommonEntity.getOptionList().get(i);
	    
	    	 accessOptionEntity.setId(UUIDUtil.getOneUUID());
	    	 AccessModelOptionEntity accessModelOptionEntity = new AccessModelOptionEntity();
	    	 accessModelOptionEntity.setRelateId(UUIDUtil.getOneUUID());
	    	 accessModelOptionEntity.setModelId(accessModelEntity.getId());
	    	 accessModelOptionEntity.setOptionId(accessOptionEntity.getId());
	    	 accessOptionEntityMapper.insert(accessOptionEntity);
	    	 accessModelOptionEntityMapper.insert(accessModelOptionEntity);
	    	 
	    }
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return HttpResponseEntity.seccuss("模型修改失败");
		}
		    return HttpResponseEntity.seccuss("模型修改成功");
	}
	 /*
     *删除模型
     */
	@Override
	public HttpResponseEntity deleteAssessModel(AccessModelEntity accessModelEntity) {
		try{
			accessModelEntity.setState("0");//1-有效 0-无效
			accessModelEntityMapper.updateByPrimaryKeySelective(accessModelEntity);			
		}catch(Exception e){
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return HttpResponseEntity.error("请求失败");
		}	
		return HttpResponseEntity.seccuss("删除成功");
	}
	 /*
     *模型列表模型
     */
	@Override
	public HttpResponseEntity queryAssessModel( Map<String, String> map) {
		
		AccessModelOptionCommonEntity accessModelOptionCommonEntity = new AccessModelOptionCommonEntity();
		accessModelOptionCommonEntity.setTitle(map.get("title"));
		accessModelOptionCommonEntity.setApplicablegrade(map.get("applicablegrade"));
		accessModelOptionCommonEntity.setCategory(map.get("category"));
		accessModelOptionCommonEntity.setModelLevel(map.get("modelLevel"));
		accessModelOptionCommonEntity.setOpenCloseState(map.get("openCloseState"));
		accessModelOptionCommonEntity.setRequired(map.get("required"));
		
		 int pageNum = Integer.parseInt(map.get("pageNum").toString());
         int pageSize = Integer.parseInt(map.get("pageSize").toString());
         pageNum = pageNum == 0 ? 1 : pageNum;
         pageSize = pageSize == 0 ? 10 : pageSize;
         PageHelper.startPage(pageNum, pageSize);
         
		List<AccessModelOptionCommonEntity> list = assessFunMapper.queryModelList(accessModelOptionCommonEntity);
        PageInfo pageInfo = new PageInfo(list);
		return HttpResponseEntity.seccuss(pageInfo);
	}
	 /*
     *详细信息
     */
	@Override
	public HttpResponseEntity detailMsgAssessModel(AccessModelEntity accessModelEntity) {
		AccessModelOptionCommonEntity accessModelOptionCommonEntity = new AccessModelOptionCommonEntity();
		//查询模型信息
		AccessModelEntity model = accessModelEntityMapper.selectByPrimaryKey(accessModelEntity.getId());
		//查询选项信息
		Map<String,String> map = new HashMap<String,String>();
		map.put("modelId", accessModelEntity.getId());
		List<AccessOptionEntity> optionList = accessOptionEntityMapper.queryAllOption(map);
		 Map<String, Object> resultMap = new HashedMap();
		 resultMap.put("model", model);
		 resultMap.put("optionList", optionList);
	     return HttpResponseEntity.seccuss(resultMap);
	}
	 /*
     *字典下拉框
     */
	@Override
	public HttpResponseEntity detailmsgselect(String dicttype) {
		Map<String, Object> resultMap = new HashedMap();	
		List<Dict> listdict = dictDao.listByType(dicttype);
		 resultMap.put("listdict", listdict);
		 return HttpResponseEntity.seccuss(resultMap);
	}
	 /*
     *查询模型带选项
     */
	@Override
	public HttpResponseEntity queryModelOptionService(Map<String, String> map) {
		
		
		
        //获取学生id
		StudentEntity studentEntity = studentEntityMapper.selectByPrimaryKey(map.get("studentId"));
		
		AccessModelOptionCommonEntity accessModelOptionCommonEntity = new AccessModelOptionCommonEntity();
		accessModelOptionCommonEntity.setTitle(map.get("title"));
		accessModelOptionCommonEntity.setApplicablegrade("5"+studentEntity.getStudentGrade());
		accessModelOptionCommonEntity.setCategory(map.get("category"));
		accessModelOptionCommonEntity.setModelLevel(map.get("modelLevel"));
		accessModelOptionCommonEntity.setOpenCloseState(map.get("openCloseState"));
		accessModelOptionCommonEntity.setRequired(map.get("required"));
		
         
		List<AccessModelOptionCommonEntity> accessModelOptionlist = assessFunMapper.queryModelAndOptionList(accessModelOptionCommonEntity);
		//把list按照category分类
		Map<String, List<AccessModelOptionCommonEntity>> devloperMap = new HashMap<>();
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		for (AccessModelOptionCommonEntity devloper: accessModelOptionlist) {
			if (devloperMap.containsKey(devloper.getCategory()+"-"+devloper.getCategoryName())) {
				devloperMap.get(devloper.getCategory()+"-"+devloper.getCategoryName()).add(devloper);
			} else {
				List<AccessModelOptionCommonEntity> devs = new ArrayList<>();
				devs.add(devloper);
				devloperMap.put(devloper.getCategory()+"-"+devloper.getCategoryName(), devs);
			}	
		}
		for(String key : devloperMap.keySet()){
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("type", key);
			resultMap.put("list", devloperMap.get(key));
			resultList.add(resultMap);
		}
		return HttpResponseEntity.seccuss(resultList);
	}

}
