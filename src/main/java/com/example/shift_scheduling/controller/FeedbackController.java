package com.example.shift_scheduling.controller;

import com.example.shift_scheduling.dto.request.FeedbackDTO;
import com.example.shift_scheduling.dto.response.ResponseData;
import com.example.shift_scheduling.service.service_implementation.FeedbackImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedback")
@Validated
@Slf4j
public class FeedbackController {

    private final FeedbackImpl feedbackImpl;

    public FeedbackController(FeedbackImpl feedbackImpl) {
        this.feedbackImpl = feedbackImpl;
    }

    @PostMapping("/")
    public ResponseData<Integer> addFeedback(@Valid @RequestBody FeedbackDTO feedback) {
        int result = feedbackImpl.addFeedback(feedback);
        return new ResponseData<>(HttpStatus.CREATED.value(), "Feedback added successfully", result);
    }

    @GetMapping("/")
    public ResponseData<?> getAllFeedbacks() {
        List<FeedbackDTO> list = feedbackImpl.getAllFeedbacks();
        return new ResponseData<>(HttpStatus.OK.value(), "Get feedback list successfully", list);
    }

    @GetMapping("/{id}")
    public ResponseData<?> getFeedbackById(@PathVariable Integer id) {
        FeedbackDTO dto = feedbackImpl.getFeedbackById(id);
        return new ResponseData<>(HttpStatus.OK.value(), "Get feedback successfully", dto);
    }
}
