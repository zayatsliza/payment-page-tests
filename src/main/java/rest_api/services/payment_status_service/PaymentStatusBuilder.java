package rest_api.services.payment_status_service;

import java.util.Map;

public class PaymentStatusBuilder {
    private String targetUrl;
    private String publicKey;
    private String secretKey;

    private String orderId;

    public PaymentStatusBuilder(String url, String pbKey, String sbKey) {
        this.targetUrl = url;
        this.publicKey = pbKey;
        this.secretKey = sbKey;
    }

    public PaymentStatusBuilder withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Map<String, String> build() {
        return new FetchPaymentStatusReq(targetUrl, publicKey, secretKey)
                .fetchPaymentStatus(orderId);
    }
}
