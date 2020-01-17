package com.czh.hive;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestHive {
    /*hiverserver 版本使用此驱动*/
    //private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    /*hiverserver2 版本使用此驱动*/
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    public static void main(String[] args) throws Exception {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Connection conn = DriverManager.getConnection("jdbc:hive2://cdh-port001:10000/default", "hdfs", "hdfs");
        /**  root 用户能查询 select * from t_emp, 但查询 select * from t_emp where age > 30 会报错 ，可能是权限问题，还没有彻底试过。 用hdfs用户就查询*/
        //  Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.0.97:10000/default", "root", "123456");
        try {
            Statement st = conn.createStatement();

            // show tables
            String sql = "show tables '" + "records" + "'";
            System.out.println("Running: " + sql);
            ResultSet res = st.executeQuery(sql);
            if (res.next()) {
                System.out.println(res.getString(1));
            }

            // describe table
            sql = "describe " + "records";
            System.out.println("Running: " + sql);
            res = st.executeQuery(sql);
            while (res.next()) {
                System.out.println(res.getString(1) + "\t" + res.getString(2));
            }

            // 查询
            sql = "select * from " + "records" + " limit 100";
            res = st.executeQuery(sql);
            while (res.next()) {
                System.out.println(String.valueOf(res.getInt(1)) + "\t"
                        + res.getString(2) + "\t"
                        + res.getString(3) + "\t"
                        + res.getString(4));
            }


            /*
            ResultSet ret = st.executeQuery("select * from records limit 100");
            if (ret.next()) {
                while (ret.next()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("id:" + ret.getInt("id"));
                    sb.append("name:" + ret.getString("name"));
                    sb.append("age:" + ret.getInt("age"));
                    //sb.append("deptName:" + ret.getString("dept_name"));
                    System.out.println(sb.toString());
                }
                System.out.println(ret.getInt(1));
            }
            */


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }








}
