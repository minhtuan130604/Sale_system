package com.example.Sale_system.service;

import com.example.Sale_system.model.SupportRequest;
import com.example.Sale_system.repository.SupportRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupportRequestService {

    private final SupportRequestRepository supportRequestRepository;

    public SupportRequestService(SupportRequestRepository supportRequestRepository) {
        this.supportRequestRepository = supportRequestRepository;
    }

    // Lấy tất cả yêu cầu hỗ trợ
    public List<SupportRequest> getAllRequests() {
        return supportRequestRepository.findAll();
    }

    // Gửi yêu cầu hỗ trợ mới
    public SupportRequest createRequest(SupportRequest request) {
        request.setStatus("Đang xử lý");
        request.setRequestDate(java.time.LocalDateTime.now());
        return supportRequestRepository.save(request);
    }

    // Cập nhật trạng thái yêu cầu
    public void updateRequestStatus(Long id, String newStatus) {
        SupportRequest request = supportRequestRepository.findById(id).orElseThrow(() -> new RuntimeException("Yêu cầu không tồn tại"));
        request.setStatus(newStatus);
        supportRequestRepository.save(request);
    }
}
