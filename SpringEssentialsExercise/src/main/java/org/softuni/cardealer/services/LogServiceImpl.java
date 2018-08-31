package org.softuni.cardealer.services;

import org.softuni.cardealer.entities.Log;
import org.softuni.cardealer.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public List<Log> getAllLogs() {
        return this.logRepository.findAllByOrderByDateTimeAsc();
    }

    @Override
    public void clearLogs() {
        this.logRepository.deleteAll();
    }

    @Override
    public List<Log> getLogsByUsername(String username) {
        return this.logRepository.findAllByUsernameOrderByDateTimeAsc(username);
    }
}
