package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    private static Validator v;
    private static MapSchema schema;

    @BeforeAll
    public static void init() {
        v = new Validator();

    }
    @BeforeEach
    public final void beforeEach() {
        schema = v.map();
    }

    @Test
    public final void testNullIsValid() {
        assertTrue(schema.isValid(null));
    }

    @Test
    public final void testRequired() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap()));

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));
    }
    @Test
    public final void testSizeOf() {
        schema.sizeof(2);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");
        assertFalse(schema.isValid(data));

        data.put("key2", "value2");
        assertTrue(schema.isValid(data));
    }
}



