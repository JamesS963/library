
	
			
delete from Authority;
insert into Authority(ID, ROLE)
			values(1, 'ROLE_USER'),
				  (2, 'ROLE_LIBRARIAN');
			
delete from User_Authority;
insert into User_Authority(UserId, AuthorityId)
			values(1,1);
			
		
	