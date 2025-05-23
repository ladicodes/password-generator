package com.example.passwordgenerator;

public class Password {
    private final String value;

    public Password(String value) {
        if (value == null) throw new IllegalArgumentException("Password cannot be null");
        this.value = value;
    }

    public String toString() {
        return value;
    }

    public String evaluateStrength() {
        if (value.length() < 8) return "Weak";
        int score = (value.matches(".*[A-Z].*") ? 1 : 0) +  // Uppercase
                (value.matches(".*[a-z].*") ? 1 : 0) +  // Lowercase
                (value.matches(".*[0-9].*") ? 1 : 0) +  // Any digit
                (value.matches(".*[!@#$%^&*_~?].*") ? 1 : 0); // Symbols
        return score >= 4 ? "Strong" : score >= 2 ? "Fair" : "Weak";
    }
}