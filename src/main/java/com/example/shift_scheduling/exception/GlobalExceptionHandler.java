package com.example.shift_scheduling.exception;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestControllerAdvice
public class GlobalExceptionHandler {

        /**
         * Handle exception when validate data
         *
         * @param e
         * @param request
         * @return errorResponse
         */
        @ExceptionHandler({ ConstraintViolationException.class,
                        MissingServletRequestParameterException.class, MethodArgumentNotValidException.class })
        @ResponseStatus(BAD_REQUEST)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                                        @Content(mediaType = APPLICATION_JSON_VALUE, examples = @ExampleObject(name = "Handle exception when the data invalid. (@RequestBody, @RequestParam, @PathVariable)", summary = "Handle Bad Request", value = """
                                                        {
                                                             "timestamp": "2024-04-07T11:38:56.368+00:00",
                                                             "status": 400,
                                                             "path": "/api/v1/...",
                                                             "error": "Invalid Payload",
                                                             "message": "{data} must be not blank"
                                                         }
                                                        """)) })
        })
        public ErrorResponse handleValidationException(Exception e, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setStatus(BAD_REQUEST.value());
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

                String message = e.getMessage();
                if (e instanceof MethodArgumentNotValidException) {
                        int start = message.lastIndexOf("[") + 1;
                        int end = message.lastIndexOf("]") - 1;
                        message = message.substring(start, end);
                        errorResponse.setError("Invalid Payload");
                        errorResponse.setMessage(message);
                } else if (e instanceof MissingServletRequestParameterException) {
                        errorResponse.setError("Invalid Parameter");
                        errorResponse.setMessage(message);
                } else if (e instanceof ConstraintViolationException) {
                        errorResponse.setError("Invalid Parameter");
                        errorResponse.setMessage(message.substring(message.indexOf(" ") + 1));
                } else {
                        errorResponse.setError("Invalid Data");
                        errorResponse.setMessage(message);
                }

                return errorResponse;
        }

        /**
         * Handle exception when the request not found data
         *
         * @param e
         * @param request
         * @return
         */
        @ExceptionHandler(ResourceNotFoundException.class)
        @ResponseStatus(NOT_FOUND)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "404", description = "Bad Request", content = {
                                        @Content(mediaType = APPLICATION_JSON_VALUE, examples = @ExampleObject(name = "404 Response", summary = "Handle exception when resource not found", value = """
                                                        {
                                                          "timestamp": "2023-10-19T06:07:35.321+00:00",
                                                          "status": 404,
                                                          "path": "/api/v1/...",
                                                          "error": "Not Found",
                                                          "message": "{data} not found"
                                                        }
                                                        """)) })
        })
        public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
                errorResponse.setStatus(NOT_FOUND.value());
                errorResponse.setError(NOT_FOUND.getReasonPhrase());
                errorResponse.setMessage(e.getMessage());

                return errorResponse;
        }

        /**
         * Handle exception when the data is conflicted
         *
         * @param e
         * @param request
         * @return
         */
        @ExceptionHandler(InvalidDataException.class)
        @ResponseStatus(CONFLICT)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "409", description = "Conflict", content = {
                                        @Content(mediaType = APPLICATION_JSON_VALUE, examples = @ExampleObject(name = "409 Response", summary = "Handle exception when input data is conflicted", value = """
                                                        {
                                                          "timestamp": "2023-10-19T06:07:35.321+00:00",
                                                          "status": 409,
                                                          "path": "/api/v1/...",
                                                          "error": "Conflict",
                                                          "message": "{data} exists, Please try again!"
                                                        }
                                                        """)) })
        })
        public ErrorResponse handleDuplicateKeyException(InvalidDataException e, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
                errorResponse.setStatus(CONFLICT.value());
                errorResponse.setError(CONFLICT.getReasonPhrase());
                errorResponse.setMessage(e.getMessage());

                return errorResponse;
        }

        /**
         * Handle exception when internal server error
         *
         * @param e
         * @param request
         * @return error
         */
        @ExceptionHandler(Exception.class)
        @ResponseStatus(INTERNAL_SERVER_ERROR)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {
                                        @Content(mediaType = APPLICATION_JSON_VALUE, examples = @ExampleObject(name = "500 Response", summary = "Handle exception when internal server error", value = """
                                                        {
                                                          "timestamp": "2023-10-19T06:35:52.333+00:00",
                                                          "status": 500,
                                                          "path": "/api/v1/...",
                                                          "error": "Internal Server Error",
                                                          "message": "Connection timeout, please try again"
                                                        }
                                                        """)) })
        })
        public ErrorResponse handleException(Exception e, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
                errorResponse.setStatus(INTERNAL_SERVER_ERROR.value());
                errorResponse.setError(INTERNAL_SERVER_ERROR.getReasonPhrase());
                errorResponse.setMessage(e.getMessage());

                return errorResponse;
        }

        @ExceptionHandler(EntityNotFoundException.class)
        @ResponseStatus(NOT_FOUND)
        public ErrorResponse handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));
                errorResponse.setStatus(NOT_FOUND.value());
                errorResponse.setError("Not Found");
                errorResponse.setMessage(e.getMessage());

                return errorResponse;
        }

        @ExceptionHandler(DataIntegrityViolationException.class)
        @ResponseStatus(HttpStatus.BAD_REQUEST)
        public ErrorResponse handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
                String rootMessage = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

                String message = "Database error";
                if (rootMessage != null && rootMessage.contains("Duplicate entry")) {
                        if (rootMessage.contains("Staff.phone")) {
                                message = "Số điện thoại đã tồn tại, vui lòng nhập số khác.";
                        } else {
                                message = "Dữ liệu đã tồn tại và vi phạm ràng buộc unique.";
                        }
                }
                if (rootMessage != null && rootMessage.contains("a foreign key constraint fails")) {
                        Pattern pattern = Pattern.compile("fails \\(`(.+?)`\\.`(.+?)`, CONSTRAINT `(.+?)` FOREIGN KEY");
                        Matcher matcher = pattern.matcher(rootMessage);
                        if (matcher.find()) {
                                String database = matcher.group(1);
                                String table = matcher.group(2);
                                String constraint = matcher.group(3);
                                message = String.format("Lỗi ràng buộc khóa ngoại (bảng: %s, constraint: %s).",
                                                table, constraint);
                        } else {
                                message = "Thao tác không hợp lệ do ràng buộc khoá ngoại.";
                        }
                }

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                errorResponse.setError("Conflict");
                errorResponse.setMessage(message);
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

                return errorResponse;
        }

        @ExceptionHandler(HttpMessageNotReadableException.class)
        public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                        WebRequest request) {
                String message = "Dữ liệu gửi lên không hợp lệ";

                Throwable cause = ex.getCause();
                if (cause instanceof InvalidFormatException) {
                        InvalidFormatException ife = (InvalidFormatException) cause;
                        if (ife.getTargetType() != null
                                        && ife.getTargetType().equals(com.example.shift_scheduling.util.LoaiNV.class)) {
                                message = "Giá trị '" + ife.getValue()
                                                + "' không hợp lệ cho trường LoaiNV. Giá trị hợp lệ: dau_bep, phuc_vu, le_tan.";
                        } else if (ife.getTargetType() != null
                                        && ife.getTargetType()
                                                        .equals(com.example.shift_scheduling.util.LoaiMon.class)) {
                                message = "Giá trị '" + ife.getValue()
                                                + "' không hợp lệ cho trường LoaiNV. Giá trị hợp lệ: mon_au, " +
                                                "mon_han, mon_nhat, mon_trung, mon_viet,  mon_chay,  mon_nuong, trang_mieng_au, trang_mieng_a.";
                        }
                }

                ErrorResponse errorResponse = new ErrorResponse();
                errorResponse.setTimestamp(new Date());
                errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                errorResponse.setError("Invalid Enum Value");
                errorResponse.setMessage(message);
                errorResponse.setPath(request.getDescription(false).replace("uri=", ""));

                return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }

}
