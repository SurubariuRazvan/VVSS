package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
	private final List<Payment> paymentList;
	private final String filename;
	
	public PaymentRepository() {
		this("data/payments.txt");
	}
	
	public PaymentRepository(String filename) {
		this.paymentList = new ArrayList<>();
		this.filename = filename;
		readPayments(filename);
	}
	
	private void readPayments(String filename) {
		ClassLoader classLoader = PaymentRepository.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while((line = br.readLine()) != null) {
				Payment payment = getPayment(line);
				paymentList.add(payment);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Payment getPayment(String line) {
		Payment item = null;
		if (line == null || line.equals(""))
			return null;
		StringTokenizer st = new StringTokenizer(line, ",");
		int tableNumber = Integer.parseInt(st.nextToken());
		String type = st.nextToken();
		double amount = Double.parseDouble(st.nextToken());
		item = new Payment(tableNumber, PaymentType.valueOf(type), amount);
		return item;
	}
	
	public void add(Payment payment) {
		paymentList.add(payment);
		writeAll();
	}
	
	public void writeAll() {
		ClassLoader classLoader = PaymentRepository.class.getClassLoader();
		File file = new File(classLoader.getResource(filename).getFile());
		
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
			for (Payment p : paymentList) {
				System.out.println(p.toString());
				bw.write(p.toString());
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public List<Payment> getAll() {
		return paymentList;
	}
	
}
