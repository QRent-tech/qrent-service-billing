create table if not exists q_queue
(
    id           serial primary key,
    occurred_at  datetime not null,
    published_at datetime not null,
    processed    boolean  not null,
    processed_at datetime,
    type         varchar  not null,
    payload      varchar  not null
);