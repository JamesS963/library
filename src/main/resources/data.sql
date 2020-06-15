delete from User;

insert into User(id, username, password)
			values(1,'James','$2y$12$hD8kLekouAbj5AtgBhso2e4rgJIciuUCFVE/KKscp22XytOKPZ76y');
			
			
delete from Authority;
insert into Authority(ID, ROLE)
			values(1, 'ROLE_USER'),
				  (2, 'ROLE_LIBRARIAN');
			
delete from User_Authority;
insert into User_Authority(UserId, AuthorityId)
			values(1,1);
			
		
	