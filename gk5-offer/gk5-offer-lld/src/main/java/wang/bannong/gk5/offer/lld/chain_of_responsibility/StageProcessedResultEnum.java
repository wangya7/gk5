package wang.bannong.gk5.offer.lld.chain_of_responsibility;

/**
 * @author <a href="mailto:bannongvipp@163.com">bn</a>
 * @date 2025/2/3
 */
public enum StageProcessedResultEnum {
    SUCCESS,
    IDEMPOTENT_FAIL,
    WRITE_USER_RIGHTS_FLOW_DB_FAIL,
    WRITE_USER_RIGHTS_RECORD_DB_FAIL,
    SKIP_ALL;

    public boolean isSuccess() {
        return this == SUCCESS;
    }
}
