package com.example.Ecommerce.service.implement;

import com.example.Ecommerce.dto.request.OrderRequest;
import com.example.Ecommerce.dto.response.OrderResponse;
import com.example.Ecommerce.entity.Cart;
import com.example.Ecommerce.entity.Order;
import com.example.Ecommerce.mapper.CartMapper;
import com.example.Ecommerce.mapper.OrderMapper;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImplement implements OrderService {
    private static final Logger log = LoggerFactory.getLogger(OrderServiceImplement.class);
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CartMapper cartMapper;
    @Override
    public OrderResponse saveOrder(OrderRequest orderRequest) {
        // Lấy cartId từ OrderRequest
        String cartId = orderRequest.getCartId();
        if (cartId == null) {
            throw new IllegalArgumentException("Cart ID must not be null");
        }

        // Tìm kiếm Cart trong cơ sở dữ liệu
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found with ID: " + cartId));

        // Chuyển đổi OrderRequest thành Order
        Order order = orderMapper.toOrder(orderRequest);

        // Gán Cart vào Order
        order.setCart(cart);

        // Lưu Order và nhận Order đã lưu
        Order savedOrder = orderRepository.save(order);

        // Tạo OrderResponse từ Order đã lưu
        OrderResponse orderResponse = orderMapper.toOrderResponse(savedOrder);

        // Chuyển đổi Cart sang CartResponse và gán vào OrderResponse
        orderResponse.setCartResponse(cartMapper.toCartResponse(cart));

        return orderResponse;
    }


    @Override
    public List<OrderResponse> getOrders() {
        // Lấy tất cả các đơn hàng từ cơ sở dữ liệu
        List<Order> orders = orderRepository.findAll();

        // Chuyển đổi từng Order thành OrderResponse và gán CartResponse cho mỗi OrderResponse
        return orders.stream().map(order -> {
            OrderResponse orderResponse = orderMapper.toOrderResponse(order);

            // Nếu order có Cart, chuyển đổi Cart thành CartResponse và gán vào OrderResponse
            if (order.getCart() != null) {
                orderResponse.setCartResponse(cartMapper.toCartResponse(order.getCart()));
            }

            return orderResponse;
        }).toList();
    }
}
