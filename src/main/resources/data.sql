INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_OPERATOR');

INSERT INTO users (user_name, email, password)
VALUES            ('Thor', 'thor@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.'),
                  ('Odin', 'odin@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.'),
                  ('Loki', 'loki@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.'),
                  ('Sif', 'sif@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.'),
                  ('Heimdallr', 'heimdallr@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.'),
                  ('Nanna', 'nanna@.ru', '$2a$12$jwcvWLpmH1PTD1/NUKRyN.NslmQK7tUW4sCoDqNOCnG0w5AX3PEk.');

INSERT INTO user_roles (user_id, role_id)
VALUES                 (1, 2),
                       (2, 1),
                       (3, 3),
                       (4, 1),
                       (5, 1),
                       (6, 1);
