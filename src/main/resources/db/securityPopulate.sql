insert into security_users(username, password, enabled, identity_info)
values ('admin', '$2y$12$r2WOi137X1H3aZxTKFGWm.hHrzYoEyMC4.sTDPxfvORrHC1MskOge', 1, null),
       ('user', '$2y$12$COBXcOilGx5kG8WoEVmANuwyl9PHO1jfXMH9Xz.zFYJuYFDRHmNne', 1, null);
commit;
insert into security_authorities(authority)
values ('ADMIN'),
       ('CUSTOMER'),
       ('EMP');
commit;
insert into security_user_authority(username, authority)
values ('admin', 'ADMIN'),
       ('user', 'EMP');
commit;