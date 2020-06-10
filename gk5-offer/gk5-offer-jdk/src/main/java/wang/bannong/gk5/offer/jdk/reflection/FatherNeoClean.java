package wang.bannong.gk5.offer.jdk.reflection;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class FatherNeoClean extends NeoClean {
    private String name;
}