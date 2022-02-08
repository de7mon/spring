create table if not exists timelog(
 id int not null auto_increment,
 method_name  varchar(50) ,
 log_timestamp varchar(50),
 primary key(id)
)