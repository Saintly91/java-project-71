import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DifferTest {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Test
    void testGenerateJsonDiff() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;

        String result = Differ.generate(file1, file2);
        assertNotNull(result);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testGenerateYamlDiff() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String expected = """
                {
                    chars1: [a, b, c]
                  - chars2: [d, e, f]
                  + chars2: false
                  - checked: false
                  + checked: true
                  - default: null
                  + default: [value1, value2]
                  - id: 45
                  + id: null
                  - key1: value1
                  + key2: value2
                    numbers1: [1, 2, 3, 4]
                  - numbers2: [2, 3, 4, 5]
                  + numbers2: [22, 33, 44, 55]
                  - numbers3: [3, 4, 5]
                  + numbers4: [4, 5, 6]
                  + obj1: {nestedKey=value, isNested=true}
                  - setting1: Some value
                  + setting1: Another value
                  - setting2: 200
                  + setting2: 300
                  - setting3: true
                  + setting3: none
                }
                """;

        String result = Differ.generate(file1, file2);
        assertNotNull(result);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testGeneratePlainDiff() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String expected = """
        Property 'chars2' was updated. From [complex value] to false
        Property 'checked' was updated. From false to true
        Property 'default' was updated. From null to [complex value]
        Property 'id' was updated. From 45 to null
        Property 'key1' was removed
        Property 'key2' was added with value: 'value2'
        Property 'numbers2' was updated. From [complex value] to [complex value]
        Property 'numbers3' was removed
        Property 'numbers4' was added with value: [complex value]
        Property 'obj1' was added with value: [complex value]
        Property 'setting1' was updated. From 'Some value' to 'Another value'
        Property 'setting2' was updated. From 200 to 300
        Property 'setting3' was updated. From true to 'none'
        """;

        String result = Differ.generate(file1, file2, "plain");
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testJsonFormatterWithJsonFiles() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String actual = Differ.generate(file1, file2, "json");
        assertNotNull(actual);

        String expected = """
                [
                  {"key":"chars1","status":"unchanged","oldValue":["a","b","c"],"newValue":["a","b","c"]},
                  {"key":"chars2","status":"changed","oldValue":["d","e","f"],"newValue":false},
                  {"key":"checked","status":"changed","oldValue":false,"newValue":true},
                  {"key":"default","status":"changed","oldValue":null,"newValue":["value1","value2"]},
                  {"key":"id","status":"changed","oldValue":45,"newValue":null},
                  {"key":"key1","status":"removed","oldValue":"value1","newValue":null},
                  {"key":"key2","status":"added","oldValue":null,"newValue":"value2"},
                  {"key":"numbers1","status":"unchanged","oldValue":[1,2,3,4],"newValue":[1,2,3,4]},
                  {"key":"numbers2","status":"changed","oldValue":[2,3,4,5],"newValue":[22,33,44,55]},
                  {"key":"numbers3","status":"removed","oldValue":[3,4,5],"newValue":null},
                  {"key":"numbers4","status":"added","oldValue":null,"newValue":[4,5,6]},
                  {"key":"obj1","status":"added","oldValue":null,"newValue":{"nestedKey":"value","isNested":true}},
                  {"key":"setting1","status":"changed","oldValue":"Some value","newValue":"Another value"},
                  {"key":"setting2","status":"changed","oldValue":200,"newValue":300},
                  {"key":"setting3","status":"changed","oldValue":true,"newValue":"none"}
                ]
                """;

        JsonNode expectedTree = MAPPER.readTree(expected);
        JsonNode actualTree = MAPPER.readTree(actual);
        assertEquals(expectedTree, actualTree);
    }

    @Test
    void testJsonFormatterWithYamlFiles() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String actual = Differ.generate(file1, file2, "json");
        assertNotNull(actual);

        String expected = """
                [
                  {"key":"chars1","status":"unchanged","oldValue":["a","b","c"],"newValue":["a","b","c"]},
                  {"key":"chars2","status":"changed","oldValue":["d","e","f"],"newValue":false},
                  {"key":"checked","status":"changed","oldValue":false,"newValue":true},
                  {"key":"default","status":"changed","oldValue":null,"newValue":["value1","value2"]},
                  {"key":"id","status":"changed","oldValue":45,"newValue":null},
                  {"key":"key1","status":"removed","oldValue":"value1","newValue":null},
                  {"key":"key2","status":"added","oldValue":null,"newValue":"value2"},
                  {"key":"numbers1","status":"unchanged","oldValue":[1,2,3,4],"newValue":[1,2,3,4]},
                  {"key":"numbers2","status":"changed","oldValue":[2,3,4,5],"newValue":[22,33,44,55]},
                  {"key":"numbers3","status":"removed","oldValue":[3,4,5],"newValue":null},
                  {"key":"numbers4","status":"added","oldValue":null,"newValue":[4,5,6]},
                  {"key":"obj1","status":"added","oldValue":null,"newValue":{"nestedKey":"value","isNested":true}},
                  {"key":"setting1","status":"changed","oldValue":"Some value","newValue":"Another value"},
                  {"key":"setting2","status":"changed","oldValue":200,"newValue":300},
                  {"key":"setting3","status":"changed","oldValue":true,"newValue":"none"}
                ]
                """;

        JsonNode expectedTree = MAPPER.readTree(expected);
        JsonNode actualTree = MAPPER.readTree(actual);
        assertEquals(expectedTree, actualTree);
    }
}
