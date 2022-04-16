package com.javalevelup.budgetApp.repositories;

import com.javalevelup.budgetApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository
        extends JpaRepository<User, Long> {
    @Query("select count(u) from Users u where upper(u.email) = upper(?1)")
    long isEmailPresent(String email);

    @Query("select count(u) from Users u where upper(u.username) = upper(?1)")
    long isUsernamePresent(String username);

    User findByUsername(String username);

    User findByEmail(String email);

}
