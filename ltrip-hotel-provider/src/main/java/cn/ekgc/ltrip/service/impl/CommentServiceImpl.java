package cn.ekgc.ltrip.service.impl;

import cn.ekgc.ltrip.dao.CommentDao;
import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import cn.ekgc.ltrip.pojo.vo.CommentCountVO;
import cn.ekgc.ltrip.pojo.vo.Page;
import cn.ekgc.ltrip.pojo.vo.SearchCommentVO;
import cn.ekgc.ltrip.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	public ScoreComment getHotelScoreByHotelId(Long hotelId) throws Exception {
		return commentDao.findScoreCommentByHotelId(hotelId);
	}

	public CommentCountVO getCommentCountByHotelId(Long hotelId) throws Exception {
		CommentCountVO commentCountVO = new CommentCountVO();
		// 设定查询参数
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", hotelId);
		// 查询总评论数
		commentCountVO.setAllcomment(commentDao.findCommentByQuery(queryMap).size());
		// 查询值得推荐
		queryMap.put("isOk", 1);
		commentCountVO.setIsok(commentDao.findCommentByQuery(queryMap).size());
		// 值得改善
		queryMap.put("isOk", 0);
		commentCountVO.setImprove(commentDao.findCommentByQuery(queryMap).size());
		// 有图片
		queryMap.remove("isOk");
		queryMap.put("isHavingImg", 1);
		commentCountVO.setHavingimg(commentDao.findCommentByQuery(queryMap).size());

		return commentCountVO;
	}

	public Page<Comment> getCommentListByPage(SearchCommentVO queryVO) throws Exception {
		// 根据查询视图进行封装查询Map集合
		Map
				<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("hotelId", queryVO.getHotelId());
		if (queryVO.getIsHavingImg() != -1) {
			queryMap.put("isHavingImg", queryVO.getIsHavingImg());
		}
		if (queryVO.getIsOk() != -1) {
			queryMap.put("isOk", queryVO.getIsOk());
		}
		queryMap.put("start", (queryVO.getPageNo() - 1) * queryVO.getPageSize());
		queryMap.put("size", queryVO.getPageSize());

		// 获取分页列表
		List<Comment> commentList = commentDao.findCommentByQuery(queryMap);
		// 获得总条数
		queryMap.remove("start");
		queryMap.remove("size");
		Integer total = commentDao.findCommentByQuery(queryMap).size();

		// 封装分页对象
		Page<Comment> page = new Page<Comment>(queryVO.getPageNo(), queryVO.getPageSize(), total);
		page.setRows(commentList);

		return page;
	}
}
