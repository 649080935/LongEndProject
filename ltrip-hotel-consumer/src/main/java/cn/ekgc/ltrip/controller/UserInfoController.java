package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.pojo.vo.AddUserLinkUserVO;
import cn.ekgc.ltrip.pojo.vo.ModifyUserLinkUserVO;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.ltrip.user.transport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import java.util.*;

@RestController("userInfoController")
@RequestMapping("/biz/api/userinfo")
public class UserInfoController extends BaseController {
	@Autowired
	private UserTransport userTransport;

	@RequestMapping(value = "/queryuserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> queryUserLinkUser(@RequestBody ValidateRoomStoreVO vo) throws Exception {
		// 获得所有的Cookie
		Cookie[] cookies = request.getCookies();
		String userCode = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}

		// 根据当前登录用户信息获得常用联系人表
		List<UserLinkUser> userLinkUserList = userTransport.getLinkUserListByLogin(userCode);
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, userLinkUserList);
	}

	@RequestMapping(value = "/modifyuserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> modifyUserLinkUser(@RequestBody ModifyUserLinkUserVO modifyUserLinkUserVO) throws Exception {
		User currentUser = new User();
		if(null != currentUser && null != modifyUserLinkUserVO){
			UserLinkUser userLinkUser = new UserLinkUser();
			userLinkUser.setId(modifyUserLinkUserVO.getId());
			userLinkUser.setLinkUserName(modifyUserLinkUserVO.getLinkUserName());
			userLinkUser.setLinkIdCardType(modifyUserLinkUserVO.getLinkIdCardType());
			userLinkUser.setLinkIdCard(modifyUserLinkUserVO.getLinkIdCard());
			userLinkUser.setUserId(Long.parseLong("29"));
			userLinkUser.setLinkPhone(modifyUserLinkUserVO.getLinkPhone());
			userLinkUser.setModifiedBy(Long.parseLong("29"));
			try {
				userTransport.updateUserLinkUser(userLinkUser);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "修改常用联系人失败");
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "修改常用联系人成功");
		}else if(null != currentUser && null == modifyUserLinkUserVO){
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "不能提交空，请填写常用联系人信息");
		}else{
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "token失效，请重新登录");
		}
	}

	@RequestMapping(value = "/adduserlinkuser", method = RequestMethod.POST)
	public ResponseResult<Object> addUserLinkUser(@RequestBody AddUserLinkUserVO addUserLinkUserVO) throws Exception {
		User currentUser = new User();
		currentUser.setActivated(1);
		if(null != currentUser && null != addUserLinkUserVO){
			UserLinkUser userLinkUser = new UserLinkUser();
			userLinkUser.setLinkUserName(addUserLinkUserVO.getLinkUserName());
			userLinkUser.setLinkIdCardType(addUserLinkUserVO.getLinkIdCardType());
			userLinkUser.setLinkIdCard(addUserLinkUserVO.getLinkIdCard());
			userLinkUser.setUserId(addUserLinkUserVO.getUserId());
			userLinkUser.setLinkPhone(addUserLinkUserVO.getLinkPhone());
			userLinkUser.setCreatedBy(addUserLinkUserVO.getUserId());
			userLinkUser.setCreationDate(new Date(System.currentTimeMillis()));
			try {
				userTransport.addUserLinkUser(userLinkUser);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "新增常用联系人失败");
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "新增常用联系人成功");
		}else if(null != currentUser && null == addUserLinkUserVO){
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "不能提交空，请填写常用联系人信息");
		}else{
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "token失效，请重新登录");
		}
	}

	@RequestMapping(value = "/deluserlinkuser/{ids}", method = RequestMethod.GET)
	public ResponseResult<Object> deluserlinkuser(@RequestParam Long ids) throws Exception {
		User user = new User();
		user.setId(Long.parseLong(request.getParameter("ids")));
		if (ids != null && !"".equals(user.getId())) {
			try {
				userTransport. deleteUserLinkUserByIds(ids);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "删除常用联系人失败");
			}
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "删除常用联系人成功");
		}else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "联系人为空，删除失败");
		}
	}

}
