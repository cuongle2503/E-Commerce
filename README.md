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
## Cấu trúc mã của dự án SpringCommerce

### Thư mục và Tệp Chính

#### 1. **`src/main/java`**
- Chứa mã nguồn Java của ứng dụng.
- Đây là nơi tổ chức các **package** và **class** quan trọng:
  - **Controller**: Chứa các lớp chịu trách nhiệm xử lý yêu cầu HTTP và điều phối logic.
  - **Service**: Chứa các lớp triển khai logic nghiệp vụ của ứng dụng.
  - **Repository**: Chứa các lớp để giao tiếp với cơ sở dữ liệu.
  - **Model/Entity**: Chứa các lớp đại diện cho dữ liệu và ánh xạ với bảng trong cơ sở dữ liệu.

#### 2. **`src/main/resources`**
- Chứa các tài nguyên cần thiết cho ứng dụng, bao gồm:
  - **File cấu hình**:
    - `application.properties`: Tệp cấu hình chính của ứng dụng, chứa thông tin như:
      - Kết nối cơ sở dữ liệu (JDBC URL, username, password).
      - Cấu hình HTTP (cổng mặc định, bảo mật, v.v.).
      - Các thuộc tính khác cho Spring Boot.
  - **File tĩnh**:
    - CSS, JavaScript, hình ảnh phục vụ giao diện người dùng.
  - **File mẫu (template)**:
    - Các file HTML được sử dụng bởi Thymeleaf để hiển thị giao diện.

#### 3. **`src/test/java`**
- Chứa mã nguồn kiểm thử (test) cho ứng dụng.
- Các bài kiểm thử thường được viết cho:
  - **Controller**: Kiểm thử API hoặc giao diện người dùng.
  - **Service**: Kiểm thử logic nghiệp vụ.
  - **Repository**: Kiểm thử tương tác với cơ sở dữ liệu.

#### 4. **`src/test/resources`**
- Chứa các tài nguyên phục vụ cho kiểm thử, như:
  - **Dữ liệu kiểm thử**: File JSON, XML, hoặc SQL để chuẩn bị dữ liệu mẫu.
  - **Cấu hình kiểm thử**: File cấu hình khác biệt so với môi trường thật.

#### 5. **`pom.xml`**
- **Tệp cấu hình Maven**:
  - Quản lý các thư viện phụ thuộc (dependencies) cho dự án, ví dụ:
    - Spring Boot Starter (Web, Data JPA, Security, v.v.).
    - Lombok, Thymeleaf, và các thư viện khác.
  - Cài đặt plugin Maven cho quy trình xây dựng và triển khai.
  - Cấu hình:
    - Phiên bản Java sử dụng.
    - Packaging type (thường là `jar` hoặc `war`).

#### 6. **`application.properties`**
- **Tệp cấu hình của ứng dụng**:
  - Chứa các thông số như:
    - Thông tin kết nối cơ sở dữ liệu: 
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
      spring.datasource.username=root
      spring.datasource.password=1234
      ```
    - Cấu hình cổng HTTP:
      ```properties
      server.port=8080
      ```
    - Các cấu hình khác:
      - `spring.jpa.hibernate.ddl-auto=update`
      - `spring.security.enabled=false`
      - `jwt.signerKey= "3NigrTaHMIZxdy/sTX3HGXOO9COzFglnhA5SffZ0ZEuoCW1ig2wO7/SqSHzmAFup"`
        
## Cấu trúc thư mục trong `src/main/java`

### 1. **Thư mục `controller`**
- **Mục đích**:
  - Chứa các lớp và tài nguyên liên quan đến việc triển khai API của ứng dụng.
- **Chức năng chính**:
  - Định nghĩa và triển khai các điểm cuối (endpoints) cho API.
  - Tích hợp và xử lý các yêu cầu từ phía client.
- **Ví dụ**:
  - Lớp `ProductController` để quản lý sản phẩm.
  - Lớp `CartController` để xử lý giỏ hàng.
    
---
### 1.1. **Thư mục `postman` nằm trong thư mục `controller`**

- **Mục đích**:
  - Chứa các lớp điều khiển (Controller) theo mô hình MVC.
- **Chức năng chính**:
  - Nhận và xử lý các yêu cầu HTTP từ người dùng.
  - Tương tác với các lớp `service` để thực hiện logic kinh doanh.
  - Trả về kết quả dưới dạng JSON hoặc HTML.
---

### 1.2. **Thư mục `admin` nằm trong thư mục `controller`**
- **Mục đích**:
  - Chứa các lớp và tài nguyên liên quan đến việc triển khai API của ứng dụng dùng cho admin.
- **Chức năng chính**:
  - Định nghĩa và triển khai các điểm cuối (endpoints) cho API.
  - Tích hợp và xử lý các yêu cầu từ phía client.
---


### 2. **Thư mục `entity`**
- **Mục đích**:
  - Chứa các lớp đại diện cho dữ liệu và các đối tượng chính trong ứng dụng.
- **Chức năng chính**:
  - Định nghĩa cấu trúc dữ liệu được ánh xạ với cơ sở dữ liệu (Entity).
  - Quản lý các đối tượng dữ liệu trong ứng dụng.
- **Ví dụ**:
  - Lớp `Product` đại diện cho sản phẩm.
  - Lớp `Order` đại diện cho đơn hàng.

---

### 3. **Thư mục `repository`**
- **Mục đích**:
  - Chứa các lớp cung cấp khả năng truy cập dữ liệu từ cơ sở dữ liệu.
- **Chức năng chính**:
  - Thực hiện các thao tác CRUD (Create, Read, Update, Delete) trên cơ sở dữ liệu.
  - Tương tác với các lớp `model` để lưu trữ hoặc truy vấn dữ liệu.
- **Ví dụ**:
  - Lớp `ProductRepository` cho các truy vấn sản phẩm.
  - Lớp `OrderRepository` cho các truy vấn đơn hàng.

---

### 4. **Thư mục `dto.request`**
- **Mục đích**:
  - Chứa các lớp đại diện cho dữ liệu từ yêu cầu gửi đến API.
- **Chức năng chính**:
  - Chuyển đổi dữ liệu từ HTTP Request thành các đối tượng Java.
  - Xác định các trường dữ liệu cần thiết từ phía client.
- **Ví dụ**:
  - Lớp `ProductRequest` để nhận thông tin sản phẩm từ yêu cầu thêm mới.

---

### 5. **Thư mục `dto.response`**
- **Mục đích**:
  - Chứa các lớp đại diện cho kết quả trả về từ API.
- **Chức năng chính**:
  - Chuyển đổi dữ liệu thành định dạng JSON hoặc HTML để gửi lại client.
  - Định nghĩa các trường cần trả về để hiển thị cho người dùng.
- **Ví dụ**:
  - Lớp `ProductResponse` để gửi thông tin sản phẩm sau khi xử lý.

---

### 6. **Thư mục `service`**
- **Mục đích**:
  - Chứa các lớp thực hiện logic kinh doanh chính của ứng dụng.
- **Chức năng chính**:
  - Xử lý nghiệp vụ (business logic).
  - Tương tác với các lớp `repository` để truy cập dữ liệu.
  - Cung cấp dữ liệu cho các lớp `controller`.
- **Ví dụ**:
  - Lớp `ProductService` để xử lý logic liên quan đến sản phẩm.
  - Lớp `CartService` để xử lý logic giỏ hàng.

---

### 7. **Thư mục `config`**
- **Mục đích**:
  - Chứa các lớp cấu hình liên quan đến ứng dụng, đặc biệt là Spring Security.
- **Chức năng chính**:
  - Cấu hình xác thực và ủy quyền.
  - Định nghĩa các chính sách bảo mật cho API và ứng dụng web.
- **Ví dụ**:
  - Lớp `SecurityConfig` để cấu hình bảo mật cho ứng dụng.
  - Lớp `WebConfig` để cấu
  - 
---

### 8. **Thư mục `mappper`**
- **Mục đích**:
  - Sử dụng để chứa các lớp đảm nhiệm việc chuyển đổi dữ liệu giữa các lớp khác nhau trong ứng dụng.
- **Chức năng chính**:
  - Chuyển đổi giữa các lớp Entity và DTO (Data Transfer Object.
  - Tách biệt logic chuyển đổi.
  - Tăng tính tái sử dụng.
    
---

### 9. **Thư mục `exception`**

- **Mục đích**:
  - Chứa các lớp xử lý ngoại lệ (exception) trong ứng dụng, giúp quản lý lỗi một cách có tổ chức và cung cấp phản hồi phù hợp khi xảy ra lỗi.

- **Chức năng chính**:
  1. **Quản lý ngoại lệ toàn cục (Global Exception Handling)**:
     - Xử lý các lỗi phổ biến xảy ra trong ứng dụng, ví dụ: lỗi không tìm thấy tài nguyên, lỗi truy cập không hợp lệ, hoặc lỗi do nhập liệu sai.
  2. **Tách biệt logic xử lý lỗi**:
     - Tách logic xử lý ngoại lệ ra khỏi `controller` hoặc `service`, giúp mã gọn gàng hơn và dễ bảo trì.
  3. **Tăng tính mô-đun và tái sử dụng**:
     - Các lớp trong thư mục `exception` có thể được sử dụng lại ở nhiều phần khác nhau của ứng dụng.

---

*Báo cáo này được viết bởi Lê Văn Cường - 52100171.*
