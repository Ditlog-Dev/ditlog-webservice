package id.ac.itb.logistik.ditlog.repository;

import id.ac.itb.logistik.ditlog.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

//    @Query(value = "SELECT ID_USER FROM SILOG_USER WHERE str1 = :str1 AND str2 = :str2")
//    User findPassedForStream(@Param("str1") String username, @Param("str2") String password);
//
//    User findUserByUsernamePassword(String username, String password);

    @Query(value = "SELECT * FROM SILOG_USER WHERE USER_NAME = ?1 AND USER_PASSWORD = ?2", nativeQuery = true)
    User findUserByUsernamePassword(String username, String password);
}

