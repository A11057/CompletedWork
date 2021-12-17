package com.amica.billing.parse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import com.amica.billing.Customer;
import com.amica.billing.Invoice;
import com.amica.billing.Terms;
import lombok.extern.java.Log;

/**
 * A parser that can read a CSV format with certain expected columns.
 *
 * @author Will Provost
 */
@Log
public class CSVParser implements Parser{

	private static final int CUSTOMER_COLUMNS = 3;
	private static final int CUSTOMER_FIRST_NAME_COLUMN = 0;
	private static final int CUSTOMER_LAST_NAME_COLUMN = 1;
	private static final int CUSTOMER_TERMS_COLUMN = 2;

	private static final int INVOICE_MIN_COLUMNS = 5;
	private static final int INVOICE_NUMBER_COLUMN = 0;
	private static final int INVOICE_FIRST_NAME_COLUMN = 1;
	private static final int INVOICE_LAST_NAME_COLUMN = 2;
	private static final int INVOICE_AMOUNT_COLUMN = 3;
	private static final int INVOICE_DATE_COLUMN = 4;
	private static final int INVOICE_PAID_DATE_COLUMN = 5;

	private Customer customer;
	private Terms terms;
	private Invoice invoice;

	/**
	 * Helper that can parse one line of comma-separated text in order to
	 * produce a {@link Customer} object.
	 */
	private Customer parseCustomer(String line) {
		String[] fields = line.split(",");   //this takes a string a spits out a customer
		if (fields.length == CUSTOMER_COLUMNS) {
			try {
				String firstName = fields[CUSTOMER_FIRST_NAME_COLUMN];
				String lastName = fields[CUSTOMER_LAST_NAME_COLUMN];
				String termsString = fields[CUSTOMER_TERMS_COLUMN];

				//TODO convert the terms string to an enum  ->  use suit/spot as example
				//Terms.valueOf(termsString);
				switch(termsString) {
					case "CASH":
						terms = Terms.CASH;
						break;
					case "30":
						terms = Terms.CREDIT_30;
						break;
					case "45":
						terms = Terms.CREDIT_45;
						break;
					case "60":
						terms = Terms.CREDIT_60;
						break;
					case "90":
						terms = Terms.CREDIT_90;
						break;
					default:
						terms = null;
				}

				//TODO create a customer object and return it
				customer = new Customer(firstName, lastName, terms);

			} catch (Exception ex) {
				log.warning(() ->
						"Couldn't parse terms value, skipping customer: "+ line);
			}
		} else {
			log.warning(() ->
					"Incorrect number of fields, skipping customer: " + line);
		}
		return customer;
	}

	/**
	 * Helper that can parse one line of comma-separated text in order to
	 * produce an {@link Invoice} object.
	 */
	private Invoice parseInvoice(String line, Map<String, Customer> customers) {
		DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String[] fields = line.split(",");
		if (fields.length >= INVOICE_MIN_COLUMNS) {
			try {
				int number = Integer.parseInt(fields[INVOICE_NUMBER_COLUMN]);
				String first = fields[INVOICE_FIRST_NAME_COLUMN];
				String last = fields[INVOICE_LAST_NAME_COLUMN];
				double amount = Double.parseDouble
						(fields[INVOICE_AMOUNT_COLUMN]);

				LocalDate date = LocalDate.parse(fields[INVOICE_DATE_COLUMN], parser);
				Optional<LocalDate> paidDate = fields.length > INVOICE_PAID_DATE_COLUMN
						? Optional.of(LocalDate.parse(fields[INVOICE_PAID_DATE_COLUMN], parser))
						: Optional.empty();

				//TODO find the corresponding customer in the map
				Customer customerFound = customers.get(first + " " + last);  //.get gets the corresponding key in customers

				//TODO create an invoice and return it
				invoice = new Invoice(number, amount, date);
				if (paidDate != null) {
					invoice.paidDate(paidDate);
				}
				if (customerFound != null) {
					invoice.assignInvoice(customerFound);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
				log.warning(() ->
						"Couldn't parse values, skipping invoice: " + line);
			}
		} else {
			log.warning(() ->
					"Incorrect number of fields, skipping invoice: " + line);
		}
		return invoice;
	}

	/**
	 * Helper to write a CSV representation of one customer.
	 */
	public String formatCustomer(Customer customer) {
		//TODO provide the values to be formatted
		return String.format("%s,%s,%s", customer.getFirstName(), customer.getLastName(), customer.getTerms());
	}

	/**
	 * Helper to write a CSV representation of one invoice.
	 */
	public String formatInvoice(Invoice invoice) {
		//TODO provide the values to be formatted
		return String.format("%d,%s,%s,%.2f,%s%s",
				invoice.getNumber(), invoice.getCustomer().getFirstName(), invoice.getCustomer().getLastName(), invoice.getAmount(),
				invoice.getInvoiceDate(), invoice.getPaidDate().isPresent()?invoice.getPaidDate():"");
	}

	@Override
	public Stream<Customer> parseCustomers(Stream<String> customerLines) { //use stream using map method, we already have customerLines which has what we want
		//customerLines is a stream of strings so we just need to map it
		return customerLines.map(line -> parseCustomer(line));
	}

	@Override
	public Stream<Invoice> parseInvoices(Stream<String> invoiceLines, Map<String, Customer> customers) {
		return invoiceLines.map(line -> parseInvoice(line, customers));
	}

	@Override
	public Stream<String> produceCustomers(Stream<Customer> customers) {
		return customers.map(line -> formatCustomer(line));
	}

	@Override
	public Stream<String> produceInvoices(Stream<Invoice> invoices) {
		return invoices.map(line -> formatInvoice(line));
	}
}
