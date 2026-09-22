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
    <title>ShopCart – Gi&#7887; h&#224;ng</title>
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

        .nav-back-btn {
            display: flex;
            align-items: center;
            gap: 8px;
            color: #ccc;
            text-decoration: none;
            font-size: 14px;
            font-weight: 600;
            transition: color 0.2s;
        }

        .nav-back-btn:hover { color: #fff; }

        .main {
            max-width: 960px;
            margin: 40px auto;
            padding: 0 20px;
        }

        .page-header {
            display: flex;
            align-items: center;
            gap: 14px;
            margin-bottom: 30px;
        }

        .page-header h1 {
            font-size: 28px;
            font-weight: 700;
            color: #1a1a2e;
        }

        .cart-count {
            background: #e94560;
            color: #fff;
            font-size: 13px;
            font-weight: 700;
            padding: 3px 10px;
            border-radius: 20px;
        }

        /* ── EMPTY STATE ── */
        .empty-card {
            background: #fff;
            border-radius: 20px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.06);
            text-align: center;
            padding: 80px 40px;
        }

        .empty-card .icon { font-size: 72px; margin-bottom: 20px; }
        .empty-card h2 { font-size: 22px; color: #1a1a2e; margin-bottom: 10px; }
        .empty-card p { color: #6c757d; margin-bottom: 30px; }

        .btn-shop {
            display: inline-block;
            background: #e94560;
            color: #fff;
            text-decoration: none;
            padding: 13px 32px;
            border-radius: 10px;
            font-weight: 700;
            font-size: 15px;
            transition: background 0.2s;
        }

        .btn-shop:hover { background: #c73652; }

        /* ── CART TABLE ── */
        .cart-card {
            background: #fff;
            border-radius: 20px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.06);
            overflow: hidden;
            margin-bottom: 24px;
        }

        table { width: 100%; border-collapse: collapse; }

        thead tr { background: #1a1a2e; color: #fff; }

        th {
            padding: 16px 20px;
            font-size: 13px;
            text-transform: uppercase;
            letter-spacing: 0.6px;
            font-weight: 600;
            text-align: left;
        }

        tbody tr {
            border-bottom: 1px solid #f0f2f5;
            transition: background 0.15s;
        }

        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #fafbfc; }

        td {
            padding: 16px 20px;
            vertical-align: middle;
            font-size: 15px;
        }

        .product-model { font-weight: 600; color: #1a1a2e; }

        .price-cell { color: #6c757d; }

        .subtotal-cell { font-weight: 700; color: #e94560; }

        /* update form */
        .qty-form { display: flex; align-items: center; gap: 8px; }

        input[type=number] {
            width: 72px;
            padding: 8px 10px;
            border: 2px solid #e9ecef;
            border-radius: 8px;
            font-size: 14px;
            text-align: center;
            transition: border-color 0.2s;
        }

        input[type=number]:focus { outline: none; border-color: #e94560; }

        .btn-update {
            padding: 8px 14px;
            background: #0a3d62;
            color: #fff;
            border: none;
            border-radius: 8px;
            font-size: 13px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.2s;
        }

        .btn-update:hover { background: #093353; }

        .btn-remove {
            padding: 8px 14px;
            background: transparent;
            color: #e94560;
            border: 2px solid #e94560;
            border-radius: 8px;
            font-size: 13px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.2s, color 0.2s;
        }

        .btn-remove:hover { background: #e94560; color: #fff; }

        /* ── SUMMARY ── */
        .cart-summary {
            background: #fff;
            border-radius: 20px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.06);
            padding: 30px 30px;
        }

        .summary-row {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 10px 0;
            border-bottom: 1px solid #f0f2f5;
            font-size: 15px;
        }

        .summary-row:last-of-type { border-bottom: none; }
        .summary-label { color: #6c757d; }
        .summary-value { font-weight: 600; color: #1a1a2e; }

        .summary-total {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin: 20px 0;
            padding: 16px 20px;
            background: #1a1a2e;
            border-radius: 12px;
            color: #fff;
        }

        .summary-total .label { font-size: 16px; font-weight: 600; }
        .summary-total .value { font-size: 24px; font-weight: 800; color: #e94560; }

        .summary-actions {
            display: flex;
            gap: 12px;
        }

        .btn-continue {
            flex: 1;
            display: block;
            text-align: center;
            text-decoration: none;
            padding: 13px;
            border: 2px solid #1a1a2e;
            color: #1a1a2e;
            border-radius: 10px;
            font-weight: 700;
            font-size: 14px;
            transition: background 0.2s, color 0.2s;
        }

        .btn-continue:hover { background: #1a1a2e; color: #fff; }

        .btn-clear {
            flex: 1;
            padding: 13px;
            background: #e94560;
            color: #fff;
            border: none;
            border-radius: 10px;
            font-weight: 700;
            font-size: 14px;
            cursor: pointer;
            transition: background 0.2s;
        }

        .btn-clear:hover { background: #c73652; }
    </style>
</head>
<body>

<nav>
    <a class="nav-brand" href="${pageContext.request.contextPath}/product">&#128717; ShopCart</a>
    <a class="nav-back-btn" href="${pageContext.request.contextPath}/product">
        &#8592; Ti&#7871;p t&#7909;c mua s&#7855;m
    </a>
</nav>

<div class="main">
    <div class="page-header">
        <h1>&#128722; Gi&#7887; h&#224;ng</h1>
    </div>

    <c:choose>
        <c:when test="${empty sessionScope.cart or empty sessionScope.cart.items}">
            <div class="empty-card">
                <div class="icon">&#128722;</div>
                <h2>Gi&#7887; h&#224;ng &#273;ang tr&#7889;ng!</h2>
                <p>B&#7841;n ch&#432;a th&#234;m s&#7843;n ph&#7849;m n&#224;o v&#224;o gi&#7887; h&#224;ng.</p>
                <a class="btn-shop" href="${pageContext.request.contextPath}/product">
                    &#128717; Mua s&#7855;m ngay
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="cart-card">
                <table>
                    <thead>
                        <tr>
                            <th>S&#7843;n ph&#7849;m</th>
                            <th>S&#7889; l&#432;&#7907;ng</th>
                            <th>&#272;&#417;n gi&#225;</th>
                            <th>Th&#224;nh ti&#7873;n</th>
                            <th>Th&#225;o</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${sessionScope.cart.items}">
                            <tr>
                                <td class="product-model">${item.product.model}</td>
                                <td>
                                    <form class="qty-form" method="post" action="${pageContext.request.contextPath}/cart">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="productId" value="${item.product.id}">
                                        <input type="number" name="quantity" value="${item.quantity}" min="0">
                                        <button class="btn-update" type="submit">C&#7853;p nh&#7853;t</button>
                                    </form>
                                </td>
                                <td class="price-cell">${item.product.price} VND</td>
                                <td class="subtotal-cell">${item.subtotal} VND</td>
                                <td>
                                    <form method="post" action="${pageContext.request.contextPath}/cart">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="productId" value="${item.product.id}">
                                        <button class="btn-remove" type="submit">&#10005; X&#243;a</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="cart-summary">
                <div class="summary-total">
                    <span class="label">T&#7893;ng c&#7897;ng</span>
                    <span class="value">${sessionScope.cart.total} VND</span>
                </div>
                <div class="summary-actions">
                    <a class="btn-continue" href="${pageContext.request.contextPath}/product">
                        &#8592; Ti&#7871;p t&#7909;c mua
                    </a>
                    <form method="post" action="${pageContext.request.contextPath}/cart" style="flex:1">
                        <input type="hidden" name="action" value="clear">
                        <button class="btn-clear" type="submit" style="width:100%">
                            &#128465; X&#243;a gi&#7887; h&#224;ng
                        </button>
                    </form>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
