package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapShapeTest {

    private static Validator v;
    private static MapSchema schema;

    @BeforeAll
    public static void init() {
        v = new Validator();
        schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(4));
        schemas.put("age", v.number().positive());

        schema.shape(schemas);
    }


    @Test
    public final void testIsValid() {
        Map<String, Object> human = new HashMap<>();
        human.put("name", "Kolya");
        human.put("age", 100);


        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required().minLength(4));
        schemas.put("age", v.number().required().positive());

        schema.shape(schemas);

        assertTrue(schema.isValid(human));
    }

    @Test
    public final void testNullEmpty() {
        Map<String, Object> human2 = new HashMap<>();

        human2.put("name", "Maya");
        human2.put("age", 11);

        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);

        assertFalse(schema.isValid(human3));
    }

    @Test
    public final void testPositive() {

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);

        assertFalse(schema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", "leon");
        human5.put("age", 6);

        assertTrue(schema.isValid(human5));
    }

    @Test
    public final void testMinLenth() {

        Map<String, Object> human6 = new HashMap<>();
        human6.put("name", "leo");
        human6.put("age", 5);

        assertFalse(schema.isValid(human6));

        Map<String, Object> human7 = new HashMap<>();
        human7.put("name", "leon");
        human7.put("age", 5);

        assertTrue(schema.isValid(human7));

    }

}


