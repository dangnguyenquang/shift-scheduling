package com.example.shift_scheduling.service;

import com.example.shift_scheduling.dto.request.FeedbackDTO;

import java.util.List;

public interface IFeedbackService {
    int addFeedback(FeedbackDTO feedback);

    FeedbackDTO getFeedbackById(int id);

    List<FeedbackDTO> getAllFeedbacks();
}
