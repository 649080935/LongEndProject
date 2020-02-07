package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelTransport;
import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.pojo.entity.Hotel;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.SearchDetailsHotelVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {

	@Autowired
	private HotelTransport hotelTransport;

	@RequestMapping(value = "/queryhotcity/{isChina}", method = RequestMethod.GET)
	public ResponseResult<Object> getHotCityByIsChina(@PathVariable("isChina") Integer isChina)throws Exception {
		List<AreaDic> areaDicList = hotelTransport.getHotCityByIsChina(isChina);
		return new ResponseResult<Object>(SuccessEnum.SUCCESS_TRUE, areaDicList);
	}

	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	public ResponseResult<Object> getHotelFeature() throws Exception {
		List<LabelDic> labelDicList = hotelTransport.getHotelFeature();
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, labelDicList);
	}

	// 根据酒店id查询酒店特色、商圈、酒店名称（视频文字描述）
	@RequestMapping(value = "/getvideodesc/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getVideoDesc(@PathVariable("hotelId") Long hotelId) throws Exception {
		// 根据酒店id查询具体的酒店对象
		Hotel hotel = hotelTransport.queryHotelVideoDescByHotelId(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, hotel);
	}

	@RequestMapping(value = "/queryhoteldetails/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> queryHotelDetails(@PathVariable("hotelId") Long hotelId) throws Exception {
		List<SearchDetailsHotelVO> detailsHotelVOList = hotelTransport.queryHotelDetails(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, detailsHotelVOList);
	}

	@RequestMapping(value = "/queryhotelfacilities/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> queryHotelFacilities(@PathVariable("hotelId") Long hotelId) throws Exception {
		String hotelfacilities = hotelTransport.queryHotelFacilities(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "", hotelfacilities);
	}

	@RequestMapping(value = "/queryhotelpolicy/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> queryHotelPolicy(@PathVariable("hotelId") Long hotelId) throws Exception {
		String hotelPolicy = hotelTransport.queryHotelPolicy(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "", hotelPolicy);
	}

	@RequestMapping(value = "/querytradearea/{cityId}", method = RequestMethod.GET)
	public ResponseResult<Object> querytradearea(@PathVariable("cityId") Long cityId) throws Exception {
		List<AreaDic> areaDicList = hotelTransport.querytradearea(cityId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, "", areaDicList);
	}
}
