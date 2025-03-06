package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> paymentData;

    void setUp(){
        this.paymentData = new HashMap<>;

    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER", this.paymentData);
        assertEquals("13652556-012a-4c07-b546-54eb1396d79b", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData, "SUCCESS");
        assertEquals("id-dummy", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows (IllegalArgumentException.class, () -> {
            Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData, "INVALID");
        });
    }

    @Test
    void testSetPaymentToDataEmpty() {
        Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData);
        this.paymentData.clear();
        assertThrows (IllegalArgumentException.class, () -> {
            payment.setPaymentData(this.paymentData);
        });
    }

    @Test
    void testSetPaymentDataToStatusSuccess() {
        Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData);
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

}
