package cn.ekgc.ltrip.hotel.transport;

import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import cn.ekgc.ltrip.pojo.vo.CommentCountVO;
import cn.ekgc.ltrip.pojo.vo.Page;
import cn.ekgc.ltrip.pojo.vo.SearchCommentVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ltrip-hotel-provider")
@RequestMapping("/comment")
public interface CommentTransport {

	@RequestMapping(value = "/gethotelscore", method = RequestMethod.POST)
	ScoreComment getHotelScoreByHotelId(@RequestParam Long hotelId) throws Exception;

	@RequestMapping(value = "/getcount", method = RequestMethod.POST)
	CommentCountVO getCommentCountByHotelId(@RequestParam Long hotelId) throws Exception;

	@RequestMapping(value = "/getcommentlist", method = RequestMethod.POST)
	Page<Comment> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception;
}
