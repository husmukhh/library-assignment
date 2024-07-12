-- "library".book definition
\c postgres;

CREATE SCHEMA library

-- Drop table

-- DROP TABLE "library".book;
CREATE TABLE "library".book (
	id serial4 NOT NULL,
	title varchar(100) NOT NULL,
	author varchar(50) NOT NULL,
	isbn varchar(13) NOT NULL,
	CONSTRAINT book_pk PRIMARY KEY (id),
	CONSTRAINT book_unique UNIQUE (title, author, isbn)
);


-- "library".borrower definition

-- Drop table

-- DROP TABLE "library".borrower;

CREATE TABLE "library".borrower (
	id serial4 NOT NULL,
	borrower_name varchar(50) NOT NULL,
	email_id varchar(100) NOT NULL,
	CONSTRAINT borrower_pk PRIMARY KEY (id)
);


-- "library".book_id_seq definition

-- DROP SEQUENCE "library".book_id_seq;

-- "library".book_borrower definition

-- Drop table

-- DROP TABLE "library".book_borrower;

CREATE TABLE "library".book_borrower (
	id serial4 NOT NULL,
	book_id int8 NOT NULL,
	borrower_id int8 NOT NULL,
	issue_date date NOT NULL,
	return_date date NULL,
	remarks text NULL,
	CONSTRAINT book_borrower_pk PRIMARY KEY (id)
);


-- "library".book_borrower foreign keys

ALTER TABLE "library".book_borrower ADD CONSTRAINT book_borrower_book_fk FOREIGN KEY (book_id) REFERENCES "library".book(id);
ALTER TABLE "library".book_borrower ADD CONSTRAINT book_borrower_borrower_fk FOREIGN KEY (borrower_id) REFERENCES "library".borrower(id);




-- "library".book_id_seq definition

-- DROP SEQUENCE "library".book_id_seq;

