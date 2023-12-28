package hexlet.code;
//import hexlet.code.Validator;
import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapSchema;

//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

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

        actual =   schema.minLength(10).isValid("what"); // false
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

    @Test
    public final void appTestMap() throws Exception {
        boolean expected;
        boolean actual;

        var v = new Validator();
        MapSchema schema = v.map();

        actual = schema.isValid(null); // true
        expected = true;
        assertEquals(expected, actual);

        schema.required();

        actual = schema.isValid(null); //false
        expected = false;
        assertEquals(expected, actual);

        actual = schema.isValid(new HashMap()); // true
        expected = true;
        assertEquals(expected, actual);

        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        actual = schema.isValid(data); // true
        expected = true;
        assertEquals(expected, actual);

        schema.sizeof(2);

        actual = schema.isValid(data); // false
        expected = false;
        assertEquals(expected, actual);

        data.put("key2", "value2");
        actual = schema.isValid(data); // true
        expected = true;
        assertEquals(expected, actual);

    }

    @Test
    public final void appTestMapShape() throws Exception {
        boolean expected;
        boolean actual;

        var v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());

        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);


        actual =  schema.isValid(human1); // true
        expected = true;
        assertEquals(expected, actual);

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);


        actual =  schema.isValid(human2); // true
        expected = true;
        assertEquals(expected, actual);

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);


        actual =  schema.isValid(human3); // false
        expected = false;
        assertEquals(expected, actual);

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);


        actual =  schema.isValid(human4); // false
        expected = false;
        assertEquals(expected, actual);

    }
}
