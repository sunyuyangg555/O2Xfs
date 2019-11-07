/*
 * Copyright (c) 2017, Andreas Fagschlunger. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package at.o2xfs.xfs.v3_00.pin;

import at.o2xfs.win32.BYTE;
import at.o2xfs.win32.ByteArray;
import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.XfsStruct;
import at.o2xfs.xfs.pin.PINAlgorithm;
import at.o2xfs.xfs.pin.PinMode;
import at.o2xfs.xfs.pin.WfsXData;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static at.o2xfs.xfs.pin.PinMode.ENCRYPT;

public class Crypt3 extends XfsStruct {

    public static class Builder {
        private PinMode pinMode = ENCRYPT;
        private String key = "";
        private byte[] encKey = new byte[0];
        private PINAlgorithm algorithm;
        private String startValueKey = "";
        private byte[] startValue = new byte[0];
        private Byte padding = new Byte("0");
        private Byte compression = new Byte("0");;
        private byte[] cryptData = new byte[0];

        public Builder(String key) {
            this.key = key;
        }

        public Builder cryptData(byte[] cryptData) {
            this.cryptData = cryptData;
            return this;
        }

        public Builder compression(Byte compression) {
            this.compression = compression;
            return this;
        }

        public Builder padding(Byte padding) {
            this.padding = padding;
            return this;
        }

        public Builder startValue(byte[] startValue) {
            this.startValue = startValue;
            return this;
        }

        public Builder startValueKey(String startValueKey) {
            this.startValueKey = startValueKey;
            return this;
        }

        public Builder algorithm(PINAlgorithm algorithm) {
            this.algorithm = algorithm;
            return this;
        }

        public Builder encKey(byte[] encKey) {
            this.encKey = encKey;
            return this;
        }

        public Builder pinMode(PinMode pinMode) {
            this.pinMode = pinMode;
            return this;
        }

        public Crypt3 build() {
            return new Crypt3(this);
        }
    }

    private final XfsWord<PinMode> mode = new XfsWord<PinMode>(PinMode.class);
    private final LPSTR key = new LPSTR();
    private final Pointer keyEncKey = new Pointer();
    private final XfsWord<PINAlgorithm> algorithm = new XfsWord<PINAlgorithm>(PINAlgorithm.class);
    private final LPSTR startValueKey = new LPSTR();
    private final Pointer startValue = new Pointer();
    private final BYTE padding = new BYTE();
    private final BYTE compression = new BYTE();
    private final Pointer cryptData = new Pointer();

    protected Crypt3() {
        add(mode).add(key)
                .add(keyEncKey)
                .add(algorithm)
                .add(startValueKey)
                .add(startValue)
                .add(padding)
                .add(compression)
                .add(cryptData);
    }

    public Crypt3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public Crypt3(Builder builder) {
        this();
        allocate();
        mode.set(builder.pinMode);
        key.set(builder.key);
        keyEncKey.pointTo(new WfsXData(builder.encKey));
        algorithm.set(builder.algorithm);
        startValueKey.set(builder.startValueKey);
        startValue.pointTo(new WfsXData(builder.startValue));
        padding.set(builder.padding);
        compression.set(builder.compression);
        cryptData.pointTo(new WfsXData(builder.cryptData));
    }

    public PinMode getMode() {
        return mode.get();
    }

    public void setMode(PinMode mode) {
        this.mode.set(mode);
    }

    public String getKey() {
        return key.toString();
    }

    public void setKey(String key) {
        this.key.put(key);
    }

    public WfsXData getKeyEncKey() {
        WfsXData result = null;
        if (!Pointer.NULL.equals(keyEncKey)) {
            result = new WfsXData(keyEncKey);
        }
        return result;
    }

    public void setKeyEncKey(byte[] keyEncKey) {
        this.keyEncKey.pointTo(new WfsXData(keyEncKey));
    }

    public XfsWord<PINAlgorithm> getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(PINAlgorithm algorithm) {
        this.algorithm.set(algorithm);
    }

    public String getStartValueKey() {
        return startValueKey.toString();
    }

    public void setStartValueKey(String startValueKey) {
        this.startValueKey.set(startValueKey);
    }

    public byte[] getStartValue() {
        byte[] result = null;
        if (!Pointer.NULL.equals(startValue)) {
            result = new WfsXData(startValue).getData();
        }
        return result;
    }

    public void setStartValue(byte[] startValue) {
        this.startValue.pointTo(new WfsXData(startValue));
    }

    public byte getPadding() {
        return padding.byteValue();
    }

    public void setPadding(byte padding) {
        this.padding.set(padding);
    }

    public byte getCompression() {
        return compression.byteValue();
    }

    public void setCompression(byte compression) {
        this.compression.set(compression);
    }

    public byte[] getCryptData() {
        byte[] result = null;
        if (!Pointer.NULL.equals(cryptData)) {
            result = new WfsXData(cryptData).getData();
        }
        return result;
    }

    public void setCryptData(byte[] cryptData) {
        this.cryptData.pointTo(new WfsXData(cryptData));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mode", getMode())
                .append("key", getKey())
                .append("keyEncKey", javax.xml.bind.DatatypeConverter.printHexBinary(getKeyEncKey().getData()))
                .append("algorithm", getAlgorithm())
                .append("startValueKey", getStartValueKey())
                .append("startValue", javax.xml.bind.DatatypeConverter.printHexBinary(getStartValue()))
                .append("padding", getPadding())
                .append("compression", getCompression())
                .append("cryptData", javax.xml.bind.DatatypeConverter.printHexBinary(getCryptData()))
                .toString();
    }
}