package com.pcmaster.afk.wishlist.wishlist.domain.services;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByIdQuery;

import java.util.Optional;

public interface WishListQueryService {
    Optional<WishList> handle(GetWishListByIdQuery query);
}
