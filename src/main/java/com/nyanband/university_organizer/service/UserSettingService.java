package com.nyanband.university_organizer.service;

import com.nyanband.university_organizer.dto.SaveUserSettingDto;
import com.nyanband.university_organizer.dto.ScheduleDto;
import com.nyanband.university_organizer.dto.UserSettingDto;

import javax.transaction.Transactional;

public interface UserSettingService {
    void update(SaveUserSettingDto saveUserSettingDto);

    boolean isSettingBelongsToUser(long userId,long settingId);

//    void save(SaveUserSettingDto userSettingDto);

    UserSettingDto getUserSetting(long userId);


}