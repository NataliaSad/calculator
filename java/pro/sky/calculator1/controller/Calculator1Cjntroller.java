package pro.sky.calculator1.controller;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.calculator1.service.CalculatorService;
import java.awt.*;
@RestController
public class Calculator1Cjntroller {
    private final CalculatorService calculatorService;

    public Calculator1Cjntroller(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @GetMapping(path = "/calculator")
    public String hello() {
        return " Добро пожаловать в калькулятор ";
    }

    @GetMapping("/calculator/plus")
    public String plus(@RequestParam(value = "num1", required = false) Integer a,
                       @RequestParam(value = "num2", required = false) Integer b) {

        return buildView(a, b, calculatorService.plus(a, b), " + ");
    }

    @GetMapping("/calculator/minus")
    public String minus(@RequestParam(value = "num1", required = false) Integer a,
                        @RequestParam(value = "num2", required = false) Integer b) {

        return buildView(a, b, calculatorService.minus(a, b), " - ");
    }

    @GetMapping("/calculator/multiply")
    public String multiply(@RequestParam(value = "num1", required = false) Integer a,
                           @RequestParam(value = "num2", required = false) Integer b) {

        return buildView(a, b, calculatorService.multiply(a, b), " * ");
    }

    @GetMapping("/calculator/divide")
    public String divide(@RequestParam(value = "num1", required = false) Integer a,
                         @RequestParam(value = "num2", required = false) Integer b) {
        return buildView(a, b, calculatorService.divide(a, b), " / ");
    }

    private String buildView(Integer a, Integer b,
                             Number result,
                             String operation) {
        if (a == null || b == null) {
            return " Не передан один из параетров! ";
        }
        if (" / ".equals(operation) && b == 0) {
            return " На ноль делить нельзя! ";
        }
        switch (operation) {
                case " - ":
                    result = calculatorService.minus(a, b);
                    break;
                case " * ":
                    result = calculatorService.multiply(a, b);
                    break;
                case " / ":
                    result = calculatorService.divide(a, b);
                    break;
                default:
                    result = calculatorService.plus(a, b);
            }

            return a + " " + operation + " " + b + " = " + result;
        }

    }

