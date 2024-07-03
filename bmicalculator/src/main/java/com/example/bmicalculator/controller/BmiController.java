package com.example.bmicalculator.controller;

import com.example.bmicalculator.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BmiController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("user", new User());
        return "index";
    }

    @PostMapping("/calculate")
    public String calculateBmi(@ModelAttribute User user, Model model) {
        double heightInMeters = user.getHeight() / 100.0;
        double bmi = user.getWeight() / (heightInMeters * heightInMeters);
        String bmiCategory;

        if (bmi < 18.5) {
            bmiCategory = "Niedowaga";
        } else if (bmi < 25) {
            bmiCategory = "Waga prawidłowa";
        } else if (bmi < 30) {
            bmiCategory = "Nadwaga";
        } else {
            bmiCategory = "Otyłość";
        }

        model.addAttribute("userName", user.getName());
        model.addAttribute("bmi", String.format("%.2f", bmi));
        model.addAttribute("bmiCategory", bmiCategory);

        return "result";
    }
}
