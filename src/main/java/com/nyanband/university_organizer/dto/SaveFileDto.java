package com.nyanband.university_organizer.dto;

import lombok.Value;

import java.io.InputStream;

@Value
public class SaveFileDto {
    String name;
    Long folderId;
    InputStream fileContent;
    String mimeType;
}
