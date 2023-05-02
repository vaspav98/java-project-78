package hexlet.code;

import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import static org.assertj.core.api.Assertions.assertThat;

public class NumberSchemaTest {

    @Test
    public void testNumberSchema() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        assertThat(schema.isValid('a')).isFalse();
        assertThat(schema.isValid(true)).isFalse();
        assertThat(schema.isValid("hexlet")).isFalse();
        assertThat(schema.isValid(new HashMap<>())).isFalse();
        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(null)).isTrue();

        schema.positive();
        schema.required();

        assertThat(schema.isValid(null)).isFalse();
        assertThat(schema.isValid(0)).isFalse();
        assertThat(schema.isValid(-1)).isFalse();

        schema.range(5, 10);

        assertThat(schema.isValid(5)).isTrue();
        assertThat(schema.isValid(10)).isTrue();
        assertThat(schema.isValid(4)).isFalse();
        assertThat(schema.isValid(11)).isFalse();
    }

    @Test
    public void testNumberSchema2() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Validator validator = new Validator();
        NumberSchema schema = validator.number();

        schema.positive();
        assertThat(schema.isValid(null)).isTrue();

        schema.range(5, 10);
        assertThat(schema.isValid(null)).isFalse();
    }
}
