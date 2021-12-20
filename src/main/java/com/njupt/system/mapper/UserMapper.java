package com.njupt.system.mapper;

import com.njupt.system.model.User;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {
    @Delete({
        "delete from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_info (id, tel, ",
        "name, student_id, ",
        "password, type, ",
        "class_id, gmt_created, ",
        "gmt_modified)",
        "values (#{id,jdbcType=INTEGER}, #{tel,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{studentId,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{classId,jdbcType=VARCHAR}, now(), ",
        "now())"
    })
    int insert(User record);

    @Select({
        "select",
        "id, tel, name, student_id, password, type, class_id, gmt_created, gmt_modified",
        "from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results(id = "resultMap", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="class_id", property="classId", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    User selectByPrimaryKey(Integer id);

    @ResultMap("resultMap")
    @Select({
            "select",
            "id, tel, name, student_id, password, type, class_id, gmt_created, gmt_modified",
            "from user_info",
            "where student_id = #{studentId,jdbcType=VARCHAR}"
    })
    User selectByStudentId(String studentId);

    @ResultMap("resultMap")
    @Select({
            "select",
            "id, tel, name, student_id, password, type, class_id, gmt_created, gmt_modified",
            "from user_info",
            "where tel = #{tel,jdbcType=VARCHAR}"
    })
    User selectByTel(String tel);

    @Select({
        "select",
        "id, tel, name, student_id, password, type, class_id, gmt_created, gmt_modified",
        "from user_info"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tel", property="tel", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="student_id", property="studentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="class_id", property="classId", jdbcType=JdbcType.VARCHAR),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<User> selectAll();

    @Update({
        "update user_info",
        "set tel = #{tel,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "student_id = #{studentId,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "class_id = #{classId,jdbcType=VARCHAR},",
          "gmt_modified = now()",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
}