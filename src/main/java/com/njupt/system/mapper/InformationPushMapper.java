package com.njupt.system.mapper;

import com.njupt.system.model.InformationPush;
import java.util.List;

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
}