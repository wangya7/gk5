package wang.bannong.gk5.test.mybatisx.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car implements Serializable {
    private static final long serialVersionUID = 3249008667320401614L;
    private String brand;
    private Long   price;
}
