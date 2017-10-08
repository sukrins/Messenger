/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.shrestha.javabrains.messenger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sukrins
 */
public class MessageDatabase {

    public Connection connection = null;
    String DB_URL = "jdbc:mysql://localhost:3306/messenger";
    String USER = "user";
    String PASSWORD = "itmd466";

    Statement statement = null;
    PreparedStatement preparedStatement = null;

    public List<Message> getAllMessages() {
        List<Message> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            System.out.println("Inside the Connection");
            statement = connection.createStatement();
            String sql = "SELECT id, message, author, dateCreated from message";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Message newMessage = new Message();
                newMessage.setId(resultSet.getInt("id"));
                newMessage.setMessage(resultSet.getString("message"));
                newMessage.setAuthor(resultSet.getString("author"));
                newMessage.setCreated(resultSet.getDate("dateCreated"));
                list.add(newMessage);
            }
        } catch (ClassNotFoundException | SQLException e) {
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se2) {
            }
        }
        return list;
    }

    public Message getMessage(long id) {
        Message newMessage = new Message();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "SELECT id, message, author, dateCreated from message where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet requestSet = preparedStatement.executeQuery();
            while (requestSet.next()) {
                newMessage.setId(requestSet.getInt("id"));
                newMessage.setMessage(requestSet.getString("message"));
                newMessage.setAuthor(requestSet.getString("author"));
                newMessage.setCreated(requestSet.getDate("dateCreated"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return newMessage;
    }

    public void insertMessage(Message message) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "Insert into Message values (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, message.getId());
            preparedStatement.setString(2, message.getMessage());
            preparedStatement.setString(3, message.getAuthor());
            preparedStatement.setDate(4, new java.sql.Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void updateMessage(Message message) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "update message set Message = ?, Author =? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message.getMessage());
            preparedStatement.setString(2, message.getAuthor());
            preparedStatement.setLong(3, message.getId());
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void deleteMessage(long id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(DB_URL, USER, PASSWORD);
            String sql = "DELETE FROM message WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
