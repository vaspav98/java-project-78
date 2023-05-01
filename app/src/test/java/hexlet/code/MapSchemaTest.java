package hexlet.code;

import hexlet.code.schemes.MapSchema;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
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
}















