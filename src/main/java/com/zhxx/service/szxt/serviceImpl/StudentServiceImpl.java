package com.zhxx.service.szxt.serviceImpl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.entity.*;
import com.zhxx.service.szxt.mapper.*;
import com.zhxx.service.szxt.service.StudentService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by huasheng on 2018/12/6.
 */
@Service
public class StudentServiceImpl implements StudentService {

    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private RemoteProperties remoteProperties;
    @Autowired
    private StudentEntityMapper studentEntityMapper;

    @Autowired
    private GradeEvaluationEntityMapper gradeEvaluationEntityMapper;

    @Autowired
    private MarkEntityMapper markEntityMapper;

    @Autowired
    private TaskRecordEntityMapper taskRecordEntityMapper;
    @Autowired
    private TaskEntityMapper taskEntityMapper;

    @Autowired
    private StageEntityMapper stageEntityMapper;
    @Autowired
    private StageRecordEntityMapper stageRecordEntityMapper;
    @Autowired
    private MajorEntityMapper majorEntityMapper;
    @Autowired
    private StudentChangeEntityMapper studentChangeEntityMapper;
    @Autowired
    private DimensionEntityMapper dimensionEntityMapper;
    @Autowired
    private WechatParentEntityMapper wechatParentEntityMapper;
    @Autowired
    private TeacherUserEntityMapper teacherUserEntityMapper;
    /** 
     * 学生添加接口
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity addStudent(StudentEntity studentEntity) {
        try {
            if (!StringUtils.isEmpty(studentEntity.getStudentNo()) && !StringUtils.isEmpty(studentEntity.getMajorId()) && !StringUtils.isEmpty(studentEntity.getStudentId()) && !StringUtils.isEmpty(studentEntity.getStudentName()) && !StringUtils.isEmpty(studentEntity.getStudentSex()) && !StringUtils.isEmpty(studentEntity.getStudentPhoneNumber())) {
                studentEntity.setStudentGrade(1);
                Date date = new Date();
                studentEntity.setCreateTime(date);
                studentEntity.setModifyTime(date);
                studentEntity.setStatus(1);
                studentEntity.setRetardation(0);
                SimpleDateFormat sdf = new SimpleDateFormat( "yyyy" );
                String str = sdf.format(date);
                System.out.println(str);
                studentEntity.setStartSchool(str);
                int i = studentEntityMapper.addStudent(studentEntity);
                if (i > 0) {
                    logger.info("注册学生成功，接口{}", "addStudent");
                    return HttpResponseEntity.seccuss("注册学生成功");
                } else {
                    logger.info("注册学生失败，接口{}", "addStudent");
                    return HttpResponseEntity.error("请求失败");
                }
            }
            logger.info("注册学生失败，接口{}", "addStudent");
            return HttpResponseEntity.error("请求失败");

        } catch (Exception e) {
            logger.info("注册学生失败，接口{}", "addStudent");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 学生信息查询
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity selectStudent(StudentEntity studentEntity) {
        try {

            StudentEntity studentEntity1 = studentEntityMapper.selectStudent(studentEntity.getStudentId());
            studentEntity1.setHeadImgPath(remoteProperties.getHttpUrl() + studentEntity1.getHeadImg());
            logger.info("学生个人信息查询成功，接口{}", "selectStudent");
            return HttpResponseEntity.seccuss(studentEntity1);
        } catch (Exception e) {
            logger.info("学生个人信息查询失败，接口{}", "selectStudent");
            return HttpResponseEntity.error("学生个人信息查询失败");
        }
    }

    /**
     * 生成六位随机数
     * by 王传营
     * @return bindingCode
     */
    @Override
    public HttpResponseEntity insertStudentBindingCode(StudentEntity studentEntity) {
        try {
            String bindingCode = "";
            Random random = new Random();
            for (int a=0;a<6;a++){
                bindingCode+=random.nextInt(10);
            }
            studentEntity.setStudentBindingCode(bindingCode);
            studentEntityMapper.insertupdateStudentBindingCode(studentEntity);
//                    String.valueOf((int) (Math.random() * 9 + 1) * 100000);
            logger.info("学生绑定码生成成功，接口{}", "insertStudentBindingCode");
            return HttpResponseEntity.seccuss(bindingCode);
        }catch (Exception e){
            logger.info("学生绑定码生成失败，接口{}", "insertStudentBindingCode");
            return HttpResponseEntity.error("学生绑定码生成失败");
        }
    }

    /**
     * 学生个人信息修改
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity updateStudentInformation(StudentEntity studentEntity) {
        try {

            StudentEntity studentEntity1 = studentEntityMapper.selectStudent(studentEntity.getStudentId());
            if (studentEntity.getHeadImg()=="" || studentEntity.getHeadImg() == null){
                studentEntity.setHeadImg(studentEntity1.getHeadImg());
            }
            int i = studentEntityMapper.updateStudentInformation(studentEntity);

            if (i > 0) {
                logger.info("学生个人信息修改成功，接口{}", "updateStudentInformation");
                return HttpResponseEntity.seccuss("学生个人信息修改成功");
            } else {
                logger.info("学生个人信息修改失败，接口{}", "updateStudentInformation");
                return HttpResponseEntity.error("学生个人信息查询失败");
            }

        } catch (Exception e) {
            logger.info("学生个人信息修改失败，接口{}", "updateStudentInformation");
            return HttpResponseEntity.error("学生个人信息修改失败");
        }
    }

    /**
     * 学生查看点评内容
     *
     * @param map
     * @return
     */
    @Override
    public HttpResponseEntity markStudentSee(Map<String, String> map) {
        try {
            if (!StringUtils.isEmpty(map.get("studentId"))) {

                String studentId = map.get("studentId");
//                GradeEvaluationEntity gradeEvaluationEntity = gradeEvaluationEntityMapper.markStudentSeequestionId(questionId);
//                String gradeEvaluationId = gradeEvaluationEntity.getGradeEvaluationId();
                MarkEntity mark = new MarkEntity();
//                mark.setGradeEvaluationId(gradeEvaluationId);
                mark.setStudentId(studentId);
                List<MarkEntity> markEntityList = markEntityMapper.markStudentSeestudentId(mark);    //获取学生的所有评测点评
                MarkEntity markEntity = new MarkEntity();
                markEntity.setDimensionId(markEntityList.get(0).getDimensionRecordEntity().get(0).getDimensionId());
                markEntity.setMarkId(markEntityList.get(0).getMarkId());
                markEntityMapper.updateByPrimaryKey1(markEntity);
                List<MarkEntity> markEntityLists = markEntityMapper.markStudentSeestudentId(mark);
                if (markEntityList.get(0).getMarkId() != null) {
                    DimensionEntity dimensionEntity = dimensionEntityMapper.selectByPrimaryKey(markEntityLists.get(0).getDimensionId());    //最后一个获取推荐维度名
                    if (dimensionEntity != null) {
                        if (dimensionEntity.getDimensionName() != null) {
                            markEntityList.get(0).setDimensionName(dimensionEntity.getDimensionName());
                        }
                    }
                }
                logger.info("学生查看评测点评成功，接口{}", "markStudentSee");
                return HttpResponseEntity.seccuss(markEntityList.get(0));
            }
            logger.info("学生查看评测点评失败，接口{}", "markStudentSee");
            return HttpResponseEntity.error("学生查看评测点评失败");


        } catch (Exception e) {
            logger.info("学生查看评测点评失败，接口{}", "markStudentSee");
            return HttpResponseEntity.error("学生查看评测点评失败");
        }
    }

    /**
     * 学生端初始化方法
     *
     * @param studentEntity
     * @return
     */

    @Override
    public HttpResponseEntity initialization(StudentEntity studentEntity) {

        try {
            String studentId = studentEntity.getStudentId();       //获取学生id
            StudentEntity student = studentEntityMapper.selectStudent(studentId);   //查询学生信息
            String openId = studentEntity.getStudentId();
            WechatParentEntity parent = wechatParentEntityMapper.selectParent(openId);
            TeacherUserEntity teacherUserEntity = teacherUserEntityMapper.selectOpenId(openId);
            if (parent == null && student == null && teacherUserEntity == null ){
                return HttpResponseEntity.seccuss(Const.PublicCode.REGISTER.getCode()); //返回11111   需要跳转到注册页面
            }
            if (parent != null){
                return HttpResponseEntity.seccuss(Const.PublicCode.REPARENT.getCode()); //返回80000   跳转到家长
            }
            if (teacherUserEntity != null){
                return HttpResponseEntity.seccuss(Const.PublicCode.TEACHER.getCode(),teacherUserEntity.getTeacherId()); //返回90000   跳转到教师
            }
            if (student != null){
                if (student.getStudentGrade() > 3) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.GRADUATION.getCode()); //返回23333   已毕业
                }
                if (student.getRetardation() == 1) {                                          //判断此学生是否是留级生
                    return HttpResponseEntity.seccuss(Const.PublicCode.ANSWER.getCode()); //返回10055   需要答  测评题
                }
                List<MarkEntity> list = markEntityMapper.selectStudentMark(student.getStudentId());
                if (list.size() == 0) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.ANSWER.getCode()); //返回10055   需要答  测评题
                }
                MarkEntity markEntity = new MarkEntity();
                markEntity = list.get(0);
                if (markEntity.getDimensionId() == null) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.WAIT.getCode());       //  返回12000   答了测评题     教师没有点评   展示唯独图
                }
                String markid = markEntity.getMarkId();
                List<TaskRecordEntity> listTaskRecordEntity = taskRecordEntityMapper.selectTaskState(markid);
                if (listTaskRecordEntity.size() == 0) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.QueryTask.getCode());    // 返回12005  先查看测评内容  没有接受任务   查询对应任务
                }
                TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                taskRecordEntity = listTaskRecordEntity.get(0);
                if (taskRecordEntity.getTaskRecordStatus() == 0) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.WAITTask.getCode());      // 返回12050  提示  任务未点评信息
                }
                if (taskRecordEntity.getTaskRecordStatus() == 1 || taskRecordEntity.getTaskRecordStatus() == 3) {
                    return HttpResponseEntity.seccuss(Const.PublicCode.TASKSEE.getCode());      // 返回12300     已接受  已点评    展示任务信息
                }
                if (taskRecordEntity.getTaskRecordStatus() == -1 || taskRecordEntity.getTaskRecordStatus() == 2) {    //已超时或已结束后  查看整个流程是否是  阶段流程 还是  入学测评
                    String str = markEntity.getGradeEvaluationId();                                          //获取发布题记录的id
                    GradeEvaluationEntity gradeEvaluationEntity = gradeEvaluationEntityMapper.selectByPrimaryKey(str);
                    int type = gradeEvaluationEntity.getEvaluationType();                                  //获取题的发布类型  0 入学  1阶段
                    if (type == 0) {
                        if (gradeEvaluationEntity.getGrade() == student.getStudentGrade()) {
                            return HttpResponseEntity.seccuss(Const.PublicCode.ENDTask.getCode());     //返回66666  提示  已超时任务   已完成任务  期待下次任务
                        } else {
                            return HttpResponseEntity.seccuss(Const.PublicCode.ANSWER.getCode());      // 返回10055   需要答  阶段测评题   展示提示   使用查题接口

                        }

                    }
                    if (type == 1) {
                        if (gradeEvaluationEntity.getGrade() == student.getStudentGrade()) {
                            return HttpResponseEntity.seccuss(Const.PublicCode.ENDTask.getCode());     //返回66666  提示  已超时任务   已完成任务  期待下次任务
                        } else {
                            return HttpResponseEntity.seccuss(Const.PublicCode.ANSWER.getCode());      // 返回10055   需要答  阶段测评题   展示提示   使用查题接口

                        }
                    }
                }
            }
            return HttpResponseEntity.error("初始化方法失败");  //这些情况都没有 说明  任务状态不合格等
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("初始化方法失败，接口{}", "initialization");
            return HttpResponseEntity.error("初始化方法失败");
        }
    }


    /**
     * 学生加载推荐维度的任务列表
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity selectTaskList(StudentEntity studentEntity) {
        try {
            if (!StringUtils.isEmpty(studentEntity.getStudentId())) {
                String studentId = studentEntity.getStudentId();
                List<MarkEntity> list = markEntityMapper.selectStudentMark(studentId);   //查询学生最新评测记录
                if (list == null) {
                    logger.info("任务列表加载失败,接口{}", "selectTaskList");
                    return HttpResponseEntity.error("任务列表加载失败，没有做测评的信息");
                }
                MarkEntity markEntity = list.get(0);
                String markId = markEntity.getMarkId();
                if (!StringUtils.isEmpty(markEntity.getDimensionId())) {   //判断是否有推荐维度    与初始化进行双重保险
                    String dimensionId = markEntity.getDimensionId();
                    List<TaskEntity> taskEntityList = taskEntityMapper.selectTaskList(dimensionId);  //获取截止前的，并且是未删除的，已经被发布的任务列表
                    if (taskEntityList == null) {
                        logger.info("任务列表加载失败,接口{}", "selectTaskList");
                        return HttpResponseEntity.error("任务列表加载失败,当前维度下目前没有对应的任务");  //推荐维度下没有对应的任务
                    }
                    taskEntityList.forEach(taskEntity -> taskEntity.setMarkId(markId));
                    logger.info("任务列表加载成功,接口{}", "selectTaskList");
                    return HttpResponseEntity.seccuss(taskEntityList);
                }
            }
            logger.info("任务列表加载失败,接口{}", "selectTaskList");
            return HttpResponseEntity.error("任务列表加载失败");
        } catch (Exception e) {
            logger.info("任务列表加载失败,接口{}", "selectTaskList");
            return HttpResponseEntity.error("任务列表加载失败");
        }
    }


    /**
     * 学生选择任务的接口
     *
     * @param taskRecordEntity
     * @return
     */

    @Override
    public HttpResponseEntity addTaskRecord(TaskRecordEntity taskRecordEntity) {
        try {
            if (!StringUtils.isEmpty(taskRecordEntity.getMarkId()) && !StringUtils.isEmpty(taskRecordEntity.getStudentId()) && !StringUtils.isEmpty(taskRecordEntity.getTaskId())) {
                String uuid = UUIDUtil.getOneUUID();
                taskRecordEntity.setTaskRecordId(uuid);
                Date date = new Date();
                taskRecordEntity.setCreateTime(date);
                taskRecordEntity.setModifyTime(date);
                taskRecordEntity.setStatus(1);
                taskRecordEntity.setTaskRecordStatus(3);
                int i = taskRecordEntityMapper.insert(taskRecordEntity);
                if (i > 0) {
                    logger.info("选择任务成功,接口{}", "addTaskRecord");
                    return HttpResponseEntity.seccuss(Const.PublicCode.WAIT.getCode());
                } else {
                    logger.info("选择任务失败,接口{}", "addTaskRecord");
                    return HttpResponseEntity.error("选择任务失败");
                }
            }

            logger.info("选择任务失败,接口{}", "addTaskRecord");
            return HttpResponseEntity.error("选择任务失败");
        } catch (Exception e) {
            logger.info("选择任务失败,接口{}", "addTaskRecord");
            return HttpResponseEntity.error("选择任务失败");
        }
    }


    /**
     * 学生查看任务详情接口
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity taskDetails(StudentEntity studentEntity) {
        try {
            if (!StringUtils.isEmpty(studentEntity.getStudentId())) {
                String studentId = studentEntity.getStudentId();
                List<MarkEntity> markEntityList = markEntityMapper.selectStudentMark(studentId);
                if (markEntityList == null) {
                    logger.info("任务详情查询失败,接口{}", "TaskDetails");   //学生没有提交过测评信息
                    return HttpResponseEntity.error("任务详情查询失败");
                }
                MarkEntity markEntity = new MarkEntity();
                markEntity = markEntityList.get(0);
                String markId = markEntity.getMarkId();                        //获取markId
                List<TaskRecordEntity> taskRecordEntityList = taskRecordEntityMapper.selectTaskState(markId);    //查询任务记录
                if (taskRecordEntityList.size() == 0) {
                    logger.info("任务详情查询失败,接口{}", "TaskDetails");   //没选过任务
                    return HttpResponseEntity.error("任务详情查询失败");
                }
                TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                taskRecordEntity = taskRecordEntityList.get(0);
                String taskId = taskRecordEntity.getTaskId();                  //获取任务id
                String taskRecordId = taskRecordEntity.getTaskRecordId();        //获取任务记录id

                String dimensionId = markEntity.getDimensionId();              //获取推荐维度id
                DimensionEntity dimensionEntity = dimensionEntityMapper.selectByPrimaryKey(dimensionId); //查询维度表
                String dimensionName = dimensionEntity.getDimensionName();                            //获取推荐维度的名字

                TaskEntity taskEntity = taskEntityMapper.selectByPrimaryKey(taskId);        //获取任务信息
                taskEntity.setDimensionName(dimensionName);                       //任务放入推荐维度的名字
                List<StageEntity> stageEntityList = stageRecordEntityMapper.selectStageRecordAll(taskRecordId);
                if (stageEntityList.size() == 0) {                                 //*******注意判断list是否为空用size（）方法********
                    List<StageEntity> list = stageEntityMapper.selectStageAll(taskId);          //说明一次阶段内容都没提交过

                    Map<String, Object> map = new HashedMap();
                    map.put("TaskEntity", taskEntity);
                    map.put("StageEntity", list.get(0));
                    return HttpResponseEntity.seccuss(map);                     //返回第一阶段内容
                }
                List<StageRecordEntity> stageRecordEntityList = stageEntityList.get(stageEntityList.size() - 1).getStageRecord();      //获取最后阶段的任务记录
                StageRecordEntity stageRecordEntity = stageRecordEntityList.get(stageRecordEntityList.size() - 1);     //获取当前阶段最后一条任务记录
                int stagerecordstatus = stageRecordEntity.getStageRecordStatus();                                               //获取最后一条任务记录点评状态
                if (stagerecordstatus == 2) {              //没点评

                    Map<String, Object> map = new HashedMap();
                    map.put("TaskEntity", taskEntity);
                    map.put("StageEntity", stageEntityList);
                    return HttpResponseEntity.seccuss(map);     //没点评，正常铺
                }
                if (stagerecordstatus == -1) {            //不通过（不及格）
                    int num = stageEntityList.get(stageEntityList.size() - 1).getStageNum();
                    List<StageEntity> list = stageEntityMapper.selectStageAll(taskId);
                    if (num == 100) {
                        stageEntityList.add(list.get(list.size() - 1));     //如果阶段的num值等于100   直接传总结阶段
                    } else {
                        stageEntityList.add(list.get(num - 1));           //否则传下一个阶段
                    }
                    Map<String, Object> map = new HashedMap();
                    map.put("TaskEntity", taskEntity);
                    map.put("StageEntity", stageEntityList);
                    return HttpResponseEntity.seccuss(map);    //没通过 ，最后一条是没通过的阶段任务
                }
                if (stagerecordstatus == 1 ||stagerecordstatus == 3 ||stagerecordstatus == 4 || stagerecordstatus == 5) {   //及格  中等   良好   优秀
                    int num = stageEntityList.get(stageEntityList.size() - 1).getStageNum();
                    List<StageEntity> list = stageEntityMapper.selectStageAll(taskId);
                    stageEntityList.add(list.get(num));                             //list从0开始，所以下一阶段num   就是这一阶段的num值取出来的
                    Map<String, Object> map = new HashedMap();
                    map.put("TaskEntity", taskEntity);
                    map.put("StageEntity", stageEntityList);
                    return HttpResponseEntity.seccuss(map);            //通过后 查询下一个阶段内容一起返回给前端
                }
            }
            logger.info("任务详情查询失败,接口{}", "TaskDetails");
            return HttpResponseEntity.error("任务详情查询失败");
        } catch (Exception e) {
            logger.info("任务详情查询失败,接口{}", "TaskDetails");
            return HttpResponseEntity.error("任务详情查询失败");
        }
    }

    /**
     * 学生提交任务
     *
     * @param map
     * @return
     */
    @Transactional
    public HttpResponseEntity submitTask(Map<String, String> map) {
        try {
            if (!StringUtils.isEmpty(map.get("studentId"))&& !StringUtils.isEmpty(map.get("stageRecordContent")) && !StringUtils.isEmpty(map.get("stageId"))) {
                String studentId = map.get("studentId");                                            //map中取出想要的信息
                String stageRecordImgPath = map.get("stageRecordImgPath");
                String stageRecordContent = map.get("stageRecordContent");
                String stageId = map.get("stageId");
                List<MarkEntity> markEntityList = markEntityMapper.selectStudentMark(studentId);    //查询最新评测记录
                MarkEntity markEntity = markEntityList.get(0);
                List<TaskRecordEntity> taskRecordEntityList = taskRecordEntityMapper.selectTaskState(markEntity.getMarkId());   //查询任务记录
                String taskRecordId = taskRecordEntityList.get(0).getTaskRecordId();             //任务记录表的id
                int taskRecordStatus = taskRecordEntityList.get(0).getTaskRecordStatus();             //任务记录表状态
                if (taskRecordStatus == 0 || taskRecordStatus == -1 || taskRecordStatus == 2) {
                    logger.info("提交任务失败,接口{}", "submitTask");
                    return HttpResponseEntity.error("提交任务失败,未点评,已超时或已完成");                    //未点评时，已超时，已完成时  不让学生在任务详情页提交阶段成果物
                }
                StageRecordEntity stageRecordEntity = new StageRecordEntity();
                stageRecordEntity.setStageRecordId(UUIDUtil.getOneUUID());
                stageRecordEntity.setTaskRecordId(taskRecordId);
                stageRecordEntity.setStageId(stageId);
                stageRecordEntity.setStageRecordImgPath(stageRecordImgPath);
                stageRecordEntity.setStageRecordContent(stageRecordContent);
                stageRecordEntity.setStageRecordStatus(2);
                Date date = new Date();
                stageRecordEntity.setCreateTime(date);
                stageRecordEntity.setModifyTime(date);
                stageRecordEntity.setStatus(1);
                int i = stageRecordEntityMapper.insertSelective(stageRecordEntity);            //提交任务阶段信息到记录中
                TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                taskRecordEntity.setTaskRecordId(taskRecordId);
                taskRecordEntity.setTaskRecordStatus(0);
                taskRecordEntity.setModifyTime(date);
                int z = taskRecordEntityMapper.updateByPrimaryKeySelective(taskRecordEntity);   //修改任务记录表的任务状态
                if (i > 0 && z > 0) {
                    logger.info("提交任务成功,接口{}", "submitTask");
                    return HttpResponseEntity.seccuss("提交任务成功");
                }
                logger.info("提交任务失败,接口{}", "submitTask");
                return HttpResponseEntity.error("提交任务失败");
            }
            logger.info("提交任务失败,接口{}", "submitTask");
            return HttpResponseEntity.error("提交任务失败");
        } catch (Exception e) {
            logger.info("提交任务失败,接口{}", "submitTask");
            return HttpResponseEntity.error("提交任务失败");
        }
    }

    /**
     * 学生查询专业
     *
     * @return
     */
    @Override
    public HttpResponseEntity selectMajorAll() {
        try {
            MajorEntity majorEntity = new MajorEntity();
            List<MajorEntity> majorEntityList = majorEntityMapper.selectMajorAll(majorEntity);
            logger.info("查询专业成功,接口{}", "selectMajorAll");
            return HttpResponseEntity.seccuss(majorEntityList);
        } catch (Exception e) {
            logger.info("查询专业失败,接口{}", "selectMajorAll");
            return HttpResponseEntity.error("查询专业失败");
        }
    }

    /**
     * pc端查询全部学生与搜索
     *
     * @param map
     * @return
     */
    @Override
    public HttpResponseEntity selectStudentAll(Map<String, String> map) {

        try {
            StudentEntity studentEntity = new StudentEntity();
            if (!StringUtils.isEmpty(map.get("majorId"))) {
                studentEntity.setMajorId(map.get("majorId"));
            }
            if (!StringUtils.isEmpty(map.get("studentGrade"))) {
                studentEntity.setStudentGrade(Integer.parseInt(map.get("studentGrade")));
            }
            if (!StringUtils.isEmpty(map.get("studentPhoneNumber"))) {
                studentEntity.setStudentPhoneNumber(map.get("studentPhoneNumber"));
            }
            if (!StringUtils.isEmpty(map.get("studentName"))) {
                studentEntity.setStudentName(map.get("studentName"));
            }
            if (!StringUtils.isEmpty(map.get("studentNo"))) {
                studentEntity.setStudentNo(map.get("studentNo"));
            }
            if (!StringUtils.isEmpty(map.get("startSchool"))) {
                studentEntity.setStartSchool(map.get("startSchool"));
            }

            //获取页数
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            //获取条数
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            //判断规格
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            //分页插件
            PageHelper.startPage(pageNum, pageSize);

            List<StudentEntity> list = studentEntityMapper.selectStudentAll(studentEntity);

            PageInfo<StudentEntity> pageInfo = new PageInfo<>(list);
            logger.info("查询学生成功,接口{}", "selectStudentAll");
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            logger.info("查询学生失败,接口{}", "selectStudentAll");
            return HttpResponseEntity.error("查询学生失败");
        }
    }

    /**
     * 删除学生所有相关信息
     * by 王传营 2020-01-14
     *
     * @param id
     * @return
     */
    @Override
    public HttpResponseEntity deleteStudent(String id) {
        try{
//            List<StudentEntity> studentEntities = studentEntityMapper.selectAllId(id);
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setStudentId(id);
//            for (int i = 0; i<studentEntities.size(); i++) {
//                studentEntities.get(i).setStudentId(id);
                studentEntityMapper.deleteStudent(studentEntity);
//            }
//            for (int i = 0; i<studentEntities.size(); i++) {
//                studentEntities.get(i).setStudentId(id);
//                studentEntityMapper.deleteStudent(studentEntities.get(i));
//            }
            return HttpResponseEntity.seccuss("删除学生成功");
        }catch (Exception e){
            return HttpResponseEntity.error("删除学生失败");
        }
    }

    /**
     * pc端修改学生查看信息
     *
     * @param studentEntity
     * @return
     */
    public HttpResponseEntity selectStudentChange(StudentEntity studentEntity) {
        try {
            if (!StringUtils.isEmpty(studentEntity.getStudentId())) {

                String studentId = studentEntity.getStudentId();

                StudentEntity studentEntity1 = studentEntityMapper.selectByPrimaryKey(studentId);
                List<StudentChangeEntity> studentChangeEntities = studentChangeEntityMapper.selectStudentChange(studentId);

                if (studentChangeEntities.size() == 0) {
                    //学生记录没有变更过
                    Map<String, Object> map = new HashedMap();
                    map.put("Student", studentEntity1);
                    map.put("StudentChange", null);
                    logger.info("查询学生变化记录成功,接口{}", "selectStudentChange");
                    return HttpResponseEntity.seccuss(map);
                }

                Map<String, Object> map = new HashedMap();
                map.put("Student", studentEntity1);                      //学生变更记录最新一条
                map.put("StudentChange", studentChangeEntities.get(0));
                logger.info("查询学生变化记录成功,接口{}", "selectStudentChange");
                return HttpResponseEntity.seccuss(map);

            }


            logger.info("查询学生变化记录失败,接口{}", "selectStudentChange");
            return HttpResponseEntity.error("查询学生变化记录失败");
        } catch (Exception e) {
            logger.info("查询学生变化记录失败,接口{}", "selectStudentChange");
            return HttpResponseEntity.error("查询学生变化记录失败");
        }
    }

    /**
     * pc端修改学生信息接口
     *
     * @param map
     * @return
     */
    @Override
    @Transactional
    public HttpResponseEntity updateStudent(Map<String, String> map) {
        try {
            StudentEntity studentEntity = new StudentEntity();
            StudentChangeEntity studentChangeEntity = new StudentChangeEntity();
            if (!StringUtils.isEmpty(map.get("studentId")) && !StringUtils.isEmpty(map.get("userId"))) {
                String studentId = map.get("studentId");
                StudentEntity studentEntity1 = studentEntityMapper.selectByPrimaryKey(studentId);     //学生个人信息查询
                studentEntity.setStudentId(map.get("studentId"));
                studentEntity.setStudentNo(map.get("studentNo"));
                studentEntity.setMajorId(map.get("majorId"));
                studentEntity.setStudentGrade(Integer.parseInt(map.get("studentGrade")));
                Date date = new Date();
                studentEntity.setModifyTime(date);
                studentChangeEntity.setStudentChangeId(UUIDUtil.getOneUUID());                        //学生改变记录赋值
                studentChangeEntity.setStatus(1);
                studentChangeEntity.setCreateTime(date);
                studentChangeEntity.setModifyTime(date);
                studentChangeEntity.setStudentId(map.get("studentId"));
                studentChangeEntity.setStudentChangeDescribe(map.get("studentChangeDescribe"));
                studentChangeEntity.setUserId(Integer.parseInt(map.get("userId")));
                if (studentEntity1.getStudentGrade() != Integer.parseInt(map.get("studentGrade"))) {    //学年不等状态改变,相等状态不变,
                    studentEntity.setRetardation(1);
                } else {                                                                                //学年相等直接修改
                    return updateStudentFunction(studentEntity, studentChangeEntity);         //修改
                }
                return updateStudentVerification(studentEntity, studentChangeEntity);       //进入修改验证方法
            }
            logger.info("修改学生信息失败,接口{}", "updateStudent");
            return HttpResponseEntity.error("修改学生信息失败");
        } catch (Exception e) {
            logger.info("修改学生信息失败,接口{}", "updateStudent");
            return HttpResponseEntity.error("修改学生信息失败");
        }
    }

    /**
     * pc端学生成长轨迹
     *
     * @param studentEntity
     * @return
     */
    @Override
    public HttpResponseEntity selectStudentGrow(StudentEntity studentEntity) {
        try {
            if (!StringUtils.isEmpty(studentEntity.getStudentId())) {
                String studentId = studentEntity.getStudentId();
                MarkEntity markEntity = new MarkEntity();
                markEntity.setStudentId(studentId);
                List<MarkEntity> markEntityList = markEntityMapper.markStudentSeestudentId(markEntity);  //获取学生下的所有测评
                int i = markEntityList.size();
                List list = new ArrayList();
                if (i > 0) {
                    for (int z = i; z > 0; z--) {
                        String gradeEvaluationId = markEntityList.get(z - 1).getGradeEvaluationId();
                        GradeEvaluationEntity gradeEvaluationEntity = gradeEvaluationEntityMapper.selectByPrimaryKey(gradeEvaluationId);
                        String markId = markEntityList.get(z - 1).getMarkId();           //获取每条数据的markId
                        if (markEntityList.get(z - 1).getDimensionId() != null) {            //推荐维度不为空

                            DimensionEntity dimensionEntity = dimensionEntityMapper.selectByPrimaryKey(markEntityList.get(z - 1).getDimensionId());  //查询对应维度名
                            if (dimensionEntity != null) {
                                if (dimensionEntity.getDimensionName() != null) {
                                    markEntityList.get(z - 1).setDimensionName(dimensionEntity.getDimensionName());
                                }
                            }
                        }

                        List<TaskRecordEntity> taskRecordEntityList = taskRecordEntityMapper.selectTaskState(markId);  //获取唯一一条任务记录

                        if (taskRecordEntityList.size() == 0) {
                            Map<String, Object> map = new HashedMap();
                            map.put("MarkEntity", markEntityList.get(z - 1));                //测评答了任务没选
                            map.put("GradeEvaluationEntity", gradeEvaluationEntity);
                            map.put("TaskRecordEntity", taskRecordEntityList);
                            list.add(map);
                        } else {
                            TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
                            taskRecordEntity = taskRecordEntityList.get(0);
                            String taskId = taskRecordEntity.getTaskId();                  //获取本次测评的任务id
                            String taskRecordId = taskRecordEntity.getTaskRecordId();       //获取本次唯一任务记录的记录表id
                            TaskEntity taskEntity = taskEntityMapper.selectPcTaskCount(taskId);
                            List<StageEntity> stageEntityList = stageRecordEntityMapper.selectStageRecordAll(taskRecordId);  //获取本次任务的所有提交过的阶段内容

                            stageEntityList.forEach(stageEntity -> stageEntity.getStageRecord().forEach(stageRecordEntity -> stageRecordEntity.setStageRecordImgPath(remoteProperties.getHttpUrl() + stageRecordEntity.getStageRecordImgPath())));  //添加服务器路径
                            DimensionEntity dimensionEntity = dimensionEntityMapper.selectByPrimaryKey(markEntityList.get(z - 1).getDimensionId());
                            taskEntity.setDimensionName(dimensionEntity.getDimensionName());    //给对应的任务添加维度名称
                            Map<String, Object> map = new HashedMap();
                            map.put("MarkEntity", markEntityList.get(z - 1));
                            map.put("TaskEntity", taskEntity);
                            map.put("StageEntity", stageEntityList);
                            map.put("GradeEvaluationEntity", gradeEvaluationEntity);
                            map.put("TaskRecordEntity", taskRecordEntityList);
                            list.add(map);
                        }
                    }
                } else {
                    logger.info("查询学生成长轨迹成功,接口{}", "selectStudentGrow");
                    return HttpResponseEntity.seccuss("该学生无记录");
                }
                logger.info("查询学生成长轨迹成功,接口{}", "selectStudentGrow");
                return HttpResponseEntity.seccuss(list);
            }
            logger.info("查询学生成长轨迹失败,接口{}", "selectStudentGrow");
            return HttpResponseEntity.error("查询学生成长轨迹失败");
        } catch (Exception e) {
            logger.info("查询学生成长轨迹失败,接口{}", "selectStudentGrow");
            return HttpResponseEntity.error("查询学生成长轨迹失败");
        }

    }


    /**
     * pc端修改学生时的验证
     *
     * @param studentEntity
     * @return
     */
    public HttpResponseEntity updateStudentVerification(StudentEntity studentEntity, StudentChangeEntity studentChangeEntity) {
        try {
            List<MarkEntity> list = markEntityMapper.selectStudentMark(studentEntity.getStudentId());
            if (list.size() == 0) {
                return updateStudentFunction(studentEntity, studentChangeEntity);    //可修改
            }
            MarkEntity markEntity = new MarkEntity();
            markEntity = list.get(0);
            if (markEntity.getDimensionId() == null) {
//                return HttpResponseEntity.seccuss(Const.PublicCode.NonModifiable.getCode());       //  返回77777   答了测评题 教师没有点评  不可修改
                return updateStudentFunction(studentEntity, studentChangeEntity);//不点评也可修改
            }
            String markid = markEntity.getMarkId();
            List<TaskRecordEntity> listTaskRecordEntity = taskRecordEntityMapper.selectTaskState(markid);
            if (listTaskRecordEntity.size() == 0) {
                return updateStudentFunction(studentEntity, studentChangeEntity);   //   先查看测评内容  没有接受任务  可修改
            }
            TaskRecordEntity taskRecordEntity = new TaskRecordEntity();
            taskRecordEntity = listTaskRecordEntity.get(0);
            if (taskRecordEntity.getTaskRecordStatus() == 0) {
                return HttpResponseEntity.seccuss(Const.PublicCode.NonModifiable.getCode());      // 返回77777  提示  任务未点评信息     不可修改
            }
            if (taskRecordEntity.getTaskRecordStatus() == 1 || taskRecordEntity.getTaskRecordStatus() == 3) {
                return HttpResponseEntity.seccuss(Const.PublicCode.NonModifiable.getCode());      // 返回77777     已接受  已点评   不可修改
            }
            if (taskRecordEntity.getTaskRecordStatus() == -1 || taskRecordEntity.getTaskRecordStatus() == 2) {    //已超时或已结束后  可修改
                return updateStudentFunction(studentEntity, studentChangeEntity);
            }
            logger.info("查询学生成长轨迹失败,接口{}", "selectStudentGrow");
            return HttpResponseEntity.error("查询学生成长轨迹失败");
        } catch (Exception e) {
            logger.info("查询学生成长轨迹失败,接口{}", "selectStudentGrow");
            return HttpResponseEntity.error("查询学生成长轨迹失败");
        }
    }

    /**
     * pc端修改学生方法
     *
     * @param studentEntity
     * @return
     */
    @Transactional
    public HttpResponseEntity updateStudentFunction(StudentEntity studentEntity, StudentChangeEntity studentChangeEntity) {
        try {
            int i = studentEntityMapper.updateByPrimaryKeySelective(studentEntity);
            int z = studentChangeEntityMapper.insertSelective(studentChangeEntity);
            if (i > 0 && z > 0) {
                logger.info("修改学生信息成功,接口{}", "updateStudent");
                return HttpResponseEntity.seccuss("修改学生信息成功");
            }
            logger.info("修改学生信息失败,接口{}", "updateStudent");
            return HttpResponseEntity.error("修改学生信息失败");
        } catch (Exception e) {
            logger.info("修改学生信息失败,接口{}", "updateStudent");
            return HttpResponseEntity.error("修改学生信息失败");
        }
    }
}
