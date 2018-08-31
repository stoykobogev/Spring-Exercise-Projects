package org.softuni.eventures.repositories;

import org.softuni.eventures.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EventRepository extends JpaRepository<Event, String> {

    @Query("select case when count(u) > 0 then true else false end from Event u " +
            "where u.name = ?1")
    boolean eventNameExists(String eventName);
}
