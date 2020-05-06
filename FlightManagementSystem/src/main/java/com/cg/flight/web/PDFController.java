package com.cg.flight.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.flight.dao.FlightDao;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PDFController {
	
	@Autowired
	private FlightDao dao;
	
	@CrossOrigin
	@GetMapping("/viewpdf/{bookingId}")
	public void downloadPdf(@PathVariable String bookingId,HttpServletResponse resp) {
		com.cg.flight.entity.Booking book =dao.viewBooking(bookingId);
	    Document document = new Document();
    try
    {
        PdfWriter writer = PdfWriter.getInstance(document, resp.getOutputStream());
        document.open();
        document.add(new Paragraph("Ticket"));
        PdfPTable table = new PdfPTable(11); // 3 columns.
        table.setWidthPercentage(100); //Width 100%
        table.setSpacingBefore(10f); //Space before table
        table.setSpacingAfter(10f); //Space after table
 
        //Set Column widths
       // float[] columnWidths = {1f, 1f, 1f, 1f};
       // table.setWidths(columnWidths);
 
        PdfPCell cell1 = new PdfPCell(new Paragraph("Booking ID"));
        PdfPCell cell2 = new PdfPCell(new Paragraph("User Name"));
        PdfPCell cell3 = new PdfPCell(new Paragraph("Seats"));
        PdfPCell cell4 = new PdfPCell(new Paragraph("Fare"));
        PdfPCell cell5 = new PdfPCell(new Paragraph("DOJ"));
        PdfPCell cell6 = new PdfPCell(new Paragraph("Arrival Time"));
        PdfPCell cell7 = new PdfPCell(new Paragraph("Departure Time"));
        PdfPCell cell8 = new PdfPCell(new Paragraph("From"));
        PdfPCell cell9 = new PdfPCell(new Paragraph("To"));
        PdfPCell cell10 = new PdfPCell(new Paragraph("Flight"));
        PdfPCell cell11 = new PdfPCell(new Paragraph("Minutes"));
        
        
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        table.addCell(cell4);
        table.addCell(cell5);
        table.addCell(cell6);
        table.addCell(cell7);
        table.addCell(cell8);
        table.addCell(cell9);
        table.addCell(cell10);
        table.addCell(cell11);
     
            
        	cell1 = new PdfPCell(new Paragraph(book.getBookingId()+""));
        	cell2 = new PdfPCell(new Paragraph(book.getUser().getUserName()+""));
        	
        	cell3 = new PdfPCell(new Paragraph(book.getNoOfSeats()+""));
        	cell4 = new PdfPCell(new Paragraph(book.getFare()+""));
        	cell5 = new PdfPCell(new Paragraph(book.getBookingDate()+""));
        	cell6 = new PdfPCell(new Paragraph(book.getSchedule().getArrivalTime()+""));
        	cell7 = new PdfPCell(new Paragraph(book.getSchedule().getDepartureTime()+""));
        	cell8 = new PdfPCell(new Paragraph(book.getSchedule().getSourceAirport().getAirportLocation()+""));
        	cell9 = new PdfPCell(new Paragraph(book.getSchedule().getDestinationAirport().getAirportLocation()+""));
        	cell10 = new PdfPCell(new Paragraph(book.getSchedule().getFlight().getFlightName()+""));
        	cell11 = new PdfPCell(new Paragraph(book.getSchedule().getMinutes()+""));
        	
        	table.addCell(cell1);
            table.addCell(cell2);
            table.addCell(cell3);
            table.addCell(cell4);
            table.addCell(cell5);
            table.addCell(cell6);
            table.addCell(cell7);
            table.addCell(cell8);
            table.addCell(cell9);
            table.addCell(cell10);
            table.addCell(cell11);
            
        document.add(table);
 
        document.close();
        writer.close();
    } catch (Exception e)
    {
        e.printStackTrace();
    }
	}

}
