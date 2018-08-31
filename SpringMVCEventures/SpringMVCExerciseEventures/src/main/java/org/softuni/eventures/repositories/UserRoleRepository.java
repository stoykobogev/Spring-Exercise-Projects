package org.softuni.eventures.repositories;

import org.softuni.eventures.entities.User;
import org.softuni.eventures.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole,String> {

    Set<UserRole> findByAuthority(String authority);

    Set<UserRole> findAllByAuthorityIn(String... authority);
}
