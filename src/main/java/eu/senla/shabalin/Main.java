package eu.senla.shabalin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        User user = new User(1L, "ser@mail.ru", "Sergei", "Shabalin", "/avatar.png");
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(str);
    }
}
