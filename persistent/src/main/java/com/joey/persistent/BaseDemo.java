package com.joey.persistent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 * @auth Joey
 * @date 2020/11/16
 *
 * 传统数据库连接步骤
 * 1、加载驱动 com.mysql.jdbc.Driver
 * 2、通过驱动管理获取数据库连接
 * 3、通过数据库连接获取预处理sql连接，并设置相关值
 * 4、通过预处理sql来获取结果集
 * 5、筛选结果集
 * 6、关闭结果集、预处理sql、数据库连接
 *
 * 弊端
 * 1、硬编码方式加载（数据库连接、驱动管理、sql语句、解析结果集）
 * 2、资源消耗频繁（数据库连接、结果集、预处理sql 频繁的获取与释放）
 * 3、结果集解析成pojo对象比较方便
 */
public class BaseDemo {

    // 传统数据库连接
    public static void main(String[] args) {

        // 获取连接、预处理sql、驱动加载、结果集
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            // 驱动加载
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            connection = DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/joey","root","123456");
            // 编辑SQL
            String SQL = "select * from student where id = ?";
            preparedStatement = connection.prepareStatement(SQL);
            // SQL预处理
            preparedStatement.setInt(1,1);
            // SQL执行
            resultSet = preparedStatement.executeQuery();
            // 结果集筛选
            while(resultSet.next()){
                System.out.println(resultSet.getString("name"));
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            // 关闭连接
            if(resultSet != null){
                try {
                    resultSet.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
