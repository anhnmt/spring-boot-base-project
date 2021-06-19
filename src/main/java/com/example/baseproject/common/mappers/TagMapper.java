package com.example.baseproject.common.mappers;

import com.example.baseproject.domains.request.TagRequest;
import com.example.baseproject.domains.model.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class TagMapper {
    private final ModelMapper modelMapper;

    public Tag convertToTag(Long tagId, TagRequest tagRequest) {
        return Tag.builder()
                .tagId(tagId)
                .name(tagRequest.getName())
                .slug(tagRequest.getSlug())
                .build();
    }

    public Tag convertToTag(Tag country, TagRequest tagRequest) {
        country.setName(tagRequest.getName());
        country.setSlug(tagRequest.getSlug());

        return country;
    }

    public Tag convertToTag(TagRequest tagRequest) {
        log.info(String.valueOf(tagRequest));
        return modelMapper.map(tagRequest, Tag.class);
    }
}
