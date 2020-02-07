package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.hotel.transport.HotelTransport;
import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.pojo.entity.Hotel;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.SearchDetailsHotelVO;
import cn.ekgc.ltrip.service.AreaDicService;
import cn.ekgc.ltrip.service.HotelService;
import cn.ekgc.ltrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelTransport")
@RequestMapping("/hotel")
public class HotelTransportImpl implements HotelTransport {

	@Autowired
	private AreaDicService areaDicService;
	@Autowired
	private LabelDicService labelDicService;
	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = "/queryhotcity", method = RequestMethod.POST)
	public List<AreaDic> getHotCityByIsChina(@RequestParam Integer isChina) throws Exception {
		return areaDicService.getHotCityByIsChina(isChina);
	}

	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	public List<LabelDic> getHotelFeature() throws Exception {
		return labelDicService.getHotelFeature();
	}

	@RequestMapping(value = "/getvideodesc", method = RequestMethod.POST)
	public Hotel queryHotelVideoDescByHotelId(@RequestParam Long hotelId) throws Exception {
		return hotelService.queryHotelVideoDescByHotelId(hotelId);
	}

	@RequestMapping(value = "/queryhoteldetails", method = RequestMethod.POST)
	public List<SearchDetailsHotelVO> queryHotelDetails(@RequestParam Long hotelId) throws Exception {
		return hotelService.queryHotelDetails(hotelId);
	}

	@RequestMapping(value = "/queryhotelfacilities", method = RequestMethod.POST)
	public String queryHotelFacilities(@RequestParam Long hotelId) throws Exception{
		return hotelService.queryHotelFacilities(hotelId);
	}

	@RequestMapping(value = "/queryhotelpolicy", method = RequestMethod.POST)
	public String queryHotelPolicy(@RequestParam Long id) throws Exception {
		return hotelService.queryHotelPolicy(id);
	}

	@RequestMapping(value = "/querytradearea", method = RequestMethod.POST)
	public List<AreaDic> querytradearea(@RequestParam Long cityId) throws Exception {
		return areaDicService.querytradearea(cityId);
	}
}
