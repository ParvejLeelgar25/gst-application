package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Address;
import com.axelor.gst.db.Contact;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;

public class InvoiceServiceImpl implements InvoiceService {

  @Override
  public void setPartyData(Invoice invoice) {

    List<Contact> contactList = new ArrayList<Contact>();
    List<Address> addressList = new ArrayList<Address>();
    Contact primaryContact = null;
    Address defaultInvoiceAddress = null;
    Address defaultShippingAddress = null;

    if (invoice.getParty() != null) {
      Party party = invoice.getParty();
      contactList = party.getContactList();
      addressList = party.getAddressList();

      if (contactList != null) {
        for (Contact contact : contactList) {
          if (contact.getType().equals("primary")) {
            primaryContact = contact;
          }
        }
      }

      if (addressList != null) {
        for (Address address : addressList) {
          if (address.getType().equals("default") || address.getType().equals("invoice")) {
            defaultInvoiceAddress = address;
          }
          if (address.getType().equals("default") || address.getType().equals("shipping")) {
            defaultShippingAddress = address;
          }
        }
      }
    }
    invoice.setPartyContact(primaryContact);
    invoice.setInvoiceAddress(defaultInvoiceAddress);
    invoice.setShippingAddress(defaultShippingAddress);
  }

  @Override
  public void setShippingAddress(Invoice invoice) {

    Address defaultShippingAddress = null;
    if (invoice.getParty() != null) {
      Party party = invoice.getParty();
      List<Address> addressList = new ArrayList<Address>();
      addressList = party.getAddressList();

      if (invoice.getIsUseInvoiceAddressAsShipping() == false) {
        if (addressList != null) {
          for (Address address : addressList) {
            if (address.getType().equals("default") || address.getType().equals("shipping")) {
              defaultShippingAddress = address;
            }
          }
        }
      }
    }
    invoice.setShippingAddress(defaultShippingAddress);
  }

  @Override
  public void setDetails(Invoice invoice) {

    BigDecimal netAmount = BigDecimal.ZERO;
    BigDecimal grossAmount = BigDecimal.ZERO;
    BigDecimal igst = BigDecimal.ZERO;
    BigDecimal sgst = BigDecimal.ZERO;
    BigDecimal cgst = BigDecimal.ZERO;

    List<InvoiceLine> invoiceItemList = new ArrayList<InvoiceLine>();
    invoiceItemList = invoice.getInvoiceItems();
    if (invoiceItemList != null) {
      for (InvoiceLine invoiceItem : invoiceItemList) {
        netAmount = netAmount.add(invoiceItem.getNetAmount());
        igst = igst.add(invoiceItem.getIgst());
        sgst = sgst.add(invoiceItem.getSgst());
        cgst = cgst.add(invoiceItem.getCgst());
        grossAmount = grossAmount.add(invoiceItem.getGrossAmount());
      }
    }
    invoice.setNetAmount(netAmount);
    invoice.setNetIgst(igst);
    invoice.setNetCgst(cgst);
    invoice.setNetSgst(sgst);
    invoice.setGrossAmount(grossAmount);
  }
}
