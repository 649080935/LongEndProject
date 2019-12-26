package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.User;

public interface UserService {

	boolean checkUserCodeForRegistry(String userCode) throws Exception;

	boolean registryUser(User user) throws Exception;

	boolean activateUser(String userCode, String activeCode) throws Exception;

	User getUserForLogin(String userCode, String userPassword) throws Exception;
}
