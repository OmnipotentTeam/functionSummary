package com.zhxx.service.szxt.controller;


import com.zhxx.service.szxt.beans.Const;
import com.zhxx.service.szxt.beans.HttpResponseEntity;
import com.zhxx.service.szxt.config.properties.RemoteProperties;
import com.zhxx.service.szxt.service.StudentService;
import com.zhxx.service.szxt.utils.FileOperate;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: website
 * @author: 王新春
 * @create: 2018-05-03 20:43
 * @description:
 **/
@RestController
@RequestMapping("/util")
public class UtilController {
    private final Logger logger = LoggerFactory.getLogger(StudentService.class);
    //    public static final String imgpath = "/mnt/project/UFLY1/";
    @Autowired
    private RemoteProperties remoteProperties;

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponseEntity saveImg(MultipartFile file) {
        try {
            String imgPath = FileOperate.imageUpload(file, Const.ConstInter.PATH_URL);
            logger.info("上传图片成功，接口{}", "saveFile");
            return HttpResponseEntity.seccuss(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传图片失败，接口{}", "saveFile");
            return HttpResponseEntity.error("保存文件失败");
        }
    }

    @RequestMapping(value = "/saveFileImg", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponseEntity saveImgs(MultipartFile file) {
        try {
            String imgPath = FileOperate.imageUpload1(file, Const.ConstInter.PATH_URL);
            logger.info("上传图片成功，接口{}", "saveFile");
            return HttpResponseEntity.seccuss(imgPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("上传图片失败，接口{}", "saveFile");
            return HttpResponseEntity.error("保存文件失败");
        }
    }

    @PostMapping(value = "/deleteFile")
    @ResponseBody
    public HttpResponseEntity deleteFile(@RequestBody Map<String, Object> map) {
        boolean flag = FileOperate.deleteDir(new File(Const.ConstInter.PATH_URL + map.get("imgName")));
        if (flag) {
            logger.info("删除文件成功，接口{}", "deleteFile");
            return HttpResponseEntity.seccuss("删除文件成功");
        } else {
            logger.error("删除文件失败，接口{}", "deleteFile");
            return HttpResponseEntity.error("删除文件失败");
        }
    }

    @RequestMapping(value = "/saveFiles", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponseEntity saveImgs(MultipartFile file[]) {
        try {
            List<String> filesPath = new ArrayList<String>();
            for (int i = 0; i < file.length; i++) {
                filesPath.add(FileOperate.imageUpload(file[i], Const.ConstInter.PATH_URL));
            }
            logger.info("保存文件成功，接口{}", "saveFiles");
            return HttpResponseEntity.seccuss(filesPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("保存文件失败，接口{}", "saveFiles");
            return HttpResponseEntity.error("保存文件失败");
        }
    }

    @RequestMapping(value = "/saveFilesHasName", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponseEntity saveFilesHasName(MultipartFile file[]) {
        try {
            List<Map> filesPath = new ArrayList<>();
            for (int i = 0; i < file.length; i++) {
                String fileName = file[i].getOriginalFilename();//获取上传文件名
                Map map = new HashMap();
                map.put("accessoryName", fileName);
                map.put("accessoryAddress", FileOperate.imageUpload(file[i], Const.ConstInter.PATH_URL));
                filesPath.add(map);
            }
            logger.info("保存文件成功，接口{}", "saveFilesHasName");
            return HttpResponseEntity.seccuss(filesPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("保存文件失败，接口{}", "saveFilesHasName");
            return HttpResponseEntity.error("保存文件失败");
        }
    }

    /**
     * base64文件保存
     *
     * @return 文件名
     */
    @RequestMapping(value = "/saveFilesForBase64", method = RequestMethod.POST)
    @ResponseBody
    public HttpResponseEntity saveFilesForBase64(@RequestBody Map<String, String> map) {
//        @RequestParam("imgBase64") String imgBase64,@RequestParam("imgType") String imgtype
        String imgName = FileOperate.base64StringToImage(map.get("imgBase64"), Const.ConstInter.PATH_URL, map.get("imgType"));
        HttpResponseEntity serverResponse = null;
        if (imgName == null) {
            logger.error("保存文件失败，接口{}", "saveFilesForBase64");
            serverResponse = HttpResponseEntity.error("保存文件失败");
        } else {
            map = new HashedMap();
            map.put("imgName", imgName);
            serverResponse = HttpResponseEntity.seccuss(map);
            logger.info("保存文件成功，接口{}", "saveFilesForBase64");
        }
        return serverResponse;
    }

}
