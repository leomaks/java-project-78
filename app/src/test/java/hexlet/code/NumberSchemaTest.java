package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class NumberSchemaTest {

    private static Validator v;
    private static NumberSchema schema;

    @BeforeEach
    public final void beforeEach() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public final void testNullIsValid() throws Exception {
        assertTrue(schema.isValid(null));
    }

    @Test
    public final void testPositive() throws Exception {
        schema.positive();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(89));
        assertFalse(schema.isValid(-89));
    }

    @Test
    public final void testRequired() throws Exception {

        schema.positive().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid("5"));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(-10));
        assertFalse(schema.isValid(0));

    }

    @Test
    public final void testRange() throws Exception {

        schema.range(5, 10);

        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));

    }


}
