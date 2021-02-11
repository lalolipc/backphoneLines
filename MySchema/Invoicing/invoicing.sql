/*invoicing:*/

CALL sp_InvoiceOnePhone (p_idPhone INT);
CALL sp_invoice_allPhones();
CREATE EVENT if not EXISTS event_invoice2;


/*ONE PHONE*/

CREATE PROCEDURE `sp_InvoiceOnePhone`(p_idPhone INT)
begin
	create temporary table callsInvoiceNull
	select id_call from calls c where c.id_origin_phone = p_idPhone and c.id_invoice is null;


	insert into invoices (id_phone,id_user,calls_amount,cost_price, total_price,due_date,date_invoice)
	select pl.id_phone , pl.id_user , count(pl.id_phone) as "calls_amount", sum(c.duration),sum(c.total_price*1.21),(CURDATE() + INTERVAL 15 DAY ),now()
	from calls c
		inner join callsInvoiceNull temp on c.id_call = temp.id_call
		inner join phones pl on c.id_origin_phone = pl.id_phone
	where pl.id_phone = p_idPhone and c.id_invoice is null
	group by pl.id_phone ;


	update calls
	inner join callsInvoiceNull temp on calls.id_call = temp.id_call
	set calls.id_invoice = LAST_INSERT_ID();
	drop table callsInvoiceNull;

end

/*ALL PHONE*/
CREATE PROCEDURE sp_invoice_allPhones()

BEGIN
 DECLARE vIdPhone int;
    DECLARE vFinished int DEFAULT 0;
     DECLARE cur_invoice CURSOR FOR select id_phone from phones ;
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET vFinished = 1;

    open cur_invoice;

    leer: LOOP
      START TRANSACTION;
          FETCH cur_invoice INTO vIdPhone;
          call sp_InvoiceOnePhone(vIdPhone);
        COMMIT;

        if vFinished = 1 then
            leave leer;
        end if;
    END LOOP leer;
    close cur_invoice;
END

/*EVENT MONTH*/
Delimiter $$
CREATE EVENT if not EXISTS event_invoice
on SCHEDULE EVERY 1 month STARTS date_format(date_add(subdate(now(),dayofmonth(now())-1),INTERVAL 1 month),'%Y-%m-%d 00:00:00')
do
BEGIN
CALL sp_invoice_allPhones();
END$$
Delimiter ;

SHOW PROCESSLIST;
 SET GLOBAL event_scheduler = ON;
