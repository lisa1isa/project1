package com.app.userstable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public ResultSet read_data(Connection conn, String table_name) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    public void insert_row(Connection conn, String table_name, String name, String birthdate, String salary) {
        Statement statement;
        try {
            String query = String.format("insert into %s(user_name,birthdate,salary) values('%s','%s','%s');", table_name, name, birthdate, salary);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row Inserted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void update_name(Connection conn, String table_name, int id, String new_name) {
        Statement statement;
        try {
            String query = String.format("update %s set user_name='%s' where user_id=%s", table_name, new_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Updated");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete_row_by_id(Connection conn, String table_name, int id) {
        Statement statement;
        try {
            String query = String.format("delete from %s where user_id=%s", table_name, id);
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Data Deleted");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}