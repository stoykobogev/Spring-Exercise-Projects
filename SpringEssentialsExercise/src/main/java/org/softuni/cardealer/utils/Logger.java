package org.softuni.cardealer.utils;

import org.softuni.cardealer.entities.Log;
import org.softuni.cardealer.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Component
public class Logger {

    private LogRepository logRepository;

    @Autowired
    public Logger(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public void logEvent(HttpSession session, String operation, String modifiedTable) {
        String username = (String) session.getAttribute("username");
        LocalDateTime dateTime = LocalDateTime.now();
        Log log = new Log(username, operation, modifiedTable, dateTime);
        this.logRepository.saveAndFlush(log);
    }
}
