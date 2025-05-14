# 🌱 Hệ Thống Phê Duyệt Đơn Xin Nghỉ Phép

Một hệ thống microservices để quản lý và phê duyệt đơn xin nghỉ phép trong doanh nghiệp, được phát triển dựa trên kiến trúc hướng dịch vụ (SOA).

![Employee Leave Management System](https://via.placeholder.com/800x400?text=Employee+Leave+Management+System)

## 📋 Tổng Quan Dự Án

Hệ thống Phê Duyệt Đơn Xin Nghỉ Phép là một giải pháp toàn diện cho phép nhân viên nộp đơn xin nghỉ phép qua hệ thống, quản lý phê duyệt hoặc từ chối dựa trên tình trạng làm việc của nhân viên và quy định của công ty. Hệ thống tự động hóa quy trình từ khi nộp đơn đến khi được phê duyệt và thông báo kết quả.

## 👩‍💻 Thành Viên Nhóm

| Tên | Mã Sinh Viên |
|-----|--------------|
| Phan Tiến Tài | B21DCCN655 |
| Lê Nguyễn Hải Đăng | B21DCCN200 |
| Phạm Đức Anh | B18DCCN033 |
### Tính Năng Chính

- **Nộp đơn nghỉ phép**: Nhân viên có thể nộp các loại đơn nghỉ phép khác nhau
- **Quản lý số ngày nghỉ**: Theo dõi và quản lý số ngày nghỉ phép đã sử dụng và còn lại
- **Phê duyệt tự động**: Quy trình phê duyệt đơn tự động từ quản lý trực tiếp
- **Thông báo**: Thông báo tự động về trạng thái đơn
- **Báo cáo**: Tạo báo cáo về tình trạng nghỉ phép của nhân viên

## 🏗️ Kiến Trúc Hệ Thống

Hệ thống được xây dựng theo kiến trúc microservices, với các thành phần chính:

- **Gateway Service**: Điểm vào duy nhất cho tất cả các request API
- **Employee Service**: Quản lý thông tin nhân viên và xác thực
- **Leave Service**: Quản lý số ngày nghỉ phép và loại nghỉ phép
- **Approval Service**: Xử lý quy trình phê duyệt đơn
- **JWT Service**: Quản lý xác thực và phân quyền
- **Password Service**: Xử lý mã hóa và xác thực mật khẩu

![Architecture Diagram](https://via.placeholder.com/800x500?text=Microservices+Architecture)

## 🚀 Quy Trình Nghiệp Vụ

Quy trình phê duyệt đơn xin nghỉ phép bao gồm các bước sau:

1. **Nộp đơn**: Nhân viên nộp đơn với thông tin ngày nghỉ, loại nghỉ phép và lý do
2. **Kiểm tra thông tin**: Hệ thống kiểm tra thông tin nhân viên và số ngày nghỉ còn lại
3. **Gửi yêu cầu phê duyệt**: Đơn được gửi đến quản lý trực tiếp của nhân viên
4. **Xem xét đơn**: Quản lý xem xét chi tiết đơn và tình trạng công việc hiện tại
5. **Quyết định**: Quản lý phê duyệt hoặc từ chối đơn dựa trên các tiêu chí
6. **Thông báo kết quả**: Hệ thống gửi thông báo về kết quả cho nhân viên
7. **Cập nhật hệ thống**: Nếu được phê duyệt, hệ thống cập nhật số ngày nghỉ còn lại

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

## 📚 API Documentation

### Employee Service

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/employee/register` | POST | Đăng ký tài khoản nhân viên mới |
| `/employee/login` | POST | Đăng nhập và lấy JWT token |
| `/employee/getListEmployeeIds/{managerId}` | GET | Lấy danh sách ID nhân viên dưới quyền quản lý |

### Leave Service

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/leave` | POST | Tạo số dư ngày nghỉ phép cho nhân viên |
| `/leave/balance/employeeId/{employeeId}` | GET | Lấy số ngày nghỉ phép còn lại của nhân viên |
| `/leave/balance` | POST | Cập nhật số ngày nghỉ phép |

### Approval Service

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/request` | POST | Tạo đơn xin nghỉ phép mới |
| `/request/getMyLeaveRequest` | GET | Lấy danh sách đơn xin nghỉ phép của bản thân |
| `/request/getMyEmployeesPendingLeaveRequest` | GET | Lấy danh sách đơn chờ phê duyệt (cho quản lý) |
| `/request/updatePendingLeaveRequest/{requestId}` | POST | Phê duyệt/từ chối đơn nghỉ phép |

### JWT Service

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/jwt` | POST | Tạo token JWT mới |
| `/jwt/getEmployeeId` | POST | Lấy ID nhân viên từ token |
| `/jwt/getManagerId` | POST | Lấy ID quản lý từ token |

### Password Service

| Endpoint | Method | Description |
|----------|--------|-------------|
| `/password/create` | POST | Tạo mật khẩu đã mã hóa |
| `/password/check` | POST | Kiểm tra mật khẩu |



## 🔗 Liên Kết Hữu Ích

- [Tài liệu API](./docs/api-specs/)
- [Kiến trúc hệ thống](./docs/architecture.md)
- [Phân tích và thiết kế hệ thống](./docs/analysis-and-design.md)

## 📜 Giấy Phép

Dự án này được phát triển dựa trên template của Hung Dang (hungdn@ptit.edu.vn, GitHub: hungdn1701).