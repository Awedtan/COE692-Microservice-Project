drop table Comments;
drop table Articles;
drop table Users;

create table Users (
	uid int not null primary key auto_increment,
	username varchar(255) unique,
	password varchar(255),
	emailAddress varchar(255) unique,
	firstName varchar(255),
	lastName varchar(255)
);
create table Articles (
	uid int not null primary key auto_increment,
	date date,
	title longtext,
	content longtext,
	isPublished boolean,
	author int not null,
	constraint `fk_article_author`
  	  foreign key(author) references Users(uid)
);
create table Comments (
	uid int not null primary key auto_increment,
	date date,
	content longtext,
	article int not null,
	author int not null,
	constraint `fk_comment_author`
  	  foreign key(author) references Users(uid),
	constraint `fk_comment_article`
  	  foreign key(article) references Articles(uid)
);

insert into Users (username, password, emailAddress, firstName, lastName) values ("admin", "adminpassword", "admin@mail.com", "Admin", "Istrator");
insert into Users (username, password, emailAddress, firstName, lastName) values ("awedtan", "rememberthis", "daniel.yuyuan.su@torontomu.ca", "Daniel", "Su");
insert into Users (username, password, emailAddress, firstName, lastName) values ("jdeere", "pw123", "tractorsrcool@mail.com", "John", "Deere");

insert into Articles (date, title, content, isPublished, author) values ("2024-03-21", "Admins only!", "If you're reading this, you're an admin!", false, 1);
insert into Articles (date, title, content, isPublished, author) values ("2024-03-21", "Site announcement", "Hello users of this site, we've got some announcements to make.", true, 1);
insert into Articles (date, title, content, isPublished, author) values ("2024-03-20", "An Intro to SQL", "This article will cover the basics of SQL queries.", true, 2);
insert into Articles (date, title, content, isPublished, author) values ("2024-03-21", "An Intro to Java", "This article will cover the basics of Java programming.", true, 2);
insert into Articles (date, title, content, isPublished, author) values ("2024-03-21", "All about tractors", "My name is John Deere and I love tractors!", true, 3);

insert into Comments (date, content, article, author) values ("2024-03-21", "This is an admin comment.", 1, 1);
insert into Comments (date, content, article, author) values ("2024-03-21", "This was a very informative article about this website.", 2, 2);
insert into Comments (date, content, article, author) values ("2024-03-21", "I now know how to use SQL queries!", 3, 1);