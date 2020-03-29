package com.zhxx.admin.server.service;

import com.zhxx.admin.server.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
