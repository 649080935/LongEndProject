package cn.ekgc.ltrip.dao;

import cn.ekgc.ltrip.pojo.entity.User;
import cn.ekgc.ltrip.pojo.entity.UserLinkUser;
import cn.ekgc.ltrip.pojo.vo.ModifyUserLinkUserVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserDao {

	List<User> findUserListByQuery(Map<String, Object> queryMap) throws Exception;

	void saveUser(User user) throws Exception;

	void updateUser(User user) throws Exception;

	List<UserLinkUser> findLinkUserListByQuery(Map<String, Object> queryMap) throws Exception;

	Integer updateUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	Integer saveUserLinkUser(UserLinkUser userLinkUser) throws Exception;

	void deleteUserLinkUserByIds(Long id) throws Exception;

}
