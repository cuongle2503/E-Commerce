# Báo cáo Giữa Kỳ - Lê Văn Cường - 52100171

## SpringCommerceEssence

### Problem Statement
SpringCommerce là một start-up nhỏ muốn xây dựng một ứng dụng mua sắm trực tuyến đơn giản để bán sản phẩm của họ. Để ra mắt nhanh chóng, họ quyết định xây dựng phiên bản MVP (Minimum Viable Product) với một tập hợp chức năng hạn chế.

---

### Các Nguyên Tắc, Mô Hình Phát Triển Phần Mềm và Thực Tiễn Đang Được Áp Dụng

#### 1. **Nguyên tắc SOLID**
- Đảm bảo thiết kế các lớp và phương thức tuân thủ:
  - **S**: Single Responsibility Principle - Mỗi lớp chỉ chịu trách nhiệm một nhiệm vụ duy nhất.
  - **O**: Open/Closed Principle - Các thành phần có thể mở rộng mà không thay đổi mã hiện có.
  - **L**: Liskov Substitution Principle - Các lớp dẫn xuất có thể thay thế lớp cơ sở mà không gây lỗi.
  - **I**: Interface Segregation Principle - Các giao diện được tách biệt để tránh các phụ thuộc không cần thiết.
  - **D**: Dependency Inversion Principle - Ưu tiên phụ thuộc vào các abstractions thay vì các chi tiết cụ thể.

#### 2. **Mô hình MVC (Model-View-Controller)**
- **Model**:
  - Quản lý dữ liệu và logic xử lý dữ liệu.
  - Ví dụ: Các lớp như `Product`, `Cart`, `Order` để xử lý sản phẩm, giỏ hàng và đơn đặt hàng.
- **View**:
  - Đại diện cho giao diện người dùng.
  - Ví dụ: Trang web hiển thị danh sách sản phẩm, thông tin giỏ hàng.
- **Controller**:
  - Điều phối tương tác giữa Model và View.
  - Ví dụ: Các lớp điều khiển như `ProductController` và `CartController`.

#### 3. **Security (Spring Security)**
- Sử dụng Spring Security để:
  - Quản lý xác thực (Authentication).
  - Quản lý ủy quyền (Authorization).
  - Bảo vệ các endpoint API của ứng dụng.

#### 4. **Spring Boot**
- Cung cấp cấu hình mặc định giúp khởi chạy ứng dụng nhanh chóng.
- Hỗ trợ phát triển các ứng dụng phức tạp với cấu trúc đơn giản hơn.

#### 5. **Thymeleaf**
- Công cụ tạo giao diện web với khả năng tích hợp liền mạch với Spring Boot.
- Hỗ trợ tùy chỉnh giao diện cho các trang hiển thị sản phẩm và giỏ hàng.

#### 6. **Maven**
- Quản lý các thư viện phụ thuộc (dependencies).
- Hỗ trợ quy trình xây dựng và triển khai dự án dễ dàng hơn.

#### 7. **Lombok**
- Sử dụng để:
  - Tự động sinh các phương thức getter, setter, và constructor.
  - Làm giảm độ phức tạp của mã nguồn.
  - Đơn giản hóa các lớp POJO (Plain Old Java Object).

---

*Báo cáo này được viết bởi Lê Văn Cường - 52100171.*
