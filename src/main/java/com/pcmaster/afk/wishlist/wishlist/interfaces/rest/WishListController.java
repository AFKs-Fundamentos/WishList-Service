package com.pcmaster.afk.wishlist.wishlist.interfaces.rest;

import com.pcmaster.afk.wishlist.wishlist.domain.model.queries.GetWishListByIdQuery;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListCommandService;
import com.pcmaster.afk.wishlist.wishlist.domain.services.WishListQueryService;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.CreateWishListResource;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.resources.WishListResource;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform.CreateWishListCommandFromResourceAssembler;
import com.pcmaster.afk.wishlist.wishlist.interfaces.rest.transform.WishListResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = { RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE })
@RestController
@RequestMapping(value = "/api/v1/wishlist", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "WishList", description = "WishList Management Endpoints")
public class WishListController {

    private final WishListQueryService wishListQueryService;
    private final WishListCommandService wishListCommandService;

    public WishListController(WishListQueryService wishListQueryService, WishListCommandService wishListCommandService){
        this.wishListQueryService = wishListQueryService;
        this.wishListCommandService = wishListCommandService;
    }

    @PostMapping
    public ResponseEntity<WishListResource> createWishList(@RequestBody CreateWishListResource resource){

        var createWishListCommand = CreateWishListCommandFromResourceAssembler
                .toCommandFromResource(resource);

        var wishListId = this.wishListCommandService.handle(createWishListCommand);

        if(wishListId.equals(0L)){
            return ResponseEntity.badRequest().build();
        }

        var getWishByIdQuery = new GetWishListByIdQuery(wishListId);
        var optionalWish = this.wishListQueryService.handle(getWishByIdQuery);

        var wishResource = WishListResourceFromEntityAssembler.toResourceFromEntity(optionalWish.get());
        return new ResponseEntity<>(wishResource, HttpStatus.CREATED);
    }
}
