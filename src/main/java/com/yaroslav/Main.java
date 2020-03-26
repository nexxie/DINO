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


  public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
    Class.forName("org.postgresql.Driver");

    Runnable createDB = new Runnable() {
      public void run() {
        CreateTable createTable = new CreateTable();

        createTable.getDBConnectionForNewDB();
        try {
          createTable.createDB();
        } catch (SQLException e) {
          e.printStackTrace();
        }

      }

    };

    Runnable createTable = new Runnable() {
      public void run() {
        CreateTable createTable = new CreateTable();
        createTable.getDBConnection();
        try {
          createTable.createTable();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        try {
          createTable.insertTable();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    };


    Thread thread = new Thread(createDB);
    thread.start();
    Thread thread1 = new Thread(createTable);
    thread1.start();


  }
}
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


