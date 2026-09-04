# Demo Chương 2 — Jakarta EE 11

Dự án Maven WAR tích hợp năm chủ đề: Servlet, Asynchronous Processing, CDI, REST API và WebSocket. Kịch bản là một trung tâm thông báo lớp học; từng demo có thể chạy độc lập, còn thao tác `POST /api/announcements` phát CDI event để WebSocket đẩy thông báo tới các trình duyệt đang kết nối.

## Yêu cầu

- JDK 21
- Maven 3.9+ (IntelliJ có Maven tích hợp)
- Eclipse GlassFish 8.0.3 Web Profile hoặc runtime tương thích Jakarta EE Web Profile 11
- IntelliJ IDEA Ultimate 2025.3.1.1 với Jakarta EE và GlassFish plugins

## Mở và chạy nhanh trong IntelliJ IDEA

1. Chọn **File → Open**, mở thư mục dự án này và chọn **Trust Project**.
2. Chọn JDK 21 tại **File → Project Structure → Project SDK**.
3. Mở cửa sổ Maven và chọn **Reload All Maven Projects**.
4. Thêm GlassFish tại **Settings → Build, Execution, Deployment → Application Servers**.
5. Tạo **GlassFish Server → Local** trong **Run → Edit Configurations**.
6. Ở thẻ Deployment, thêm artifact `jakarta-ee-chapter2-demos:war exploded`, đặt Application context là `/chapter2-demos`.
7. Chạy cấu hình và mở <http://localhost:8080/chapter2-demos/>.

## Các điểm truy cập

| Chủ đề | Method và URL |
|---|---|
| Servlet | `GET /chapter2-demos/demo/servlet/runtime` |
| Async | `GET /chapter2-demos/demo/async/report?delayMs=1500` |
| CDI | `GET /chapter2-demos/demo/cdi/greeting?name=Lan` |
| REST list | `GET /chapter2-demos/api/announcements` |
| REST create | `POST /chapter2-demos/api/announcements` |
| REST 404 | `GET /chapter2-demos/api/announcements/999` |
| WebSocket | `ws://localhost:8080/chapter2-demos/ws/announcements` |

## Build bằng Maven

```shell
mvn clean package
```

Kết quả là `target/chapter2-demos.war`. Dependency Jakarta EE có scope `provided`, vì các API và implementation được runtime cung cấp.

## Payload tạo thông báo

```json
{
  "title": "Đổi phòng học",
  "content": "Buổi học chuyển sang phòng H3.1."
}
```

## Điều cần quan sát

- `servletInstanceId` ổn định giữa các request, nhưng thread có thể thay đổi.
- Session counter gắn với cookie của trình duyệt; application counter được mọi session dùng chung.
- Async request trả về sau độ trễ mà không tự tạo `Thread`; `AsyncContext.start()` dùng tài nguyên do container quản lý.
- `serviceInstanceId` của CDI bean `@ApplicationScoped` ổn định; `requestId` của bean `@RequestScoped` thay đổi.
- REST trả `201 Created` và header `Location`; dữ liệu sai trả error contract `400`.
- Khi REST tạo thông báo, CDI event kích hoạt observer và WebSocket broadcast tới các client.

> Dữ liệu và danh sách WebSocket session chỉ nằm trong bộ nhớ của một server. Chúng mất khi redeploy và chưa phù hợp với cluster nhiều node.
