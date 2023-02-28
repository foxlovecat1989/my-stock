package com.ed.app.service;

import com.ed.app.clients.watch_list.AddStock2WatchListRequest;
import com.ed.app.model.entity.AppUserPo;
import com.ed.app.model.entity.WatchListPo;

import java.util.UUID;

public interface AppUserService {
    AppUserPo createAppUser(AppUserPo appUserPo);
    AppUserPo updateAppUser(AppUserPo appUserPo);
    AppUserPo findAppUserByUserId(UUID userId);
    WatchListPo addStockToWatchList(AddStock2WatchListRequest request);
    AppUserPo save(AppUserPo appUserPo);
}
