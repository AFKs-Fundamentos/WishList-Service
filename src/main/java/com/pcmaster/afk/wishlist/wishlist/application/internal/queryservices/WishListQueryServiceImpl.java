package com.pcmaster.afk.wishlist.wishlist.application.internal.queryservices;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByIdQuery;
import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByUserIdQuery;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListQueryService;
import com.pcmaster.afk.wishlist.wishlist.infrastructure.persistence.jpa.repositories.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WishListQueryServiceImpl implements WishListQueryService {

    private final WishListRepository wishListRepository;

    public WishListQueryServiceImpl(WishListRepository wishListRepository){
        this.wishListRepository = wishListRepository;
    }

    @Override
    public Optional<WishList> handle(GetWishListByIdQuery query) {
        return this.wishListRepository.findById(query.wishlistId());
    }

    @Override
    public List<WishList> handle(GetWishListByUserIdQuery query) {
        return this.wishListRepository.findByUserId(query.userId());
    }
}
