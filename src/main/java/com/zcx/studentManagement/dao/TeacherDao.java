package com.zcx.studentManagement.dao;

import com.zcx.studentManagement.entity.Teacher;
import com.zcx.studentManagement.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDao {

    private static Teacher getTeacher(ResultSet resultSet){ //根据查询结果返回老师对象
        Teacher teacher = new Teacher();
        try {
            int teacher_id = resultSet.getInt("teacher_id");
            String teacher_name = resultSet.getString("teacher_name");
            String teacher_sex = resultSet.getString("teacher_sex");
            String teacher_mobile = resultSet.getString("teacher_mobile");
            String teacher_email = resultSet.getString("teacher_email");
            teacher.setId(teacher_id);
            teacher.setName(teacher_name);
            teacher.setSex(teacher_sex);
            teacher.setMobile(teacher_mobile);
            teacher.setEmail(teacher_email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public static List<Teacher> getTeachers(int page, int limit) {  //返回老师列表
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from teacher limit ?, ?");
            statement.setInt(1, (page - 1) * limit);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Teacher teacher = getTeacher(resultSet);
                teachers.add(teacher);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public static int getCount(){   //返回全体老师数量
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from teacher");
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                count = resultSet.getInt(1);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int delTeacherById(int id){   //通过id删除老师
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("delete from teacher where teacher_id = ?");
            statement.setInt(1, id);
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int updateTeacher(Teacher teacher){   //更新老师
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("update teacher set teacher_name = ?, teacher_sex = ?, teacher_mobile = ?, teacher_email = ? where teacher_id = ?");
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSex());
            statement.setString(3, teacher.getMobile());
            statement.setString(4, teacher.getEmail());
            statement.setInt(5, teacher.getId());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Teacher getTeacherById(int id){   //通过id获取老师
        Teacher teacher = new Teacher();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from teacher where teacher_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                teacher = getTeacher(resultSet);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teacher;
    }

    public static int addTeacher(Teacher teacher){  //添加老师
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("insert into teacher (teacher_name, teacher_sex, teacher_mobile, teacher_email) values (?, ?, ?, ?)");
            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getSex());
            statement.setString(3, teacher.getMobile());
            statement.setString(4, teacher.getEmail());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int delTeachers(List<Teacher> teachers){  //批量删除老师
        int rows = 0;
        for(int i = 0; i < teachers.size(); i ++){
            rows = delTeacherById(teachers.get(i).getId());
        }
        return  rows;
    }

    public static List<Teacher> searchTeachers(String info, int page, int limit){   //模糊查找老师
        info = "%" + info + "%";
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from teacher where teacher_id like ? or teacher_name like ? or teacher_sex like ? or teacher_mobile like ? or teacher_email like ? limit ?, ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
            statement.setString(4, info);
            statement.setString(5, info);
            statement.setInt(6, (page - 1) * limit);
            statement.setInt(7, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Teacher teacher = getTeacher(resultSet);
                teachers.add(teacher);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public static List<Teacher> getAllTeachers() {  //获取所有老师
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from teacher");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Teacher teacher = getTeacher(resultSet);
                teachers.add(teacher);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teachers;
    }

    public static int getSearchCount(String info){  //获取查找出的老师数量
        info = "%" + info + "%";
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from teacher where teacher_id like ? or teacher_name like ? or teacher_sex like ? or teacher_mobile like ? or teacher_email like ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
            statement.setString(4, info);
            statement.setString(5, info);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
               count = resultSet.getInt(1);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

}
