INSERT INTO roles (id, "name") VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles (id, "name") VALUES(2, 'ROLE_SYSTEM');
INSERT INTO roles (id, "name") VALUES(3, 'ROLE_USER');

INSERT INTO users_info (id, enabled, "password", username) VALUES(1, true, 'asdw312312asd!@#!@$@sdasd12lp;lk[klk;lk;lkl,huhi123joiasl', 'system');
INSERT INTO users_info (id, enabled, "password", username) VALUES(2, true, 'M7z£19W=6f[/', 'api_admin');

INSERT INTO user_roles (user_id, role_id) VALUES(1, 2);
INSERT INTO user_roles (user_id, role_id) VALUES(2, 1);

ALTER SEQUENCE public.users_info_id_seq
	MINVALUE 1001
	START 1001
	RESTART 1001;
