package com.nhlstenden.bmdb.database;

import java.sql.*;
import java.util.*;


public class DatabaseConnection {
    private static DatabaseConnection _instance = null;

    private Connection _conn;

    private String _url;
    public String _username;
    private String _password;

    /**
     * Constructor of class DatabaseConnection
     */
    private DatabaseConnection(){
        this._conn = null;

        // TODO: read this from .json file!
        this._url = "jdbc:postgresql://84.86.32.123:5432/bmdb";
        this._username = "bmdb";
        this._password = "B1g_M0v13_D4t4b4s3_P455w0rd_F0r_5ch00l";
    }

    /**
     * Get instance of class
     * @return DatabaseConnection
     */
    public static DatabaseConnection getInstance(){
        if(_instance == null)
            _instance = new DatabaseConnection();
        return _instance;
    }

    /**
     * Connect to database
     */
    public void connect(){

        Properties props = new Properties();
        props.setProperty("user", _username);
        props.setProperty("password", _password);

        try {
            _conn = DriverManager.getConnection(_url, props);
        }catch(Exception e){
            System.out.println(e);
            _conn = null;
        }
    }

    /**
     * Disconnect from database
     */
    public void disconnect(){
        try {
            _conn.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Check connection with database
     * @return Boolean
     */
    public boolean isConnected(){
        if(_conn == null){
            return false;
        }
        try{
            return !_conn.isClosed();
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    /**
     * Get connection to database
     * @return Connection
     */
    public Connection getConnection(){
        return _conn;
    }

    public Map<String, String[]> query(String _sqlQuery){
        Map<String, String[]> list = new HashMap<String, String[]>();

        try {
            // execute SQL
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery(_sqlQuery);

            // get column count and fetch size
            int columnSize = rs.getMetaData().getColumnCount();

            // create temp data array
            ArrayList<ArrayList<String>> tempDataList = new ArrayList<ArrayList<String>>();
            for(int i = 0; i < columnSize; i++){
                tempDataList.add(new ArrayList<String>());
            }

            // get data
            while (rs.next()) {
                for (int i = 1; i <= columnSize; i++){
                    tempDataList.get(i-1).add(rs.getString(i));
                }
            }

            // print column names
            for (int i = 1; i <= columnSize; i++){
                list.put(rs.getMetaData().getColumnName(i), tempDataList.get(i-1).toArray(new String[tempDataList.get(i-1).size()]));
            }

            rs.close();
            st.close();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }

        return list;
    }
}
