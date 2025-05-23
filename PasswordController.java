package com.example.passwordgenerator;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordController {
    @PostMapping("/generate")
    public String generate(@RequestBody GenerateRequest request) {
        if (!request.uppercase && !request.lowercase && !request.numbers && !request.symbols) {
            return "Error: At least one character type must be selected.";
        }
        if (request.length <= 0) {
            return "Error: Length must be positive.";
        }
        Generator generator = new Generator(request.uppercase, request.lowercase, request.numbers, request.symbols);
        return generator.generatePassword(request.length).toString();
    }

    @PostMapping("/check")
    public String check(@RequestBody CheckRequest request) {
        if (request.password == null || request.password.isEmpty()) {
            return "Error: Password is empty.";
        }
        Generator generator = new Generator(false, false, false, false);
        return generator.checkPassword(request.password);
    }

    @GetMapping("/info")
    public String info() {
        Generator generator = new Generator(false, false, false, false);
        return generator.getUsefulInfo();
    }

    @PostMapping("/save")
    public String save(@RequestBody SaveRequest request) {
        Generator generator = new Generator(false, false, false, false);
        return generator.savePassword(request.password, request.site);
    }
}

class GenerateRequest {
    public boolean uppercase, lowercase, numbers, symbols;
    public int length;
}

class CheckRequest {
    public String password;
}

class SaveRequest {
    public String password, site;
}