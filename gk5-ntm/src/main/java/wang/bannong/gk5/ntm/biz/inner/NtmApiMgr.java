package wang.bannong.gk5.ntm.biz.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.ntm.common.constant.NtmConstant;
import wang.bannong.gk5.ntm.common.domain.NtmApi;
import wang.bannong.gk5.ntm.common.domain.NtmApiParam;
import wang.bannong.gk5.ntm.common.dto.ApiDto;
import wang.bannong.gk5.ntm.common.dto.ApiParamDto;
import wang.bannong.gk5.ntm.common.model.NtmResult;
import wang.bannong.gk5.ntm.common.model.PaginationResult;
import wang.bannong.gk5.ntm.common.vo.NtmApiParamVo;
import wang.bannong.gk5.ntm.common.vo.NtmApiVo;
import wang.bannong.gk5.ntm.dao.NtmApiDao;
import wang.bannong.gk5.ntm.dao.NtmApiParamDao;
import wang.bannong.gk5.util.DateUtils;

@Slf4j
@Component
public class NtmApiMgr {

    @Autowired
    private NtmApiDao      masterNtmApiDao;
    @Autowired
    private NtmApiDao      slaveNtmApiDao;
    @Autowired
    private NtmApiParamDao masterNtmApiParamDao;
    @Autowired
    private NtmApiParamDao slaveNtmApiParamDao;

    public NtmApi queryById(Long id) {
        return masterNtmApiDao.selectById(id);
    }

    public List<NtmApi> queryByIds(List<Long> ids) {
        return masterNtmApiDao.selectBatchIds(ids);
    }

    public NtmApi queryByUnique(String unique) {
        QueryWrapper<NtmApi> wrapper = new QueryWrapper<>();
        wrapper.eq("unique", unique);
        return masterNtmApiDao.selectOne(wrapper);
    }

    public NtmApi queryByUniqueAndVersionAppid(String unique, int version, String appid) {
        QueryWrapper<NtmApi> wrapper = new QueryWrapper<>();
        wrapper.eq("unique", unique);
        wrapper.eq("version", version);
        wrapper.eq("appid", appid);
        return masterNtmApiDao.selectOne(wrapper);
    }

    public List<NtmApiParam> queryParams(Long apiId) {
        QueryWrapper<NtmApiParam> wrapper = new QueryWrapper<>();
        wrapper.eq("apiId", apiId);
        return masterNtmApiParamDao.selectList(wrapper);
    }

    public List<NtmApiParam> queryParamsByPid(Long apiId) {
        QueryWrapper<NtmApiParam> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", apiId);
        return masterNtmApiParamDao.selectList(wrapper);
    }

    public NtmApiParam queryParamByAppidAndName(Long apiId, String name) {
        QueryWrapper<NtmApiParam> wrapper = new QueryWrapper<>();
        wrapper.eq("apiId", apiId);
        wrapper.eq("name", name);
        return masterNtmApiParamDao.selectOne(wrapper);
    }

    public NtmApiParam queryParamById(Long id) {
        return masterNtmApiParamDao.selectById(id);
    }

    public List<NtmApiParam> queryParamByIds(List<Long> ids) {
        return masterNtmApiParamDao.selectBatchIds(ids);
    }

    //************************ 接口

    public NtmResult apiList(ApiDto dto) throws Exception {
        QueryWrapper<NtmApi> wrapper = new QueryWrapper<>();
        if (dto.getName() != null) {
            wrapper.like("name", dto.getName());
        }
        if (dto.getUnique() != null) {
            wrapper.like("unique", dto.getUnique());
        }
        if (dto.getAppid() != null) {
            wrapper.eq("appid", dto.getAppid());
        }

        Page<NtmApi> ntmApiPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        slaveNtmApiDao.selectPage(ntmApiPage, wrapper);
        List<NtmApi> apiList = ntmApiPage.getRecords();
        if (CollectionUtils.isNotEmpty(apiList)) {
            List<NtmApiVo> vos = new ArrayList<>();
            for (NtmApi record : apiList) {
                NtmApiVo vo = new NtmApiVo();
                vo.setId(String.valueOf(record.getId()));
                vo.setUnique(record.getUnique());
                vo.setName(record.getName());
                vo.setVersion(record.getVersion());
                vo.setMethod(record.getMethod());
                vo.setAppid(record.getAppid());
                vo.setInnerInterface(record.getInnerInterface());
                vo.setInnerMethod(record.getInnerMethod());
                vo.setIsIa(record.getIsIa());
                vo.setIsAsync(record.getIsAsync());
                vo.setDailyFlowLimit(record.getDailyFlowLimit());
                vo.setResult(record.getResult());
                vo.setStatus(record.getStatus());
                vo.setCreateTime(DateUtils.format(record.getCreateTime()));
                vo.setModifyTime(DateUtils.format(record.getModifyTime()));
                vos.add(vo);
            }
            return NtmResult.success(PaginationResult.of(ntmApiPage, vos));
        }
        return NtmResult.success(PaginationResult.empty(dto.getPageNum(), dto.getPageSize()));
    }

    @Transactional
    public NtmResult addApi(ApiDto dto) throws Exception {
        String appid = dto.getAppid();
        String unique = dto.getUnique();
        int version = dto.getVersion();
        NtmApi record = queryByUniqueAndVersionAppid(unique, version, appid);
        if (record != null) {
            return NtmResult.fail("接口已经存在");
        }
        record = new NtmApi();
        record.setUnique(unique);
        record.setName(dto.getName());
        record.setVersion(version);
        record.setMethod(dto.getMethod());
        record.setAppid(appid);
        record.setInnerInterface(dto.getInnerInterface());
        record.setInnerMethod(dto.getInnerMethod());
        record.setIsIa(dto.getIsIa());
        record.setIsAsync(dto.getIsAsync());
        record.setDailyFlowLimit(dto.getDailyFlowLimit());
        record.setResult(dto.getResult());
        record.setStatus(NtmConstant.EFF_STATUS);
        record.setCreateTime(new Date());
        record.setModifyTime(record.getCreateTime());
        masterNtmApiDao.insert(record);
        return NtmResult.success(record);
    }

    @Transactional
    public NtmResult modifyApi(ApiDto dto) throws Exception {
        Long id = dto.getId();
        NtmApi record = queryById(id);
        if (record == null) {
            return NtmResult.fail("接口不存在");
        }
        String appid = dto.getAppid();
        String unique = dto.getUnique();
        int version = dto.getVersion();
        NtmApi recordCheck = queryByUniqueAndVersionAppid(unique, version, appid);
        if (recordCheck != null && !recordCheck.getId().equals(id)) {
            return NtmResult.fail("接口已经存在");
        }

        record.setUnique(unique);
        record.setName(dto.getName());
        record.setVersion(version);
        record.setMethod(dto.getMethod());
        record.setAppid(appid);
        record.setInnerInterface(dto.getInnerInterface());
        record.setInnerMethod(dto.getInnerMethod());
        record.setIsIa(dto.getIsIa());
        record.setIsAsync(dto.getIsAsync());
        record.setDailyFlowLimit(dto.getDailyFlowLimit());
        record.setResult(dto.getResult());
        record.setStatus(dto.getStatus());
        record.setModifyTime(new Date());
        masterNtmApiDao.updateById(record);
        return NtmResult.success(record);
    }

    @Transactional
    public NtmResult deleteApi(ApiDto dto) throws Exception {
        masterNtmApiDao.deleteById(dto.getId());
        return NtmResult.SUCCESS;
    }

    public NtmResult apiParamList(ApiParamDto dto) throws Exception {
        QueryWrapper<NtmApiParam> wrapper = new QueryWrapper<>();
        wrapper.eq("apiId", dto.getApiId());
        Page<NtmApiParam> ntmApiParamPage = new Page<>(dto.getPageNum(), dto.getPageSize());
        slaveNtmApiParamDao.selectPage(ntmApiParamPage, wrapper);
        List<NtmApiParam> apiParamList = ntmApiParamPage.getRecords();
        if (CollectionUtils.isNotEmpty(apiParamList)) {
            List<NtmApiParamVo> vos = new ArrayList<>();
            for (NtmApiParam record : apiParamList) {
                NtmApiParamVo vo = new NtmApiParamVo();
                vo.setId(String.valueOf(record.getId()));
                vo.setPid(String.valueOf(record.getPid()));
                vo.setApiId(String.valueOf(record.getApiId()));
                vo.setName(record.getName());
                vo.setStatus(record.getStatus());
                vo.setType(record.getType());
                vo.setIsRequired(record.getIsRequired());
                vo.setErrMsg(record.getErrMsg());
                vo.setDesp(record.getDesp());
                vo.setExample(record.getExample());
                vo.setCreateTime(DateUtils.format(record.getCreateTime()));
                vo.setModifyTime(DateUtils.format(record.getModifyTime()));
                vos.add(vo);
            }
            return NtmResult.success(PaginationResult.of(ntmApiParamPage, vos));
        }
        return NtmResult.success(PaginationResult.empty(dto.getPageNum(), dto.getPageSize()));
    }

    @Transactional
    public NtmResult addApiParam(ApiParamDto dto) throws Exception {
        Long apiId = dto.getApiId();
        String name = dto.getName();
        NtmApiParam record = queryParamByAppidAndName(apiId, name);
        if (record != null) {
            return NtmResult.fail("接口参数已经存在");
        }
        record = new NtmApiParam();
        record.setPid(dto.getPid());
        record.setApiId(apiId);
        record.setName(name);
        record.setStatus(NtmConstant.EFF_STATUS);
        record.setType(dto.getType());
        record.setIsRequired(dto.getIsRequired());
        record.setErrMsg(dto.getErrMsg());
        record.setDesp(dto.getDesp());
        record.setExample(dto.getExample());
        record.setCreateTime(new Date());
        record.setModifyTime(record.getCreateTime());
        masterNtmApiParamDao.insert(record);
        return NtmResult.success(record);
    }

    @Transactional
    public NtmResult modifyApiParam(ApiParamDto dto) throws Exception {
        Long id = dto.getId();
        String name = dto.getName();
        NtmApiParam record = queryParamById(id);

        NtmApiParam recordCheck = queryParamByAppidAndName(record.getApiId(), name);
        if (recordCheck != null && !recordCheck.getId().equals(id)) {
            return NtmResult.fail("接口参数已经存在");
        }
        record.setPid(dto.getPid());
        record.setName(name);
        record.setStatus(dto.getStatus());
        record.setType(dto.getType());
        record.setIsRequired(dto.getIsRequired());
        record.setErrMsg(dto.getErrMsg());
        record.setDesp(dto.getDesp());
        record.setExample(dto.getExample());
        record.setModifyTime(new Date());
        masterNtmApiParamDao.updateById(record);
        return NtmResult.success(record);
    }

    @Transactional
    public NtmResult deleteApiParam(ApiParamDto dto) throws Exception {
        masterNtmApiParamDao.deleteById(dto.getId());
        return NtmResult.SUCCESS;
    }
}
