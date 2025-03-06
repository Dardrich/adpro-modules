package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp(){
        this.paymentData = new HashMap<>();

    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData);
        assertEquals("id-dummy", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertSame(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("id-dummy", "VOUCHER", this.paymentData, "SUCCESS");
        assertEquals("id-dummy", payment.getId());
        assertEquals("VOUCHER", payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
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
        this.paymentData.put("voucherCode", "code-dummy");
        payment.setPaymentData(this.paymentData);
        assertSame(this.paymentData, payment.getPaymentData());
    }

}
