package support;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.stats.GameUnit;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtils {

    private static final String TEST_JSON_PATH = "src/main/resources/test.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static List<GameUnit> readUnits(String path) {
        try {
            File file = new File(path);
            file.createNewFile();
            return mapper.readValue(file, new TypeReference<List<GameUnit>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<GameUnit> readUnits() {
        return readUnits(TEST_JSON_PATH);
    }

    public static void writeUnits(List<GameUnit> gameUnits, String path) {
        try {
            File file = new File(path);
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, gameUnits);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeUnits(List<GameUnit> gameUnits) {
        writeUnits(gameUnits, TEST_JSON_PATH);
    }

    public static void initializeUnits() {
        // TODO will read unit info from JSON and initialize the static variables on Units class
        // Units.DRONE, etc
    }
}
