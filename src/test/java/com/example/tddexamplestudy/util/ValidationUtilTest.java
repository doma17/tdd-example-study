package com.example.tddexamplestudy.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilTest {

    @Test
    void isValidEmail_테스트1() {
        // given
        String validEmail = "test@test.com";
        // when
        boolean result = ValidationUtil.isValidEmail(validEmail);
        // then
        assertTrue(result);
    }

    @Test
    void isValidEmail_테스트2() {
        // given
        String invalidEmail = "invalid-email";
        // when
        boolean result = ValidationUtil.isValidEmail(invalidEmail);
        // then
        assertFalse(result);
    }

    @Test
    void isValidEmail_테스트3() {
        // given
        String invalidEmail = "test@.com";
        // when
        boolean result = ValidationUtil.isValidEmail(invalidEmail);
        // then
        assertFalse(result);
    }
}