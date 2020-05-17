package org.mycode.service.impl;

import org.apache.poi.xwpf.usermodel.*;
import org.mycode.dto.DocumentTableRow;
import org.mycode.service.ReportMakerService;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class ReportMakerServiceImpl implements ReportMakerService {
    @Override
    public XWPFDocument generateReport(List<DocumentTableRow> rows) {
        XWPFDocument document = new XWPFDocument();
        XWPFTable table = document.createTable();
        table.setCellMargins(50, 50, 100, 50);
        table.setWidth("100%");

        XWPFTableRow head = table.getRow(0);
        styleCell(head.getCell(0), rows.get(0).getColumns().get(0), true, ParagraphAlignment.CENTER);
        for (int i = 1; i < rows.get(0).getColumns().size(); i++) {
            styleCell(head.addNewTableCell(), rows.get(0).getColumns().get(i), true, ParagraphAlignment.CENTER);
        }

        for (int i = 1; i < rows.size(); i++) {
            XWPFTableRow row = table.createRow();
            if (rows.get(i).getColumns().get(0).equalsIgnoreCase("total")) {
                styleCell(row.getCell(0), rows.get(i).getColumns().get(0), false, ParagraphAlignment.LEFT);
                int toColSpan = rows.get(i).getColumns().size() - 2;
                styleCell(row.getCell(toColSpan + 1), rows.get(i).getColumns().get(toColSpan + 1), true,
                        ParagraphAlignment.CENTER);
                mergeCellHorizontally(table, i, 0, toColSpan);
            } else {
                for (int j = 0; j < rows.get(i).getColumns().size(); j++) {
                    if (j <= rows.get(0).getColumns().size()) {
                        styleCell(row.getCell(j), rows.get(i).getColumns().get(j), false,
                                isNum(rows.get(i).getColumns().get(j)) ?
                                        ParagraphAlignment.CENTER : ParagraphAlignment.LEFT);
                    } else {
                        styleCell(row.addNewTableCell(), rows.get(i).getColumns().get(j), false,
                                isNum(rows.get(i).getColumns().get(j)) ?
                                        ParagraphAlignment.CENTER : ParagraphAlignment.LEFT);
                    }
                }
            }
        }
        return document;
    }

    private void mergeCellHorizontally(XWPFTable table, int row, int fromCol, int toCol) {
        XWPFTableCell cell = table.getRow(row).getCell(fromCol);
        CTTcPr tcPr = cell.getCTTc().getTcPr();
        if (tcPr == null) tcPr = cell.getCTTc().addNewTcPr();
        if (tcPr.isSetGridSpan()) {
            tcPr.getGridSpan().setVal(BigInteger.valueOf(toCol - fromCol + 1));
        } else {
            tcPr.addNewGridSpan().setVal(BigInteger.valueOf(toCol - fromCol + 1));
        }
        for (int colIndex = toCol; colIndex > fromCol; colIndex--) {
            table.getRow(row).getCtRow().removeTc(colIndex);
            table.getRow(row).removeCell(colIndex);
        }
    }

    private void styleCell(XWPFTableCell cell, String text, boolean isBold, ParagraphAlignment alignment) {
        XWPFParagraph paragraph = cell.getParagraphs().get(0);
        paragraph.setAlignment(alignment);
        XWPFRun run = paragraph.createRun();
        run.setBold(isBold);
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
        run.setText(text);
    }

    private boolean isNum(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
