package com.travelcompany.eshop.validator;

import com.travelcompany.eshop.model.Customer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class CustomerValidator {


    public boolean supports(Class<?> clazz) {
        return Optional.class.equals(clazz);
    }


    public boolean isValidName(String name) {
        String regex = "^[\\p{L} .'-]+$";
        if (name == null)
            return false;
        return name.matches(regex);
    }

    public boolean isValidNumber(String number) {
        String regex = ".*[0-9].*";
        if (number == null)
            return false;
        return number.matches(regex);
    }

    public boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (email == null)
            return false;
        return email.matches(regex);
    }
    public <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.name().equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidIATACode(String city) {
        String regex = "[a-zA-Z]+";
        if (city == null)
            return false;
        return city.matches(regex) && city.length() == 3;
    }


    public boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr,DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}


