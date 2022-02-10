INSERT INTO TBL_ROLES (role_id, role) VALUES (1, 'ROLE_USER');
INSERT INTO TBL_ROLES (role_id, role) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO TBL_ROLES (role_id, role) VALUES (3, 'ROLE_ADMIN');

INSERT INTO TBL_USERS (user_id, username, email, password)VALUES (1, 'user_test01', 'user_tes01@letscode.com',  '$2a$10$zrLnJTer2evTZ/Rdey6N4.kZ1O.VgxpjRe3CSBCrhSQMSgA9kdNoq');
INSERT INTO TBL_USERS (user_id, username, email, password)VALUES (2, 'user_test02', 'user_tes02@letscode.com',  '$2a$10$zrLnJTer2evTZ/Rdey6N4.kZ1O.VgxpjRe3CSBCrhSQMSgA9kdNoq');

INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 1);
INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 2);
INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (1, 3);

INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (2, 1);
INSERT INTO TBL_USER_ROLES (user_id, role_id) VALUES (2, 2);