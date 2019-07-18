package com.axelor.gst.service;

import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Address;
import com.axelor.gst.db.Contact;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.Party;

public class InvoiceServiceImpl implements InvoiceService {

  @Override
  public void setPartyData(Invoice invoice) {

    Party party = invoice.getParty();
    List<Contact> contactList = new ArrayList<Contact>();
    List<Address> addressList = new ArrayList<Address>();
    Contact primaryContact = null;
    Address defaultInvoiceAddress = null;
    contactList = party.getContactList();
    addressList = party.getAddressList();

    if (contactList != null) {
      for (Contact contact : contactList) {
        if (contact.getType().equals("primary")) {
          primaryContact = contact;
        }
      }
    }
    invoice.setPartyContact(primaryContact);

    if (addressList != null) {
      for (Address address : addressList) {
        if (address.getType().equals("default") || address.getType().equals("invoice")) {
          defaultInvoiceAddress = address;
        }
      }
    }
    invoice.setInvoiceAddress(defaultInvoiceAddress);
  }

  @Override
  public void setShippingAddress(Invoice invoice) {
    Party party = invoice.getParty();
    List<Address> addressList = new ArrayList<Address>();
    addressList = party.getAddressList();
    Address defaultShippingAddress = null;
    boolean getBooleanData = invoice.getIsUseInvoiceAddressAsShipping();
    if (invoice.getIsUseInvoiceAddressAsShipping() == false) {
      if (addressList != null) {
        for (Address address : addressList) {
          if (address.getType().equals("default") || address.getType().equals("shipping")) {
            defaultShippingAddress = address;
          }
        }
      }
    }
    invoice.setShippingAddress(defaultShippingAddress);
  }
}
