package com.zcx.studentManagement.dao;

import com.zcx.studentManagement.entity.Student;
import com.zcx.studentManagement.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private static Student getStudent(ResultSet resultSet){ //根据查询结果返回学生对象
        Student student = new Student();
        try {
            int student_id = resultSet.getInt("student_id");
            String student_name = resultSet.getString("student_name");
            String student_sex = resultSet.getString("student_sex");
            Date student_birthday = resultSet.getDate("student_birthday");
            String student_mobile = resultSet.getString("student_mobile");
            String student_email = resultSet.getString("student_email");
            String student_clazzName = resultSet.getString("student_clazzName");
            String student_teacherName = resultSet.getString("student_teacherName");
            student.setId(student_id);
            student.setName(student_name);
            student.setSex(student_sex);
            student.setBirthday(student_birthday);
            student.setMobile(student_mobile);
            student.setEmail(student_email);
            student.setClazzName(student_clazzName);
            student.setTeacherName(student_teacherName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static List<Student> getStudents(int page, int limit) {  //返回学生分页信息
        List<Student> students = new ArrayList<Student>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from student limit ?, ?");
            statement.setInt(1, (page - 1) * limit);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Student student = getStudent(resultSet);
                students.add(student);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static int getCount(){   //返回学生总数
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from student");
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

    public static int delStudentById(int id){   //通过id删除学生
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("delete from student where student_id = ?");
            statement.setInt(1, id);
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int updateStudent(Student student){   //更新学生
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("update student set student_name = ?, student_sex = ?, student_birthday = ?, student_mobile = ?, student_email = ?, student_clazzName = ?, student_teacherName = ? where student_id = ?");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSex());
            statement.setDate(3, student.getBirthday());
            statement.setString(4, student.getMobile());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getClazzName());
            statement.setString(7, student.getTeacherName());
            statement.setInt(8, student.getId());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Student getStudentById(int id){   //通过id获取学生
        Student student = new Student();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from student where student_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                student = getStudent(resultSet);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static int addStudent(Student student){  //添加学生
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("insert into student (student_name, student_sex, student_birthday, student_mobile, student_email, student_clazzName, student_teacherName) values (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSex());
            statement.setDate(3, student.getBirthday());
            statement.setString(4, student.getMobile());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getClazzName());
            statement.setString(7, student.getTeacherName());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int delStudents(List<Student> students){  //批量删除学生
        int rows = 0;
        for(int i = 0; i < students.size(); i ++){
            rows = delStudentById(students.get(i).getId());
        }
        return  rows;
    }

    public static List<Student> searchStudents(String info, int page, int limit){   //模糊查找学生
        info = "%" + info + "%";
        List<Student> students = new ArrayList<Student>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from student where student_id like ? or student_name like ? or student_sex like ? or student_birthday like ? or student_mobile like ? or student_email like ? or student_clazzName like ? or student_teacherName like ?  limit ?, ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
            statement.setString(4, info);
            statement.setString(5, info);
            statement.setString(6, info);
            statement.setString(7, info);
            statement.setString(8, info);
            statement.setInt(9, (page - 1) * limit);
            statement.setInt(10, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Student student = getStudent(resultSet);
                students.add(student);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static int getSearchCount(String info){  //获取查找出的学生数量
        info = "%" + info + "%";
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from student where student_id like ? or student_name like ? or student_sex like ? or student_birthday like ? or student_mobile like ? or student_email like ? or student_clazzName like ? or student_teacherName like ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
            statement.setString(4, info);
            statement.setString(5, info);
            statement.setString(6, info);
            statement.setString(7, info);
            statement.setString(8, info);
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
