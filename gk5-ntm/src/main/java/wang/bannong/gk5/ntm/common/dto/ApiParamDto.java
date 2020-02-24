package wang.bannong.gk5.ntm.common.dto;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

import lombok.Data;
import wang.bannong.gk5.ntm.common.domain.ApiParam;
import wang.bannong.gk5.util.DateUtils;

@Data
public class ApiParamDto extends ApiParam {

    private static final long serialVersionUID = -1285545281939659754L;
    private List<String> ids;

    private String startTimeStr;
    private String endTimeStr;

    private String likeName;

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
