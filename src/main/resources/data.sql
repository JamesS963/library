
	
delete from User;
insert into User(USER_ID, username, password)
			values(1,'James','$2y$12$hD8kLekouAbj5AtgBhso2e4rgJIciuUCFVE/KKscp22XytOKPZ76y'),
			(2,'David','$2y$12$hD8kLekouAbj5AtgBhso2e4rgJIciuUCFVE/KKscp22XytOKPZ76y'),
			(3,'Josh','$2y$12$hD8kLekouAbj5AtgBhso2e4rgJIciuUCFVE/KKscp22XytOKPZ76y'),
			(4,'Evelyn','$2y$12$hD8kLekouAbj5AtgBhso2e4rgJIciuUCFVE/KKscp22XytOKPZ76y');
		
			
delete from Authority;
insert into Authority(AUTHORITY_ID, ROLE)
			values(1, 'ROLE_USER'),
				  (2, 'ROLE_LIBRARIAN');
			
delete from User_Authority;
insert into User_Authority(USER_ID, AUTHORITY_ID)
			values(1,1),(1,2),(2,1),(3,1),(3,2),(4,2);
			

delete from Book;
insert into Book(BOOK_ID, isbn, title, author, description, size,stock,cover)
			values
			(1,'04410135979780441013593','Dune','Frank Herbert','A stunning blend of adventure and mysticism, environmentalism and politics, Dune won the first Nebula Award, shared the Hugo Award, and formed the basis of what it undoubtedly the grandest epic in science fiction.',528,2,FILE_READ('src/main/resources/bookImages/dune.jpg')),
			(2,'14677326059781467732604','The Wonderful Wizard Of Oz','L. Frank Baum','Best known as the author of the Wizard of Oz series, Lyman Frank Baum was born on May 15, 1856, in New York. When Baum was a young man, his father, who had made a fortune in oil, gave him several theaters in New York and Pennsylvania to manage.',138,2,FILE_READ('src/main/resources/bookImages/wizard_of_oz.jpg')),
			(3,'0-02-615170-7','Roadside Picnic','Arkady and Boris Strugatsky','Roadside Picnic is a work of fiction based on the aftermath of an extraterrestrial event called the Visitation that simultaneously took place in half a dozen separate locations around Earth over a two-day period. Neither the Visitors themselves nor their means of arrival or departure were ever seen by the local populations who lived inside the relatively small areas, each a few square kilometers, of the six Visitation Zones. The zones exhibit strange and dangerous phenomena not understood by humans, and contain artifacts with inexplicable, seemingly supernatural properties. The title of the novel derives from an analogy proposed by the character Dr. Valentine Pilman, who compares the Visitation to a picnic.',224,1,FILE_READ('src/main/resources/bookImages/roadside_picnic.jpg'));

		
	