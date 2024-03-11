create schema if not exists schedule;

create table schedule.t_lesson(
    id serial primary key,
    c_start_time timestamp,

)