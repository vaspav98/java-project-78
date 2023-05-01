package hexlet.code;


public class App {
    public static void main(String[] args) throws Exception {
        Validator val = new Validator();
        var schema = val.string().minLength(5).contains("mam");


    }
}
