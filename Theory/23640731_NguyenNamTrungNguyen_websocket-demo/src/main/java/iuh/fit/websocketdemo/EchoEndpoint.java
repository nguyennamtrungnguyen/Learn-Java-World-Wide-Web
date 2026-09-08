package iuh.fit.websocketdemo;

import jakarta.websocket.CloseReason;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;

@ServerEndpoint("/ws/echo")
public class EchoEndpoint {

    private static final System.Logger LOGGER =
            System.getLogger(EchoEndpoint.class.getName());

    /**
     * Được gọi khi client kết nối thành công.
     * Server gửi lời chào kèm Session ID về cho client.
     */
    @OnOpen
    public void onOpen(Session session) throws IOException {
        LOGGER.log(
                System.Logger.Level.INFO,
                "Mở kết nối: {0}",
                session.getId()
        );
        session.getBasicRemote().sendText(
                "Kết nối thành công. Session ID: " + session.getId()
        );
    }

    /**
     * Được gọi khi server nhận message từ client.
     * Trả về chuỗi String → tự động được gửi lại cho client.
     */
    @OnMessage
    public String onMessage(String message, Session session) {
        LOGGER.log(
                System.Logger.Level.INFO,
                "Session {0} gửi: {1}",
                session.getId(),
                message
        );
        return "Server đã nhận: " + message;
    }

    /**
     * Được gọi khi kết nối WebSocket đóng lại.
     */
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        LOGGER.log(
                System.Logger.Level.INFO,
                "Đóng session {0}: {1}",
                session.getId(),
                closeReason.getReasonPhrase()
        );
    }

    /**
     * Được gọi khi có lỗi xảy ra trong quá trình giao tiếp.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        String sessionId = session == null ? "unknown" : session.getId();
        LOGGER.log(
                System.Logger.Level.ERROR,
                "Lỗi WebSocket tại session " + sessionId,
                error
        );
    }
}