insert into authorities(authority) values ('ROLE_USER');
insert into authorities(authority) values ('ROLE_ADMIN');

insert into users(password, username, enabled, authority_id)
values ('$2a$10$1vJMqTELPtjRmBm6gtAyEug7/4EGKZQA6eWZypWZNlQncRqPqU/oS', 'root', true, 1);

insert into topics(name, status, author_id) values('Продажа авто', 1, 1);

insert into posts(created, description, name, creator_id, topic_id)
values('2020-07-28 18:29:52.000000', 'Продам машину тойоту', 'Тойота', 1, 1);

insert into posts(created, description, name, creator_id, topic_id)
values('2020-07-29 21:53:30.545000', 'Продам Mercedes S600 за 5.7 млн рублей', 'Mercedes S600', 1, 1);