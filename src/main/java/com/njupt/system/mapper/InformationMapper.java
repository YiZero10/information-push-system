package com.njupt.system.mapper;

import com.njupt.system.model.Information;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface InformationMapper {
    @Delete({
        "delete from information",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into information (id, title, ",
        "cover_img, type, ",
        "admin_id, status, ",
        "visible_range, release_time, ",
        "gmt_created, gmt_modified, ",
        "content)",
        "values (#{id,jdbcType=INTEGER}, #{title,jdbcType=VARCHAR}, ",
        "#{coverImg,jdbcType=VARCHAR}, #{type,jdbcType=INTEGER}, ",
        "#{adminId,jdbcType=INTEGER}, #{status,jdbcType=INTEGER}, ",
        "#{visibleRange,jdbcType=INTEGER}, #{releaseTime,jdbcType=TIMESTAMP}, ",
        "now(), now(), ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insert(Information record);

    @Select({
        "select",
        "id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, ",
        "gmt_modified, content",
        "from information",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results(id = "defaultMap", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_img", property="coverImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="visible_range", property="visibleRange", jdbcType=JdbcType.INTEGER),
        @Result(column="release_time", property="releaseTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Information selectByPrimaryKey(Integer id);

    @ResultMap("defaultMap")
    @Select({
            "select * from information where admin_id = #{adminId}"
    })
    List<Information> selectByAdminId(Integer adminId);

    @ResultMap("defaultMap")
    @Select({
            "select",
            "id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, ",
            "gmt_modified, content",
            "from information",
            "where type = #{type,jdbcType=INTEGER} and status = #{status} ",
            "and now() > release_time"
    })
    List<Information> selectByTypeAndStatus(Integer type, Integer status);

    @ResultMap("defaultMap")
    @Select({
            "select",
            "id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, ",
            "gmt_modified, content",
            "from information",
            "where type = #{type,jdbcType=INTEGER} and status = #{status} and visible_range = #{visible}",
            "and now() > release_time"
    })
    List<Information> selectByTypeAndStatusAndVisible(Integer type, Integer status, Integer visible);

    @Select({
        "select",
        "id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, ",
        "gmt_modified, content",
        "from information"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_img", property="coverImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.INTEGER),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="visible_range", property="visibleRange", jdbcType=JdbcType.INTEGER),
        @Result(column="release_time", property="releaseTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<Information> selectAll();

    @Update({
        "update information",
        "set title = #{title,jdbcType=VARCHAR},",
          "cover_img = #{coverImg,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=INTEGER},",
          "admin_id = #{adminId,jdbcType=INTEGER},",
          "status = #{status,jdbcType=INTEGER},",
          "visible_range = #{visibleRange,jdbcType=INTEGER},",
          "release_time = #{releaseTime,jdbcType=TIMESTAMP},",
          "gmt_modified = now(),",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Information record);
}