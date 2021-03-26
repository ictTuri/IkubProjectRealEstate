CREATE TABLE ROLES(
	id serial PRIMARY KEY NOT NULL,
	name varchar(25) NOT NULL
);

CREATE TABLE USERS (
	id serial PRIMARY KEY NOT NULL,
	first_name varchar(50) NOT NULL,
	last_name varchar(20) NOT NULL,
	email varchar(40) NOT NULL UNIQUE,
	username varchar(25) NOT NULL UNIQUE,
	password varchar(50) NOT NULL,	
	valid boolean NOT NULL,
	created_on date,
	updated_on date,
	role_id serial references roles (id)
);

CREATE TABLE LOCATION (
	id serial PRIMARY KEY NOT NULL,
	city_name varchar(20) NOT NULL,
	street_name varchar(20) NOT NULL,
	zip_code int NOT NULL,
	description varchar(50) NOT NULL
);

CREATE TABLE PROPERTY_INFO (
	id serial PRIMARY KEY NOT NULL,
	area integer NOT NULL,
	has_garage boolean NOT NULL,
	has_elevator boolean NOT NULL,
	has_pool boolean NOT NULL,
	floor_number SMALLINT NOT NULL,
	bathroom_number SMALLINT NOT NULL,
	bedroom_number SMALLINT NOT NULL
	
);

CREATE TABLE PROPERTY_TYPE (
	id serial PRIMARY KEY NOT NULL,
	name varchar(30) NOT NULL,
	description varchar(100)
);

CREATE TABLE PROPERTY (
	id serial PRIMARY KEY NOT NULL,
	owner integer references USERS (user_id),
	description varchar(150) NOT NULL,
	renting_price integer NOT NULL,
	selling_price integer NOT NULL,
	category varchar(25) NOT NULL,
	property_type serial references PROPERTY_TYPE (id) NOT NULL,
	property_location serial references LOCATIONS (id) NOT NULL,
	property_info serial references PROPERTY_INFO (id) NOT NULL
);

CREATE TABLE TRADE (
	id serial PRIMARY KEY NOT NULL,
    user serial references USERS (id) NOT NULL,
	property serial references PROPERTIES (id) NOT NULL,
	trade_date timestamp without time zone NOT NULL,
	end_trade_date timestamp without time zone,
    trade_type varchar(20) NOT NULL,
	payment_type varchar(20) NOT NULL	
);

CREATE TABLE ISSUES (
	id serial PRIMARY KEY NOT NULL,
	category varchar(20) NOT NULL,
	resolution_status varchar(30) NOT NULL,
	description varchar(50) NOT NULL,
	created_date timestamp without time zone NOT NULL,
    user_id integer references users (id) NOT NULL,
	property_id integer references properties (id) NOT NULL
);
