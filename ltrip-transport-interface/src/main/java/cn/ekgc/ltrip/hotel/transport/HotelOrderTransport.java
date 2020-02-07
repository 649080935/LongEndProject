package cn.ekgc.ltrip.hotel.transport;

import cn.ekgc.ltrip.pojo.entity.HotelOrder;
import cn.ekgc.ltrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient("ltrip-hotel-provider")
@RequestMapping("/hotelorder")
public interface HotelOrderTransport {

	@RequestMapping(value = "/getpreorderinfo", method = RequestMethod.POST)
	RoomStoreVO getPreOrderInfo(@RequestBody ValidateRoomStoreVO validateRoomStoreVO) throws Exception;

	@RequestMapping(value = "/addhotelorder", method = RequestMethod.POST)
	Map<String, Object> addHotelOrder(@RequestBody HotelOrderAddVO hotelOrderAddVO) throws Exception;

	@RequestMapping(value = "/getpersonalorderinfo", method = RequestMethod.POST)
	List<HotelOrder> getPersonalOrderInfo(@RequestParam Long orderId) throws Exception;

	@RequestMapping(value = "/getpersonalorderroominfo", method = RequestMethod.POST)
	PersonalOrderRoomVO getPersonalOrderRoomInfo(@RequestParam Long orderId) throws Exception;

	@RequestMapping(value = "/queryOrderById", method = RequestMethod.POST)
	ModifyHotelOrderVO queryOrderByID(@RequestParam Long orderId) throws Exception;

	@RequestMapping(value = "/prepay", method = RequestMethod.POST)
	HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception;

	@RequestMapping(value = "/getpersonalorderlist", method = RequestMethod.POST)
	Page<HotelOrder> getHotelOrderListByPage(@RequestBody SearchOrderVO searchOrderVO) throws Exception;
}
