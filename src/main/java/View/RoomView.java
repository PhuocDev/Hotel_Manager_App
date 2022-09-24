package View;

import Model.Room;

import java.util.ArrayList;
import java.util.Scanner;

public class RoomView {
    private Scanner scanner = new Scanner(System.in);

    public void showMessage(String mess) {
        System.out.println(mess);
    }
    public void showAll(ArrayList<Room> dsRooms) {
        System.out.println("Room View: Danh sach room: ");
        for (Room room : dsRooms) {
            System.out.println(room);
        }
    }
    public Room getRoomInfo() {
        Room room = new Room();
        System.out.println("Room_ Loai phong: ");
        room.setLoaiPhong(scanner.nextLine());
        boolean exitLoop = true;
        do {
            System.out.println("Room_ Status: 1. Empty  2. Booked");
            int option = 0;
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    room.setStatus("empty");
                    break;
                case 2:
                    room.setStatus("booked");
                    break;
                default:
                    System.out.println("Nhap 1 hoac 2");
            }
        } while (!exitLoop);

        return room;
    }
}
