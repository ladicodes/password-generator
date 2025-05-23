package com.example.passwordgenerator;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Generator {
    public Alphabet alphabet;

    public Generator(boolean includeUpper, boolean includeLower, boolean includeNum, boolean includeSym) {
        alphabet = new Alphabet(includeUpper, includeLower, includeNum, includeSym);
    }

    public Password generatePassword(int length) {
        final StringBuilder pass = new StringBuilder();
        final int alphabetLength = alphabet.getAlphabet().length();
        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * range) + min;
            pass.append(alphabet.getAlphabet().charAt(index));
        }
        return new Password(pass.toString());
    }

    public String checkPassword(String password) {
        return new Password(password).evaluateStrength();
    }

    public String getUsefulInfo() {
        StringBuilder info = new StringBuilder();
        info.append("- Use a minimum password length of 8 or more characters if permitted\n");
        info.append("- Include lowercase and uppercase alphabetic characters, numbers and symbols if permitted\n");
        info.append("- Generate passwords randomly where feasible\n");
        info.append("- Avoid using the same password twice\n");
        info.append("- Avoid character repetition, keyboard patterns, dictionary words, etc.\n");
        info.append("- Avoid using information that others might know about the user\n");
        info.append("- Do not use passwords consisting wholly of weak components");
        return info.toString();
    }

    public String savePassword(String password, String site) {
        if (password == null || password.isEmpty() || site == null || site.trim().isEmpty()) {
            return "Error: Password or site name is empty.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-mm-dd-MMMM-yyyy");
        String timestamp = LocalDateTime.now().format(formatter);
        String filename = "C:\\Users\\showict\\Desktop\\passwordGen\\Generated_password(" + timestamp + ").txt";
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("\nName of Site: " + site + "\nPassword Generated: " + password + "\nDate & Time Generated: " + timestamp);
            return "Password saved successfully to Desktop!";
        } catch (IOException e) {
            return "Failed to save password: " + e.getMessage();
        }
    }
}