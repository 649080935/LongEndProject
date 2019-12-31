package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.hotel.transport.HotelRoomTransport;
import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;
import cn.ekgc.ltrip.service.HotelRoomService;
import cn.ekgc.ltrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelRoomTransport")
@RequestMapping("/hotelroom")
public class HotelRoomTransportImpl implements HotelRoomTransport {

	@Autowired
	private HotelRoomService hotelRoomService;
	@Autowired
	private LabelDicService labelDicService;

	@RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST)
	public List<HotelRoomVO> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception {
		return hotelRoomService.queryHotelRoomByHotel(searchHotelRoomVO);
	}

	@RequestMapping(value = "/queryhotelroombed", method = RequestMethod.POST)
	public List<LabelDicVO> queryHotelRoombed() throws Exception {
		return labelDicService.queryLabelDicVOListByParent(1L);
	}

	@RequestMapping(value = "/getimg", method = RequestMethod.POST)
	public List<HotelImage> getImgByTargetIdAndTypeId(@RequestParam Long targetId, @RequestParam String typeId) throws Exception {
		return hotelRoomService.getImgByTargetIdAndTypeId(targetId, typeId);
	}

}
