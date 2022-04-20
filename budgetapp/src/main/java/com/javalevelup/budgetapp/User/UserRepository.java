package com.javalevelup.budgetapp.User;

import com.javalevelup.budgetapp.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository
        extends JpaRepository<User, Long> {
    @Query("select count(u) from Users u where upper(u.email) = upper(?1)")
    long isEmailPresent(String email);

    @Query("select count(u) from Users u where upper(u.username) = upper(?1)")
    long isUsernamePresent(String username);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Modifying
    @Query("delete from Users u where upper(u.username) = upper(:username)")
    void deleteByUsername(String username);

    User findByEmail(String email);

}
