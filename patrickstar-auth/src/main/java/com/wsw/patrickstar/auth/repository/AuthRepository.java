package com.wsw.patrickstar.auth.repository;

import com.wsw.patrickstar.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author WangSongWen
 * @Date: Created in 13:11 2021/1/18
 * @Description:
 */
@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    User findUserByUsernameAndPassword(String username, String password);

    List<User> findUsersByUsername(String username);

    User findUserByUsername(String username);
}
