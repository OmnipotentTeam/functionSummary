package com.zhxx.service.szxt.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.admin.server.utils.UserUtil;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.MajorEntity;
import com.zhxx.service.szxt.entity.ProblemEntity;
import com.zhxx.service.szxt.mapper.MajorEntityMapper;
import com.zhxx.service.szxt.service.MajorService;
import com.zhxx.service.szxt.utils.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by huasheng on 2018/12/10.
 */
@Service
public class MajorServiceImpl implements MajorService {
    private final Logger logger = LoggerFactory.getLogger(MajorService.class);
    @Autowired
    private MajorEntityMapper majorEntityMapper;


    /**
     * pc端查询全部专业与搜索专业
     *
     * @param map
     * @return
     */
    public HttpResponseEntity selectMajorAll(Map<String, String> map) {
        try {
            MajorEntity majorEntity = new MajorEntity();
            if (!StringUtils.isEmpty(map.get("pageNum")) && !StringUtils.isEmpty(map.get("pageSize"))) {

                if (!StringUtils.isEmpty(map.get("majorName"))) {
                    majorEntity.setMajorName(map.get("majorName"));
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

                List<MajorEntity> list = majorEntityMapper.selectMajorAll(majorEntity);

                PageInfo<MajorEntity> pageInfo = new PageInfo<>(list);
                logger.info("题库查询成功，接口{}", "selectMajorAll");
                return HttpResponseEntity.seccuss(pageInfo);
            }
            logger.info("专业查询失败，接口{}", "selectMajorAll");
            return HttpResponseEntity.error("请求失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("专业查询失败，接口{}", "selectMajorAll");
            return HttpResponseEntity.error("专业查询失败");
        }


    }

    /**
     * 添加专业
     *
     * @param majorEntity
     * @return
     */
    public HttpResponseEntity insertMajor(MajorEntity majorEntity) {
        try {
            if (!StringUtils.isEmpty(majorEntity.getMajorName())) {

                majorEntity.setMajorId(UUIDUtil.getOneUUID());
                majorEntity.setStatus(1);
                Date date = new Date();
                majorEntity.setCreateTime(date);
                majorEntity.setModifyTime(date);
                int i = majorEntityMapper.insert(majorEntity);
                if (i > 0) {
                    logger.info("专业查询成功，接口{}", "insertMajor");
                    return HttpResponseEntity.seccuss("专业查询成功");
                }
                logger.info("专业查询失败，接口{}", "insertMajor");
                return HttpResponseEntity.error("专业查询失败");
            }
            logger.info("专业查询失败，接口{}", "insertMajor");
            return HttpResponseEntity.error("专业查询失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("专业查询失败，接口{}", "insertMajor");
            return HttpResponseEntity.error("专业查询失败");
        }
    }

    /**
     * 修改专业
     *
     * @param majorEntity
     * @return
     */
    public HttpResponseEntity updateMajor(MajorEntity majorEntity) {
        try {
            if (!StringUtils.isEmpty(majorEntity.getMajorId()) && !StringUtils.isEmpty(majorEntity.getMajorName())) {
                Date date = new Date();
                majorEntity.setModifyTime(date);
                int i = majorEntityMapper.updateMajor(majorEntity);
                if (i > 0) {
                    logger.info("专业修改成功，接口{}", "updateMajor");
                    return HttpResponseEntity.seccuss("专业修改成功");
                }
                logger.info("专业修改失败，接口{}", "updateMajor");
                return HttpResponseEntity.error("专业修改失败");
            }
            logger.info("专业修改失败，接口{}", "updateMajor");
            return HttpResponseEntity.error("专业修改失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("专业修改失败，接口{}", "updateMajor");
            return HttpResponseEntity.error("专业修改失败");
        }
    }

    /**
     * 删除专业
     *
     * @param majorEntity
     * @return
     */
    public HttpResponseEntity deleteMajor(MajorEntity majorEntity) {
        try {
            if (!StringUtils.isEmpty(majorEntity.getMajorId())) {
                Date date = new Date();
                majorEntity.setModifyTime(date);
                majorEntity.setStatus(0);
                int i = majorEntityMapper.updateMajor(majorEntity);
                if (i > 0) {
                    logger.info("专业删除成功，接口{}", "deleteMajor");
                    return HttpResponseEntity.seccuss("专业删除成功");
                }
                logger.info("专业删除失败，接口{}", "deleteMajor");
                return HttpResponseEntity.error("专业删除失败");
            }
            logger.info("专业删除失败，接口{}", "deleteMajor");
            return HttpResponseEntity.error("专业删除失败");
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("专业删除失败，接口{}", "deleteMajor");
            return HttpResponseEntity.error("专业删除失败");
        }
    }

    public HttpResponseEntity selectMajor() {
        try {
            List<MajorEntity> list = majorEntityMapper.selectMajor();
            logger.info("试题查询成功，接口{}", "selectMajor");
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("试题查询失败，接口{}", "selectMajor");
            return HttpResponseEntity.error("请求失败");
        }
    }
}
