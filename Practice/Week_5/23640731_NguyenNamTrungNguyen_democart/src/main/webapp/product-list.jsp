<%--
  Created by IntelliJ IDEA.
  User: TrungNguyen
  Date: 9/22/2026
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ShopCart – Sản phẩm</title>
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            color: #1a1a2e;
            min-height: 100vh;
        }

        nav {
            background: linear-gradient(135deg, #1a1a2e 0%, #16213e 100%);
            padding: 0 40px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            height: 64px;
            box-shadow: 0 2px 12px rgba(0,0,0,0.3);
            position: sticky;
            top: 0;
            z-index: 100;
        }

        .nav-brand {
            font-size: 22px;
            font-weight: 700;
            color: #e94560;
            text-decoration: none;
            letter-spacing: 1px;
        }

        .nav-cart-btn {
            display: flex;
            align-items: center;
            gap: 8px;
            background: #e94560;
            color: white;
            text-decoration: none;
            padding: 9px 20px;
            border-radius: 25px;
            font-weight: 600;
            font-size: 14px;
            transition: background 0.2s, transform 0.15s;
        }

        .nav-cart-btn:hover {
            background: #c73652;
            transform: translateY(-1px);
        }

        .main {
            max-width: 1200px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .page-title {
            font-size: 28px;
            font-weight: 700;
            color: #1a1a2e;
            margin-bottom: 8px;
        }

        .page-subtitle {
            color: #6c757d;
            margin-bottom: 30px;
            font-size: 15px;
        }

        .products-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
            gap: 24px;
        }

        .card {
            background: #fff;
            border-radius: 16px;
            overflow: hidden;
            box-shadow: 0 2px 12px rgba(0,0,0,0.08);
            transition: transform 0.25s, box-shadow 0.25s;
            display: flex;
            flex-direction: column;
        }

        .card:hover {
            transform: translateY(-6px);
            box-shadow: 0 12px 30px rgba(0,0,0,0.15);
        }

        .card-img-wrap {
            background: #f8f9fa;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 180px;
            padding: 16px;
        }

        .card-img-wrap img {
            max-width: 100%;
            max-height: 148px;
            object-fit: contain;
            transition: transform 0.3s;
        }

        .card:hover .card-img-wrap img { transform: scale(1.05); }

        .card-no-img { font-size: 60px; color: #dee2e6; }

        .card-body {
            padding: 18px;
            flex: 1;
            display: flex;
            flex-direction: column;
        }

        .card-title {
            font-size: 16px;
            font-weight: 700;
            color: #1a1a2e;
            margin-bottom: 6px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        .card-stock { font-size: 13px; color: #6c757d; margin-bottom: 10px; }

        .card-price {
            font-size: 20px;
            font-weight: 700;
            color: #e94560;
            margin-bottom: 16px;
        }

        .card-price span { font-size: 13px; font-weight: 400; color: #aaa; }

        .card-actions {
            display: flex;
            gap: 8px;
            margin-top: auto;
        }

        .btn-detail {
            flex: 1;
            padding: 9px 0;
            border: 2px solid #e94560;
            background: transparent;
            color: #e94560;
            border-radius: 8px;
            font-weight: 600;
            font-size: 13px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            transition: background 0.2s, color 0.2s;
        }

        .btn-detail:hover { background: #e94560; color: #fff; }

        .btn-add {
            flex: 1;
            padding: 9px 0;
            background: #e94560;
            color: #fff;
            border: none;
            border-radius: 8px;
            font-weight: 600;
            font-size: 13px;
            cursor: pointer;
            transition: background 0.2s, transform 0.1s;
            width: 100%;
        }

        .btn-add:hover { background: #c73652; }
        .btn-add:active { transform: scale(0.97); }

        .empty-state { text-align: center; padding: 80px 20px; color: #aaa; }
        .empty-state .icon { font-size: 64px; margin-bottom: 16px; }
        .empty-state p { font-size: 18px; }
    </style>
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/product">&#128717; ShopCart</a>
    <a class="nav-cart-btn" href="${pageContext.request.contextPath}/cart">
        &#128722; Gi&#7887; h&#224;ng
    </a>
</nav>

<div class="main">
    <h1 class="page-title">Danh s&#225;ch s&#7843;n ph&#7849;m</h1>
    <p class="page-subtitle">Kh&#225;m ph&#225; c&#225;c s&#7843;n ph&#7849;m ch&#7845;t l&#432;&#7907;ng cao c&#7911;a ch&#250;ng t&#244;i</p>

    <div class="products-grid">
        <c:choose>
            <c:when test="${empty products}">
                <div class="empty-state">
                    <div class="icon">&#128230;</div>
                    <p>Hi&#7879;n ch&#432;a c&#243; s&#7843;n ph&#7849;m n&#224;o.</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="p" items="${products}">
                    <div class="card">
                        <div class="card-img-wrap">
                            <c:choose>
                                <c:when test="${not empty p.imgURL}">
                                    <img src="${p.imgURL}" alt="${p.model}">
                                </c:when>
                                <c:otherwise>
                                    <div class="card-no-img">&#128247;</div>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="card-body">
                            <div class="card-title">${p.model}</div>
                            <div class="card-stock">C&#242;n l&#7841;i: ${p.quantity} s&#7843;n ph&#7849;m</div>
                            <div class="card-price">${p.price} <span>VND</span></div>
                            <div class="card-actions">
                                <a class="btn-detail"
                                   href="${pageContext.request.contextPath}/product?id=${p.id}">Chi ti&#7871;t</a>
                                <form method="post" action="${pageContext.request.contextPath}/cart" style="flex:1">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="id" value="${p.id}">
                                    <button class="btn-add" type="submit">&#128722; Th&#234;m</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>

</body>
</html>
