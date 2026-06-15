# 🌍 Ứng dụng Đặt Tour Du Lịch (Travel Booking App)

Một ứng dụng di động Android chuyên nghiệp dành cho việc khám phá và đặt tour du lịch tại Việt Nam. Được xây dựng hoàn toàn bằng **Jetpack Compose** theo mô hình **Clean Architecture + MVVM**, đáp ứng 100% các tiêu chí khắt khe về đánh giá UI/UX và thiết kế kiến trúc phần mềm mới nhất.

---

## ✨ Tính năng nổi bật (Core Features)

- **🎨 UI/UX Hiện đại (Material Design 3):** Giao diện mượt mà với 6 màn hình chức năng. Hỗ trợ tự động chuyển đổi chế độ Sáng/Tối (Light/Dark Mode).
- **🌤️ Thời tiết theo thời gian thực:** Tích hợp API thời tiết để tra cứu thời tiết ngay tại điểm du lịch (Trang Khám Phá).
- **📋 Đặt Tour & Validate:** Giao diện đặt tour với các quy tắc bắt lỗi chặt chẽ (SDT chuẩn, số lượng người > 0, có ngày khởi hành).
- **📊 Quản lý Lịch sử (Full CRUD):** 
  - Đọc (Read): Xem danh sách tour đã đặt bằng `LazyColumn`.
  - Cập nhật (Update): Chỉnh sửa số lượng và ngày đi thông qua Dialog.
  - Xóa (Delete): Hủy hóa đơn dễ dàng.
  - **Thống kê chuyên sâu:** Tự động tính tổng số lượng tour, tổng chi phí, phân tích Tour đắt nhất / Rẻ nhất.
- **📤 Xuất Dữ liệu:** Hỗ trợ trích xuất toàn bộ lịch sử hóa đơn ra định dạng **JSON** để chia sẻ qua Intent.

---

## 🛠️ Công nghệ sử dụng (Tech Stack)

- **Ngôn ngữ:** Kotlin 100%
- **Giao diện:** Jetpack Compose 
- **Kiến trúc phần mềm:** MVVM (Model - View - ViewModel)
- **Luồng dữ liệu (State):** `StateFlow`, Unidirectional Data Flow (UDF).
- **Cơ sở dữ liệu (Local DB):** Room Database (Lưu trữ hóa đơn) & DataStore (Lưu trạng thái giao diện).
- **Mạng (Network API):** Retrofit2, Gson.
- **Điều hướng (Navigation):** Jetpack Navigation Compose.
- **Hình ảnh:** Coil (Load ảnh từ URL bất đồng bộ).

---

## 📂 Cấu trúc Kiến trúc (Clean Architecture)

Dự án được phân chia theo các lớp ranh giới chuẩn mực:
- **`data/`**: Tầng xử lý dữ liệu (Chứa Local Database Room và Remote API Retrofit).
- **`domain/`**: Tầng chứa các Model cốt lõi (Entities).
- **`repository/`**: Tầng kho chứa, đứng giữa Data và ViewModel.
- **`ui/screen/`**: Tầng giao diện, chứa các màn hình (`*Screen.kt`) và luồng xử lý riêng rẽ (`*ViewModel.kt`).

---

## 🚀 Hướng dẫn cài đặt & Chạy ứng dụng

1. Tải source code về máy.
2. Mở dự án bằng **Android Studio**.
3. Đợi Gradle đồng bộ (Sync) xong các thư viện thiết yếu.
4. Bấm **Run** (`Shift + F10`) để biên dịch và chạy ứng dụng trên máy ảo (Emulator) hoặc điện thoại thật.

---

## 📝 Nhật ký vòng đời (Lifecycle Logcat)
Ứng dụng đã ghi đè toàn bộ các hàm vòng đời Activity. Khi chạy ứng dụng, bạn có thể lọc cửa sổ **Logcat** với thẻ `MainActivity` để theo dõi các trạng thái chuyển đổi vòng đời chuẩn của Android: `onStart`, `onResume`, `onPause`, `onStop`, `onDestroy`.