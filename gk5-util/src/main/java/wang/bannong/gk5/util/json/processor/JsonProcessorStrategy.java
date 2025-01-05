package wang.bannong.gk5.util.json.processor;

public class JsonProcessorStrategy {
    public static JsonProcessor processor() {
        return new JacksonProcessor();
    }
}
