/*show index from phonelines.calls*/
/*calls:*/
create index idx_calls_dates_user on calls(id_user,date_call) using btree;
create index if not exists idx_calls_phone_from_to_date on calls(id_origin_phone,id_destination_phone,date_call) using btree;
create index if not exists idx_calls_phone_origin_date on calls(id_origin_phone,date_call) using btree;
create index if not exists idx_calls_phone_destination_date on calls(id_destination_phone,date_call) using btree;
create index if not exists idx_calls_dates on calls(date_call) using btree;

/*show index from phonelines.invoices*/
/*invoices:*/
create index idx_invoices_dates_user on invoices(id_user,date_invoice) using btree;//cliente facturas x rango fechas
create index if not exists idx_invoices_idphone_date on invoices(id_phone,due_date) using btree;
create index if not exists idx_invoices_dates on invoices(due_date) using btree;

/*examples*/

/*idx_calls_dates_user on calls(id_user,date_call)*/

explain SELECT * FROM calls c
WHERE c.id_user=1  and date_call BETWEEN 2010-01-01 and 2021-01-01;


/*idx_calls_dates_user on invoices(id_user,date_invoice)*/

explain SELECT * FROM invoices i
WHERE i.id_user=1 and i.date_invoice BETWEEN 2010-01-01 and 2021-01-01 ;

/*drop index*/
/*drop index idx_calls_dates_user on calls*/
/*drop index idx_invoices_dates_user on calls*/