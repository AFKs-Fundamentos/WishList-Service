package com.pcmaster.afk.wishlist.wishlist.application.internal.commandservices;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import com.pcmaster.afk.wishlist.wishlist.domain.model.commands.CreateWishListCommand;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListCommandService;
import com.pcmaster.afk.wishlist.wishlist.infrastructure.persistence.jpa.repositories.WishListRepository;
import org.springframework.stereotype.Service;

@Service
public class WishListCommandServiceImpl implements WishListCommandService {

    private final WishListRepository wishListRepository;

    public WishListCommandServiceImpl(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Long handle(CreateWishListCommand command) {

        var wishList = new WishList(command);
        try {
            this.wishListRepository.save(wishList);
        }catch (Exception e){
            throw new IllegalArgumentException("Error while saving Wish List: " + e.getMessage());
        }

        return wishList.getId();
    }
}
