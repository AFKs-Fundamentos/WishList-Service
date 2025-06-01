package com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform;

import com.pcmaster.afk.wishlist.wishlist.domain.model.aggregates.WishList;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.WishListResource;

public class WishListResourceFromEntityAssembler{
    public static WishListResource toResourceFromEntity(WishList entity){
        return new WishListResource(
                entity.getUserId(),
                entity.getProductId()
        );
    }
}
