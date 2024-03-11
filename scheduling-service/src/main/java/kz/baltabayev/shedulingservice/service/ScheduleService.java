package kz.baltabayev.shedulingservice.service;

import kz.baltabayev.shedulingservice.model.entity.Schedule;

public interface ScheduleService {

    Schedule getById(Long id);

    Schedule save(Schedule schedule);

    void delete(Long id);
}
