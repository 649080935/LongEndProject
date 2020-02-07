package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.pojo.vo.ModifyUserLinkUserVO;

import java.util.List;
import java.util.Map;

public interface UserService {

	boolean checkUserCodeForRegistry(String userCode) throws Exception;

	boolean registryUser(User user) throws Exception;

	boolean activateUser(String userCode, String activeCode) throws Exception;

	User getUserForLogin(String userCode, String userPassword) throws Exception;

	User getUserByUserCode(String userCode) throws Exception;

	List<UserLinkUser> getLinkUserListByLogin(String userCode) throws Exception;

	Integer updateUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	Integer addUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	void deleteUserLinkUserByIds(Long id) throws Exception;

}
