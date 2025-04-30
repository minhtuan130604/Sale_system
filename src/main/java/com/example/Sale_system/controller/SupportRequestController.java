package com.example.Sale_system.controller;

import com.example.Sale_system.model.SupportRequest;
import com.example.Sale_system.service.SupportRequestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Đổi từ @RestController để có thể điều hướng trang HTML
@RequestMapping("/dashboard/support")
public class SupportRequestController {

    private final SupportRequestService supportRequestService;

    public SupportRequestController(SupportRequestService supportRequestService) {
        this.supportRequestService = supportRequestService;
    }

    // Hiển thị trang hỗ trợ khách hàng
    @GetMapping
    public String showSupportPage(Model model) {
        List<SupportRequest> supportRequests = supportRequestService.getAllRequests();
        model.addAttribute("supportRequests", supportRequests);
        return "dashboard/support"; // Trả về giao diện hỗ trợ khách hàng
    }

    // Gửi yêu cầu hỗ trợ
    @PostMapping
    public String createRequest(@ModelAttribute SupportRequest request) {
        supportRequestService.createRequest(request);
        return "redirect:/dashboard/support"; // Chuyển về trang hỗ trợ sau khi gửi yêu cầu
    }

    // Cập nhật trạng thái yêu cầu
    @PutMapping("/{id}/status")
    public void updateRequestStatus(@PathVariable Long id, @RequestParam String status) {
        supportRequestService.updateRequestStatus(id, status);
    }

    @GetMapping("/history")
    @ResponseBody
    public List<SupportRequest> getRequestHistory() {
        return supportRequestService.getAllRequests();
    }

}
