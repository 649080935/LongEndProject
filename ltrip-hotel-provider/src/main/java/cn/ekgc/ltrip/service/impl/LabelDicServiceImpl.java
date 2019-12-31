package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.LabelDicDao;
import cn.ekgc.ltrip.pojo.entity.LabelDic;
import cn.ekgc.ltrip.pojo.vo.LabelDicVO;
import cn.ekgc.ltrip.service.LabelDicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

	public List<LabelDicVO> queryLabelDicVOListByParent(Long parentId) throws Exception {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("parentId", parentId);
		List<LabelDic> labelDicList = labelDicDao.findLabelDicListByQuery(queryMap);
		// 将对应的实体对象切换成视图对象
		List<LabelDicVO> labelDicVOList = new ArrayList<LabelDicVO>();
		if (labelDicList != null && labelDicList.size() > 0) {
			for (LabelDic labelDic : labelDicList) {
				LabelDicVO labelDicVO = new LabelDicVO();
				BeanUtils.copyProperties(labelDic, labelDicVO);
				labelDicVOList.add(labelDicVO);
			}
		}
		return labelDicVOList;
	}
}
