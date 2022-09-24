package Model;

public class Room {
    private String roomID;
    private String loaiPhong;
    private String status;

    public Room () {

    }
    public Room(String roomID, String loaiPhong, String status) {
        this.roomID = roomID;
        this.loaiPhong = loaiPhong;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomID='" + roomID + '\'' +
                ", loaiPhong='" + loaiPhong + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
