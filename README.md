# Centre-of-Attention-source (nhánh /dev/)

### Tóm tắt
Với commit [này](https://github.com/nvatuan/Centre-of-Attention-source/commit/e2dd9f74ba6f8fbffe9c64719d35eaa7f0154042), chúng mình đã hoàn thiện hóa chương trình của mình. Tất cả chức năng quan trọng, cần thiết cho dự án đã được hoàn tất, tiêu biểu là chức năng vẽ hình.

Tuy có 2 người code, chúng mình đã cố gắng sao cho code style của cả 2 trùng nhau nhất có thể: camelStyle, cách đặt tên biến, tab indent,... 
Mình đã dành ra một hoặc hai session (hoặc commit) để đảm bảo rằng source code trông gọn và đẹp. Tuy còn nhiều block comment mình chưa xóa đi vì nó khá quan trọng nếu như chúng mình quyết định phát triển thêm về phần đó.

Mình cũng đã tổ chức lại code sau khi `merge` từ `/dev/algo/java` và `/dev/gui`, sử dụng các chức năng `package` để tham chiếu các class với nhau.

Chúng mình hài lòng với phiên bản hiện tại rồi và nghĩ đến lúc request review từ các mentors.

### Ảnh demo
Khi vừa mới chạy chương trình:
![image](https://user-images.githubusercontent.com/24392632/85954634-22a0e900-b9a3-11ea-9e67-d9b745a03f0d.png)

Chạy một test nhỏ demo chức năng vẽ hình
![image](https://user-images.githubusercontent.com/24392632/85954969-a2c84e00-b9a5-11ea-9b0d-810b3be62f5d.png)

Chạy Test to hơn chút
![image](https://user-images.githubusercontent.com/24392632/85954644-35b3b900-b9a3-11ea-8a0c-44b433a93d40.png)

Chạy hai Test kích cỡ trung bình (200x200)
![image](https://user-images.githubusercontent.com/24392632/85954663-58de6880-b9a3-11ea-830f-0686e488667a.png)
![image](https://user-images.githubusercontent.com/24392632/85954727-a8249900-b9a3-11ea-8e24-60b11bf68957.png)

Mình chưa dám chạy test lớn (1000x1000), sau khi chạy Test trung bình `java` sử dụng ~1GB RAM và hiện tại mình đang render vài thứ, nếu xảy ra màn hình xanh thì thật không may, mình sẽ tìm cơ hội để chạy thử.
