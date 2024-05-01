package com.driver.repository;
import com.driver.model.User;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    @Transactional
    @Modifying
    @Query(value = "update user set password=:password where id=:userId",nativeQuery = true)
    void updatePassword(Integer userId, String password);

    @Query(value = "select * from user where id=:id",nativeQuery = true)
    User findUserById(int id);


}
