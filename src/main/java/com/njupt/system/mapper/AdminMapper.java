package com.njupt.system.mapper;

import com.njupt.system.model.Admin;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface AdminMapper {
    @Delete({
        "delete from admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into admin (id, job_id, ",
        "password, type, ",
        "department, gmt_created, ",
        "gmt_modified)",
        "values (#{id,jdbcType=INTEGER}, #{jobId,jdbcType=VARCHAR}, ",
        "#{password,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{department,jdbcType=INTEGER}, now(), ",
        "now())"
    })
    int insert(Admin record);

    @Select({
        "select",
        "id, job_id, password, type, department, gmt_created, gmt_modified",
        "from admin",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results(id = "resultMap", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="job_id", property="jobId", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    Admin selectByPrimaryKey(Integer id);

    @ResultMap("resultMap")
    @Select({
            "select",
            "id, job_id, password, type, department, gmt_created, gmt_modified",
            "from admin",
            "where job_id = #{jobId,jdbcType=VARCHAR}"
    })
    Admin selectByJobId(String jobId);

    @Select({
        "select",
        "id, job_id, password, type, department, gmt_created, gmt_modified",
        "from admin"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="job_id", property="jobId", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Admin> selectAll();

    @Select({
            "select",
            "id",
            "from admin",
            "where department = #{admin.department}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="job_id", property="jobId", jdbcType=JdbcType.VARCHAR),
            @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
            @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
            @Result(column="department", property="department", jdbcType=JdbcType.INTEGER),
            @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP)
    })
    List<Integer> selectMyAdmins(int dept);

    @ResultMap("resultMap")
    @Select({
            "select",
            "id, job_id, password, type, department, gmt_created, gmt_modified",
            "from admin",
            "where department = #{department}"
    })
    List<Admin> selectDepartment(int department);

    @Update({
        "update admin",
        "set job_id = #{jobId,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "department = #{department,jdbcType=INTEGER},",
          "gmt_modified = now()",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Admin record);
}