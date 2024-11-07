package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.CartRequest;
import com.example.Ecommerce.dto.response.CartResponse;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.CartItem;
import com.example.Ecommerce.entity.Customer;
import com.example.Ecommerce.entity.Product;
import com.example.Ecommerce.mapper.CartMapper;
import com.example.Ecommerce.repository.CartItemRepository;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.CartService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CartServiceImplement implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartMapper cartMapper;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public CartResponse addCart(CartRequest cartRequest) {
        Customer customer = customerRepository.findById(cartRequest.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Khách hàng không tìm thấy"));

        Cart cart = cartRepository.findByCustomer(customer);
        if (cart == null) {
            cart = Cart.builder()
                    .customer(customer)
                    .cartItems(new ArrayList<>())
                    .totalPrice(0.0)
                    .build();
        }

        List<Product> products = productRepository.findAllById(cartRequest.getProductIds());

        for (Product product : products) {
            // Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
            boolean productExists = false;
            for (CartItem cartItem : cart.getCartItems()) {
                if (cartItem.getProduct().equals(product)) {
                    // Cập nhật số lượng sản phẩm
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    productExists = true;
                    break;
                }
            }

            // Nếu sản phẩm chưa có trong giỏ hàng, thêm mới
            if (!productExists) {
                CartItem newCartItem = CartItem.builder()
                        .cart(cart)
                        .product(product)
                        .quantity(1)
                        .build();
                cart.getCartItems().add(newCartItem);
            }
        }

        // Cập nhật tổng giá trị giỏ hàng
        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);

        return cartMapper.toCartResponse(cartRepository.save(cart));
    }


    @Override
    public List<CartResponse> getCarts() {
        return cartRepository.findAll()
                .stream()
                .map(cartMapper::toCartResponse)
                .toList();
    }


    @Override
    public void deleteProductFromCart(String customerId, String productId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Khách hàng không tìm thấy"));

        Cart cart = cartRepository.findByCustomer(customer);

        if (cart == null) {
            throw new EntityNotFoundException("Giỏ hàng không tồn tại");
        }

        CartItem cartItem = cartItemRepository.findByCartAndProduct_Id(cart, productId)
                .orElseThrow(() -> new EntityNotFoundException("Sản phẩm không tìm thấy trong giỏ hàng"));

        cartItemRepository.delete(cartItem);

        double totalPrice = cart.getCartItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);

        cartRepository.save(cart);
    }

    @Override
    public CartResponse getCartByIdCustomer(String customerId) {
        // Tìm kiếm khách hàng theo ID
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Khách hàng không tìm thấy"));

        // Lấy giỏ hàng theo khách hàng
        Cart cart = cartRepository.findByCustomer(customer);
        if (cart == null) {
            throw new EntityNotFoundException("Giỏ hàng không tồn tại cho khách hàng này");
        }

        // Chuyển đổi giỏ hàng thành CartResponse
        return cartMapper.toCartResponse(cart);
    }

    @Override
    public CartResponse getCartById(String cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new EntityNotFoundException("Giỏ hàng không tồn tại với ID: " + cartId));

        return cartMapper.toCartResponse(cart);
    }

}
