package com.example.Ecommerce.exception;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor()
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    EMAIL_EXISTED("Your email have exist."),
    EMAIL_NOT_EXISTED("You email not exist."),
    PASSWORD_INCORRECT("Incorrect password."),
    PASSWORD_INVALID_SIZE("Password must be at least 8 characters."),
    PASSWORD_INVALID_PATTERN("Password must contain at least one letter and one number.")
    ;
    String message;
}
