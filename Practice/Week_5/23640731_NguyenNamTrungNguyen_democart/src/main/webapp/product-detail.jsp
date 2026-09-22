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
    <title>ShopCart – Chi ti&#7871;t s&#7843;n ph&#7849;m</title>
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

        .nav-cart-btn:hover { background: #c73652; transform: translateY(-1px); }

        .main {
            max-width: 900px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .breadcrumb {
            display: flex;
            align-items: center;
            gap: 8px;
            color: #6c757d;
            font-size: 14px;
            margin-bottom: 24px;
        }

        .breadcrumb a { color: #e94560; text-decoration: none; }
        .breadcrumb a:hover { text-decoration: underline; }

        .product-card {
            background: #fff;
            border-radius: 20px;
            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.08);
            display: flex;
            gap: 0;
        }

        .product-img-col {
            flex: 0 0 340px;
            background: #f8f9fa;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 40px;
            min-height: 400px;
        }

        .product-img-col img {
            max-width: 100%;
            max-height: 300px;
            object-fit: contain;
        }

        .product-no-img { font-size: 100px; color: #dee2e6; }

        .product-info-col {
            flex: 1;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }

        .product-id-badge {
            display: inline-block;
            background: #f0f2f5;
            color: #6c757d;
            font-size: 12px;
            padding: 4px 10px;
            border-radius: 20px;
            margin-bottom: 14px;
            font-weight: 600;
        }

        .product-name {
            font-size: 28px;
            font-weight: 700;
            color: #1a1a2e;
            margin-bottom: 14px;
            line-height: 1.3;
        }

        .product-description {
            color: #6c757d;
            font-size: 15px;
            line-height: 1.7;
            margin-bottom: 24px;
        }

        .product-meta {
            display: flex;
            gap: 20px;
            margin-bottom: 24px;
        }

        .meta-item {
            background: #f8f9fa;
            padding: 12px 18px;
            border-radius: 12px;
            text-align: center;
        }

        .meta-label { font-size: 11px; color: #aaa; text-transform: uppercase; letter-spacing: 0.5px; }
        .meta-value { font-size: 16px; font-weight: 700; color: #1a1a2e; margin-top: 4px; }

        .product-price {
            font-size: 34px;
            font-weight: 800;
            color: #e94560;
            margin-bottom: 30px;
        }

        .product-price span { font-size: 16px; font-weight: 500; color: #aaa; }

        .btn-add-to-cart {
            background: #e94560;
            color: #fff;
            border: none;
            padding: 16px 36px;
            border-radius: 12px;
            font-size: 17px;
            font-weight: 700;
            cursor: pointer;
            transition: background 0.2s, transform 0.1s;
            width: 100%;
            margin-bottom: 14px;
        }

        .btn-add-to-cart:hover { background: #c73652; }
        .btn-add-to-cart:active { transform: scale(0.98); }

        .btn-back {
            display: block;
            text-align: center;
            text-decoration: none;
            color: #6c757d;
            font-size: 14px;
            padding: 10px;
            border-radius: 8px;
            transition: background 0.2s;
        }

        .btn-back:hover { background: #f0f2f5; color: #1a1a2e; }

        @media (max-width: 680px) {
            .product-card { flex-direction: column; }
            .product-img-col { flex: none; min-height: 240px; }
        }
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
    <div class="breadcrumb">
        <a href="${pageContext.request.contextPath}/product">S&#7843;n ph&#7849;m</a>
        <span>&#8250;</span>
        <span>${product.model}</span>
    </div>

    <div class="product-card">
        <div class="product-img-col">
            <c:choose>
                <c:when test="${not empty product.imgURL}">
                    <img src="${product.imgURL}" alt="${product.model}">
                </c:when>
                <c:otherwise>
                    <div class="product-no-img">&#128247;</div>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="product-info-col">
            <span class="product-id-badge">ID: ${product.id}</span>
            <h1 class="product-name">${product.model}</h1>
            <p class="product-description">${product.description}</p>

            <div class="product-meta">
                <div class="meta-item">
                    <div class="meta-label">T&#7891;n kho</div>
                    <div class="meta-value">${product.quantity}</div>
                </div>
            </div>

            <div class="product-price">
                ${product.price} <span>VND</span>
            </div>

            <form method="post" action="${pageContext.request.contextPath}/cart">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="id" value="${product.id}">
                <button class="btn-add-to-cart" type="submit">
                    &#128722; Th&#234;m v&#224;o gi&#7887; h&#224;ng
                </button>
            </form>

            <a class="btn-back" href="${pageContext.request.contextPath}/product">
                &#8592; Quay l&#7841;i danh s&#225;ch
            </a>
        </div>
    </div>
</div>

</body>
</html>
