drop table if exists events;

create table events (
                        id identity primary key,
                        title varchar(30),
                        startDate varchar(10),
                        endDate varchar(10)
);