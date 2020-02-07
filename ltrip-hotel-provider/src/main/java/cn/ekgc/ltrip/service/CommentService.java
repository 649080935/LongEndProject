package cn.ekgc.ltrip.service;

import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import cn.ekgc.ltrip.pojo.vo.CommentCountVO;
import cn.ekgc.ltrip.pojo.vo.Page;
import cn.ekgc.ltrip.pojo.vo.SearchCommentVO;

public interface CommentService {

	ScoreComment getHotelScoreByHotelId(Long hotelId) throws Exception;

	CommentCountVO getCommentCountByHotelId(Long hotelId) throws Exception;

	Page<Comment> getCommentListByPage(SearchCommentVO queryVO) throws Exception;

}
