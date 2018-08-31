package org.softuni.residentevil.models.bindingmodels;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.softuni.residentevil.enums.Magnitude;
import org.softuni.residentevil.enums.Mutation;
import org.softuni.residentevil.validation.annotaions.ReleaseDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.List;

import static org.softuni.residentevil.constants.ValidationMessages.*;

public class VirusBindingModel {

    @NotNull(message = NULL_NAME_MESSAGE)
    @Length(min = 3, max = 10, message = INVALID_NAME_LENGTH_MESSAGE)
    private String name;

    @NotNull(message = NULL_DESCRIPTION_MESSAGE)
    @Length(min = 5, max = 100, message = INVALID_DESCRIPTION_LENGTH_MESSAGE)
    private String description;

    @NotNull(message = NULL_MESSAGE)
    @Length(max = 50,message = INVALID_SIDE_EFFECTS_MESSAGE)
    private String sideEffects;

    @NotNull(message = NULL_MESSAGE)
    @Pattern(regexp = "^[C,c]orp$", message = INVALID_CREATOR_MESSAGE)
    private String creator;

    private Boolean isDeadly;

    private Boolean isCurable;

    @NotNull(message = NULL_MUTATION_MESSAGE)
    @Enumerated(EnumType.STRING)
    private Mutation mutation;

    @NotNull(message = NULL_MESSAGE)
    @Range(max = 100, message = INVALID_TURNOVER_RATE_MESSAGE)
    private Integer turnoverRate;

    @NotNull(message = NULL_MESSAGE)
    @Range(min = 1, max = 12, message = INVALID_HOURS_UNTIL_TURN_MESSAGE)
    private Integer hoursUntilTurn;

    @NotNull(message = NULL_MAGNITUDE_MESSAGE)
    @Enumerated(EnumType.STRING)
    private Magnitude magnitude;

    @ReleaseDate(message = INVALID_RELEASE_DATE_MESSAGE)
    private String releasedOn;

    @NotNull(message = NULL_CAPITAL_MESSAGE)
    @NotEmpty(message = NULL_CAPITAL_MESSAGE)
    private List<String> capitals;

    public VirusBindingModel() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSideEffects() {
        return this.sideEffects;
    }

    public void setSideEffects(String sideEffects) {
        this.sideEffects = sideEffects;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getIsDeadly() {
        return this.isDeadly;
    }

    public void setIsDeadly(Boolean deadly) {
        isDeadly = deadly;
    }

    public Boolean getIsCurable() {
        return this.isCurable;
    }

    public void setIsCurable(Boolean curable) {
        isCurable = curable;
    }

    public Mutation getMutation() {
        return this.mutation;
    }

    public void setMutation(Mutation mutation) {
        this.mutation = mutation;
    }

    public Integer getTurnoverRate() {
        return this.turnoverRate;
    }

    public void setTurnoverRate(Integer turnoverRate) {
        this.turnoverRate = turnoverRate;
    }

    public Integer getHoursUntilTurn() {
        return this.hoursUntilTurn;
    }

    public void setHoursUntilTurn(Integer hoursUntilTurn) {
        this.hoursUntilTurn = hoursUntilTurn;
    }

    public Magnitude getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(Magnitude magnitude) {
        this.magnitude = magnitude;
    }

    public String getReleasedOn() {
        return this.releasedOn;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }

    public List<String> getCapitals() {
        return this.capitals;
    }

    public void setCapitals(List<String> capitals) {
        this.capitals = capitals;
    }
}
