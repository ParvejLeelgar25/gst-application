package com.axelor.gst.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.axelor.gst.db.Address;
import com.axelor.gst.db.Contact;
import com.axelor.gst.db.Invoice;
import com.axelor.gst.db.InvoiceLine;
import com.axelor.gst.db.Party;
import com.axelor.gst.db.Product;
import com.axelor.gst.db.Wizard;
import com.axelor.gst.db.repo.InvoiceLineRepository;
import com.axelor.gst.db.repo.InvoiceRepository;
import com.axelor.gst.db.repo.PartyRepository;
import com.axelor.gst.db.repo.ProductRepository;
import com.axelor.gst.db.repo.WizardRepository;
import com.axelor.inject.Beans;
import com.google.inject.Inject;

public class InvoiceServiceImpl implements InvoiceService {

  @Inject InvoiceLineServiceImpl invoiceLineServiceImpl;

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

  @Override
  public Invoice setInvoiceData(Invoice invoice, List<Integer> productIdList, int partyId) {
    Party party = Beans.get(PartyRepository.class).all().filter("self.id = ?1", partyId).fetchOne();
    invoice.setParty(party);
    setPartyData(invoice);

    List<InvoiceLine> invoiceItem = new ArrayList<>();
    for (Integer productId : productIdList) {
      Product product =
          Beans.get(ProductRepository.class).all().filter("self.id = ?1", productId).fetchOne();
      InvoiceLine invoiceLine = new InvoiceLine();
      invoiceLine.setProduct(product);
      invoiceLine.setItem(product.getName());
      invoiceLine.setHsbn(product.getHsbn());
      invoiceLine.setGstRate(product.getGstRate());
      invoiceLine.setPrice(product.getSalePrice());
      invoiceLineServiceImpl.calcNetAmount(invoiceLine, invoice);
      invoiceItem.add(invoiceLine);
    }
    invoice.setInvoiceItems(invoiceItem);
    setDetails(invoice);
    return invoice;
  }
  
  @Override
  public Invoice reCalculation(Invoice invoice) {
	  
	  List<InvoiceLine> invoiceLineList = new ArrayList<>();
	  if(invoice.getInvoiceItems() != null) {
		  invoiceLineList = invoice.getInvoiceItems();
		  List<InvoiceLine> newInvoiceLineList = new ArrayList<>();
		  for(InvoiceLine invoiceLine : invoiceLineList) {
			  	BigDecimal netAmount = BigDecimal.ZERO;
			    BigDecimal grossAmount = BigDecimal.ZERO;
			    BigDecimal igst = BigDecimal.ZERO;
			    BigDecimal sgst = BigDecimal.ZERO;
			    BigDecimal cgst = BigDecimal.ZERO;
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
			      cgst = sgst;
			      grossAmount = netAmount.add(sgst).add(cgst);
			    }

			    invoiceLine.setNetAmount(netAmount);
			    invoiceLine.setIgst(igst);
			    invoiceLine.setSgst(sgst);
			    invoiceLine.setCgst(cgst);
			    invoiceLine.setGrossAmount(grossAmount);
			    newInvoiceLineList.add(invoiceLine);
		  }

		  invoice.setInvoiceItems(newInvoiceLineList);
		  setDetails(invoice);
	  }
	  
	  return invoice;
  }
}
