insert into `user` (id, username, `password`)
values (1, 'admin', 'password'),
       (2, 'user', 'password');

insert into `role` (id, `name`)
values (1, 'ADMIN'),
       (2, 'USER');

insert into `user_role` (user_id, role_id)
values (1, 1),
       (1, 2),
       (2, 2);
