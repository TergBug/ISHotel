package org.mycode.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.mycode.dto.DocumentTableRow;

import java.util.List;

public interface ReportMakerService {
    XWPFDocument generateReport(List<DocumentTableRow> rows);
}
