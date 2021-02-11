package com.utn.PhoneLines.repository;

import com.utn.PhoneLines.model.Invoice;
import com.utn.PhoneLines.projection.InvoiceUserAndDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Integer> {

    @Query(value = " CALL sp_InvoicesUserRangeDate(:iduser,:datefrom,:dateto);", nativeQuery = true)
    List<InvoiceUserAndDate> getReportInvoicesByUserByDate(@Param("iduser") Integer idUser, @Param("datefrom") Date dateFrom, @Param("dateto") Date dateTo );

    @Query( value= "select b.* from invoices b " +
            "inner join phone_lines pl on pl.id_phone_line = b.id_phone_line " +
            "where pl.id_user = ?1 order by b.date asc", nativeQuery = true)
    List<Invoice> getInvoicesByIdUser(Integer idUser);
}
