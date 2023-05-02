package hexlet.code;

import hexlet.code.schemes.BaseSchema;
import hexlet.code.schemes.MapSchema;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static org.assertj.core.api.Assertions.assertThat;

public class MapSchemaTest {

    @Test
    public void testMapSchema() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid('a')).isFalse();
        assertThat(schema.isValid(true)).isFalse();
        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid(new ArrayList<>())).isFalse();
        assertThat(schema.isValid(new TreeMap<>())).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();
        Map<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(data)).isTrue();

        schema.sizeof(2);
        assertThat(schema.isValid(data)).isFalse();

        data.put("key2", "value2");
        assertThat(schema.isValid(data)).isTrue();
    }

    @Test
    public void testShapeOfMapSchema() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Validator validator = new Validator();
        MapSchema schema = validator.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", validator.string().required());
        schemas.put("age", validator.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertThat(schema.isValid(human1)).isTrue();

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertThat(schema.isValid(human2)).isTrue();

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertThat(schema.isValid(human3)).isFalse();

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Vallya");
        human4.put("age", -5);
        assertThat(schema.isValid(human4)).isFalse();

        Map<String, BaseSchema> schemas2 = new HashMap<>();
        schemas2.put("name", validator.string().required().contains("lya").minLength(6));
        schemas2.put("age", validator.number());
        schema.shape(schemas2);

        assertThat(schema.isValid(human1)).isFalse();
        assertThat(schema.isValid(human2)).isFalse();
        assertThat(schema.isValid(human4)).isTrue();
    }
}















