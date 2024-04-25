package com.yevhenii.organisationSystem.dto;

import lombok.Data;

@Data
public class SaveActivityDTO {
    private String title;
    private String description;
    private int amountSeats;
    private String organisation;
    private String genre;
    private String activityType;

}
