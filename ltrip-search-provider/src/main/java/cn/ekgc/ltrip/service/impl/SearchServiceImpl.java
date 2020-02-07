package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.pojo.vo.HotelVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotCityVO;
import cn.ekgc.ltrip.pojo.vo.SearchHotelVO;
import cn.ekgc.ltrip.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("searchService")
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SolrClient solrClient;

	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO searchHotCityVO) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + searchHotCityVO.getCityId());
		solrQuery.setRows(searchHotCityVO.getCount());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponsey = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponsey.getBeans(HotelVO.class);
	}

	public List<SearchHotelVO> searchItripHotelPage(SearchHotelVO searchHotelVO) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		httpSolrClient.setParser(new XMLResponseParser());
		// 创建Solr的查询参数
		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("destination:" + searchHotelVO.getDestination());
		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponsey = solrClient.query(solrQuery);
		// 通过使用QueryResponse提取结果
		return queryResponsey.getBeans(SearchHotelVO.class);
	}
}
