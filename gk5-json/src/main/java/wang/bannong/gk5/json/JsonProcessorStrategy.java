package wang.bannong.gk5.json;

public class JsonProcessorStrategy {
    public static JsonProcessor processor() {
        return new JacksonProcessor();
    }
}
