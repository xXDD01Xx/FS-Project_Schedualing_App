BEGIN TRANSACTION;

INSERT INTO user_status (user_status_desc)
VALUES ('Active'),('Inactive'),('Needs Approval'),('Not Approved');

INSERT INTO users (username,password_hash,role,user_status_id) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER',
														(SELECT MAX(user_status_id) 
														FROM user_status
														WHERE user_status_desc='Active')),('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN',
														(SELECT MAX(user_status_id) 
														FROM user_status
														WHERE user_status_desc='Active'));

COMMIT TRANSACTION;