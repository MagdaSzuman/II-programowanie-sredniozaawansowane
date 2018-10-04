package pl.sda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonApplication {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "{\n" +
                "  \"firstName\": \"Szymon\",\n" +
                "  \"lastName\": \"Nowak\",\n" +
                "  \"age\": 24,\n" +
                "  \"phoneNumbers\": [\n" +
                "    {\n" +
                "      \"number\": \"123456789\",\n" +
                "      \"name\": \"prywatny\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"number\": \"6758493024\",\n" +
                "      \"name\": \"slubowy\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        User user = objectMapper.readValue(json, User.class);
        System.out.println();
    }
}
