package task3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FillReportJson {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Необходимо передать три аргумента: путь к tests.json, values.json и output.json.");
            return;
        }

        String testsFilePath = args[0];
        String valuesFilePath = args[1];
        String outputFilePath = args[2];

        try {
            // Чтение входных JSON файлов
            String testsJsonContent = new String(Files.readAllBytes(Paths.get(testsFilePath)));
            String valuesJsonContent = new String(Files.readAllBytes(Paths.get(valuesFilePath)));

            // Преобразуем в JSON объекты
            JSONObject testsJson = new JSONObject(testsJsonContent);
            JSONArray testsArray = testsJson.getJSONArray("tests");

            JSONObject valuesJson = new JSONObject(valuesJsonContent);
            JSONArray valuesArray = valuesJson.getJSONArray("values");

            // Создаем отображение ID в значениях для быстрого поиска
            Map<Integer, String> valuesMap = new HashMap<>();
            for (int i = 0; i < valuesArray.length(); i++) {
                JSONObject valueObj = valuesArray.getJSONObject(i);
                int id = valueObj.getInt("id");
                String value = valueObj.getString("value");
                valuesMap.put(id, value);
            }

            // Заполняем значения в tests.json
            fillValues(testsArray, valuesMap);

            // Записываем результат в файл report.json
            Files.write(Paths.get(outputFilePath), testsJson.toString(4).getBytes());

            System.out.println("Отчет успешно сгенерирован: " + outputFilePath);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }

    // Рекурсивная функция для заполнения значений
    private static void fillValues(JSONArray testsArray, Map<Integer, String> valuesMap) {
        for (int i = 0; i < testsArray.length(); i++) {
            JSONObject testObj = testsArray.getJSONObject(i);

            // Заполняем значение для текущего теста
            int testId = testObj.getInt("id");
            if (valuesMap.containsKey(testId)) {
                testObj.put("value", valuesMap.get(testId));
            }

            // Если есть вложенные значения, вызываем рекурсивно
            if (testObj.has("values")) {
                JSONArray subTestsArray = testObj.getJSONArray("values");
                fillValues(subTestsArray, valuesMap);
            }
        }
    }
}