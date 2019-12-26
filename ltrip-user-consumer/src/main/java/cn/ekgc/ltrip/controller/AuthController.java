package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.user.transport.UserTransport;
import cn.ekgc.ltrip.util.ConstantUtil;
import cn.ekgc.ltrip.util.JWTUtil;
import cn.ekgc.ltrip.util.MD5Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "用户认证控制，集中完成用户的登陆和注册相关操作")
@RestController("authController")
@RequestMapping("/auth/api")
public class AuthController extends BaseController {

	@Autowired
	private UserTransport userTransport;

	@ApiOperation(value = "校验用户注册所提供的Email地址是否可用")
	@RequestMapping(value = "/ckusr", method = RequestMethod.GET)
	public ResponseResult<User> checkEmailForRegistry(String name) throws Exception {
		if (name != null && !"".equals(name.trim())) {
			if (name.matches(ConstantUtil.REGEX_CELLPHONE) || name.matches(ConstantUtil.REGEX_EMAIL)) {
				if (userTransport.checkUserCodeForRegistry(name)) {
					return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
				} else {
					return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "该电子邮件地址或手机号码已被注册");
				}
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请正确填写电子邮件地址或手机号码");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写电子邮件地址或手机号码");
		}
	}

	@RequestMapping(value = "/doregister", method = RequestMethod.POST)
	public ResponseResult<User> registryUserByEmail(@RequestBody User user) throws Exception {
		if (user.getUserCode() != null && !"".equals(user.getUserCode().trim()) && user.getUserPassword() != null && !"".equals(user.getUserPassword().trim())) {
			if (user.getUserCode().matches(ConstantUtil.REGEX_EMAIL)) {
				if (userTransport.checkUserCodeForRegistry(user.getUserCode())) {
					user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
					if (userTransport.registryUser(user)) {
						return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
					}else {
						return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "注册失败");
					}
				} else {
					return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "该邮箱已注册");
				}
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写正确的邮箱");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写邮箱和密码");
		}
	}

	@RequestMapping(value = "/activate", method = RequestMethod.PUT)
	public ResponseResult<User> activateUserForEmail(String user, String code) throws Exception {
		// 校验用户所填写的email地址和校验码是否为null
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 此时的email和code都是不为null的
			// 校验电子邮件是否正确
			if (user.matches(ConstantUtil.REGEX_EMAIL)) {
				// 电子邮件格式正确
				// 进行激活的
				if (userTransport.activateUser(user, code)) {
					return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
				} else {
					return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "激活失败，请联系管理员");
				}
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写正确的电子邮件地址");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写电子邮件和激活码");
		}
	}

	@RequestMapping(value = "/registerbyphone", method = RequestMethod.POST)
	public ResponseResult<User> registryUserByCellphone(@RequestBody User user) throws Exception {
		// 校验用户所填写信息是否正确
		if (user.getUserCode() != null && !"".equals(user.getUserCode().trim())
				&& user.getUserPassword() != null && !"".equals(user.getUserPassword().trim())) {
			if (user.getUserCode().matches(ConstantUtil.REGEX_CELLPHONE)) {
				if (userTransport.checkUserCodeForRegistry(user.getUserCode())) {
					// 对于密码进行MDK5加密
					user.setUserPassword(MD5Util.encrypt(user.getUserPassword()));
					if (userTransport.registryUser(user)) {
						return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
					} else {
						return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "注册失败，请稍后再试");
					}
				} else {
					return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "该手机号码已被注册");
				}
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请正确填写手机号码");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写手机号码和登录密码");
		}
	}

	@RequestMapping(value = "/validatephone", method = RequestMethod.PUT)
	public ResponseResult<User> activateUserForCellphone(String user, String code) throws Exception {
		// 校验用户所填写的email地址和校验码是否为null
		if (user != null && !"".equals(user.trim()) && code != null && !"".equals(code.trim())) {
			// 此时的email和code都是不为null的
			// 校验电子邮件是否正确
			if (user.matches(ConstantUtil.REGEX_CELLPHONE)) {
				// 电子邮件格式正确
				// 进行激活的
				if (userTransport.activateUser(user, code)) {
					return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
				} else {
					return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "激活失败，请联系管理员");
				}
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写正确的手机号码");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写手机号码和激活码");
		}
	}

	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public ResponseResult<User> loginUser(String name, String password) throws Exception {
		// 校验用户所提交的信息有效
		if (name != null && !"".equals(name.trim()) && password != null && !"".equals(password.trim())) {
			// 提交的信息有效，对于密码进行加密
			password = MD5Util.encrypt(password);
			// 进行用户登录
			User user = userTransport.getUserForLogin(name, password);
			if (user != null) {
				// 登陆成功
				// 绑定到Token中
				String token = JWTUtil.createToken(user.getId());
				response.setHeader("Authorization", token);
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, user);
			} else {
				return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "登陆失败，请稍后再试");
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "请填写手机号码或电子邮件以及登陆密码");
		}
	}
}
