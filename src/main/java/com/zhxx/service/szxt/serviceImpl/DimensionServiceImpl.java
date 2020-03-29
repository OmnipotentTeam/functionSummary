package com.zhxx.service.szxt.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.entity.DimensionEntity;
import com.zhxx.service.szxt.mapper.DimensionEntityMapper;
import com.zhxx.service.szxt.service.DimensionService;
import com.zhxx.service.szxt.service.MarkService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 维度的增删改查service层
 *
 * @author:高明凯 Created by dell on 2018/12/6.
 */
@Service
public class DimensionServiceImpl implements DimensionService {
    private final Logger logger = LoggerFactory.getLogger(MarkService.class);
    @Autowired
    private DimensionEntityMapper dimensionEntityMapper;
    @Autowired
    private DimensionEntity dimensionEntity;

    public static int equipmentNo = 0;//初始编号

    /**
     * 查询所有维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6 14:30
     */
    public HttpResponseEntity selectDimensionService(Map<String, Object> map) {
        try {
            DimensionEntity dimensionEntity = new DimensionEntity();
            int pageNum = Integer.parseInt(map.get("pageNum").toString());
            int pageSize = Integer.parseInt(map.get("pageSize").toString());
            //判断维度名称是否存在
            if (StringUtils.isEmpty(dimensionEntity.getDimensionName())) {
                dimensionEntity.setDimensionName(map.get("dimensionName").toString());
            }
            pageNum = pageNum == 0 ? 1 : pageNum;
            pageSize = pageSize == 0 ? 10 : pageSize;
            PageHelper.startPage(pageNum, pageSize);
            List<DimensionEntity> list;
            list = dimensionEntityMapper.selectDimension(dimensionEntity);
            PageInfo pageInfo = new PageInfo(list);
            return HttpResponseEntity.seccuss(pageInfo);
        } catch (Exception e) {
            logger.info("预览失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");

        }
    }

    /**
     * 修改维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6 14:30
     */
    public HttpResponseEntity updateDimensionService(DimensionEntity dimensionEntity) {

        try {
            dimensionEntity.setStatus(1);
            int a = dimensionEntityMapper.updateDimension(dimensionEntity);
            if (a > 0) {
                return HttpResponseEntity.seccuss("修改维度成功");
            } else {
                return HttpResponseEntity.seccuss("修改维度失败");
            }
        } catch (Exception e) {
            logger.info("预览失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 删除维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6 14:50
     */
    public HttpResponseEntity deleteDimensionService(DimensionEntity dimensionEntity) {
        try {
            dimensionEntity.setStatus(1);
            int a = dimensionEntityMapper.deleteDimension(dimensionEntity.getDimensionId());
            if (a > 0) {
                return HttpResponseEntity.seccuss("维度删除成功");
            } else {
                return HttpResponseEntity.seccuss("维度删除失败");
            }
        } catch (Exception e) {
            logger.info("预览失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 添加维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/6 15：00
     */

    public HttpResponseEntity insertDimensionService(DimensionEntity dimensionEntity) {
        try {
            dimensionEntity.setDimensionId(getNewEquipmentNo());
            dimensionEntity.setCreateTime(new Date());
            dimensionEntity.setModifyTime(new Date());
            dimensionEntity.setStatus(1);
            int a = dimensionEntityMapper.insertDimension(dimensionEntity);
            if (a > 0) {
                return HttpResponseEntity.seccuss("成功");
            } else {
                return HttpResponseEntity.seccuss("失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("添加维度失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");
        }
    }

    /**
     * 说明：
     *
     * @author： 王新春
     * @return： 生成规则设备编号:设备类型+五位编号（从1开始，不够前补0）
     * @date： 2018/12/17 10:52
     */
    public String getNewEquipmentNo() {
        equipmentNo = equipmentNo + 1;
        DecimalFormat df = new DecimalFormat("000000");
        String str2 = df.format(equipmentNo);
        System.out.println(str2);
        String str = Const.ConstInter.EQUIPMENT_NOSTR + str2;
        DimensionEntity dimensionEntity = dimensionEntityMapper.selectByPrimaryKey(str);
        if (dimensionEntity == null) {
            return str;
        } else {
            return getNewEquipmentNo();
        }
    }


    /**
     * 查询所有维度
     *
     * @param
     * @return
     * @author:高明凯 date:2018/12/11 10：00
     */
    public HttpResponseEntity selectAllDimensionService() {
        try {
            List<DimensionEntity> list = dimensionEntityMapper.selectAllDimension();
            return HttpResponseEntity.seccuss(list);
        } catch (Exception e) {
            logger.info("预览失败，接口{}", "problemAndSolution");
            return HttpResponseEntity.error("请求失败");
        }
    }
}

