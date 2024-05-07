package galeria.usuarios;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
public class FileUtils {
    private static final String USER_DATA_FILE = "userdata.txt";

  
    private static Map<String, String> userCredentials = new HashMap<>();


    public static void registerUser(String username, String password, String role) throws IOException {
        String userData = username + "," + password + "," + role;
        userCredentials.put(username, userData);
        saveUserCredentials();
    }

    private static void saveUserCredentials() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE))) {
            for (Map.Entry<String, String> entry : userCredentials.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        }
    }

  
    public static void loadUserCredentials() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    String role = parts[2].trim();
                    userCredentials.put(username, username + "," + password + "," + role);
                }
            }
        }
    }

  
    public static boolean verifyUser(String username, String password) {
        if (userCredentials.containsKey(username)) {
            String[] details = userCredentials.get(username).split(",");
            
            if (details.length == 3) {
                String storedPassword = details[1];
                boolean isAuthenticated = storedPassword.equals(password);
                
                return isAuthenticated;
            }
        }
        return false;
    }

    public static String getRole(String username) {
        if (userCredentials.containsKey(username)) {
            String[] parts = userCredentials.get(username).split(",");
            if (parts.length == 3) {
                String role = parts[2].trim();
                
                return role;
            }
        }
        return null;
    }

   
    public static boolean isUserAuthorized(String username, String operation) {
        if (userCredentials.containsKey(username)) {
            String[] details = userCredentials.get(username).split(",");
            String role = details[1];
       
            return checkAuthorization(role, operation);
        }
        return false;
    }

    private static boolean checkAuthorization(String role, String operation) {
    
        switch (role) {
            case "Administrador":
                return true; 
            case "Cajero":
                return operation.equals("ProcesandoPago");
            case "CompradorPropietario":
            	return true;
            case "Operador":
                return operation.equals("registrandoOferta");
            default:
                return false;
        }
    }
}
