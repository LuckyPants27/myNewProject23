package pro.sky.calculator.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public String checkParameters(Integer num1, Integer num2) {
        if (num1 == null) {
            return "<p style=\"color: red\">Параметр num1 не передан!</p>";
        } else if (num2 == null) {
            return "<p style=\"color: red\">Параметр num2 не передан!</p>";
        }
        return null;
    }
}
