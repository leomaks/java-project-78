package hexlet.code;
//import hexlet.code.Validator;
import hexlet.code.schemas.NumberSchema;
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

    @Test
    public final void appTestNumber() throws Exception {
        boolean expected;
        boolean actual;

        var v = new Validator();
        NumberSchema schema = v.number();

        actual = schema.isValid(null); // true
        expected = true;
        assertEquals(expected, actual);

        actual = schema.positive().isValid(null); //true
        expected = true;
        assertEquals(expected, actual);

        schema.required();

        actual = schema.isValid(null); // false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid("5"); // false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid(10); // true
        expected = true;
        assertEquals(expected, actual);

        actual = schema.isValid(-10); // false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid(0); // false
        expected = false;
        assertEquals(expected, actual);

        schema.range(5, 10);

        actual = schema.isValid(5); // true
        expected = true;
        assertEquals(expected, actual);

        actual = schema.isValid(10); // true
        expected = true;
        assertEquals(expected, actual);

        actual = schema.isValid(4); // false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid(11); // false
        expected = false;
        assertEquals(expected, actual);
    }
}
/*


//  Ноль — не положительное число


schema.isValid(10); // true
schema.isValid(4); // false
schema.isValid(11); // false
*/

