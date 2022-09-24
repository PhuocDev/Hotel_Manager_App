package DAO;

import Connection.DBconnection;
import Model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class RoomDAO {
    public ArrayList<Room> getAll() {
        ArrayList<Room> dsRoom = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * from room";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String roomID = resultSet.getString("roomID").replaceAll(" ","");
                String loaiPhong = resultSet.getString("loaiPhong").replaceAll(" ","");
                String status = resultSet.getString("status").replaceAll(" ", "");
                Room room = new Room(roomID, loaiPhong, status);
                dsRoom.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsRoom;
    }
    public void addRoom(Room roomAdded) {
        try {
            DBconnection.open();
            String sqlQuery = "INSERT into room values (?,?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, generateRoomID());
            preparedStatement.setString(2, roomAdded.getLoaiPhong());
            preparedStatement.setString(3, roomAdded.getStatus());
            preparedStatement.executeUpdate();
            System.out.println("Successfully added new room");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.close();
        }
    }
    private static String generateRoomID() {
        String ID = UUID.randomUUID().toString().replaceAll("-", "").substring(0,5);
        return ID;
    }

    public void updateRoomStatus(String roomID, String status) {
        try {
            DBconnection.open();
            String sqlQuery = "Update room SET status = ? where roomID = ?";
            PreparedStatement preparedStatement = DBconnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, status);
            preparedStatement.setString(2, roomID);
            preparedStatement.executeUpdate();
            System.out.println("Updated successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBconnection.close();
        }
    }
    public void addBooking(String userID, String roomID, String ngayBook) {
        try {
            DBconnection.open();
            String sqlQuery = "INSERT into booking values (?,?,?)";
            PreparedStatement preparedStatement = DBconnection.getConnection().prepareStatement(sqlQuery);
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, roomID);
            preparedStatement.setString(3, ngayBook);
            preparedStatement.executeUpdate();
            System.out.println("room booked successfully!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            DBconnection.close();
        }
    }

    public ArrayList<Room> getAlEmptyRoom() {
        ArrayList<Room> dsEmptyRoom = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(DBconnection.getURL());
            Statement statement = connection.createStatement();
            String sqlQuery = "SELECT * from room where status = 'empty'";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                String roomID = resultSet.getString("roomID").replaceAll(" ","");
                String loaiPhong = resultSet.getString("loaiPhong").replaceAll(" ","");
                String status = resultSet.getString("status").replaceAll(" ", "");
                Room room = new Room(roomID, loaiPhong, status);
                dsEmptyRoom.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsEmptyRoom;
    }
}
