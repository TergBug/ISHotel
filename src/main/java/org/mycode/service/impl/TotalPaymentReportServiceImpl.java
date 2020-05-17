package org.mycode.service.impl;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.mycode.dto.DocumentTableRow;
import org.mycode.model.Customer;
import org.mycode.model.CustomerFacility;
import org.mycode.repository.CustomerRepository;
import org.mycode.service.ReportMakerService;
import org.mycode.service.TotalPaymentReportService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TotalPaymentReportServiceImpl implements TotalPaymentReportService {
    private ReportMakerService reportMakerService;
    private CustomerRepository customerRepository;

    public TotalPaymentReportServiceImpl(ReportMakerService reportMakerService, CustomerRepository customerRepository) {
        this.reportMakerService = reportMakerService;
        this.customerRepository = customerRepository;
    }

    @Override
    public XWPFDocument report(String customerPassport) {
        Customer customer = customerRepository.findCustomerByPassport(customerPassport);
        List<DocumentTableRow> rows = new ArrayList<>();
        rows.add(new DocumentTableRow(Arrays.asList("Type", "Name", "Quantity", "Price")));
        rows.add(new DocumentTableRow(Arrays.asList("Room",
                customer.getRoom().getRoomType().getName().toUpperCase() + " for "
                        + customer.getRoom().getRoomType().getNumberOfPersons()
                        + " persons on " + customer.getRoom().getFloor() + " floor", "1",
                customer.getRoom().getPrice().toString())));
        for (int i = 0; i < customer.getServices().size(); i++) {
            rows.add(new DocumentTableRow(Arrays.asList("Service", customer.getServices().get(i).getName(), "1",
                    customer.getServices().get(i).getPrice().toString())));
        }
        CustomerFacility[] facilities = customer.getFacilities().toArray(new CustomerFacility[0]);
        for (CustomerFacility facility : facilities) {
            BigDecimal subTotal = facility.getId().getFacility().getPrice()
                    .multiply(BigDecimal.valueOf(facility.getQuantity()));
            rows.add(new DocumentTableRow(Arrays.asList("Facility", facility.getId().getFacility().getName(),
                    String.valueOf(facility.getQuantity()), subTotal.toString())));
        }
        rows.add(new DocumentTableRow(Arrays.asList("Total", "", "", customer.totalPayment().toString())));
        return reportMakerService.generateReport(rows);
    }
}
