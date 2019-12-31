package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelRoomTransport;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelRoomController")
@RequestMapping("/biz/api/hotelroom")
public class HotelRoomController {

	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	@RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST)
	public ResponseResult<Object> queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO)
			throws Exception {
		List<HotelRoomVO> hotelRoomVOList = hotelRoomTransport.queryHotelRoomByHotel(searchHotelRoomVO);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, hotelRoomVOList);
	}

	@RequestMapping(value = "/queryhotelroombed", method = RequestMethod.GET)
	public ResponseResult<Object> queryHotelRoombed() throws Exception {
		List<LabelDicVO> labelVOList = hotelRoomTransport.queryHotelRoombed();
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, labelVOList);
	}

}
