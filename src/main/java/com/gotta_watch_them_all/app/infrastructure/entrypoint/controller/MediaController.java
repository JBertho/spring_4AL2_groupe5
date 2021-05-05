package com.gotta_watch_them_all.app.infrastructure.entrypoint.controller;

import com.gotta_watch_them_all.app.core.exception.AlreadyCreatedException;
import com.gotta_watch_them_all.app.core.exception.NotFoundException;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.adapter.MediaAdapter;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.request.CreateMediaRequest;
import com.gotta_watch_them_all.app.infrastructure.entrypoint.response.MediaResponse;
import com.gotta_watch_them_all.app.usecase.media.AddMedia;
import com.gotta_watch_them_all.app.usecase.media.DeleteMedia;
import com.gotta_watch_them_all.app.usecase.media.FindAllMedias;
import com.gotta_watch_them_all.app.usecase.media.FindMediaById;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("api/media")
public class MediaController {
    private final FindAllMedias findAllMedias;
    private final FindMediaById findMediaById;
    private final AddMedia addMedia;
    private final DeleteMedia deleteMedia;

    @GetMapping
    public ResponseEntity<List<MediaResponse>> findAll() {
        var medias = findAllMedias.execute().stream()
                .map(MediaAdapter::domainToResponse)
                .collect(Collectors.toList());
        return ok(medias);
    }

    @GetMapping("{id}")
    public ResponseEntity<MediaResponse> findById(
            @PathVariable("id")
            @Pattern(regexp = "^\\d$", message = "id has to be an integer")
            @Min(value = 1, message = "id has to be equal or more than 1") String mediaId
    ) throws NotFoundException {
        var media = findMediaById.execute(Long.parseLong(mediaId));
        return ok(MediaAdapter.domainToResponse(media));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<URI> saveOne(@Valid @RequestBody CreateMediaRequest request) throws AlreadyCreatedException {
        var newMediaId = addMedia.execute(request.getName());
        var uid = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newMediaId)
                .toUri();
        return created(uid).build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteOne(
            @PathVariable("id")
            @Pattern(regexp = "^\\d$", message = "id has to be an integer")
            @Min(value = 1, message = "id has to be equal or more than 1") String mediaId
    ) throws NotFoundException {
        deleteMedia.execute(Long.parseLong(mediaId));
        return noContent().build();
    }
}
