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
        "#{adminId,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
        "#{visibleRange,jdbcType=INTEGER}, #{releaseTime,jdbcType=TIMESTAMP}, ",
        "#{gmtCreated,jdbcType=TIMESTAMP}, #{gmtModified,jdbcType=TIMESTAMP}, ",
        "#{content,jdbcType=LONGVARCHAR})"
    })
    int insert(Information record);

    @Select({
        "select",
        "id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, ",
        "gmt_modified, content",
        "from information",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="title", property="title", jdbcType=JdbcType.VARCHAR),
        @Result(column="cover_img", property="coverImg", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.VARCHAR),
        @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
        @Result(column="visible_range", property="visibleRange", jdbcType=JdbcType.INTEGER),
        @Result(column="release_time", property="releaseTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_created", property="gmtCreated", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="gmt_modified", property="gmtModified", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="content", property="content", jdbcType=JdbcType.LONGVARCHAR)
    })
    Information selectByPrimaryKey(Integer id);

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
        @Result(column="admin_id", property="adminId", jdbcType=JdbcType.VARCHAR),
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
          "admin_id = #{adminId,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=INTEGER},",
          "visible_range = #{visibleRange,jdbcType=INTEGER},",
          "release_time = #{releaseTime,jdbcType=TIMESTAMP},",
          "gmt_created = #{gmtCreated,jdbcType=TIMESTAMP},",
          "gmt_modified = #{gmtModified,jdbcType=TIMESTAMP},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Information record);
}