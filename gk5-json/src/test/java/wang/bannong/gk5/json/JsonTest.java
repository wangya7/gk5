package wang.bannong.gk5.json;

import java.util.List;
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
    public void toJavaListTest() {
        List<Staff> staff = Json.toJavaList("[{\"age\":23,\"name\":\"Tom\",\"occupation\":\"Teacher\"},{\"age\":20,\"name\":\"Jerry\",\"occupation\":\"Doctor\"}]", Staff.class);
        Assert.assertNotNull(staff);
        Assert.assertEquals(staff.size(), 2);
        Assert.assertEquals(staff.get(0).getOccupation(), "Teacher");
        Assert.assertEquals(staff.get(1).getName(), "Jerry");
    }

    @Test
    public void toJsonTest() {
        Staff tom = new Staff();
        tom.setAge(23);
        tom.setName("Tom");
        tom.setOccupation("Teacher");
        Staff staff = Json.toJavaObject(Json.toJson(tom), Staff.class);
        Assert.assertNotNull(staff);
        Assert.assertEquals(staff.getName(), "Tom");
        Assert.assertEquals(staff.getOccupation(), "Teacher");
        Assert.assertEquals(Optional.ofNullable(staff.getAge()), Optional.ofNullable(23));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("Teacher", Json.get("{\"age\":23,\"name\":\"Tom\",\"occupation\":\"Teacher\"}", "occupation"));
    }

    @Test
    public void getIntTest() {
        Assert.assertEquals(23, Json.getInt("{\"age\":23,\"name\":\"Tom\",\"occupation\":\"Teacher\"}", "age"));
    }

}
