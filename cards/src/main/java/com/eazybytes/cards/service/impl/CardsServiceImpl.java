package com.eazybytes.cards.service.impl;

import com.eazybytes.cards.entity.Cards;
import com.eazybytes.cards.exception.CardAlreadyExistsException;
import com.eazybytes.cards.mapper.CardsMapper;
import com.eazybytes.cards.repository.CardsRepository;
import com.eazybytes.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@AllArgsConstructor
@Service
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> cards=cardsRepository.findByMobileNumber(mobileNumber);
        if(cards.isPresent())
            throw new CardAlreadyExistsException("Card already exists on this mobile number: "+mobileNumber);
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber){
        Cards cards=new Cards();
        cards.setMobileNumber(mobileNumber);
        return cards;
    }
}
