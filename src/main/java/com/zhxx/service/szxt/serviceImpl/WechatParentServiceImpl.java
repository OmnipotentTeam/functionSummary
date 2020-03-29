package com.zhxx.service.szxt.serviceImpl;


import com.zhxx.admin.server.dao.RoleDao;
import com.zhxx.admin.server.model.Role;
import com.zhxx.admin.server.model.User;
import com.zhxx.admin.server.utils.UserUtil;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.StudentEntity;
import com.zhxx.service.szxt.entity.WechatParentEntity;
import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.mapper.WechatParentEntityMapper;
import com.zhxx.service.szxt.service.GradeEvaluationService;
import com.zhxx.service.szxt.service.WechatParentService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WechatParentServiceImpl implements WechatParentService {
    private final Logger logger = LoggerFactory.getLogger(GradeEvaluationService.class);
    @Autowired
    private WechatParentEntityMapper wechatParentEntityMapper;
    @Autowired
    private StudentEntityMapper studentEntityMapper;
    @Autowired
    private RoleDao roleDao;

    //判断是否绑定
    @Override
    public Map<String, String> judgebinding(WechatParentEntity wechatParentEntity) {
        Map<String,String> map = new HashMap<String,String>();
        WechatParentEntity wechatParentEntity1 = new WechatParentEntity();
        wechatParentEntity1.setOpenId(wechatParentEntity.getOpenId());
        List<WechatParentEntity> wechatparentlist  = wechatParentEntityMapper.selectBy(wechatParentEntity1);
        if(wechatparentlist.size() == 0){
            map.put("code", "1");
            map.put("msg", "跳入家长绑定页面");
            return map;
        }
        else{
            map.put("code", "0");
            map.put("openid", wechatparentlist.get(0).getOpenId());
            map.put("msg", "已绑定");
            return map;
        }
    }

    /**
     * by 马英帅  2020-01-14
     * 判断家长信息是否正确，保存
     *
     * 修改 王传营 2020-01-15
     * @param wechatParentEntity
     * @return
     */
    //绑定保存
    @Override
    public Map<String, String> binding(WechatParentEntity wechatParentEntity) {
        Map<String,String> map = new HashMap<String,String>();
        List<WechatParentEntity> wechatparentlist = wechatParentEntityMapper.selectStudentNo(wechatParentEntity);
        if(wechatparentlist.size() == 0){
            map.put("code", "1");
            map.put("msg", "学生学号或绑定码不正确");
            return map;
        }
        else {
            wechatParentEntity.setParentId(UUIDUtil.getOneUUID());
            wechatParentEntity.setStudentId(wechatparentlist.get(0).getStudentId());
            //添加到家长表
            wechatParentEntityMapper.insert(wechatParentEntity);
            //修改学生表中的student_binding值为1  ————意为：已绑定
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentId(wechatparentlist.get(0).getStudentId());
            studentEntityMapper.updateStudentBindingCode(studentEntity);
            map.put("code", "0");
            map.put("msg", "绑定成功");
            return map;
        }
    }


    //删除绑定
    @Override
    public Map<String, String> deletebinding(WechatParentEntity wechatParentEntity) {
        Map<String,String> map = new HashMap<String,String>();
        WechatParentEntity wechatParentEntity1 = new WechatParentEntity();
        wechatParentEntity1.setOpenId(wechatParentEntity.getOpenId());
        List<WechatParentEntity> wechatparentlist  = wechatParentEntityMapper.selectBy(wechatParentEntity1);
        if(wechatparentlist.size() == 0){
            map.put("code", "1");
            map.put("msg", "无绑定信息");
            return map;
        }
        else{
            wechatParentEntityMapper.deletebinding(wechatParentEntity);
            map.put("code", "0");
            map.put("openid", wechatparentlist.get(0).getOpenId());
            map.put("msg", "解除成功");
            return map;
        }
    }

    /**
     * by 王传营  2020-01-14
     * 根据学生id查询绑定家长信息
     * @param id
     * @return
     */
    @Override
    public HttpResponseEntity selectParent(String id) {
        try {
            //获取当前登陆用户，以及角色
            User currentUser = UserUtil.getCurrentUser();
            List<Role> roles = roleDao.listByUserId(currentUser.getId());
            WechatParentEntity wechatParentEntity = new WechatParentEntity();
            wechatParentEntity.setStudentNo(id);
            //查询所有信息
            List<WechatParentEntity> wechatParentEntities = wechatParentEntityMapper.selectBy(wechatParentEntity);
            //判断身份
            String identification = "";
            if ("企业".equals(roles.get(0).getName())) {
                identification = "企业";  //识别身份
            }else{
                identification = "教师";  //识别身份
            }
            //长度不为0时，放值
            if (wechatParentEntities.size() != 0){
                wechatParentEntities.get(0).setIdentification(identification);
            }
            logger.info("学生家长信息查询成功，接口{}", "selectParent");
            return HttpResponseEntity.seccuss(wechatParentEntities);
        }catch (Exception e){
            logger.info("学生家长信息查询失败，接口{}", "selectParent");
            return HttpResponseEntity.error("学生家长信息查询失败");
        }
    }

    /**
     * by 王传营  2020-01-15
     * 根据openid查询绑定家长信息
     * @param wechatParentEntity
     * @return
     */
    @Override
    public HttpResponseEntity selectStudentId(WechatParentEntity wechatParentEntity) {
        try {
            List<WechatParentEntity> wechatParentEntities = wechatParentEntityMapper.selectBy(wechatParentEntity);
            logger.info("绑定家长信息查询成功，接口{}", "selectStudentId");
            return HttpResponseEntity.seccuss(wechatParentEntities);
        }catch (Exception e){
            logger.info("绑定家长信息查询失败，接口{}", "selectStudentId");
            return HttpResponseEntity.error("绑定家长信息查询失败");
        }
    }
}
