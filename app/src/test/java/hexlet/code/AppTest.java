package hexlet.code;
//import hexlet.code.Validator;
import hexlet.code.schemas.StringSchema;


//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public final void appTestString() throws Exception {
        boolean expected;
        boolean actual;

        var v = new Validator();
        StringSchema schema = v.string();
        actual = schema.isValid(""); // true
        expected = true;
        assertEquals(expected, actual);

        actual = schema.isValid(null);  // true
        expected = true;
        assertEquals(expected, actual);

        schema.required();

        actual = schema.isValid(null);
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid("");
        expected = false;
        assertEquals(expected, actual);
// Пока не вызван метод required(), null и пустая строка считаются валидным
        // true

        actual = schema.isValid(5);  // true
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid("what does the fox say");  // true
        expected = true;
        assertEquals(expected, actual);


        actual = schema.isValid("hexlet");  // true
        expected = true;
        assertEquals(expected, actual);


        actual = schema.contains("wh").isValid("what does the fox say"); // true
        expected = true;
        assertEquals(expected, actual);

        actual =  schema.contains("what").isValid("what does the fox say"); // true
        expected = true;
        assertEquals(expected, actual);


        actual =   schema.contains("whatthe").isValid("what does the fox say"); // false
        expected = false;
        assertEquals(expected, actual);


        actual =   schema.isValid("what does the fox say"); // false
        expected = false;
        assertEquals(expected, actual);


    }
}
