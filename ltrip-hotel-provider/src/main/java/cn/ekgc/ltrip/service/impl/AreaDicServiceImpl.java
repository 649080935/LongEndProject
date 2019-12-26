package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.AreaDicDao;
import cn.ekgc.ltrip.pojo.entity.AreaDic;
import cn.ekgc.ltrip.service.AreaDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("areaDicService")
@Transactional
public class AreaDicServiceImpl implements AreaDicService {

	@Autowired
	private AreaDicDao areaDicDao;

	public List<AreaDic> getHotCityByIsChina(Integer isChina) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();

		queryMap.put("isHot", 1);
		queryMap.put("isChina", isChina);
		queryMap.put("isActivated", 1);

		return areaDicDao.findAreaDicListByQuery(queryMap);
	}
}
