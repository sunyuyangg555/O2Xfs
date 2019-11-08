package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINRSASignatureAlgorithm;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.win32.XfsDWord;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ExportRSAIssuerSignedItemOutput3 extends XfsStruct {

    private Pointer value = new Pointer();
    private XfsDWord<PINRSASignatureAlgorithm> signatureAlgorithm = new XfsDWord<>(PINRSASignatureAlgorithm.class);
    private Pointer signature = new Pointer();

    private ExportRSAIssuerSignedItemOutput3() {
        add(value)
                .add(signatureAlgorithm)
                .add(signature);
    }

    public ExportRSAIssuerSignedItemOutput3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public void setValue(byte[] value) {
        this.value.pointTo(new WfsXData(value));
    }

    public byte[] getValue() {
        byte[] result = null;
        if (!Pointer.NULL.equals(value)) {
            result = new WfsXData(value).getData();
        }
        return result;
    }

    public PINRSASignatureAlgorithm getSignatureAlgorithm() {
        return signatureAlgorithm.get();
    }

    public void setSignatureAlgorithm(PINRSASignatureAlgorithm signatureAlgorithm) {
        this.signatureAlgorithm.set(signatureAlgorithm);
    }

    public byte[] getSignature() {
        byte[] result = null;
        if (!Pointer.NULL.equals(signature)) {
            result = new WfsXData(signature).getData();
        }
        return result;
    }

    public void setSignature(byte[] signature) {
        this.signature.pointTo(new WfsXData(signature));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("signature", javax.xml.bind.DatatypeConverter.printHexBinary(getSignature()))
                .append("value", javax.xml.bind.DatatypeConverter.printHexBinary(getValue()))
                .append("signatureAlgorithm", getSignatureAlgorithm())
                .toString();
    }
}
