package org.softuni.eventures.repositories;

import org.softuni.eventures.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select case when count(u) > 0 then true else false end from User u " +
            "where u.username = ?1")
    boolean usernameExists(String username);

    @Query("select case when count(u) > 0 then true else false end from User u " +
            "where u.email = ?1")
    boolean emailExists(String email);

    @Query("select case when count(u) > 0 then true else false end from User u " +
            "where u.uniqueCitizenNumber = ?1")
    boolean ucnExists(String ucn);

    User findUserByUsername(String username);

    User findUserByEmail(String email);
}
