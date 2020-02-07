package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.UserDao;
import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ltrip.service.UserService;
import cn.ekgc.ltrip.util.ActiveCodeUtil;
import cn.ekgc.ltrip.util.ConstantUtil;
import cn.ekgc.ltrip.util.communication.email.EmailUtil;
import cn.ekgc.ltrip.util.communication.sms.SMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private EmailUtil emailUtil;
	@Autowired
	private SMSUtil smsUtil;
	@Autowired
	private StringRedisTemplate redisTemplate;

	public boolean checkUserCodeForRegistry(String userCode) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		List<User> userList = userDao.findUserListByQuery(queryMap);

		if (userList != null && userList.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

	public boolean registryUser(User user) throws Exception {
		try {
			// 默认状态未激活：0
			user.setActivated(0);
			// 将数据保存到数据库
			userDao.saveUser(user);
			// 产生激活码
			String activeCode = ActiveCodeUtil.createActiveCode();
			if (activeCode.matches(ConstantUtil.REGEX_EMAIL)) {
				emailUtil.sendEmail(user.getUserCode(), activeCode);
			} else {
				smsUtil.sendSMS(user.getUserCode(), activeCode);
			}

			// 将激活码保存到redis中
			redisTemplate.opsForValue().set(user.getUserCode(), activeCode);
			// 设置redis过期时间
			redisTemplate.expire(user.getUserCode(), ConstantUtil.ACTIVE_CODE_TIMEOUT * 60, TimeUnit.SECONDS);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean activateUser(String userCode, String activeCode) throws Exception {
		try {
			String registryCode = redisTemplate.opsForValue().get(userCode);
			if (activeCode.equals(registryCode)) {
				User user = new User();

				user.setUserCode(userCode);
				user.setActivated(1);

				userDao.updateUser(user);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public User getUserForLogin(String userCode, String userPassword) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		queryMap.put("userPassword", userPassword);
		// 进行查询
		List<User> userList = userDao.findUserListByQuery(queryMap);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	public User getUserByUserCode(String userCode) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);
		// 进行查询
		List<User> userList = userDao.findUserListByQuery(queryMap);
		System.out.println(userList);
		if (userList != null && userList.size() > 0) {
			return userList.get(0);
		}
		return null;
	}

	public List<UserLinkUser> getLinkUserListByLogin(String userCode) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("userCode", userCode);

		List<UserLinkUser> linkUserList = userDao.findLinkUserListByQuery(queryMap);
		return linkUserList;
	}

	public Integer updateUserLinkUser(UserLinkUser userLinkUser) throws Exception {
		userLinkUser.setModifyDate(new Date(System.currentTimeMillis()));
		return userDao.updateUserLinkUser(userLinkUser);
	}

	public Integer addUserLinkUser(UserLinkUser userLinkUser) throws Exception {
		userLinkUser.setCreationDate(new Date(System.currentTimeMillis()));
		return userDao.saveUserLinkUser(userLinkUser);
	}

	public void deleteUserLinkUserByIds(Long id) throws Exception {
		userDao.deleteUserLinkUserByIds(id);
	}

}
