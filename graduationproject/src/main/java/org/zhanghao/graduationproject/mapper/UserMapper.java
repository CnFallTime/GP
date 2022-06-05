package org.zhanghao.graduationproject.mapper;

import org.apache.ibatis.annotations.*;
import org.zhanghao.graduationproject.bean.Text;
import org.zhanghao.graduationproject.bean.User;

import java.util.List;

/**
 * @author 张昊
 * @date 2019/9/26 - 18:20
 **/
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,password,email,tel) value(#{name},#{password},#{email},#{tel})")
    int insertUser(User user);

    @Select("select * from user where email=#{email} and password=#{password}")
    List<User> getByName(String email,String password);

    @Select("select * from user where name=#{name}")
    User getByNameDetail(String email);

    @Delete("delete from user where id=#{id}")
    int deleteDeptById(int id);
    @Select("select user.email from user")
    List<String> getAllEmail();

    @Update("update user set name=#{name},password=#{password},tel=#{tel} where email=#{email}")
    int updateDept(User user);

    @Insert("insert into text(name,email,text) value(#{name},#{email},#{text})")
    int insertText(Text text);

    @Select("select * from text")
    List<Text> queralll();

}
