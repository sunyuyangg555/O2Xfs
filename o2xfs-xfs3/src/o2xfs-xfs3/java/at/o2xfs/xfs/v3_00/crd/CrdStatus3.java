/*
 * Copyright (c) 2018, Andreas Fagschlunger. All rights reserved.
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

package at.o2xfs.xfs.v3_00.crd;

import at.o2xfs.win32.Pointer;
import at.o2xfs.win32.Struct;
import at.o2xfs.xfs.crd.*;

import at.o2xfs.xfs.win32.XfsMultiByteMap;
import at.o2xfs.xfs.win32.XfsWord;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 *
 * typedef struct _wfs_crd_status
 * {
 *  WORD fwDevice;
 *  WORD fwDispenser;
 *  WORD fwTransport;
 *  WORD fwMedia;
 *  WORD fwShutter;
 *  LPSTR lpszExtra;
 *  DWORD dwGuidLights[WFS_CRD_GUIDLIGHTS_MAX];
 *  WORD wDevicePosition;
 *  USHORT usPowerSaveRecoveryTime;
 * } WFSCRDSTATUS, *LPWFSCRDSTATUS;
 *
 */
public class CrdStatus3 extends Struct {

    protected final XfsWord<CrdDeviceState> device = new XfsWord<>(CrdDeviceState.class);
    protected final XfsWord<Dispenser> dispenser = new XfsWord<>(Dispenser.class);
    protected final XfsWord<Transport> transport = new XfsWord<>(Transport.class);
    protected final XfsWord<MediaPosition> media = new XfsWord<>(MediaPosition.class);
    protected final XfsWord<Shutter> shutter = new XfsWord<>(Shutter.class);
    protected final XfsMultiByteMap extra = new XfsMultiByteMap();


    public CrdDeviceState getDevice() {
        return device.get();
    }

    public Dispenser getDispenser() {
        return dispenser.get();
    }

    public Transport getTransport() {
        return transport.get();
    }

    public MediaPosition getMediaPosition() {
        return media.get();
    }

    public Shutter getShutter() {
        return shutter.get();
    }

    public Map<String, String> getExtra() {
        return extra.get();
    }



    protected CrdStatus3() {
        add(device);
        add(dispenser);
        add(transport);
        add(media);
        add(shutter);
        add(extra);
    }

    public CrdStatus3(Pointer p) {
        this();
        assignBuffer(p);
    }

    public CrdStatus3(CrdStatus3 copy) {
        this();
        allocate();
        set(copy);
    }

    protected void set(CrdStatus3 copy) {
        device.set(copy.getDevice());
        dispenser.set(copy.getDispenser());
        transport.set(copy.getTransport());
        media.set(copy.getMediaPosition());
        shutter.set(copy.getShutter());
        extra.set(copy.getExtra());
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(getDevice())
                .append(getDispenser())
                .append(getTransport())
                .append(getMediaPosition())
                .append(getShutter())
                .append(getExtra())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CrdStatus3) {
            CrdStatus3 crdStatus3 = (CrdStatus3) obj;
            return new EqualsBuilder()
                    .append(getDevice(), crdStatus3.getDevice())
                    .append(getDispenser(), crdStatus3.getDispenser())
                    .append(getTransport(), crdStatus3.getTransport())
                    .append(getMediaPosition(), crdStatus3.getMediaPosition())
                    .append(getShutter(), crdStatus3.getShutter())
                    .append(getExtra(), crdStatus3.getExtra())
                    .isEquals();
        }
        return false;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("device", getDevice())
                .append("dispenser", getDispenser())
                .append("transport", getTransport())
                .append("media", getMediaPosition())
                .append("shutter", getShutter())
                .append("extra", getExtra())
                .toString();
    }
}
