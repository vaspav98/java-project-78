package hexlet.code;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    @Test
    public void testStringSchema() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Validator validator = new Validator();
        StringSchema schema = validator.string();

        assertThat(schema.isValid(5)).isFalse();
        assertThat(schema.isValid('a')).isFalse();
        assertThat(schema.isValid(true)).isFalse();

        assertThat(schema.isValid("")).isTrue();
        assertThat(schema.isValid(null)).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.required();

        assertThat(schema.isValid("")).isFalse();
        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid("hexlet")).isTrue();

        schema.minLength(4);

        assertThat(schema.isValid("hex")).isFalse();
        assertThat(schema.isValid("hexl")).isTrue();

        schema.contains("wh");

        assertThat(schema.isValid("what does the fox say")).isTrue();

        schema.contains("whatt");

        assertThat(schema.isValid("what does the fox say")).isFalse();
    }
}














