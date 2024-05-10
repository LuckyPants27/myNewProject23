package pro.sky.calculator.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.calculator.service.CalculatorService;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping(value = "/plus", produces = MediaType.TEXT_HTML_VALUE)
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return process(a, b, "+");
    }

    @GetMapping(value = "/minus", produces = MediaType.TEXT_HTML_VALUE)
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return process(a, b, "-");
    }

    @GetMapping(value = "/multiply", produces = MediaType.TEXT_HTML_VALUE)
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return process(a, b, "*");
    }

    @GetMapping(value = "/divide", produces = MediaType.TEXT_HTML_VALUE)
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        return process(a, b, "/");
    }

    private String checkParameters(Integer num1, Integer num2) {
        if (num1 == null) {
            return "<p style=\"color: red\">Параметр num1 не передан!</p>";
        } else if (num2 == null) {
            return "<p style=\"color: red\">Параметр num2 не передан!</p>";
        }
        return null;
    }

    private String process (Integer a, Integer b, String operation) {
        String checkResult = checkParameters(a, b);
        if (checkResult != null) {
            return checkResult;
        }
        if ("/".equals(operation) && b == 0) {
            return "<p style=\"color: red\">Деление на 0!</p>";
        }
        int result = switch (operation) {
            case "-" -> calculatorService.minus(a, b);
            case "*" -> calculatorService.multiply(a, b);
            case "/" -> calculatorService.divide(a, b);
            default -> calculatorService.plus(a, b);
        };
        return buildResponse(a, b, operation, result);
    }

    private String buildResponse(Integer a, Integer b, String operation, Integer result) {
        return "%d %s %d = %d".formatted(a, operation, b, result);
    }
}
