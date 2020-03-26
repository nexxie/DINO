package com.yaroslav;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.Properties;

public class CreateTable {


  static String DB_URL_FOR_CREATE_DB = "jdbc:postgresql://127.0.0.1:5432/postgres";
  static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/traffic_limits";
  static String USER = "nexxie";
  static String PASS = "Z57105930";


  public static Connection getDBConnectionForNewDB() {
    Connection dbConnection = null;
    try {
      Class.forName("com.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      dbConnection = DriverManager.getConnection(DB_URL_FOR_CREATE_DB, USER, PASS);
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return dbConnection;
  }

  public static Connection getDBConnection() {
    Connection dbConnection = null;
    try {
      Class.forName("com.postgresql.Driver");
    } catch (ClassNotFoundException e) {
      System.out.println(e.getMessage());
    }
    try {
      dbConnection = DriverManager.getConnection(DB_URL, USER, PASS);
      return dbConnection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return dbConnection;
  }

  public static void createTable() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;


    String createTableSQL = "CREATE TABLE limits_per_hour("
            + "limit_name text, "
            + "limit_value INTEGER NOT NULL, "
            + "effective_date DATE "
            + ")";

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();

      // выполнить SQL запрос
      statement.execute(createTableSQL);
      System.out.println("Table  is created!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }
  }


  public static void createDB() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;

    String createDBSQL = "CREATE DATABASE traffic_limits";

    try {
      dbConnection = getDBConnectionForNewDB();
      statement = dbConnection.createStatement();

      // выполнить SQL запрос
      statement.execute(createDBSQL);
      System.out.println("DB  is created!");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }


  }

  static void insertTable() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;

    String insertTableSQL = "INSERT INTO limits_per_hour"
            + "(limit_name, limit_value,effective_date) " + "VALUES"
            + "('min',1234, CURRENT_DATE)";

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();

      // выполнить SQL запрос
      statement.execute(insertTableSQL);
      System.out.println("Data has been inserted !");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }


  }

  static void selectData() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;
    ResultSet resultSet = null;

    String selectDataSql = "SELECT * FROM  limits_per_hour"
            + "(limit_name, limit_value,effective_date) " + "VALUES"
            + "('test',1234, CURRENT_DATE)";

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();

      statement.execute(selectDataSql);

      System.out.println("Data has been inserted !");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    } finally {
      if (statement != null) {
        statement.close();
      }
      if (dbConnection != null) {
        dbConnection.close();
      }
    }

  }
}