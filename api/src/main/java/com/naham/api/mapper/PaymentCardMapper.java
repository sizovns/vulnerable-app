package com.naham.api.mapper;

import com.naham.api.model.dto.request.CreateCardRequest;
import com.naham.api.model.dto.request.UpdateCardRequest;
import com.naham.api.model.dto.response.CardResponse;
import com.naham.api.model.entity.PaymentCard;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentCardMapper {
    PaymentCard mapPaymentCard(CreateCardRequest source);

    PaymentCard mapPaymentCard(UpdateCardRequest source);

    CardResponse mapCardResponse(PaymentCard source);
}
