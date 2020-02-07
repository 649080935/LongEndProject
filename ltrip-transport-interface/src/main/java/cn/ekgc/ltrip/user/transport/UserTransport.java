package cn.ekgc.ltrip.user.transport;

import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.pojo.vo.ModifyUserLinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(name = "ltrip-user-provider")
@RequestMapping("/user")
public interface UserTransport {

	@RequestMapping(value = "/checkUserCodeForRegistry", method = RequestMethod.POST)
	boolean checkUserCodeForRegistry(@RequestParam String userCode) throws Exception;

	@RequestMapping(value = "/registryUser", method = RequestMethod.POST)
	boolean registryUser(@RequestBody User user) throws Exception;

	@RequestMapping(value = "/activateUser", method = RequestMethod.POST)
	boolean activateUser(@RequestParam String userCode, @RequestParam String activeCode) throws Exception;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	User getUserForLogin(@RequestParam String userCode, @RequestParam String userPassword) throws Exception;

	@RequestMapping(value = "/queryUserByUserCode", method = RequestMethod.POST)
	User getUserByUserCode(@RequestParam String userCode) throws Exception;

	@RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
	List<UserLinkUser> getLinkUserListByLogin(@RequestParam String userCode) throws Exception;

	@RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
	Integer updateUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception;

	@RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
	Integer addUserLinkUser(@RequestBody UserLinkUser userLinkUser) throws Exception;

	@RequestMapping(value = "/deluserlinkuser", method = RequestMethod.POST)
	void deleteUserLinkUserByIds(@RequestParam Long ids) throws Exception;

}
