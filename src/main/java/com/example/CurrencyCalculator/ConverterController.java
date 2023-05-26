package com.example.CurrencyCalculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
public class ConverterController {

    private CurrencyFromTo currencyFromTo = new CurrencyFromTo("USD", "SEK");
    private final CurrencyConverter currencyConverter = new CurrencyConverter();
    private double USDtoNOK = 0.0;
    private double USDtoEUR = 0.0;
    private double USDtoSEK = 0.0;
    private double SEKtoNOK = 0.0;

    @GetMapping("/")
    public String home(Model model) throws IOException {
        //Simple check to prevent unnecessary API requests
        if (USDtoNOK == 0.0) {
            setDefaultCurrencies();
        }
        model.addAttribute("currencyClass", new CurrencyFromTo());
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("result", currencyConverter.rate(currencyFromTo.getFrom(), currencyFromTo.getTo()));
        model.addAttribute("selectedFrom", Currency.valueOf(currencyFromTo.getFrom()));
        model.addAttribute("selectedTo", Currency.valueOf(currencyFromTo.getTo()));
        //default currencies at the top of the page
        model.addAttribute("USDtoNOK", USDtoNOK);
        model.addAttribute("SEKtoNOK", SEKtoNOK);
        model.addAttribute("USDtoEUR", USDtoEUR);
        model.addAttribute("USDtoSEK", USDtoSEK);
        return "Main";
    }

    @PostMapping("/getRate")
    public String getCurrencies(@ModelAttribute CurrencyFromTo currencyClass) throws IOException {
        currencyFromTo = currencyClass;
        return "redirect:/";
    }


    public void setDefaultCurrencies() throws IOException {
        USDtoNOK = currencyConverter.rateUSDtoNOK();
        USDtoEUR = currencyConverter.rateUSDtoEUR();
        USDtoSEK = currencyConverter.rateUSDtoSEK();
        SEKtoNOK = currencyConverter.rateSEKtoNOK();
    }
}
