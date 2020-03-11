package wang.bannong.gk5.test.mybatisx.kafka;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by bn. on 2019/8/28 4:01 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long   orderId;
    private String orderNum;
    private Date   createTime;
}
