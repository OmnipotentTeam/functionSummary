package com.zhxx.admin.server.service;

import java.util.List;

import com.zhxx.admin.server.model.Mail;

public interface MailService {

	void save(Mail mail, List<String> toUser);
}
