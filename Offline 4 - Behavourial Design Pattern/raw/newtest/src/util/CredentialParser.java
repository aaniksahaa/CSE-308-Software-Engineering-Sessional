package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CredentialParser {
    public static HashMap<String, String> parseCredentialFile() {
        String filePath = "src/credentials.txt";
        HashMap<String, String> credentialsMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    String username = parts[0];
                    String password = parts[1];
                    credentialsMap.put(username, password);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return credentialsMap;
    }
    public static void main(String[] args) {
        // Example usage:
        Map<String, String> credentials = parseCredentialFile();
        System.out.println("Parsed Credentials: " + credentials);
    }
}
