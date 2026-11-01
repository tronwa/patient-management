create table if not exists snapshots
(
    qualified_name varchar                  not null,
    identifier     varchar                  not null,
    position_type  varchar                  not null,
    position_value bigint                   not null,
    version        varchar                  not null,
    payload        bytea                    not null,
    timestamp      timestamp with time zone not null,
    metadata       json                     not null,
    primary key (qualified_name, identifier, version)
);


