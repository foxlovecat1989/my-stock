package com.ed.app.service.impl;

import com.ed.app.model.entity.WatchListPo;
import com.ed.app.repository.WatchListRepository;
import com.ed.app.service.WatchListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public record WatchListServiceImpl(WatchListRepository watchListRepository) implements WatchListService {

    @Override
    public WatchListPo insert(UUID uuid, String stockSymbol) {
        var newOne = WatchListPo.builder()
                .userId(uuid)
                .stockSymbol(stockSymbol)
                .build();

        return save(newOne);
    }

    @Override
    public WatchListPo update(WatchListPo updatePo) {
        var originOne = findBySeq(updatePo.getSeq());
        originOne.setName(updatePo.getName());

        return save(originOne);
    }

    @Override
    public void delete(UUID uuid, String stockSymbol) {
        var deleteOne = findByUserIdAndStockSymbol(uuid, stockSymbol);
        watchListRepository.delete(deleteOne);
    }

    @Override
    public List<WatchListPo> findAllByUserId(UUID uuid) {
        return null;
    }

    @Override
    public WatchListPo findBySeq(Long seq) {
        return watchListRepository.findById(seq).orElseThrow(() -> {
            var errorMsg = String.format("Seq: %s - 查無資料", seq);
            log.error(errorMsg);
            throw new IllegalStateException(errorMsg);
        });
    }

    @Override
    public WatchListPo findByUserIdAndStockSymbol(UUID uuid, String stockSymbol) {
        return watchListRepository.findByUserIdAndStockSymbol(uuid, stockSymbol);
    }

    @Override
    public WatchListPo save(WatchListPo watchListPo) {
        return watchListRepository.save(watchListPo);
    }
}
