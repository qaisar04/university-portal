package kz.baltabayev.shedulingservice.service.impl;

import kz.baltabayev.shedulingservice.exception.ScheduleNotFoundException;
import kz.baltabayev.shedulingservice.model.entity.Schedule;
import kz.baltabayev.shedulingservice.repository.ScheduleRepository;
import kz.baltabayev.shedulingservice.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Override
    public Schedule getById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }
}
