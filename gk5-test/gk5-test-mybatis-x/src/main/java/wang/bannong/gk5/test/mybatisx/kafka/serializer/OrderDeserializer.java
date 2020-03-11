package wang.bannong.gk5.test.mybatisx.kafka.serializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import wang.bannong.gk5.test.mybatisx.kafka.Order;

/**
 * Created by bn. on 2019/8/29 1:56 PM
 */
public class OrderDeserializer implements Deserializer<Order> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public Order deserialize(String topic, byte[] data) {
        if (null == data) {
            return null;
        }

        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        int    orderIdLen;
        Long   orderId = null;

        int    orderNumLen;
        String orderNum = null;

        int    createTimeLen;
        Date   createTime = null;

        orderIdLen = byteBuffer.getInt();
        byte[] orderIdBytes = new byte[orderIdLen];
        byteBuffer.get(orderIdBytes);

        orderNumLen = byteBuffer.getInt();
        byte[] orderNumBytes = new byte[orderNumLen];
        byteBuffer.get(orderNumBytes);

        createTimeLen = byteBuffer.getInt();
        byte[] createTimeBytes = new byte[createTimeLen];
        byteBuffer.get(createTimeBytes);

        try {
            orderId = Long.valueOf(new String(orderIdBytes, "UTF-8"));
            orderNum = new String(orderNumBytes, "UTF-8");
            DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
            createTime = dateFormat.parse(new String(createTimeBytes, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Order(orderId, orderNum, createTime);
    }

    @Override
    public void close() {

    }
}
