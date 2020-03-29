package com.zhxx.service.szxt.utils;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * 文件操作工具类
 * Created by maru on 2018/01/04 下午3:07.
 */
public class FileOperate {

    /**
     * 图片上传
     *
     * @param file   图片文件
     * @param parent 图片存储地址
     * @return 文件名
     */
    public static String imageUpload1(MultipartFile file, String parent) throws IOException {

        String imgName;
//        List<String> imageList = new ArrayList<String>();
        String fileName = file.getOriginalFilename();//获取上传文件名
        String showFileName = fileName;
        System.out.println("文件名：" + fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String name = UUID.randomUUID().toString();
        fileName = name + suffixName;
        File targetFile = new File(parent, fileName);
        //将上传文件存储到服务器中
        file.transferTo(targetFile);
        //图片压缩处理方法
        imgUtil.imageResize(targetFile,targetFile,200,200,0.7f);
        //创建文件夹
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        //背景图片地址
        imgName = targetFile.getName();
        //将地址添加到集合中
        //imageList.add(url);
        System.out.println("图片名称为:" + imgName);
//            accessory.setAccessoryName(showFileName);
//            accessory.setAccessoryAddress(imgName);
        return imgName;
    }

    /**
     * 图片上传
     *
     * @param file   图片文件
     * @param parent 图片存储地址
     * @return 文件名
     */
    public static String imageUpload(MultipartFile file, String parent) throws IOException {

        String imgName;
//        List<String> imageList = new ArrayList<String>();
        String fileName = file.getOriginalFilename();//获取上传文件名
        String showFileName = fileName;
        System.out.println("文件名：" + fileName);
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String name = UUID.randomUUID().toString();
        fileName = name + suffixName;
        File targetFile = new File(parent, fileName);
         //创建文件夹
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }
        //将上传文件存储到服务器中
        file.transferTo(targetFile);
        //背景图片地址
        imgName = targetFile.getName();
        //将地址添加到集合中
        //imageList.add(url);
        System.out.println("图片名称为:" + imgName);
//            accessory.setAccessoryName(showFileName);
//            accessory.setAccessoryAddress(imgName);
        return imgName;
    }

    /**
     * 图片移动
     *
     * @param srcFileName  旧图片文件名
     * @param destFilePath 目标路径
     * @param parent       旧图片路径
     * @return 自定义状态码
     */
    public int copyFile(String srcFileName, String destFilePath, String parent) throws Exception {
        int i;
        //判断原文件是否存在
        File srcFile = new File(parent, srcFileName);
        File target = new File(destFilePath);
        if (!srcFile.exists()) {
            if (!target.exists()) {
                //文件不存在
                i = -1;
            } else {
                i = 1;
            }
        } else if (!srcFile.isFile()) {
            //原文件不是一个文件
            i = -2;
        } else {
            if (!target.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建目录
//                System.out.println("目标文件所在的目录不存在，准备创建它！");
                target.getParentFile().mkdirs();
                if (!target.getParentFile().exists()) {
                    //创建文件目录失败
                    i = 0;
                } else {
                    srcFile.renameTo(target);
                    i = 1;
                }
            } else {
                //如果原文件存在，覆盖它
                srcFile.renameTo(target);
                i = 1;
            }
        }
        return i;
    }

    /**
     * 文件删除
     *
     * @param dir 要删除的文件
     * @return 删除状态
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * base64文件保存
     *
     * @param base64String base64码
     * @param toImagePath  存储路径
     * @param imageType    文件类型
     * @return 返回文件名   没有为null
     */
    public static String base64StringToImage(String base64String, String toImagePath, String imageType) {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] bytes1 = decoder.decodeBuffer(base64String);

            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            RenderedImage bi1 = ImageIO.read(bais);
            String imgName = UUIDUtil.getOneUUID() + "." + imageType;
            File w2 = new File(toImagePath + imgName);// 可以是jpg,png,gif格式
            if (!w2.exists()) {
                w2.createNewFile();
                System.out.println("no exist=====");
            }
            System.out.println("pass...........");
            if (ImageIO.write(bi1, imageType, w2)) {
                return imgName;
            }
            return null;// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
