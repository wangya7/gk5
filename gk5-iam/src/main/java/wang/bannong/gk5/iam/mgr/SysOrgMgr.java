package wang.bannong.gk5.iam.mgr;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;
import wang.bannong.gk5.iam.common.constant.IamConstant;
import wang.bannong.gk5.iam.common.domain.SysOrg;
import wang.bannong.gk5.iam.common.dto.SysOrgDto;
import wang.bannong.gk5.iam.dao.SysOrgDao;
import wang.bannong.gk5.util.domain.FlexDataBus;

@Slf4j
@Component
public class SysOrgMgr {

    @Autowired
    private SysOrgDao  sysOrgDao;
    @Autowired
    private SysUserMgr sysUserMgr;

    public SysOrg queryById(Long id) throws Exception {
        return sysOrgDao.selectByPrimaryKey(id);
    }

    public List<SysOrg> queryByIds(Set<Long> ids) throws Exception {
        return sysOrgDao.selectByIds(ids);
    }

    public Map<Long, SysOrg> queryMapByIds(Set<Long> ids) throws Exception {
        List<SysOrg> sysOrgs = queryByIds(ids);
        if (CollectionUtils.isNotEmpty(sysOrgs)) {
            return sysOrgs.stream().collect(Collectors.toMap(SysOrg::getId, i -> i));
        }
        return Collections.EMPTY_MAP;
    }

    public Map<Long, String> queryNameMapByIds(Set<Long> ids) throws Exception {
        List<SysOrg> sysOrgs = queryByIds(ids);
        if (CollectionUtils.isNotEmpty(sysOrgs)) {
            return sysOrgs.stream().collect(Collectors.toMap(SysOrg::getId, i -> i.getName()));
        }
        return Collections.EMPTY_MAP;
    }

    //************* 接口操作

    public FlexDataBus querySysOrg(SysOrgDto dto) throws Exception {


        return FlexDataBus.SUCCESS;
    }

    @Transactional
    public FlexDataBus addSysOrg(SysOrgDto dto) throws Exception {
        SysOrg bo = new SysOrg();
        bo.setPid(dto.getPid());
        bo.setName(dto.getName());
        bo.setStatus(IamConstant.EFF);
        List<SysOrg> sysOrgs = sysOrgDao.selectByBo(bo);
        if (CollectionUtils.isNotEmpty(sysOrgs)) {
            return FlexDataBus.fail("组织名称重复，重新操作");
        }
        SysOrg record = new SysOrg();
        record.setPid(dto.getPid());
        record.setName(dto.getName());
        record.setNumber(dto.getNumber());
        record.setAddress(dto.getAddress());
        record.setStreet(dto.getStreet());
        record.setArea(dto.getArea());
        record.setCity(dto.getCity());
        record.setProvince(dto.getProvince());
        record.setType(dto.getType());
        record.setStatus(dto.getStatus());
        record.setCreateTime(new Date());
        record.setModifyTime(record.getCreateTime());
        sysOrgDao.insert(record);
        return FlexDataBus.succ(record);
    }

    @Transactional
    public FlexDataBus modifySysOrg(SysOrgDto dto) throws Exception {
        Long id = dto.getId();
        SysOrg record = sysOrgDao.selectByPrimaryKey(id);
        SysOrg bo = new SysOrg();
        bo.setPid(record.getPid());
        bo.setName(dto.getName());
        bo.setStatus(IamConstant.EFF);
        List<SysOrg> sysOrgs = sysOrgDao.selectByBo(bo);
        if (CollectionUtils.isNotEmpty(sysOrgs)) {
            return FlexDataBus.fail("组织名称重复，重新操作");
        }
        record.setName(dto.getName());
        record.setNumber(dto.getNumber());
        record.setAddress(dto.getAddress());
        record.setStreet(dto.getStreet());
        record.setArea(dto.getArea());
        record.setCity(dto.getCity());
        record.setProvince(dto.getProvince());
        record.setType(dto.getType());
        record.setStatus(dto.getStatus());
        record.setModifyTime(new Date());
        sysOrgDao.updateByPrimaryKey(record);
        return FlexDataBus.succ(record);
    }

    @Transactional
    public FlexDataBus deleteSysOrg(SysOrgDto dto) throws Exception {
        Long id = dto.getId();
        SysOrg bo = new SysOrg();
        bo.setPid(id);
        bo.setStatus(IamConstant.EFF);
        List<SysOrg> sysOrgs = sysOrgDao.selectByBo(bo);
        if (CollectionUtils.isNotEmpty(sysOrgs)) {
            return FlexDataBus.fail("组织还存在下属，先处理下级组织");
        }

        if (sysUserMgr.isExistStaff(id)) {
            return FlexDataBus.fail("组织还存在员工，先处理下级员工");
        }

        SysOrg record = sysOrgDao.selectByPrimaryKey(id);
        record.setStatus(IamConstant.EXP);
        record.setModifyTime(new Date());
        sysOrgDao.updateByPrimaryKey(record);
        return FlexDataBus.succ(record);
    }
}
