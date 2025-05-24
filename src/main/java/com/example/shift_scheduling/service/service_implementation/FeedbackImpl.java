package com.example.shift_scheduling.service.service_implementation;

import com.example.shift_scheduling.dto.request.FeedbackDTO;
import com.example.shift_scheduling.entity.Feedback;
import com.example.shift_scheduling.repository.FeedbackRepository;
import com.example.shift_scheduling.service.IFeedbackService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackImpl implements IFeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackImpl(
            FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public int addFeedback(FeedbackDTO dto) {
        Feedback fb = new Feedback(
                dto.getDanhGia(),
                dto.getMaNV(),
                dto.getMaCa(),
                dto.getChiTiet()
        );

        feedbackRepository.save(fb);
        return 1;
    }

    @Override
    public FeedbackDTO getFeedbackById(int id) {
        Feedback chef = feedbackRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy feedback với ID = " + id));

        return mapToDTO(chef);
    }

    @Override
    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private FeedbackDTO mapToDTO(Feedback feedback) {
        return FeedbackDTO.builder()
                .maNV(feedback.getMaNV())
                .maCa(feedback.getMaCa())
                .danhGia(feedback.getDanhGia())
                .chiTiet(feedback.getChiTiet())
                .build();
    }
}
