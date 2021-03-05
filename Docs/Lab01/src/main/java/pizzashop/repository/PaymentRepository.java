package pizzashop.repository;

import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class PaymentRepository {
	private static String filename = "data/payments.txt";
	private List<Payment> paymentList;

	public PaymentRepository() {
		this.paymentList = new ArrayList<>();
		readPayments();
	}

	private void readPayments() {
		URL resource = getClass().getClassLoader().getResource(filename);
		try {
			Files.readAllLines(Paths.get(resource.toURI())).forEach((line -> paymentList.add(getPayment(line))));
		} catch (IOException | URISyntaxException e) {
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

		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			for (Payment p : paymentList) {
				System.out.println(p.toString());
				bw.write(p.toString());
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Payment> getAll() {
		return paymentList;
	}

}
