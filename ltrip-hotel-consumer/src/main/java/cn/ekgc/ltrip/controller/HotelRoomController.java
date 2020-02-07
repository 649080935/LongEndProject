package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelRoomTransport;
import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("hotelRoomController")
@RequestMapping("/biz/api/hotelroom")
public class HotelRoomController {

	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	@RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST)
	public ResponseResult<Object> queryHotelRoombyHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO)
			throws Exception {
		List<List<HotelRoomVO>> resultList = new ArrayList<List<HotelRoomVO>>();
		List<HotelRoomVO> hotelRoomVOList = hotelRoomTransport.queryHotelRoomByHotel(searchHotelRoomVO);
		for (HotelRoomVO hotelRoomVO : hotelRoomVOList) {
			List<HotelRoomVO> tempList = new ArrayList<HotelRoomVO>();
			tempList.add(hotelRoomVO);
			resultList.add(tempList);
		}
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, resultList);
	}

	@RequestMapping(value = "/queryhotelroombed", method = RequestMethod.GET)
	public ResponseResult<Object> queryHotelRoombed() throws Exception {
		List<LabelDicVO> labelVOList = hotelRoomTransport.queryHotelRoombed();
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, labelVOList);
	}

	@RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET)
	public ResponseResult<Object> getImgByTargetId(@PathVariable("targetId") Long targetId) throws Exception {
		List<HotelImage> hotelImageList = hotelRoomTransport.getImgByTargetIdAndTypeId(targetId, "1");
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, hotelImageList);
	}

}
