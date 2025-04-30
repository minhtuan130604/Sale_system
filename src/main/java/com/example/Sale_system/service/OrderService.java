package com.example.Sale_system.service;

import com.example.Sale_system.model.Order;
import com.example.Sale_system.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Lấy tất cả đơn hàng
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Lấy đơn hàng theo ID
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    // Lấy danh sách đơn hàng theo người dùng
    public List<Order> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    // Tạo đơn hàng mới với phương thức thanh toán
    public Order createOrder(Order order, String paymentMethod) {
        order.setStatus("Chờ xác nhận"); // Trạng thái mặc định
        order.setOrderDate(java.time.LocalDateTime.now()); // Lưu ngày đặt hàng
        order.setPaymentMethod(paymentMethod); // Ghi nhận phương thức thanh toán
        return orderRepository.save(order);
    }

    // Xóa đơn hàng
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // Cập nhật trạng thái đơn hàng
    public void updateOrderStatus(Long id, String newStatus) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            order.setStatus(newStatus);

            if ("Đã nhận".equals(newStatus)) {
                order.setDeliveryDate(java.time.LocalDateTime.now());
                order.setCompleted(true); // Đánh dấu đơn hàng đã hoàn thành
            } else if ("Đang giao".equals(newStatus)) {
                order.setDeliveryDate(java.time.LocalDateTime.now().plusDays(3)); // Dự kiến giao hàng
            }

            orderRepository.save(order);
        }
    }

    // Lấy lịch sử đơn hàng đã mua
    public List<Order> getOrderHistory(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .filter(Order::isCompleted) // Lọc các đơn hàng đã giao thành công
                .collect(Collectors.toList());
    }
}
