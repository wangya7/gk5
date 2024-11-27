package wang.bannong.gk5.json.processor;

public class JsonProcessorStrategy {
    public static JsonProcessor processor() {
        return new JacksonProcessor();
    }
}
