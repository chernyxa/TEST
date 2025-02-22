package task3;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FillReportJson {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Usage: java FillReportJson <values.json> <tests.json> <report.json>");
            return;
        }

        String valuesPath = args[0];
        String testsPath = args[1];
        String reportPath = args[2];

        try {
            ObjectMapper mapper = new ObjectMapper();

            // Чтение файлов
            JsonNode valuesNode = mapper.readTree(new File(valuesPath));
            JsonNode testsNode = mapper.readTree(new File(testsPath));

            // Создаем карту id → value
            Map<Integer, String> valuesMap = new HashMap<>();
            JsonNode valuesArray = valuesNode.get("values");
            if (valuesArray != null && valuesArray.isArray()) {
                for (JsonNode value : valuesArray) {
                    valuesMap.put(value.get("id").asInt(), value.get("value").asText());
                }
            } else {
                System.out.println("Поле 'values' не найдено или не является массивом!");
            }

            // Заполняем тесты результатами
            fillTestValues(testsNode, valuesMap);

            // Сохраняем результат
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportPath), testsNode);

            System.out.println("Report generated: " + reportPath);
        } catch (IOException e) {
            System.out.println("Error processing files: " + e.getMessage());
        }
    }

    private static void fillTestValues(JsonNode node, Map<Integer, String> valuesMap) {
        if (node.isArray()) {
            for (JsonNode item : node) {
                fillTestValues(item, valuesMap);
            }
        } else if (node.isObject()) {
            if (node.has("id") && node.has("value")) {
                int id = node.get("id").asInt();
                if (valuesMap.containsKey(id)) {
                    ((ObjectNode) node).put("value", valuesMap.get(id));
                }
            }
            if (node.has("values")) {
                fillTestValues(node.get("values"), valuesMap);
            }
        }
    }
}