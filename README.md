# 🌱 Hệ Thống Phê Duyệt Đơn Xin Nghỉ Phép

Một hệ thống microservices để quản lý và phê duyệt đơn xin nghỉ phép trong doanh nghiệp, được phát triển dựa trên kiến trúc hướng dịch vụ (SOA).



## Phê duyệt yêu cầu nghỉ phép
 
### Mô tả nghiệp vụ: 
Quy trình phê duyệt yêu cầu nghỉ phép cho phép nhân viên nộp đơn xin nghỉ phép qua hệ thống. Quản lý sau đó sẽ xem xét yêu cầu và phê duyệt hoặc từ chối dựa trên tình trạng làm việc của nhân viên và các quy định nghỉ phép của công ty. Nếu đơn xin nghỉ được phê duyệt, hệ thống sẽ gửi thông báo cho nhân viên và cập nhật tình trạng nghỉ phép của họ. 

### Yêu cầu phân tích thiết kế hướng dịch vụ cho nghiệp vụ (Usecase)

Phân tích và thiết kế hệ thống phê duyệt yêu cầu nghỉ phép dựa trên kiến trúc hướng dịch vụ (SOA), đảm bảo quy trình từ khi nhân viên nộp đơn, xác minh thông tin, đến phê duyệt hoặc từ chối, và thông báo kết quả được thực hiện tự động và chính xác. 

### Mô tả chi tiết các bước nghiệp vụ: 

1. Nhân viên nộp yêu cầu nghỉ phép: Nhân viên nhập thông tin về ngày nghỉ dự kiến, loại nghỉ phép (ví dụ: nghỉ ốm, nghỉ phép năm), và lý do nộp đơn xin nghỉ. 

2. Nhận thông tin chi tiết về nhân viên: Hệ thống nhận thông tin về nhân viên, bao gồm mã nhân viên, tên, và phòng ban.

3. Kiểm tra lịch sử nghỉ phép: Hệ thống truy xuất lịch sử nghỉ phép của nhân viên để kiểm tra số ngày nghỉ phép đã sử dụng trong năm. 

4. Kiểm tra số ngày nghỉ còn lại: Hệ thống xác minh xem nhân viên còn bao nhiêu ngày nghỉ phép và liệu yêu cầu có vượt quá số ngày nghỉ hiện có hay không.

5. Gửi yêu cầu đến quản lý: Nếu thông tin hợp lệ, hệ thống gửi yêu cầu nghỉ phép đến quản lý trực tiếp của nhân viên để phê duyệt. 

6. Quản lý nhận thông báo yêu cầu phê duyệt: Quản lý nhận được thông báo về yêu cầu nghỉ phép của nhân viên và truy cập hệ thống để xem chi tiết. 

7. Xem xét yêu cầu: Quản lý kiểm tra thông tin yêu cầu, bao gồm ngày nghỉ, lý do, và tình trạng công việc hiện tại.

8. Phê duyệt hoặc từ chối yêu cầu: Quản lý quyết định phê duyệt hoặc từ chối yêu cầu dựa trên thông tin đã xem xét. 

9. Nếu phê duyệt, hệ thống gửi thông báo chấp nhận: Nếu quản lý phê duyệt yêu cầu, hệ thống gửi thông báo chấp nhận nghỉ phép cho nhân viên và cập nhật lịch nghỉ. 

10. Nếu từ chối, hệ thống gửi thông báo từ chối: Nếu quản lý từ chối yêu cầu, hệ thống gửi thông báo từ chối cho nhân viên kèm lý do. 

11. Cập nhật trạng thái nghỉ phép của nhân viên: Nếu yêu cầu được phê duyệt, hệ thống cập nhật trạng thái nghỉ phép của nhân viên trong hệ thống quản lý nhân sự.

12. Kết thúc quy trình: Quy trình kết thúc sau khi hệ thống gửi thông báo và cập nhật thông tin.


## 👩‍💻 Thành Viên Nhóm

| Tên | Mã Sinh Viên |
|-----|--------------|
| Phan Tiến Tài | B21DCCN655 |
| Lê Nguyễn Hải Đăng | B21DCCN200 |
| Phạm Đức Anh | B18DCCN033 |


## 💻 Công Nghệ Sử Dụng

### Backend
- Java Spring Boot 3.4.5
- Spring Cloud Gateway
- Spring Data JPA
- JWT Authentication
- MySQL 8

### Frontend
- React.js
- Tailwind CSS
- Context API

### DevOps
- Docker
- Docker Compose
- Git/GitHub

## 📥 Cài Đặt & Triển Khai

### Yêu Cầu Hệ Thống

- Docker và Docker Compose
- Java Development Kit (JDK) 21
- Node.js và npm (cho phát triển frontend)
- Git

### Cài Đặt Môi Trường Dev

1. Clone repository:
   ```bash
   git clone https://github.com/yourusername/employee-leave-management.git
   
   ```

2. Thiết lập biến môi trường:
   ```bash
   cp .env.example .env
   # Chỉnh sửa file .env với thông tin cần thiết
   ```

3. Khởi động hệ thống:
   ```bash
   docker-compose up --build
   ```

4. Truy cập hệ thống:
   - Backend API: http://localhost:8080
   - Frontend: http://localhost:3000




## 📜 Giấy Phép

Dự án này được phát triển dựa trên template của Hung Dang (hungdn@ptit.edu.vn, GitHub: hungdn1701).