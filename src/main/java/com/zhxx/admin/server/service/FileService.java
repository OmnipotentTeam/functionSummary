package com.zhxx.admin.server.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.zhxx.admin.server.model.FileInfo;

public interface FileService {

	FileInfo save(MultipartFile file) throws IOException;

	void delete(String id);

}
