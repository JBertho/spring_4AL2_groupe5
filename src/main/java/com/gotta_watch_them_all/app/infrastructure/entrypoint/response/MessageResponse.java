package com.gotta_watch_them_all.app.infrastructure.entrypoint.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class MessageResponse {
    private final String message;
}
