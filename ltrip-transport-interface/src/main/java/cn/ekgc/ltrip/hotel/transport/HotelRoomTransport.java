package cn.ekgc.ltrip.hotel.transport;

import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.vo.HotelRoomVO;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelRoomVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "ltrip-hotel-provider")
@RequestMapping("/hotelroom")
public interface HotelRoomTransport {

	@RequestMapping(value = "/queryhotelroombyhotel", method = RequestMethod.POST)
	List<HotelRoomVO> queryHotelRoomByHotel(@RequestBody SearchHotelRoomVO searchHotelRoomVO) throws Exception;

	@RequestMapping(value = "/queryhotelroombed", method = RequestMethod.POST)
	List<LabelDicVO> queryHotelRoombed() throws Exception;

	@RequestMapping(value = "/getimg", method = RequestMethod.POST)
	List<HotelImage> getImgByTargetIdAndTypeId(@RequestParam Long targetId, @RequestParam String typeId) throws Exception;
}
