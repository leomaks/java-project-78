package hexlet.code;

import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MapSchemaTest {

    private static Validator v;
    private static MapSchema schema;


    @BeforeEach
    public final void beforeEach() {
        v = new Validator();
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
    public final void testSizeOfNull() {
        assertTrue(schema.sizeof(0).isValid(new HashMap<>()));
    }

    @Test
    public final void testSizeOf() {

        schema.sizeof(3);

        Map<String, Object> data = new HashMap<>();
        data.put("key1", "value1");
        data.put("key2", 3);

        assertFalse(schema.isValid(data));

        data.put("key3", "value2");
        assertTrue(schema.isValid(data));
    }


}



