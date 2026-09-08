<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Echo Demo</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body {
            font-family: Arial, sans-serif;
            background-color: #f0f2f5;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            padding: 40px 16px;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 100%;
            max-width: 680px;
        }

        h1 {
            font-size: 1.6rem;
            color: #1a1a2e;
            margin-bottom: 12px;
        }

        .status-bar {
            display: flex;
            align-items: center;
            gap: 8px;
            margin-bottom: 20px;
            font-size: 0.95rem;
            color: #555;
        }

        .status-dot {
            width: 10px;
            height: 10px;
            border-radius: 50%;
            background-color: #ccc;
            display: inline-block;
            transition: background-color 0.3s;
        }

        .status-dot.connected    { background-color: #28a745; }
        .status-dot.disconnected { background-color: #dc3545; }

        #status {
            font-weight: bold;
            color: #333;
        }

        #messages {
            height: 280px;
            overflow-y: auto;
            border: 1px solid #ddd;
            border-radius: 6px;
            padding: 12px;
            margin-bottom: 16px;
            background-color: #fafafa;
        }

        #messages p {
            margin-bottom: 6px;
            font-size: 0.9rem;
            line-height: 1.5;
            word-break: break-word;
        }

        .server { color: #006400; }
        .client { color: #003f9e; }
        .error  { color: #b00020; }

        .controls {
            display: flex;
            gap: 8px;
        }

        #messageInput {
            flex: 1;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 0.95rem;
            outline: none;
            transition: border-color 0.2s;
        }

        #messageInput:focus {
            border-color: #4a90e2;
        }

        button {
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            font-size: 0.9rem;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        #sendButton {
            background-color: #4a90e2;
            color: white;
        }

        #sendButton:hover:not(:disabled) {
            background-color: #357abd;
        }

        #closeButton {
            background-color: #dc3545;
            color: white;
        }

        #closeButton:hover:not(:disabled) {
            background-color: #b02a37;
        }

        button:disabled {
            background-color: #ccc;
            cursor: not-allowed;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>WebSocket Echo Demo</h1>

    <div class="status-bar">
        <span class="status-dot" id="statusDot"></span>
        Trạng thái: <span id="status">Chưa kết nối</span>
    </div>

    <div id="messages"></div>

    <div class="controls">
        <input id="messageInput"
               type="text"
               placeholder="Nhập thông điệp và nhấn Enter hoặc Gửi..."
               autocomplete="off">
        <button id="sendButton" type="button" disabled>Gửi</button>
        <button id="closeButton" type="button" disabled>Đóng</button>
    </div>
</div>

<script>
    // ─── Xác định URL WebSocket ──────────────────────────────────────────────
    const contextPath  = "${pageContext.request.contextPath}";
    const protocol     = window.location.protocol === "https:" ? "wss" : "ws";
    const websocketUrl = protocol + "://" + window.location.host + contextPath + "/ws/echo";

    // ─── Lấy các phần tử DOM ────────────────────────────────────────────────
    const statusEl    = document.getElementById("status");
    const statusDot   = document.getElementById("statusDot");
    const messagesEl  = document.getElementById("messages");
    const inputEl     = document.getElementById("messageInput");
    const sendBtn     = document.getElementById("sendButton");
    const closeBtn    = document.getElementById("closeButton");

    // ─── Tạo kết nối WebSocket ──────────────────────────────────────────────
    const socket = new WebSocket(websocketUrl);

    // Kết nối thành công
    socket.onopen = function () {
        setStatus("Đã kết nối", true);
        sendBtn.disabled  = false;
        closeBtn.disabled = false;
        appendMessage("Đã kết nối đến: " + websocketUrl, "server");
    };

    // Nhận message từ server
    socket.onmessage = function (event) {
        appendMessage(event.data, "server");
    };

    // Lỗi WebSocket
    socket.onerror = function () {
        appendMessage("Đã xảy ra lỗi WebSocket!", "error");
    };

    // Kết nối đóng
    socket.onclose = function (event) {
        setStatus("Đã đóng kết nối", false);
        sendBtn.disabled  = true;
        closeBtn.disabled = true;
        appendMessage("Kết nối đã đóng. Code: " + event.code, "error");
    };

    // ─── Xử lý sự kiện nút Gửi ─────────────────────────────────────────────
    sendBtn.addEventListener("click", sendMessage);

    // Nhấn Enter cũng gửi message
    inputEl.addEventListener("keydown", function (e) {
        if (e.key === "Enter") sendMessage();
    });

    // Nhấn nút Đóng
    closeBtn.addEventListener("click", function () {
        socket.close(1000, "Client đóng kết nối");
    });

    // ─── Hàm gửi message ────────────────────────────────────────────────────
    function sendMessage() {
        const message = inputEl.value.trim();
        if (message === "") return;

        if (socket.readyState !== WebSocket.OPEN) {
            appendMessage("WebSocket chưa sẵn sàng!", "error");
            return;
        }

        socket.send(message);
        appendMessage("Client: " + message, "client");
        inputEl.value = "";
        inputEl.focus();
    }

    // ─── Hàm thêm message vào khung hiển thị ────────────────────────────────
    function appendMessage(text, cssClass) {
        const p = document.createElement("p");
        p.textContent = text;
        p.className   = cssClass;
        messagesEl.appendChild(p);
        messagesEl.scrollTop = messagesEl.scrollHeight;
    }

    // ─── Hàm cập nhật trạng thái kết nối ────────────────────────────────────
    function setStatus(text, isConnected) {
        statusEl.textContent = text;
        statusDot.className  = "status-dot " + (isConnected ? "connected" : "disconnected");
    }
</script>
</body>
</html>
