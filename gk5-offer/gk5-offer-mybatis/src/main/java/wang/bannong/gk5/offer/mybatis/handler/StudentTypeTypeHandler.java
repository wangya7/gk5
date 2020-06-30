package wang.bannong.gk5.offer.mybatis.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import wang.bannong.gk5.offer.mybatis.enums.StudentTypeEnum;

/**
 * 采用自动配置typeHandlers， 相应的需要配置扫码的包路径
 */
@MappedJdbcTypes(JdbcType.TINYINT)
public class StudentTypeTypeHandler extends BaseTypeHandler<StudentTypeEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, StudentTypeEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setByte(i, parameter.getCode());
    }

    @Override
    public StudentTypeEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return StudentTypeEnum.of(rs.getByte(columnName));
    }

    @Override
    public StudentTypeEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return StudentTypeEnum.of(rs.getByte(columnIndex));
    }

    @Override
    public StudentTypeEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return StudentTypeEnum.of(cs.getByte(columnIndex));
    }
}
