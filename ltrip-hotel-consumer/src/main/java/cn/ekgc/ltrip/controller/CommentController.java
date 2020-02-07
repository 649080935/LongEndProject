package cn.ekgc.ltrip.controller;

import cn.ekgc.ltrip.base.controller.BaseController;
import cn.ekgc.ltrip.base.enums.SuccessEnum;
import cn.ekgc.ltrip.hotel.transport.CommentTransport;
import cn.ekgc.ltrip.hotel.transport.HotelRoomTransport;
import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.HotelImage;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import cn.ekgc.ltrip.pojo.vo.CommentCountVO;
import cn.ekgc.ltrip.pojo.vo.Page;
import cn.ekgc.ltrip.pojo.vo.ResponseResult;
import cn.ekgc.ltrip.pojo.vo.SearchCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("commentController")
@RequestMapping("/biz/api/comment")
public class CommentController extends BaseController {

	@Autowired
	private CommentTransport commentTransport;
	@Autowired
	private HotelRoomTransport hotelRoomTransport;

	@RequestMapping(value = "/gethotelscore/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getHotelScore(@PathVariable("hotelId") Long hotelId) throws Exception {
		ScoreComment scoreComment = commentTransport.getHotelScoreByHotelId(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, scoreComment);
	}

	@RequestMapping(value = "/getcount/{hotelId}", method = RequestMethod.GET)
	public ResponseResult<Object> getCount(@PathVariable("hotelId") Long hotelId) throws Exception {
		CommentCountVO commentCountVO = commentTransport.getCommentCountByHotelId(hotelId);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, commentCountVO);
	}

	@RequestMapping(value = "/getimg/{targetId}", method = RequestMethod.GET)
	public ResponseResult<Object> getImgByTargetId(@PathVariable("targetId") Long targetId) throws Exception {
		List<HotelImage> hotelImageList = hotelRoomTransport.getImgByTargetIdAndTypeId(targetId, "2");
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, hotelImageList);
	}

	@RequestMapping(value = "/getcommentlist", method = RequestMethod.POST)
	public ResponseResult<Object> getCommentListByPage(@RequestBody SearchCommentVO queryVO) throws Exception {
		Page<Comment> page = commentTransport.getCommentListByPage(queryVO);
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, page);
	}

	/*@RequestMapping(value = "/gettraveltype", method = RequestMethod.GET)
	public ResponseResult<Object> gettraveltype() throws Exception {
		Page<Comment> page = commentTransport.gettraveltype();
		return new ResponseResult<>(SuccessEnum.SUCCESS_TRUE, page);
	}*/

}
