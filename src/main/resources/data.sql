INSERT INTO TBL_ROLES (id, role) VALUES (1, 'ROLE_USER');
INSERT INTO TBL_ROLES (id, role) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO TBL_ROLES (id, role) VALUES (3, 'ROLE_ADMIN');

INSERT INTO TBL_USERS (id, username, email, password)VALUES (1, 'taylor', 'taylor@letscode.com',  '$2a$10$zrLnJTer2evTZ/Rdey6N4.kZ1O.VgxpjRe3CSBCrhSQMSgA9kdNoq');

INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 2);
INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 3);
