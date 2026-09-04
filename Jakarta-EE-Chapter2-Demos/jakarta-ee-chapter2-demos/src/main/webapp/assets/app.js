const contextPath = window.location.pathname.split('/').slice(0, 2).join('/');
const endpoint = (path) => `${contextPath}${path}`;
const pretty = (value) => typeof value === 'string' ? value : JSON.stringify(value, null, 2);

async function showFetch(url, outputId, options = {}) {
    const output = document.getElementById(outputId);
    output.textContent = 'Đang gửi request…';
    try {
        const response = await fetch(url, options);
        const text = await response.text();
        let data;
        try { data = JSON.parse(text); } catch { data = text; }
        output.textContent = pretty({
            httpStatus: response.status,
            correlationId: response.headers.get('X-Correlation-Id'),
            body: data
        });
        return data;
    } catch (error) {
        output.textContent = `Lỗi: ${error.message}`;
    }
}

document.getElementById('run-servlet').addEventListener('click', () =>
    showFetch(endpoint('/demo/servlet/runtime'), 'servlet-output'));

document.getElementById('run-async').addEventListener('click', () => {
    const delay = document.getElementById('delay').value;
    showFetch(endpoint(`/demo/async/report?delayMs=${encodeURIComponent(delay)}`), 'async-output');
});

document.getElementById('run-cdi').addEventListener('click', () => {
    const name = document.getElementById('student-name').value;
    showFetch(endpoint(`/demo/cdi/greeting?name=${encodeURIComponent(name)}`), 'cdi-output');
});

document.getElementById('list-announcements').addEventListener('click', () =>
    showFetch(endpoint('/api/announcements'), 'rest-output'));

document.getElementById('invalid-announcement').addEventListener('click', () =>
    showFetch(endpoint('/api/announcements'), 'rest-output', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({title: '', content: ''})
    }));

document.getElementById('announcement-form').addEventListener('submit', (event) => {
    event.preventDefault();
    showFetch(endpoint('/api/announcements'), 'rest-output', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify({
            title: document.getElementById('title').value,
            content: document.getElementById('content').value
        })
    });
});

const wsProtocol = window.location.protocol === 'https:' ? 'wss:' : 'ws:';
const socket = new WebSocket(`${wsProtocol}//${window.location.host}${contextPath}/ws/announcements`);
const wsOutput = document.getElementById('ws-output');
const wsStatus = document.getElementById('ws-status');
const wsIndicator = document.getElementById('ws-indicator');
const wsEvents = [];

socket.addEventListener('open', () => {
    wsStatus.textContent = 'Đã kết nối';
    wsIndicator.classList.add('online');
});

socket.addEventListener('message', (event) => {
    let payload;
    try { payload = JSON.parse(event.data); } catch { payload = event.data; }
    wsEvents.unshift({receivedAt: new Date().toISOString(), payload});
    wsOutput.textContent = pretty(wsEvents.slice(0, 10));
});

socket.addEventListener('close', () => {
    wsStatus.textContent = 'Đã ngắt kết nối';
    wsIndicator.classList.remove('online');
});

socket.addEventListener('error', () => {
    wsStatus.textContent = 'Lỗi WebSocket';
    wsIndicator.classList.remove('online');
});

document.getElementById('send-ws').addEventListener('click', () => {
    if (socket.readyState === WebSocket.OPEN) {
        socket.send(document.getElementById('ws-message').value);
    }
});

