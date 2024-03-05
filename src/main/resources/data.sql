INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('ROLE_OPERATOR');

INSERT INTO users (user_name, email, password)
VALUES            ('Thor', 'thor@.ru', '123'),
                  ('Odin', 'odin@.ru', '123'),
                  ('Loki', 'loki@.ru', '123'),
                  ('Sif', 'sif@.ru', '123'),
                  ('Heimdallr', 'heimdallr@.ru', '123'),
                  ('Nanna', 'nanna@.ru', '123');

INSERT INTO user_roles (user_id, role_id)
VALUES                 (1, 2),
                       (2, 1),
                       (3, 3),
                       (4, 1),
                       (5, 1),
                       (6, 1);
