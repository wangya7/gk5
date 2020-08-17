package wang.bannong.gk5.test.mybatisx.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.test.mybatisx.common.MeiDi;
import wang.bannong.gk5.test.mybatisx.mapper.MeiDiMapper;
import wang.bannong.gk5.util.OkHttpUtils;

@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MeiDiSpider {

    private final static String base_url1 = "http://dcfm.eastmoney.com//em_mutisvcexpandinterface/api/js/get?type=HSGTSHHDDET&token=70f12f2f4f091e459a279469fe49eca5&st=HDDATE,SHAREHOLDPRICE&sr=3&p=";
    private final static String base_url2 = "&ps=50&js=var%20sCzjRoPZ={pages:(tp),data:(x)}&filter=(SCODE=%27000333%27)(HDDATE%3E=^2020-06-17^%20and%20HDDATE%3C=^2020-07-30^)&rt=53206922";

    @Autowired
    private MeiDiMapper masterMeiDiMapper;

    @Test
    public void run() {
        List<MeiDi> list = new ArrayList<>();
        for (int i = 0; i < 33; i++) {
            String url = base_url1 + (i + 1) + base_url2;
            String result = OkHttpUtils.get(url);
            int fromIndex = result.indexOf("[");
            int toIndex = result.indexOf("]");
            String arr = result.substring(fromIndex, toIndex + 1);
            JSONArray array = JSON.parseArray(arr);
            int size = array.size();
            for (int j = 0; j < size; j ++) {
                JSONObject obj = (JSONObject) array.get(j);
                MeiDi record = new MeiDi();
                record.setHddate(obj.getString("HDDATE"));
                record.setHkcode(obj.getString("HKCODE"));
                record.setScode(obj.getString("SCODE"));
                record.setSname(obj.getString("SNAME"));
                record.setParticipantcode(obj.getString("PARTICIPANTCODE"));
                record.setParticipantname(obj.getString("PARTICIPANTNAME"));
                record.setShareholdsum(obj.getString("SHAREHOLDSUM"));
                record.setSharesrate(obj.getString("SHARESRATE"));
                record.setCloseprice(obj.getString("CLOSEPRICE"));
                record.setZdf(obj.getString("ZDF"));
                record.setShareholdprice(obj.getString("SHAREHOLDPRICE"));
                record.setShareholdpriceone(obj.getString("SHAREHOLDPRICEONE"));
                record.setShareholdpricefive(obj.getString("SHAREHOLDPRICEFIVE"));
                record.setShareholdpriceten(obj.getString("SHAREHOLDPRICETEN"));
                record.setMarket(obj.getString("MARKET"));
                record.setShareholdsumchg(obj.getString("ShareHoldSumChg"));
                record.setZb(obj.getString("Zb"));
                record.setZzb(obj.getString("Zzb"));
                list.add(record);
            }
            System.out.println();
        }

        if (CollectionUtils.isNotEmpty(list)) {
            for (MeiDi record : list) {
                masterMeiDiMapper.insert(record);
                log.info("success to insert record[{}]", record);
            }
        }
    }
}
