package wang.bannong.gk5.json;

import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

public class JsonTest {

    @Test
    public void isJsonTest() {
        Assert.assertFalse(Json.isJson(""));
        Assert.assertTrue(Json.isJson("{}"));
        Assert.assertTrue(Json.isJson("{\"age\":23,\"name\":\"Tome\",\"occupation\":\"Teacher\"}"));
        Assert.assertTrue(Json.isJson("[{\"age\":23,\"name\":\"Tome\",\"occupation\":\"Teacher\"}]"));
        Assert.assertFalse(Json.isJson("{\"name\"=\"Tome\"}"));
    }

    @Test
    public void toJavaObjectTest() {
        Staff tom = Json.toJavaObject("{\"age\":23,\"name\":\"Tom\",\"occupation\":\"Teacher\"}", Staff.class);
        Assert.assertNotNull(tom);
        Assert.assertEquals(tom.getName(), "Tom");
        Assert.assertEquals(tom.getOccupation(), "Teacher");
        Assert.assertEquals(Optional.ofNullable(tom.getAge()), Optional.ofNullable(23));
    }

    @Test
    public void toJavaList() {

    }

    @Test
    public void toJson() {

    }

}
