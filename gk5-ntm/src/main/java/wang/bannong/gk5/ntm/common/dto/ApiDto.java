package wang.bannong.gk5.ntm.common.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import lombok.Data;
import wang.bannong.gk5.ntm.common.domain.Api;
import wang.bannong.gk5.util.DateUtils;

@Data
public class ApiDto extends Api {

    private static final long serialVersionUID = 6256707767503617544L;

    private String startTimeStr;
    private String endTimeStr;

    private String likeName;
    private String likeAppId;

    private Date beginTime;
    private Date endTime;

    private int page = 1;
    private int rows = 10;

    public void makeTimeInterval() {
        if (StringUtils.isNotBlank(this.startTimeStr) && StringUtils.isNotBlank(this.endTimeStr)) {
            this.setBeginTime(DateUtils.beginTimeOfDay(DateUtils.parse(DateUtils.YMD3, startTimeStr)));
            this.setEndTime(DateUtils.endTimeOfDay(DateUtils.parse(DateUtils.YMD3, endTimeStr)));
        }
    }
}
