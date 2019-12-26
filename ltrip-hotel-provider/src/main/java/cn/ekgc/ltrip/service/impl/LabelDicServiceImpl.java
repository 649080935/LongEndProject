package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.LabelDicDao;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.service.LabelDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("labelDicService")
@Transactional
public class LabelDicServiceImpl implements LabelDicService {

	@Autowired
	private LabelDicDao labelDicDao;


	public List<LabelDic> getHotelFeature() throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();

		queryMap.put("parentId", 16);

		List<LabelDic> labelDicList = labelDicDao.findLabelDicListByQuery(queryMap);

		return labelDicList;
	}
}
