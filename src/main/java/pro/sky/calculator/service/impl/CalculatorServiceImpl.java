package pro.sky.calculator.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.calculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

    ValidationService validationService = new ValidationService();
    @Override
    public int plus(int a, int b) {
        return a + b;
    }

    @Override
    public int minus(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    public String process (Integer a, Integer b, String operation) {
        String checkResult = validationService.checkParameters(a, b);
        if (checkResult != null) {
            return checkResult;
        }
        if ("/".equals(operation) && b == 0) {
            return "<p style=\"color: red\">Деление на 0!</p>";
        }
        int result = switch (operation) {
            case "-" -> minus(a, b);
            case "*" -> multiply(a, b);
            case "/" -> divide(a, b);
            default -> plus(a, b);
        };
        return buildResponse(a, b, operation, result);
    }

    private String buildResponse(Integer a, Integer b, String operation, Integer result) {
        return "%d %s %d = %d".formatted(a, operation, b, result);
    }
}
