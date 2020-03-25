package com.yaroslav;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.util.NifSelector;
import org.spark_project.jetty.server.Authentication;

import java.io.IOException;
import java.net.PasswordAuthentication;
import java.sql.*;
import java.util.Properties;

public class Main {

  private static String DB_URL = "jdbc:postgresql://127.0.0.1:5432/traffic_limits";
  private static String USER = "nexxie";
  private static String PASS = "Z57105930";



  public static void main(String[] args) throws SQLException, ClassNotFoundException {
    Class.forName("org.postgresql.Driver");
    getDBConnection();
   // createDB();
   // createDbUserTable();
    insertTable();

    ////////////////////////////////////

//    SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("Spark");
//    JavaSparkContext context = new JavaSparkContext(conf);
//
//
//    JavaRDD<String> stringJavaRDD = context.textFile("/home/nexxie/IdeaProjects/DINO/src/main/resources/text.txt");
//
//    System.out.println(stringJavaRDD.count());
//
//    System.out.println();

  }


  private static Connection getDBConnection() {
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

  private static void createDbUserTable() throws SQLException {
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

  private static void createDB() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;

    String createDBSQL = "CREATE DATABASE traffic_limits";

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();

      // выполнить SQL запрос
      statement.execute(createDBSQL);
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

  private static void insertTable() throws SQLException {
    Connection dbConnection = null;
    Statement statement = null;

    String insertTableSQL = "INSERT INTO limits_per_hour"
            + "(limit_name, limit_value,effective_date) " + "VALUES"
            + "('test',1234, CURRENT_DATE)"
            ;

    try {
      dbConnection = getDBConnection();
      statement = dbConnection.createStatement();

      // выполнить SQL запрос
      statement.execute(insertTableSQL);
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




}
