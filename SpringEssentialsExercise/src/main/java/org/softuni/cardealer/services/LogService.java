package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Log;

import java.util.List;

public interface LogService {
    List<Log> getAllLogs();

    void clearLogs();

    List<Log> getLogsByUsername(String username);
}
