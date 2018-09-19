INSERT INTO `user` (id, username, `password`)
VALUES (1, 'admin', 'password'),
       (2, 'user', 'password');

INSERT INTO `role` (id, `name`)
VALUES (1, 'ADMIN'),
       (2, 'USER');

INSERT INTO `user_role` (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (2, 2);

INSERT INTO `permission` (id, `name`)
VALUES (1, 'ITEM_CREATE'),
       (2, 'ITEM_READ'),
       (3, 'ITEM_UPDATE'),
       (4, 'ITEM_DELETE');

INSERT INTO `role_permission` (role_id, permission_id)
VALUES (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (2, 2);
