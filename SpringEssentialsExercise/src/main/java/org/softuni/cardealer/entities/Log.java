package org.softuni.cardealer.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "logs")
public class Log {

    private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm:ss");

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column
    private String username;

    @Column
    private String operation;

    @Column
    private String modifiedTable;

    @Column
    private LocalDateTime dateTime;

    public Log() {
    }

    public Log(String username, String operation, String modifiedTable, LocalDateTime dateTime) {
        this.username = username;
        this.operation = operation;
        this.modifiedTable = modifiedTable;
        this.dateTime = dateTime;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return this.operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getModifiedTable() {
        return this.modifiedTable;
    }

    public void setModifiedTable(String modifiedTable) {
        this.modifiedTable = modifiedTable;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String extractDateTime() {
        return this.dateTime.format(DATETIME_FORMATTER);
    }
}
