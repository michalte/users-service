CREATE TABLE users (
    id bigint auto_increment,
    name varchar(100),
    balance int,
    primary key (id)
);

CREATE TABLE user_transaction(
    id bigint auto_increment,
    user_id bigint,
    amount int,
    transaction_date timestamp,
    foreign key (user_id) references users(id) on delete cascade
);

INSERT INTO users (name, balance) VALUES ('Samuel', 1000), ('Michael', 2000), ('Jake', 700)
