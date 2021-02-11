/*USUARIOS*/
create user 'gocellalugo'@'%' identified by '123'
create user backoffice@'%' identified by '123'
create user 'clientes'@'%' identified by '123'
create user 'antena'@'%' identified by '123'

/*privileges*/
Grant all privileges on phonelines to 'gocellalugo'@'%';

grant all privileges on  phonelines.users to 'backoffice'@'%';
grant all privileges on  phonelines.phones to 'backoffice'@'%';
grant all privileges on  phonelines.rates to 'backoffice'@'%';
grant select on  phonelines.invoices to 'backoffice'@'%';
grant  select on   phonelines.calls to 'backoffice'@'%';

grant select on phonelines.Vcalls to 'clientes'@'%';
grant select on  phonelines.Vinvoices to 'clientes'@'%';

grant all privileges on phonelines.calls to 'antena'@'%';

/*show*/
Show grants for  'gocellalugo'@'%'
Show grants for  backoffice@'%'
Show grants for  clientes@'%'
