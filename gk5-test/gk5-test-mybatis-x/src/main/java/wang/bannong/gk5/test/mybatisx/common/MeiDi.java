package wang.bannong.gk5.test.mybatisx.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class MeiDi implements Serializable {
    private static final long serialVersionUID = 7694541400900303531L;

    private Long   id;
    private String hddate;
    private String hkcode;
    private String scode;
    private String sname;
    private String participantcode;
    private String participantname;
    private String shareholdsum;
    private String sharesrate;
    private String closeprice;
    private String zdf;
    private String shareholdprice;
    private String shareholdpriceone;
    private String shareholdpricefive;
    private String shareholdpriceten;
    private String market;
    private String shareholdsumchg;
    private String zb;
    private String zzb;

}