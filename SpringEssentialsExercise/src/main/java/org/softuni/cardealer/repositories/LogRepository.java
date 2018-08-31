package org.softuni.cardealer.repositories;

import org.softuni.cardealer.entities.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {

    List<Log> findAllByUsernameOrderByDateTimeAsc(String username);

    List<Log> findAllByOrderByDateTimeAsc();
}
