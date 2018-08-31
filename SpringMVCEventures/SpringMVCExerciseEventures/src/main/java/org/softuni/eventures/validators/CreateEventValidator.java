package org.softuni.eventures.validators;

import org.softuni.eventures.eceptions.FormArgumentException;
import org.softuni.eventures.models.binding.CreateEventBindingModel;
import org.softuni.eventures.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.softuni.eventures.consts.DateTimeFormat.DATE_TIME_FORMATTER;

@Component
public class CreateEventValidator implements Validator {

    private static final String GENERIC_NOT_EMPTY_MESSAGE = "Field cannot be empty";
    private static final String GENERIC_OUT_OF_RANGE_MESSAGE = "Field value is out of range";
    private static final String NAME_EXISTS_MESSAGE = "Event with this name already exists";
    private static final String INVALID_START_MESSAGE = "Start cannot be before tomorrow";
    private static final String INVALID_END_MESSAGE = "End cannot be before start";
    private static final String INVALID_NAME_LENGTH_MESSAGE = "Name should be at least 10 symbols";
    private static final String INVALID_TICKET_COUNT_MESSAGE = "Tickets should be at least 1";

    private final EventRepository eventRepository;

    @Autowired
    public CreateEventValidator(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public void validate(Object target, Errors errors) {
        CreateEventBindingModel model = (CreateEventBindingModel) target;

        try {
            validateName(model, errors);
            validatePlace(model, errors);
            validateDates(model, errors);
            validateTotalTickets(model, errors);
            validatePricePerTickets(model, errors);
        } catch (Exception e) {
            throw new FormArgumentException();
        }
    }

    private void validateName(CreateEventBindingModel model, Errors errors) {
        if (model.getName() == null || model.getName().isEmpty()) {
            errors.rejectValue("name", "EMPTY_NAME", GENERIC_NOT_EMPTY_MESSAGE);
        } else if (model.getName().length() < 10) {
            errors.rejectValue("name", "INVALID_NAME_LENGTH", INVALID_NAME_LENGTH_MESSAGE);
        } else if (this.eventRepository.eventNameExists(model.getName())) {
            errors.rejectValue("name", "NAME_EXISTS", NAME_EXISTS_MESSAGE);
        }
    }

    private void validatePlace(CreateEventBindingModel model, Errors errors) {
        if (model.getPlace() == null || model.getPlace().isEmpty()) {
            errors.rejectValue("place", "EMPTY_PLACE", GENERIC_NOT_EMPTY_MESSAGE);
        }
    }

    private void validateDates(CreateEventBindingModel model, Errors errors) {
        LocalDateTime start = LocalDateTime.parse(model.getStart(), DATE_TIME_FORMATTER);
        LocalDateTime now = LocalDateTime.now();
        if (start.isBefore(now) || start.isEqual(now)) {
            errors.rejectValue("start", "START_BEFORE_TODAY", INVALID_START_MESSAGE);
        }

        LocalDateTime end = LocalDateTime.parse(model.getEnd(), DATE_TIME_FORMATTER);
        if (end.isBefore(start) || end.isEqual(start)) {
            errors.rejectValue("end", "END_BEFORE_START", INVALID_END_MESSAGE);
        }
    }

    private void validateTotalTickets(CreateEventBindingModel model, Errors errors) {
        if (model.getTotalTickets() < 1) {
            errors.rejectValue("totalTickets", "INVALID_TOTAL_TICKETS", INVALID_TICKET_COUNT_MESSAGE);
        }
    }

    private void validatePricePerTickets(CreateEventBindingModel model, Errors errors) {
        if (model.getPricePerTicket().compareTo(BigDecimal.ZERO) < 0) {
            errors.rejectValue("pricePerTicket", "NEGATIVE_PRICE_PER_TICKET", GENERIC_OUT_OF_RANGE_MESSAGE);
        }
    }
}
