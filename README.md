# Lime Places — Java + XML (không dùng Fragment)

Project Android Studio đã được chuyển sang kiến trúc Java Activity + XML layout.

## Cấu trúc chính
- `MainActivity.java`: màn hình điều hướng chính.
- `screens/HomeActivity.java`: trang chủ và danh sách địa điểm.
- `screens/PlaceDetailActivity.java`: chi tiết địa điểm, lưu, chia sẻ và mở chỉ đường.
- `screens/AddPlaceActivity.java`: giao diện thêm địa điểm.
- `screens/MapActivity.java`: màn hình bản đồ minh họa.
- `screens/FriendsActivity.java`: bạn bè và lời mời.
- `screens/ProfileActivity.java`: hồ sơ và thiết lập.
- Các giao diện nằm trong `app/src/main/res/layout/activity_*.xml`; các thẻ dùng lại nằm trong `item_*.xml`.

## Mở project
1. Giải nén ZIP.
2. Mở thư mục gốc có `settings.gradle` bằng Android Studio.
3. Chờ Gradle Sync hoàn tất, sau đó chạy ứng dụng.

## Lưu ý
- Các màn hình được mở bằng `Intent`; không còn lớp nào kế thừa `Fragment` và đã bỏ dependency Fragment.
- Dữ liệu hiện vẫn là dữ liệu mẫu trong bộ nhớ (`AppData`), chưa có backend/database.
- Chức năng chọn ảnh, lưu địa điểm mới và chỉnh sửa hiện là giao diện minh họa, chưa lưu dữ liệu bền vững.
- Bản đồ hiện là bản vẽ minh họa; nút chỉ đường mở ứng dụng bản đồ ngoài bằng geo intent.
- Môi trường hiện tại không có Android SDK/Gradle wrapper để chạy build Android đầy đủ, vì vậy hãy Sync và Run trong Android Studio để kiểm tra trên máy của bạn.
