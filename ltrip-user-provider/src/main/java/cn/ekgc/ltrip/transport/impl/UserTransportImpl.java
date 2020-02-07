package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.service.UserService;
import cn.ekgc.ltrip.user.transport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("userTransport")
@RequestMapping("/user")
public class UserTransportImpl implements UserTransport {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/checkUserCodeForRegistry", method = RequestMethod.POST)
	public boolean checkUserCodeForRegistry(@RequestParam String userCode) throws Exception {
		return userService.checkUserCodeForRegistry(userCode);
	}

	@RequestMapping(value = "/registryUser", method = RequestMethod.POST)
	public boolean registryUser(@RequestBody User user) throws Exception {
		return userService.registryUser(user);
	}

	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	public boolean activateUser(@RequestParam String userCode, @RequestParam String activeCode) throws Exception {
		return userService.activateUser(userCode, activeCode);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public User getUserForLogin(@RequestParam String userCode, @RequestParam String userPassword) throws Exception {
		return userService.getUserForLogin(userCode, userPassword);
	}

	@RequestMapping(value = "/queryUserByUserCode", method = RequestMethod.POST)
	public User getUserByUserCode(@RequestParam String userCode) throws Exception {
		return userService.getUserByUserCode(userCode);
	}

	@RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
	public List<UserLinkUser> getLinkUserListByLogin(@RequestParam String userCode) throws Exception {
		return userService.getLinkUserListByLogin(userCode);
	}

	@RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
	public Integer updateUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userService.updateUserLinkUser(userLinkUser);
	}

	@RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
	public Integer addUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception {
		return userService.addUserLinkUser(userLinkUser);
	}

	@RequestMapping(value = "/deluserlinkuser", method = RequestMethod.POST)
	public void deleteUserLinkUserByIds(@RequestParam Long id) throws Exception {
		userService.deleteUserLinkUserByIds(id);
	}

}
