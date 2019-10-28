package wang.bannong.gk5.ttm.admin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

import wang.bannong.gk5.ttm.admin.core.model.XxlJobLogGlue;

/**
 * job log for glue
 * @author xuxueli 2016-5-19 18:04:56
 */
@Mapper
public interface XxlJobLogGlueDao {
	
	public int save(XxlJobLogGlue xxlJobLogGlue);
	
	public List<XxlJobLogGlue> findByJobId(@Param("jobId") int jobId);

	public int removeOld(@Param("jobId") int jobId, @Param("limit") int limit);

	public int deleteByJobId(@Param("jobId") int jobId);
	
}
