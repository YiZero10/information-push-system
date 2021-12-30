package com.njupt.system.mapper;

import com.njupt.system.model.Information;
import com.njupt.system.model.InformationPush;
import java.util.List;

import com.njupt.system.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface InformationPushMapper {
    @Delete({
        "delete from information_push",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into information_push (id, information_id, ",
        "user_id)",
        "values (#{id,jdbcType=INTEGER}, #{informationId,jdbcType=INTEGER}, ",
        "#{userId,jdbcType=INTEGER})"
    })
    int insert(InformationPush record);

    @Select({
        "select",
        "id, information_id, user_id",
        "from information_push",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="information_id", property="informationId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    InformationPush selectByPrimaryKey(Integer id);

    @Select({"SELECT information.id, title, cover_img, type, admin_id, status, visible_range, release_time, gmt_created, gmt_modified, content FROM information,information_push " +
            "WHERE user_id = #{user.id} " +
            "AND information.id = information_push.information_id " +
            "AND information.`status` = 0 " +
            "AND type = #{type} "+
            "AND NOW() > release_time"})
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
    List<Information> selectMyInformation(User user,Integer type);

    @Select({
        "select",
        "id, information_id, user_id",
        "from information_push"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="information_id", property="informationId", jdbcType=JdbcType.INTEGER),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.INTEGER)
    })
    List<InformationPush> selectAll();

    @Update({
        "update information_push",
        "set information_id = #{informationId,jdbcType=INTEGER},",
          "user_id = #{userId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(InformationPush record);

    @Insert({
            "<script>",
            "insert into information_push(information_id, user_id) values ",
            "<foreach collection='list' item='item' index='index' separator=','>",
            "(#{infoId}, #{item})",
            "</foreach>",
            "</script>"
    })
    void insertSpecifiedClass(@Param("infoId") Integer infoId, @Param("list") List<Integer> list);
}