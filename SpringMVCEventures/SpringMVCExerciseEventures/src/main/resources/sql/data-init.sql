CREATE PROCEDURE insert_admin()
  BEGIN
    IF ((SELECT COUNT(*) FROM user_roles LIMIT 1) = 0) THEN
      INSERT INTO user_roles (role_id, authority) VALUES
        (UUID(), 'USER'),
        (UUID(), 'ADMIN');
    END IF;
  END ^

CALL insert_admin()^
DROP PROCEDURE insert_admin^