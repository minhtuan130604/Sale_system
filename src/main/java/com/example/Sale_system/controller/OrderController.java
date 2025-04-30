package com.example.Sale_system.controller;

import com.example.Sale_system.model.Order;
import com.example.Sale_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/dashboard/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Hiển thị danh sách đơn hàng trong dashboard
    @GetMapping
    public String showOrders(Model model, @RequestParam String userId) {
        List<Order> orderHistory = orderService.getOrdersByUser(userId);
        model.addAttribute("orderHistory", orderHistory);
        return "dashboard/orders"; // Đảm bảo có đúng file trong thư mục templates
    }


    // Hiển thị chi tiết đơn hàng
    @GetMapping("/{id}")
    public String showOrderDetails(@PathVariable Long id, Model model) {
        Optional<Order> order = orderService.getOrderById(id);
        if (order.isPresent()) {
            model.addAttribute("order", order.get());
            return "dashboard/order-details"; // Trả về giao diện chi tiết đơn hàng
        } else {
            model.addAttribute("errorMessage", "Không tìm thấy đơn hàng!");
            return "dashboard/orders"; // Trả về danh sách đơn hàng với thông báo lỗi
        }
    }

    // Cập nhật trạng thái đơn hàng
    @PostMapping("/{id}/update")
    public String updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return "redirect:/dashboard/orders"; // Quay về danh sách đơn hàng sau khi cập nhật
    }
}
