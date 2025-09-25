import hexlet.code.Differ;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DifferTest {

    @Test
    void testGenerateReturnsNotNull() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String result = Differ.generate(file1, file2);

        assertNotNull(result);
    }

    @Test
    void testGenerateParsesFiles() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String result = Differ.generate(file1, file2);

        assertTrue(result.contains("hexlet.io"));
        assertTrue(result.contains("timeout"));
    }

    @Test
    void testGenerateShowDiff() throws Exception {
        String file1 = "src/test/resources/file1.json";
        String file2 = "src/test/resources/file2.json";

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String result = Differ.generate(file1, file2);

        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void testGenerateYamlReturnsNotNull() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String result = Differ.generate(file1, file2);

        assertNotNull(result);
    }

    @Test
    void testGenerateYamlParsesFiles() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String result = Differ.generate(file1, file2);

        assertTrue(result.contains("hexlet.io"));
        assertTrue(result.contains("timeout"));
    }

    @Test
    void testGenerateYamlShowDiff() throws Exception {
        String file1 = "src/test/resources/file1.yml";
        String file2 = "src/test/resources/file2.yml";

        String expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }
                """;
        String result = Differ.generate(file1, file2);

        assertEquals(expected.trim(), result.trim());
    }
}
