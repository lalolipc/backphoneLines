/*antena desde BD*/
call sp_infraestructure(115234567,2205123458,333,now());

drop PROCEDURE if EXISTS sp_infraestructure;
DELIMITER $$
CREATE PROCEDURE sp_infraestructure(p_originNumber varchar(11),p_destinationNumber varchar(11),p_duration float,p_datecall DateTime,out idCall int)
BEGIN
INSERT INTO calls(calls.number_origin,calls.number_destination,calls.duration,calls.date_call)VALUES(p_originNumber,p_destinationNumber,p_duration,p_datecall);
set idCall=Last_INSERT_ID();

 END$$
 DELIMITER ;


/*call :*/

CALL sp_CallsUserRangeDate(:iduser,:datefrom,:dateto);
CALL sp_top10CallsbyUser(:iduser);
CALL sp_CallsbyUserBackoffice(:iduser);

/*invoices:*/

CALL sp_InvoicesUserRangeDate(:iduser,:datefrom,:dateto);

/*create: */

/*call range date*/

CREATE PROCEDURE sp_CallsUserRangeDate(id_user int(11),date_from  Date,date_to Date)
BEGIN
SELECT number_origin as numberorigin,city_origin as cityorigin ,number_destination as numberdestination,city_destination as citydestination,duration,date_call as datecall, round(total_price,2) as totalprice
FROM vcalls vc
WHERE vc.id_user=pid_user  and date_call BETWEEN date_From and date_to + 1 ;
END

/*top10*/

CREATE PROCEDURE sp_top10CallsbyUser(id_user int(11))
BEGIN
SELECT ci.name as destination,count(id_call) as amount from calls INNER JOIN phones on id_phone=calls.id_destination_phone INNER JOIN cities ci on ci.id_city=phones.id_city WHERE calls.id_user=id_user
GROUP by ci.id_city order by amount DESC limit 10;
END

/*calls by user*/

CREATE PROCEDURE sp_CallsbyUserBackoffice(pid_user int(11))
BEGIN
SELECT number_origin as numberorigin,city_origin as cityorigin ,number_destination as numberdestination,city_destination as citydestination,duration,date_call as datecall, round(total_price,2) as totalprice,cost_price as costprice,sale_price as saleprice
FROM calls c
WHERE c.id_user=pid_user;
END

/*invoices range date*/
CREATE PROCEDURE sp_InvoicesUserRangeDate(pid_user int(11),date_from  Date,date_to Date)
BEGIN
SELECT number,calls_amount as callsamount,round(total_price,2) as totalprice,due_date as duedate,date_invoice as dateinvoice,paid
FROM Vinvoices
WHERE id_user=id_user  and date_invoice BETWEEN date_From and date_to + 1 ;

END
