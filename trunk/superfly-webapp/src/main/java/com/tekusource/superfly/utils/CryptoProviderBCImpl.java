package com.tekusource.superfly.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.security.spec.KeySpec;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.annotation.ThreadSafe;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author kevin.o
 */
@ThreadSafe
@Service
public class CryptoProviderBCImpl implements CryptoProvider {

    private final Logger logger;
    private JceKeyTransRecipient keyRecipient;
    private X509Certificate publicCertificate;
    private SecretKeySpec macKey;
    private Mac mac;

    public CryptoProviderBCImpl() {
        logger = Logger.getLogger(this.getClass().getName());

        if (Security.getProvider("BC") == null) {
            logger.debug("Added BC Provider...");
            Security.addProvider(new BouncyCastleProvider());
        }

        //TODO if new key pair created then these should be updated!
        try {
            SaltKeyStoreProvider keyStoreProv = new SaltKeyStoreProvider();
            KeyStore keyStore = keyStoreProv.openKeyStore();
            PrivateKey prvKey = keyStoreProv.getPrivateKey(keyStore);
            publicCertificate = keyStoreProv.getPublicKey(keyStore);
            macKey = keyStoreProv.getHmacKey(keyStore);
            keyRecipient = new JceKeyTransEnvelopedRecipient(prvKey).setProvider(new BouncyCastleProvider());
            // Get an hmac Mac instance and initialize with the key
            mac = Mac.getInstance(AttacheConstants.PARAMETER_ALGORITHM_HMAC);
            mac.init(macKey);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public byte[] decrypt(InputStream cipherStream) throws AttacheException {
        CMSEnvelopedDataParser ep;
        byte[] bDecoded = null;
        try {
            ep = new CMSEnvelopedDataParser(cipherStream);
            RecipientInformationStore recipients = ep.getRecipientInfos();
            Collection c = recipients.getRecipients();
            Iterator it = c.iterator();
            logger.debug("recipients: " + c.size());

            // assuming only single recipient bind in the envelope
            if (it.hasNext()) {
                RecipientInformation recipient = (RecipientInformation) it.next();
                logger.debug("decrypting CMS envelope..");
                CMSTypedStream recData = recipient.getContentStream(keyRecipient);
                InputStream instream = recData.getContentStream();
                logger.debug("CMS env decrypted,encoding.. ");
                bDecoded = processDataStream(instream);
                logger.debug("CMS data encoded, " + bDecoded.length);
            }
        } catch (CMSException e) {
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_DECRYPTION_FAILED);
        } catch (IOException e) {
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_DECRYPTION_FAILED);
        }
        return bDecoded;

    }

    private byte[] processDataStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] bytes = new byte[8];
        int numBytes = 0;

        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        return os.toByteArray();
    }

    @Override
    public void generateKey(String alias, Map parameters) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setKeyAliasInUse(String alias) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteKey(String alias) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] decrypt(byte[] cipheredData) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] decrypt(byte[] cipheredData, Map parameters) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] encrypt(byte[] plainData) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] encrypt(byte[] plainData, Map parameters) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public byte[] getPublicKey() throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAvailableKeyAliases() throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAvailableKeyAliases(Map parameters) throws AttacheException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public X509Certificate getPublicKeyCertificate() {
        return publicCertificate;
    }

    @Override
    public Map<String, String> getPublicKeyCertificateInfo() throws AttacheException {
        Map publicKeyCertificateInfo = new HashMap();
        Base64 encoder = new Base64();
        //BASE64Encoder encoder = new BASE64Encoder();
        try {
            publicKeyCertificateInfo.put("pemFormat",
                    "\n-----BEGIN CERTIFICATE-----\n"
                    + Base64.toBase64String(publicCertificate.getEncoded()).trim()
                    + "\n-----END CERTIFICATE-----"
            );

            publicKeyCertificateInfo.put("issuer", publicCertificate.getIssuerX500Principal().getName().substring(3));
            publicKeyCertificateInfo.put("serialNumber", publicCertificate.getSerialNumber());
            publicKeyCertificateInfo.put("algorithm", publicCertificate.getSigAlgName());
            publicKeyCertificateInfo.put("type", publicCertificate.getType());
            publicKeyCertificateInfo.put("version", publicCertificate.getVersion());
            publicKeyCertificateInfo.put("startValidity", publicCertificate.getNotBefore());
            publicKeyCertificateInfo.put("endValidity", publicCertificate.getNotAfter());

        } catch (CertificateEncodingException e) {
            logger.error(e);
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_CERTIFICATE_ENCODING_FAILED);
        }

        return publicKeyCertificateInfo;
    }

    @Override
    public void createKSArtifacts(String keyStoreFolder, String pemFolder) throws AttacheException {
        SaltKeyStoreProvider keyStoreProv = new SaltKeyStoreProvider();
        keyStoreProv.createKSArtifacts(keyStoreFolder, pemFolder);
    }

    @Override
    public String getHmac(String value) {
        mac.reset();
        // Compute the hmac on input data bytes
        byte[] hmacBytes = mac.doFinal(value.getBytes());
        return Base64.toBase64String(hmacBytes);
    }

    public String encrypt(String plainText, SecretKey key) throws AttacheException {
        try {
            Cipher encryptor = Cipher.getInstance("AES");
            encryptor.init(Cipher.ENCRYPT_MODE, key);
            return Base64.toBase64String(encryptor.doFinal(plainText.getBytes()));
        } catch (Exception ex) {
            logger.error(ex);
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_ENCRYPTION_FAILED);
        }
    }

    public String decrypt(String cypheredText, SecretKey key) throws AttacheException {
        try {
            Cipher decryptor = Cipher.getInstance("AES");
            decryptor.init(Cipher.DECRYPT_MODE, key);
            return new String(decryptor.doFinal(Base64.decode(cypheredText)));
        } catch (Exception ex) {
            logger.error(ex);
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_DECRYPTION_FAILED);
        }
    }

    public SecretKey generateAESKey() throws AttacheException {
        SecretKey secretKey = null;
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128); // 192 and 256 bits may not be available

            // Generate the secret key
            SecretKey skey = kgen.generateKey();
            // Generate the secret key spec
            byte[] raw = skey.getEncoded();
            secretKey = new SecretKeySpec(raw, "AES");
        } catch (NoSuchAlgorithmException ex) {
            logger.error(ex);
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_KEY_GENERATION_FAILED);
        }
        return secretKey;
    }

    public SecretKey generateAESKey(String password, String salt) throws AttacheException {
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
            SecretKey tmp = factory.generateSecret(spec);
            return new SecretKeySpec(tmp.getEncoded(), "AES");
        } catch (Exception ex) {
            logger.error(ex);
            throw new AttacheException(AttacheConstants.ERROR_SECURITY_KEY_GENERATION_FAILED);
        }
    }

    public SecretKey getAESKey() throws AttacheException {
        return generateAESKey("SamplePassword", "SampleSalt");
    }
}
