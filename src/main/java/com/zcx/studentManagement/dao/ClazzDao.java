package com.zcx.studentManagement.dao;

import com.zcx.studentManagement.entity.Clazz;
import com.zcx.studentManagement.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClazzDao {

    private static Clazz getClazz(ResultSet resultSet){ //通过查询结果获取一个班级对象
        Clazz clazz = new Clazz();
        try {
            int clazz_id = resultSet.getInt("clazz_id");
            String clazz_name = resultSet.getString("clazz_name");
            String clazz_information = resultSet.getString("clazz_information");
            clazz.setId(clazz_id);
            clazz.setName(clazz_name);
            clazz.setInformation(clazz_information);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static List<Clazz> getClazzs(int page, int limit) {  //获取班级分页信息
        List<Clazz> clazzs = new ArrayList<Clazz>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from clazz limit ?, ?");
            statement.setInt(1, (page - 1) * limit);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Clazz clazz = getClazz(resultSet);
                clazzs.add(clazz);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazzs;
    }

    public static int getCount(){   //获取班级数量
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from clazz");
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

    public static int delClazzById(int id){ //通过id删除班级
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("delete from clazz where clazz_id = ?");
            statement.setInt(1, id);
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int updateClazz(Clazz clazz){ //通过id更新班级
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("update clazz set clazz_name = ?, clazz_information = ? where clazz_id = ?");
            statement.setString(1, clazz.getName());
            statement.setString(2, clazz.getInformation());
            statement.setInt(3, clazz.getId());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static Clazz getClazzById(int id){   //通过班级id返回班级对象
        Clazz clazz = new Clazz();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from clazz where clazz_id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                clazz = getClazz(resultSet);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    public static int addClazz(Clazz clazz){    //添加班级
        int rows = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("insert into clazz (clazz_name, clazz_information) values (?, ?)");
            statement.setString(1, clazz.getName());
            statement.setString(2, clazz.getInformation());
            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows;
    }

    public static int delClazzs(List<Clazz> clazzs){    //批量删除班级
        int rows = 0;
        for(int i = 0; i < clazzs.size(); i ++){
            rows = delClazzById(clazzs.get(i).getId());
        }
        return  rows;
    }

    public static List<Clazz> searchClazzs(String info, int page, int limit){   //根据信息模糊查询班级
        info = "%" + info + "%";
        List<Clazz> clazzs = new ArrayList<Clazz>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from clazz where clazz_id like ? or clazz_name like ? or clazz_information like ? limit ?, ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
            statement.setInt(4, (page - 1) * limit);
            statement.setInt(5, limit);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Clazz clazz = getClazz(resultSet);
                clazzs.add(clazz);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazzs;
    }

    public static List<Clazz> getAllClazzs() {  //获取全体班级
        List<Clazz> clazzs = new ArrayList<Clazz>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from clazz");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Clazz clazz = getClazz(resultSet);
                clazzs.add(clazz);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clazzs;
    }

    public static int getSearchCount(String info){  //获取查找到的班级数量
        info = "%" + info + "%";
        int count = 0;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select count(*) from clazz where clazz_id like ? or clazz_name like ? or clazz_information like ?");
            statement.setString(1, info);
            statement.setString(2, info);
            statement.setString(3, info);
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

}
