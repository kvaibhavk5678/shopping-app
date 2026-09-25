# ShopApp — simple Amazon-style shopping app (Java / Spring Boot)

A minimal shopping web app with:
- Sign up / log in (Spring Security, passwords hashed with BCrypt)
- Product browsing (seeded with sample products)
- Add to cart / view cart / remove items, with a running total

Data is stored in an **in-memory H2 database**, so it resets every time you restart the app. Swap it for MySQL/Postgres later by changing `application.properties`.

## Requirements
- Java 17+
- Maven 3.8+ (or use the included wrapper if you add one)

## Run it

```bash
cd shopping-app
mvn spring-boot:run
```

Then open **http://localhost:8080** in your browser.

1. You'll land on the login page — click "New here? Create an account" to sign up.
2. After signing up, log in.
3. Browse products at `/products`, click "Add to cart".
4. View your cart at `/cart`, remove items as needed.

## Project structure

```
src/main/java/com/example/shopapp/
  ShopAppApplication.java       - entry point
  entity/                       - User, Product, CartItem (JPA entities)
  repository/                   - Spring Data JPA repositories
  service/                      - CustomUserDetailsService, CartService
  controller/                   - AuthController, ProductController, CartController
  config/                       - SecurityConfig, DataInitializer (seeds sample products)
src/main/resources/
  application.properties        - DB + server config
  templates/                    - Thymeleaf pages (login, signup, products, cart)
  static/css/style.css          - styling
```

## Notes / things to build next
- Checkout flow and order history (not included in this first version)
- Product search and categories
- Quantity editing in the cart (currently: add increments by 1, remove deletes the line entirely)
- Switch H2 for a persistent database for real use
- Admin screens for managing products

This was generated in a sandboxed environment without internet access, so the Maven build could not be run/verified here — please run `mvn spring-boot:run` locally and let me know if anything doesn't compile so I can fix it.
