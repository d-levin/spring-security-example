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

insert into `permission` (id, `name`)
values (1, 'item_create'),
       (2, 'item_delete'),
       (3, 'item_update'),
       (4, 'item_view');

insert into `role_permission` (role_id, permission_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 4);
