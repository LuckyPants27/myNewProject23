package pro.sky.calculator.controller;

import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.calculator.service.CalculatorService;
import pro.sky.calculator.service.impl.CalculatorServiceImpl;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorServiceImpl calculatorServiceImpl;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorServiceImpl = new CalculatorServiceImpl();
    }

    @GetMapping(value = "/plus")
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return calculatorServiceImpl.process(a, b, "+");
    }

    @GetMapping(value = "/minus")
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return calculatorServiceImpl.process(a, b, "-");
    }

    @GetMapping(value = "/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {
        return calculatorServiceImpl.process(a, b, "*");
    }

    @GetMapping(value = "/divide")
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        return calculatorServiceImpl.process(a, b, "/");
    }

}
