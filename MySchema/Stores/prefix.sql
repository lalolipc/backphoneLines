/* prefix of number, calculate city*/
/*call BI*/
CREATE TRIGGER call_BI before INSERT on calls for EACH ROW
BEGIN
DECLARE vIdNumberto int ;
DECLARE vIdCityFrom int ;
DECLARE vIdCityTo int ;

SET new.id_origin_phone=(SELECT id_phone FROM phones  WHERE number=new.number_origin);
SET new.id_destination_phone=(SELECT id_phone FROM phones WHERE number=new.number_destination);
SET vIdCityFrom=(SELECT c.id_city FROM cities c WHERE new.number_origin  like CONCAT(c.prefix,'%')order by length(c.prefix) desc limit 1);
SET vIdCityTo=(SELECT c.id_city FROM cities c WHERE new.number_destination  like CONCAT(c.prefix,'%')order by length(c.prefix) desc limit 1);
SET new.city_origin =(SELECT c.name FROM cities c WHERE c.id_city=vIdCityFrom);
SET new.city_destination =(SELECT c.name FROM cities c WHERE c.id_city=vIdCityTo);
SET new.id_rate=(SELECT r.id_rate FROM rates r WHERE r.id_city_from=vIdCityFrom and r.id_city_to=vIdCityTo);
set new.cost_price=(SELECT r.cost_price FROM rates r WHERE r.id_rate=new.id_rate);
set new.sale_price =(SELECT r.sale_price FROM rates r WHERE r.id_rate=new.id_rate);
set new.total_price=round(new.sale_price *(new.duration/60),2);

SET new.id_user=(SELECT id_user FROM phones p WHERE p.id_phone=new.id_origin_phone);

 END
