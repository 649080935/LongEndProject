package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.hotel.transport.HotelTransport;
import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.service.AreaDicService;
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

	@RequestMapping(value = "/queryhotcity", method = RequestMethod.POST)
	public List<AreaDic> getHotCityByIsChina(@RequestParam Integer isChina) throws Exception {
		return areaDicService.getHotCityByIsChina(isChina);
	}

	@RequestMapping(value = "/queryhotelfeature", method = RequestMethod.GET)
	public List<LabelDic> getHotelFeature() throws Exception {
		return labelDicService.getHotelFeature();
	}
}
