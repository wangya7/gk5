package wang.bannong.gk5.small.biz.oss;

import wang.bannong.gk5.small.biz.service.SysConfigService;
import wang.bannong.gk5.small.common.utils.Constant;
import wang.bannong.gk5.util.SpringBeanUtils;

/**
 * 文件上传Factory
 */
public final class OSSFactory {
    private static SysConfigService sysConfigService;

    static {
        OSSFactory.sysConfigService = (SysConfigService) SpringBeanUtils.getBean("sysConfigService");
    }

    public static CloudStorageService build() {
        //获取云存储配置信息
        wang.bannong.gk5.small.common.wang.bannong.gk5.small.common.CloudStorageConfig config =
                sysConfigService.getConfigObject(Constant.CLOUD_STORAGE_CONFIG_KEY,
                        wang.bannong.gk5.small.common.wang.bannong.gk5.small.common.CloudStorageConfig.class);

        if (config.getType() == Constant.CloudService.QINIU.getValue()) {
            return new QiniuCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.ALIYUN.getValue()) {
            return new AliyunCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.QCLOUD.getValue()) {
            return new QcloudCloudStorageService(config);
        } else if (config.getType() == Constant.CloudService.DISCK.getValue()) {
            return new DiskCloudStorageService(config);
        }

        return null;
    }

}
