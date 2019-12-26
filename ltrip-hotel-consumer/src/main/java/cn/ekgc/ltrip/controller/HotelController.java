package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.HotelTransport;
import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelController")
@RequestMapping("/biz/api/hotel")
public class HotelController extends BaseController {

	@Autowired
	private HotelTransport hotelTransport;

	@RequestMapping(value = "/queryhotcity/{isChina}", method = RequestMethod.GET)
	public ResponseResult<Object> getHotCityByIsChina(@PathVariable("isChina") Integer isChina)throws Exception {
		List<AreaDic> areaDicList = hotelTransport.getHotCityByIsChina(isChina);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, areaDicList);
	}

	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	public ResponseResult<Object> getHotelFeature() throws Exception {
		List<LabelDic> labelDicList = hotelTransport.getHotelFeature();
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, labelDicList);
	}

}
