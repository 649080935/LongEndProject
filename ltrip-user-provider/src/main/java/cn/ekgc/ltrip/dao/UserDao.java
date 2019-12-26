package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

	List<User> findUserListByQuery(Map<String, Object> queryMap) throws Exception;

	void saveUser(User user) throws Exception;

	void updateUser(User user) throws Exception;
}
