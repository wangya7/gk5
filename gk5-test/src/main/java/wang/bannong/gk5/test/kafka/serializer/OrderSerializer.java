package wang.bannong.gk5.test.kafka.serializer;

import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import wang.bannong.gk5.test.kafka.Order;

/**
 * Created by bn. on 2019/8/28 4:00 PM
 */
public class OrderSerializer implements Serializer<Order> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Order data) {
        if (null == data) {
            return null;
        }

        byte[] orderId;
        byte[] orderNum;
        byte[] createTime;

        if (data.getOrderId() != null) {
            orderId = String.valueOf(data.getOrderId()).getBytes();
        } else {
            orderId = new byte[0];
        }

        if (data.getOrderNum() != null) {
            orderNum = data.getOrderNum().getBytes();
        } else {
            orderNum = new byte[0];
        }

        if (data.getCreateTime() != null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            createTime = dateFormat.format(data.getCreateTime()).getBytes();
        } else {
            createTime = new byte[0];
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(31 + orderId.length + orderNum.length + createTime.length);
        byteBuffer.putInt(orderId.length);
        byteBuffer.put(orderId);
        byteBuffer.putInt(orderNum.length);
        byteBuffer.put(orderNum);
        byteBuffer.putInt(createTime.length);
        byteBuffer.put(createTime);

        return byteBuffer.array();
    }

    @Override
    public void close() {
    }
}
