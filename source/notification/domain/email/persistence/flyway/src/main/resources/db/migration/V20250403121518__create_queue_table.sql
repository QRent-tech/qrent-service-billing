create table if not exists q_queue
(
    id           serial primary key,
    occurred_at  timestamp not null,
    published_at timestamp not null,
    processed    boolean  not null,
    processed_at timestamp,
    type         varchar  not null,
    payload      jsonb    not null
);