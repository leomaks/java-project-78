package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class StringSchemaTest {

    private static Validator v;
    private static StringSchema schema;

    @BeforeAll
    public static void init() {
        v = new Validator();

    }
    @BeforeEach
    public final void beforeEach() {
        schema = v.string();
    }

    @Test
    public final void testNullIsValid() {
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
    }

    @Test
    public final void testRequired() {
        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));

        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid("what does the fox say"));
    }

    @Test
    public final void testContains() {

        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));

        assertFalse(schema.contains("whatthe").isValid("what does the fox say"));
        assertFalse(schema.isValid("what does the fox say"));
    }

    @Test
    public final void testMinLength() {
        schema.minLength(10);

        assertTrue(schema.isValid("what does the fox say"));
        assertFalse(schema.isValid("what"));

        assertFalse(schema.contains("what").isValid("what"));
    }

}


