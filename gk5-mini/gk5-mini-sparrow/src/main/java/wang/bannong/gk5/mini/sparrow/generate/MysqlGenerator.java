package wang.bannong.gk5.mini.sparrow.generate;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * Mysql代码生成器
 */
public class MysqlGenerator extends SuperGenerator {

    /**
     * <p>
     * MySQL generator
     * </p>
     */
    public void generator(String tableName) {

        // 代码生成器
        AutoGenerator mpg = getAutoGenerator(tableName);
        mpg.execute();
        if (tableName == null) {
            System.err.println(" Generator Success !");
        } else {
            System.err.println(" TableName【 " + tableName + " 】" + "Generator Success !");

        }
    }


}
