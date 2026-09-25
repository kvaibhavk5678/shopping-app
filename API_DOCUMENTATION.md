# Shopping App Product API Documentation

This document describes the REST API endpoints for managing products in the Shopping App.

## Base URL
```
http://localhost:8080/api/products
```

## API Endpoints

### 1. GET All Products
**Endpoint:** `GET /api/products`

**Description:** Retrieve all products from the database.

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Wireless Headphones",
    "description": "Over-ear Bluetooth headphones with active noise cancellation and 30-hour battery life.",
    "price": 59.99,
    "imageUrl": "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=600&q=80",
    "stock": 50
  },
  {
    "id": 2,
    "name": "Mechanical Keyboard",
    "description": "Compact 75% mechanical keyboard with hot-swappable switches and RGB backlighting.",
    "price": 89.00,
    "imageUrl": "https://images.unsplash.com/photo-1587829741301-dc798b83add3?auto=format&fit=crop&w=600&q=80",
    "stock": 30
  }
]
```

**cURL Example:**
```bash
curl -X GET http://localhost:8080/api/products
```

**PowerShell Example:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/products" -Method GET
```

---

### 2. GET Product by ID
**Endpoint:** `GET /api/products/{id}`

**Description:** Retrieve a specific product by its ID.

**Parameters:**
- `id` (required): Product ID (e.g., 1, 2, 3, etc.)

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Wireless Headphones",
  "description": "Over-ear Bluetooth headphones with active noise cancellation and 30-hour battery life.",
  "price": 59.99,
  "imageUrl": "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=600&q=80",
  "stock": 50
}
```

**Response:** `404 Not Found` (if product doesn't exist)

**cURL Example:**
```bash
curl -X GET http://localhost:8080/api/products/1
```

**PowerShell Example:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/products/1" -Method GET
```

---

### 3. POST - Add New Product
**Endpoint:** `POST /api/products`

**Description:** Create a new product.

**Request Body:**
```json
{
  "name": "USB-C Hub",
  "description": "7-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader",
  "price": 49.99,
  "imageUrl": "https://images.unsplash.com/photo-1625948515291-69613efd103f?auto=format&fit=crop&w=600&q=80",
  "stock": 45
}
```

**Response:** `201 Created`
```json
{
  "id": 9,
  "name": "USB-C Hub",
  "description": "7-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader",
  "price": 49.99,
  "imageUrl": "https://images.unsplash.com/photo-1625948515291-69613efd103f?auto=format&fit=crop&w=600&q=80",
  "stock": 45
}
```

**Response:** `400 Bad Request` (if required fields are missing or invalid)

**cURL Example:**
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "USB-C Hub",
    "description": "7-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader",
    "price": 49.99,
    "imageUrl": "https://images.unsplash.com/photo-1625948515291-69613efd103f?auto=format&fit=crop&w=600&q=80",
    "stock": 45
  }'
```

**PowerShell Example:**
```powershell
$body = @{
    name = "USB-C Hub"
    description = "7-in-1 USB-C hub with HDMI, USB 3.0, and SD card reader"
    price = 49.99
    imageUrl = "https://images.unsplash.com/photo-1625948515291-69613efd103f?auto=format&fit=crop&w=600&q=80"
    stock = 45
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:8080/api/products" `
    -Method POST `
    -ContentType "application/json" `
    -Body $body
```

---

### 4. PUT - Update Product
**Endpoint:** `PUT /api/products/{id}`

**Description:** Update an existing product. Only provided fields are updated.

**Parameters:**
- `id` (required): Product ID

**Request Body:**
```json
{
  "name": "USB-C Hub Pro",
  "description": "7-in-1 USB-C hub with HDMI, USB 3.0, SD card reader, and 100W power delivery",
  "price": 59.99,
  "stock": 50
}
```

**Response:** `200 OK`
```json
{
  "id": 9,
  "name": "USB-C Hub Pro",
  "description": "7-in-1 USB-C hub with HDMI, USB 3.0, SD card reader, and 100W power delivery",
  "price": 59.99,
  "imageUrl": "https://images.unsplash.com/photo-1625948515291-69613efd103f?auto=format&fit=crop&w=600&q=80",
  "stock": 50
}
```

**Response:** `404 Not Found` (if product doesn't exist)

**cURL Example:**
```bash
curl -X PUT http://localhost:8080/api/products/9 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "USB-C Hub Pro",
    "description": "7-in-1 USB-C hub with HDMI, USB 3.0, SD card reader, and 100W power delivery",
    "price": 59.99,
    "stock": 50
  }'
```

**PowerShell Example:**
```powershell
$body = @{
    name = "USB-C Hub Pro"
    description = "7-in-1 USB-C hub with HDMI, USB 3.0, SD card reader, and 100W power delivery"
    price = 59.99
    stock = 50
} | ConvertTo-Json

Invoke-WebRequest -Uri "http://localhost:8080/api/products/9" `
    -Method PUT `
    -ContentType "application/json" `
    -Body $body
```

---

### 5. DELETE Product
**Endpoint:** `DELETE /api/products/{id}`

**Description:** Delete a product from the database.

**Parameters:**
- `id` (required): Product ID

**Response:** `204 No Content` (successful deletion)

**Response:** `404 Not Found` (if product doesn't exist)

**cURL Example:**
```bash
curl -X DELETE http://localhost:8080/api/products/9
```

**PowerShell Example:**
```powershell
Invoke-WebRequest -Uri "http://localhost:8080/api/products/9" -Method DELETE
```

---

## Request/Response Fields

### Product Object
| Field | Type | Required | Description |
|-------|------|----------|-------------|
| id | Long | No (auto-generated) | Unique product identifier |
| name | String | Yes | Product name (must not be blank) |
| description | String | No | Detailed product description |
| price | BigDecimal | Yes | Product price (must be > 0) |
| imageUrl | String | No | URL to product image |
| stock | Integer | No | Available stock count (defaults to 0) |

---

## Error Responses

### 400 Bad Request
Returned when required fields are missing or invalid.
```json
{
  "status": 400,
  "message": "Bad Request"
}
```

### 404 Not Found
Returned when product doesn't exist.
```json
{
  "status": 404,
  "message": "Not Found"
}
```

---

## Authentication
The Product API endpoints are **publicly accessible** without authentication.

---

## Notes
- All monetary values should be provided as decimal numbers (e.g., 49.99, 129.99)
- Image URLs should be valid HTTP/HTTPS URLs
- Stock quantity should be a non-negative integer
- When updating a product, only provide fields that need to be changed
- Prices must be greater than 0

