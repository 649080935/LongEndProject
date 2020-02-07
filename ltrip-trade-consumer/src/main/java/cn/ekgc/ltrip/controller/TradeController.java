package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelOrderTransport;
import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.vo.PersonalOrderRoomVO;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {

	@Autowired
	private HotelOrderTransport hotelOrderTransport;

	@RequestMapping(value = "/prepay/{orderNo}", method = RequestMethod.GET)
	public ResponseResult<Object> payOrder(@PathVariable("orderNo") String orderNo) throws Exception {
		// 通过订单编号查找对应的订单信息
		HotelOrder hotelOrder = hotelOrderTransport.getHotelOrderByNo(orderNo);

		// 判断该订单是否存在，另外订单处于未支付状态
		if (hotelOrder != null && hotelOrder.getOrderStatus() == 0) {
			// 查询房间信息
			PersonalOrderRoomVO roomVO = hotelOrderTransport.getPersonalOrderRoomInfo(hotelOrder.getId());
			// 该订单可以进行支付
			// 获得订单金额
			DecimalFormat decimalFormat = new DecimalFormat("#.00");
			String total_amount = decimalFormat.format(hotelOrder.getPayAmount());
			// 订单编号使用当前时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			String out_trade_no = dateFormat.format(new Date()) + System.currentTimeMillis();
			String product_code = orderNo;
			String subject = hotelOrder.getHotelName();
			String body = roomVO.getRoomTitle() + "，" + hotelOrder.getCount() + "间，" + hotelOrder.getBookingDays() + "天";

			// 开始支付
			AlipayClient alipayClient = new DefaultAlipayClient(
					"https://openapi.alipaydev.com/gateway.do",
					"2016102100729673",
					"MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDx97FRmewAeI+bk0qcNZdBSiwP4eU2C79IpknMOIVFRcQG3mLEHgZl2hbMuUIz6MgxWhqxb3iNgBbfyBjL2AvUnbksGv5fXpDR0kc9D8y/CaZDMUAcTwbhGTiT+bCyFNAtltThn8GaSDQ0BhVP9efqYMp+1djwdF78NcjFalcxi55wpyiKKuVyFl7MB2r0vKj2rMDohPWsy4pDSRIFXrIFGCltDldPmWw4A4UXgTt5OmBdceZZM/yd/1Xb/Is2jurQdV4Mv3yLR/pHQD5faHCJZ3eJ2wPlJVtHYtLT5Fl0FQXZA8URnM9SVm+WmxrS4Wf+WJKZESd8p6OHKfLrOURxAgMBAAECggEAer44b/mR02dVCgl8tqMk2FTk5yWjXXg2D4dzNiqRyGMsnoNfPLcybaZlHHL511VFgGuB0PKIB9erQtfBZF5zGebskSgcU4qoEhgGVT5Xka02by1jpTidd8CQh9gIvz15oTa7RL0Cu93WxXE0jHxkqetxrUeTpd6SdIRgFWztUE7TjE0/VHe2gV0I3xsjjyRB5R27j63rYVNVMYhazVx+qyadC3UQA3W3Dp8jmacA7kQBZDayPfo4cyWKWWj2Y6jmYKK7mrW7Dx2YZEwmsVYPnkn74FJAJs5eDl/jIWP8+gXmBu7TfTRwtaZUKg0Z0dCannHq2xeYxjxWysnJfoTpsQKBgQD6huSLehTC6yX0sEKVCJ0QI/R1pCH/LTBL+VDOsimnDuQw8ZXv45STVL1aN4vAVRvWwSu9stvEo9KB/gtibyNizp8T3+UeFY82VRPP/AcbEPEYbDDlTgnPbScpsVk3NFe8FURAu21jXMbUqWQ5Lc9bODowHn3IUKHGbpy6ssiotQKBgQD3QO4tdJGYfypN6JOWH1Ev8suCTnR7GPpFIMDDejUcpQP1BD5+ScMNDDks2QYd+sDRi8OCm8K8LsTaV+gHNUeOAG1S25xzqQZGF2Sk7Spca9whkmbWYBt4aTMAZM/xGOJVm/Ii22MtQwbY72BLkn5uBZ6rz0jLWzPRyHSLmxYuTQKBgAvla+TTnzjCQcMku4SoLA0gJ/OOH68mTizNYfDutNuh8WQBlMUXYW69j2BBUvmHERNZiOIxJA5qWQyjK5c4/80pUpR3BUIwc+R4lcBCj3PRkiE+wfOhiQW4gSuRqFLewWvE9sC8Ja2aMjbW9FNiwX3rZZfzWrWwXn41smuLIcwhAoGAXm2AyQFc3XWrbWemfc3mDr7WNCTMXXrTkfUJSV5xzEfFNBzz1P1Mrb3+U/9qU6bBXwXWnBnqjD38Vjr+VUpm0nedSOiwXoet6rTibg5ZgER9JKEp/zNjKmBa5wqZU8WiIvnUxrUsskuH4O/CwfnnqRMiXxFiBrAFIVhCElMfbH0CgYAeO3M7q2RX6xqZL6PdscDjT0O+mayc+XhXRkZWpHR2D79R7WypHEAi13KPjPGsaT3piQNlTGKuhqmLKdoxeBnlxaiMuvUwvnkzeTyMllB+0WmtFZ7WrjBNGuGxptlgxzUTr/j25p/3bzBf4gI+ZkToo0oD+AwXyH/WLEETd7wqig==",
					"json",
					"UTF-8",
					"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA8fexUZnsAHiPm5NKnDWXQUosD+HlNgu/SKZJzDiFRUXEBt5ixB4GZdoWzLlCM+jIMVoasW94jYAW38gYy9gL1J25LBr+X16Q0dJHPQ/MvwmmQzFAHE8G4Rk4k/mwshTQLZbU4Z/Bmkg0NAYVT/Xn6mDKftXY8HRe/DXIxWpXMYuecKcoiirlchZezAdq9Lyo9qzA6IT1rMuKQ0kSBV6yBRgpbQ5XT5lsOAOFF4E7eTpgXXHmWTP8nf9V2/yLNo7q0HVeDL98i0f6R0A+X2hwiWd3idsD5SVbR2LS0+RZdBUF2QPFEZzPUlZvlpsa0uFn/liSmREnfKejhyny6zlEcQIDAQAB",
					"RSA2"
			); //获得初始化的AlipayClient
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
			alipayRequest.setReturnUrl("http://localhost/itrip");
			alipayRequest.setNotifyUrl("http://itrip.project.bdqn.cn/trade/api/notify/" + hotelOrder.getId());//在公共参数中设置回跳和通知地址

			String json = "{\"out_trade_no\":\"" + out_trade_no
					+ "\", \"product_code\":\"FAST_INSTANT_TRADE_PAY\", "
					+ "\"total_amount\":\"" + total_amount
					+ "\",\"subject\":\"" + subject
					+ "\",\"body\":\"" + body + "\","
					+ "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\",\"extend_params\":{}}";
			System.out.println(json);

			alipayRequest.setBizContent(json);//填充业务参数

			String form="";
			try {
				form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().write(form);//直接将完整的表单html输出到页面
			response.getWriter().flush();
			response.getWriter().close();
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE);
	}

}
