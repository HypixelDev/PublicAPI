package net.hypixel.api.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

// TODO replace this because this is really really insecure
@Deprecated
public class TrustingX509Manager implements X509TrustManager {
    private static TrustingX509Manager instance = new TrustingX509Manager();

    public static TrustingX509Manager getInstance() {
        return instance;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
