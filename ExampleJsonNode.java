import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;

public class ExampleJsonNode {

    private static String value = "{\n" +
            "    \"response\": {\n" +
            "        \"features\": {\n" +
            "            \"history\": 1\n" +
            "        }\n" +
            "     },\n" +
            "    \"history\": {\n" +
            "        \"date\": {\n" +
            "            \"pretty\": \"April 13, 2010\",\n" +
            "            \"year\": \"2010\",\n" +
            "            \"mon\": \"04\",\n" +
            "            \"mday\": \"13\",\n" +
            "            \"hour\": \"12\",\n" +
            "            \"min\": \"00\",\n" +
            "            \"tzname\": \"America/Los_Angeles\"\n" +
            "        }\n" +
            "    }\n" +
            "}";
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        JsonNode jsonNode = objectMapper.readValue(value, JsonNode.class);
        System.out.println("===============value = " + jsonNode.get("response").get("features").get("history").asInt());
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Product {
        private String id;
        private String name;
        private String brandName;
        private String ownerName;
    }
}
