package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.Comment;
import cn.ekgc.ltrip.pojo.entity.ScoreComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CommentDao {

	ScoreComment findScoreCommentByHotelId(Long hotelId) throws Exception;

	List<Comment> findCommentByQuery(Map<String, Object> queryMap) throws Exception;
}
