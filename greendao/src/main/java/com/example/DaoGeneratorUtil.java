package com.example;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.DaoGenerator;
public class DaoGeneratorUtil {
    public static void main(String[] args) {
        //生成实体类entity 即对应的表
        Schema schema = new Schema(1, "com.student.entity");
        //添加节点
        addStudent(schema);

        schema.setDefaultJavaPackageDao("com.student.dao");// 设置数据的会话层


        //将生成的内容放在指定的路径下
        try {
            new DaoGenerator().generateAll(schema, "/Users/hl/Develop/ASProject/mydemo/app/src/main/java-gen");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    ////创建数据库的表
    private static void addStudent(Schema schema) {

        Entity entity = schema.addEntity("Student"); //创建数据库的表


        /**
         * 设置字符串获其他类型为主键
         * entity.addStringProperty("身份证号").primaryKey();
         */
        //当前表中的列

        entity.addIdProperty();// 主键
        entity.addStringProperty("name");
        entity.addStringProperty("address");
        entity.addIntProperty("age");

    }

}
