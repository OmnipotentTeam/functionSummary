package com.zhxx.service.szxt.config;

import com.zhxx.service.szxt.mapper.StudentEntityMapper;
import com.zhxx.service.szxt.mapper.TaskRecordEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ScheduledTask
 * @Auther: 王新春
 * @Date: 2018/12/12 09:46
 * @Description:
 */
@Component
public class ScheduledTask {
    private final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    private StudentEntityMapper studentEntityMapper;
    @Autowired
    private TaskRecordEntityMapper taskRecordEntityMapper;

    /**
     * 说明： 每天23点执行，检测接受任务状态
     *
     * @author： 王新春
     * @date： 2018/12/12 10:02
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void scheduled() {
        logger.info("=====>>>>>执行任务过期检验", System.currentTimeMillis());
        taskRecordEntityMapper.updateTaskRecordStatus();
    }

    /**
     * 说明： 每年的固定时间进行转换，增加学生的年级
     *
     * @author： 王新春
     * @date： 2018/12/12 10:32
     */
    @Scheduled(cron = "0 28 10 1 8 ?")
    public void scheduled1() {
        logger.info("=====>>>>>执行年级转换", System.currentTimeMillis());
        taskRecordEntityMapper.updateEndTaskRecordStatus();   //修改所有学生的任务状态   没完成的所有任务会变成超时
        studentEntityMapper.updateStudentGrade();             //学生升学年
    }
    @Scheduled(cron = "0 28 10 1 2 ?")
    public void scheduled2() {
        logger.info("=====>>>>>执行学期转换", System.currentTimeMillis());
        taskRecordEntityMapper.updateEndTaskRecordStatus();   //修改所有学生的任务状态   没完成的所有任务会变成超时
        studentEntityMapper.updateStudentRetardation();             //学生学期重置
    }
}
