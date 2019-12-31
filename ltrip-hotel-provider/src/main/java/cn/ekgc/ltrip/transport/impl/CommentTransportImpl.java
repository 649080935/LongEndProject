package cn.ekgc.ltrip.transport.impl;

import cn.ekgc.ltrip.hotel.transport.CommentTransport;
import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import cn.ekgc.ltrip.pojo.vo.CommentCountVO;
import cn.ekgc.ltrip.pojo.vo.Page;
import cn.ekgc.ltrip.pojo.vo.SearchCommentVO;
import cn.ekgc.ltrip.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("commentTransport")
@RequestMapping("/comment")
public class CommentTransportImpl implements CommentTransport {

	@Autowired
	private CommentService commentService;

	@RequestMapping(value = "/gethotelscore", method = RequestMethod.POST)
	public ScoreComment getHotelScoreByHotelId(@RequestParam Long hotelId) throws Exception {
		return commentService.getHotelScoreByHotelId(hotelId);
	}

	@RequestMapping(value = "/getcount", method = RequestMethod.POST)
	public CommentCountVO getCommentCountByHotelId(@RequestParam Long hotelId) throws Exception {
		return commentService.getCommentCountByHotelId(hotelId);
	}

	@RequestMapping(value = "/getcommentlist", method = RequestMethod.POST)
	public Page<Comment> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception{
		return commentService.getCommentListByPage(queryVO);
	}

}
