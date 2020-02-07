package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.hotel.transport.HotelOrderTransport;
import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.vo.*;
import cn.ekgc.ltrip.service.HotelOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController("hotelOrderTransport")
@RequestMapping("/hotelorder")
public class HotelOrderTransportImpl implements HotelOrderTransport {

	@Autowired
	private HotelOrderService hotelOrderService;

	@RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
	public RoomStoreVO getPreOrderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception {
		return hotelOrderService.getPreOrderInfo(validateRoomStoreVO);
	}

	@RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
	public Map<String, Object> addHotelOrder(@RequestBody HotelOrderAddVO hotelOrderAddVO) throws Exception {
		return hotelOrderService.addHotelOrder(hotelOrderAddVO);
	}

	@RequestMapping(value = "/getpersonalorderinfo", method = RequestMethod.POST)
	public List<HotelOrder> getPersonalOrderInfo(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getPersonalOrderInfo(orderId);
	}

	@RequestMapping(value = "/getpersonalorderroominfo", method = RequestMethod.POST)
	public PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.getPersonalOrderRoomInfo(orderId);
	}

	@RequestMapping(value = "/queryOrderById", method = RequestMethod.POST)
	public ModifyHotelOrderVO queryOrderByID(@RequestParam Long orderId) throws Exception {
		return hotelOrderService.queryOrderById(orderId);
	}

	@RequestMapping(value = "/prepay", method = RequestMethod.POST)
	public HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception {
		return hotelOrderService.getOrderByNo(orderNo);
	}

	@RequestMapping(value = "/getpersonalorderlist", method = RequestMethod.POST)
	public Page<HotelOrder> getHotelOrderListByPage(@RequestBody SearchOrderVO searchOrderVO) throws Exception {
		return hotelOrderService.getHotelOrderListByPage(searchOrderVO);
	}

}
