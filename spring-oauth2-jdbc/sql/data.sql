USE auth;

INSERT INTO auth.oauth_client_details(
	client_id,
	client_secret,
	scope,
	authorized_grant_types,
	web_server_redirect_uri,
	authorities,
	access_token_validity,
	refresh_token_validity,
	autoapprove)
VALUES (
	'clientId',
	'secret'
	'read,write'
	'authorization_code,implicit,password,refresh_token,client_credentials',
	'http://localhost:8083/login',
	'ROLE_CLIENT,ROLE_TRUSTED_CLIENT',
	3600,
	2592000,
	false
	);

INSERT INTO auth.users (
	username,
	password,
	enabled)
VALUES (
	'admin',
	'admin123',
	true);

INSERT INTO auth.users (
	username,
	password,
	enabled)
VALUES (
	'user',
	'user123',
	true);

INSERT INTO auth.users (
	username,
	password,
	enabled)
VALUES (
	'guest',
	'guest123',
	true);
	
INSERT INTO auth.authorities (username, authority)
VALUES('admin', 'ADMIN');

INSERT INTO auth.authorities (username, authority)
VALUES('admin', 'USER');

INSERT INTO auth.authorities (username, authority)
VALUES('user', 'USER');

INSERT INTO auth.authorities (username, authority)
VALUES('guest', 'GUEST');
