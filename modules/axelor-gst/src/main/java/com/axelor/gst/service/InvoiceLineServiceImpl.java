package com.axelor.gst.service;

import java.math.BigDecimal;

import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Product;

public class InvoiceLineServiceImpl implements InvoiceLineService {

  @Override
  public void calcNetAmount(InvoiceLine invoiceLine, Invoice invoice) {

    BigDecimal netAmount = BigDecimal.ZERO;
    BigDecimal grossAmount = BigDecimal.ZERO;
    BigDecimal igst = BigDecimal.ZERO;
    BigDecimal sgst = BigDecimal.ZERO;
    BigDecimal netAmountPercent = BigDecimal.ZERO;

    netAmount = invoiceLine.getPrice().multiply(BigDecimal.valueOf(invoiceLine.getQty()));
    netAmountPercent =
        (netAmount.multiply(invoiceLine.getGstRate())).divide(BigDecimal.valueOf(100));
    if (!(invoice
        .getCompany()
        .getAddress()
        .getState()
        .equals(invoice.getInvoiceAddress().getState()))) {
      igst = netAmountPercent;
      grossAmount = netAmount.add(igst);
    } else {
      sgst = (netAmountPercent).divide(BigDecimal.valueOf(2));
      grossAmount = netAmount.add(sgst).add(sgst);
    }
    invoiceLine.setNetAmount(netAmount);
    invoiceLine.setIgst(igst);
    invoiceLine.setSgst(sgst);
    invoiceLine.setCgst(sgst);
    invoiceLine.setGrossAmount(grossAmount);
  }

  @Override
  public void setProductDetails(InvoiceLine invoiceLine, Invoice invoice) {

    if (invoiceLine.getProduct() != null) {
      Product product = invoiceLine.getProduct();
      invoiceLine.setGstRate(product.getGstRate());
      invoiceLine.setItem("[" + product.getCode() + "]" + product.getName());
      invoiceLine.setPrice(product.getSalePrice());
    } else {
      invoiceLine.setGstRate(null);
      invoiceLine.setItem(null);
      invoiceLine.setPrice(null);
    }
  }
}
