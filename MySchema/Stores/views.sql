/*CALLS TO CLIENT:*/

create VIEW Vcalls(id_call,number_origin,city_origin,number_destination,city_destination,total_price,duration,date_call,id_user)
as
 SELECT id_call,number_origin,city_origin,number_destination,city_destination,total_price,duration,date_call,id_user
FROM calls;


/*INVOICES TO CLIENT:*/

drop view if EXISTS vinvoices;
create VIEW Vinvoices(id_invoice,id_phone,id_user,calls_amount, total_price,due_date,date_invoice,number,paid)
as
 SELECT inv.id_invoice,inv.id_phone,inv.id_user,inv.calls_amount
,inv.total_price,inv.due_date,inv.date_invoice,ph.number,inv.paid
FROM invoices inv inner join phones ph on ph.id_phone=inv.id_phone;