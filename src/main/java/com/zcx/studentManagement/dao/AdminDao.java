package com.zcx.studentManagement.dao;

import com.zcx.studentManagement.entity.Administrator;
import com.zcx.studentManagement.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {

    public static List<Administrator> getAdministratorByName(String name){  //通过名称获取管理员对象列表
        List<Administrator> administrators = new ArrayList<Administrator>();
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareCall("select * from administrator where admin_name = ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int admin_id = resultSet.getInt("admin_id");
                String admin_name = resultSet.getString("admin_name");
                String admin_password = resultSet.getString("admin_password");

                Administrator administrator = new Administrator();
                administrator.setId(admin_id);
                administrator.setName(admin_name);
                administrator.setPassword(admin_password);

                administrators.add(administrator);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administrators;
    }

}
