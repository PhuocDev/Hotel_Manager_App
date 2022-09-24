Coder: Bui Nhu Phuoc

***** HƯỚNG DẪN CÀI ĐẶT VÀ SỬ DỤNG ******
* Hướng dẫn cài đặt:
- Khởi tạo database bằng cách copy script đã viết trong SQL_spript.txt và chạy trong SQL_server
- Đổi tên tài khoản SQLserver tại java/Connection/DBconnection
- Add dependency vào pom.xmml:
            <dependency>
                <groupId>com.microsoft.sqlserver</groupId>
                <artifactId>mssql-jdbc</artifactId>
                <version>11.2.0.jre11</version>
            </dependency>
-  Test JDBC connection trong java/test
* Hướng dẫn sử dụng:
- Chạy chương trình ở file java/App/Main
- Các tài khoản được tạo sẵn đã có sẵn vai trò (role)
   + Testing account:
        admin:  admin - admin
        clerk: nv001 - nv001
        customer: cu001 - cu001
        Ứng với mỗi tài khoản sẽ có các chức năng khác nhau phụ thuộc vào role của user.
        (Phân loại tự động dựa theo role đã đưa vào trước và có thể thay đổi nếu admin chọn chức năng set Role)
   + Ứng với mỗi role khác nhau sẽ trả về các chức năng khác nhau


