package com.naham.api.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardNumberValidator implements ConstraintValidator<CardNumber, String> {
    @Override
    public void initialize(CardNumber constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        if (!StringUtils.hasLength(cardNumber) || cardNumber.length() < 13 || cardNumber.length() > 16) {
            return false;
        }

        String purportedCC = cardNumber.replaceAll(" ", "");
        int sum = 0;
        for (int i = 0; i < purportedCC.length(); i++) {
            int cardNum = Integer.parseInt(Character.toString(purportedCC.charAt(i)));

            if ((purportedCC.length() - i) % 2 == 0) {
                cardNum = cardNum * 2;

                if (cardNum > 9) {
                    cardNum = cardNum - 9;
                }
            }
            sum += cardNum;
        }
        return sum % 10 == 0;
    }
}
