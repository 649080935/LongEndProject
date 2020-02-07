package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelOrderTransport;
import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.vo.*;
import cn.ekgc.ltrip.user.transport.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

@RestController("hotelOrderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelOrderController extends BaseController {

	@Autowired
	private HotelOrderTransport hotelOrderTransport;
	@Autowired
	private UserTransport userTransport;

	@RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
	public ResponseResult<Object> getPreOrderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		RoomStoreVO roomStoreVO = hotelOrderTransport.getPreOrderInfo(validateRoomStoreVO);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, roomStoreVO);
	}

	@RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
	public ResponseResult<Object> addHotelOrder(@RequestBody HotelOrderAddVO hotelOrderAddVO) throws Exception {
		// 判断此时是否有房间库存
		ValidateRoomStoreVO queryVO = new ValidateRoomStoreVO();
		queryVO.setCheckInDate(hotelOrderAddVO.getCheckInDate());
		queryVO.setCheckOutDate(hotelOrderAddVO.getCheckOutDate());
		queryVO.setCount(hotelOrderAddVO.getCount());
		queryVO.setHotelId(hotelOrderAddVO.getHotelId());
		queryVO.setRoomId(hotelOrderAddVO.getRoomId());

		RoomStoreVO roomStoreVO = hotelOrderTransport.getPreOrderInfo(queryVO);
		// 获得库存信息
		Integer store = roomStoreVO.getStore();
		if (store != null && store >= hotelOrderAddVO.getCount()) {
			// 获得当前登录用户
			Cookie[] cookies = request.getCookies();
			String userCode = "";
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					userCode = cookie.getValue();
				}
			}
			System.out.println(userCode);
			// 酒店的数据满足
			// 开始进行入库操作
			// 获得当前登录用户主键
			User user = userTransport.getUserByUserCode(userCode);
			hotelOrderAddVO.setUserId(user.getId());
			Map<String, Object> resultMap = hotelOrderTransport.addHotelOrder(hotelOrderAddVO);
			if (resultMap != null) {
				return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, resultMap);
			}
		} else {
			return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "库存不足");
		}

		return null;
	}

	@RequestMapping(value = "/getpersonalorderinfo/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> getPersonalOrderInfo(@PathVariable("orderId") Long orderId) throws Exception {
		List<HotelOrder> orderList = hotelOrderTransport.getPersonalOrderInfo(orderId);
		if (orderList != null && orderList.size() > 0) {
			return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, orderList.get(0));
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_FALSE, "未获得结果");
	}

	@RequestMapping(value = "/getpersonalorderroominfo/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> getPersonalOrderRoomInfo(@PathVariable("orderId") Long orderId) throws Exception {
		PersonalOrderRoomVO personalOrderRoomVO = hotelOrderTransport.getPersonalOrderRoomInfo(orderId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, personalOrderRoomVO);
	}

	@RequestMapping(value = "/queryOrderById/{orderId}", method = RequestMethod.GET)
	public ResponseResult<Object> queryOrderById(@PathVariable("orderId") Long orderId) throws Exception {
		ModifyHotelOrderVO hotelOrderVO = hotelOrderTransport.queryOrderByID(orderId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, orderId);
	}

	@RequestMapping(value = "/getpersonalorderlist", method = RequestMethod.POST)
	public ResponseResult<Object> getpersonalorderlist(@RequestBody SearchOrderVO searchOrderVO) throws Exception {
		String userCode = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user")) {
				userCode = cookie.getValue();
			}
		}

		searchOrderVO.setUserCode(userCode);

		Page<HotelOrder> page = hotelOrderTransport.getHotelOrderListByPage(searchOrderVO);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, page);
	}

}
