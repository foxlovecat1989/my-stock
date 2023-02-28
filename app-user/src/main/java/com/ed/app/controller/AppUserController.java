package com.ed.app.controller;

import com.ed.app.clients.watch_list.AddStock2WatchListRequest;
import com.ed.app.model.entity.AppUserPo;
import com.ed.app.model.entity.WatchListPo;
import com.ed.app.service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/app-user")
public record AppUserController(AppUserService appUserService) {

    @PostMapping(value = "/create")
    public AppUserPo createUser(@RequestBody AppUserPo appUserPo) {
        return appUserService.createAppUser(appUserPo);
    }

    @PostMapping(value = "/add-stock-2-watch-list")
    public WatchListPo addStockToWatchList(@RequestBody AddStock2WatchListRequest request) {
        return appUserService.addStockToWatchList(request);
    }
}
