package org.mycode.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface TotalPaymentReportService {
    XWPFDocument report(String customerPassport);
}
