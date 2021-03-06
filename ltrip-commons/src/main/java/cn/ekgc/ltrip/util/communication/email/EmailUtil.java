package cn.ekgc.ltrip.util.communication.email;

import cn.ekgc.ltrip.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <b>电子邮件发送工具类</b>
 * @author Arthur
 * @version 4.0.0
 * @since 4.0.0
 */
@Component("emailUtil")
public class EmailUtil {
	@Autowired
	private JavaMailSender mailSender;

	/**
	 * <b>使用电子邮件发送验证码信息</b>
	 * @param email
	 * @param activeCode
	 * @throws Exception
	 */
	@Async("asyncServieExecutor")
	public void sendEmail(String email, String activeCode) throws Exception {
		// 发送邮件到用户邮箱
		// 设定发送人
		// 创建邮件发送对象，使用MimeMailMessage对象可以发送HTML格式的邮件
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(ConstantUtil.MAIL_FROM);
		mailMessage.setTo(email);
		mailMessage.setSubject("账户激活码");
		mailMessage.setText("您的激活码是："+ activeCode + "请在" + ConstantUtil.ACTIVE_CODE_TIMEOUT + "分钟内登录系统，输入本验证码激活您的账户！");
		mailSender.send(mailMessage);
	}
}
