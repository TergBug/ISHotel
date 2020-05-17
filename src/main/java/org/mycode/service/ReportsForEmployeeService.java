package org.mycode.service;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public interface ReportsForEmployeeService {
    XWPFDocument freeRooms();

    XWPFDocument customers();

    XWPFDocument facilities();
}
