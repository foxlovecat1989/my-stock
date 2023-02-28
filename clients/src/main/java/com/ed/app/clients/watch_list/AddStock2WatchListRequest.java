package com.ed.app.clients.watch_list;

import java.util.UUID;

public record AddStock2WatchListRequest(UUID userId, String symbol, UUID watchListId) {
}
