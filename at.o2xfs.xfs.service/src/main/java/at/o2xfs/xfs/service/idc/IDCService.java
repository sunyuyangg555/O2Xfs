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

package at.o2xfs.xfs.service.idc;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.idc.DevicePosition;
import at.o2xfs.xfs.idc.IdcMessage;
import at.o2xfs.xfs.idc.ResetOut;
import at.o2xfs.xfs.idc.RetainBin;
import at.o2xfs.xfs.ptr.Lamp;
import at.o2xfs.xfs.v3_00.idc.CardAction3;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.v3_10.idc.PowerSaveChange310;
import at.o2xfs.xfs.win32.XfsWord;

public class IDCService extends XfsService<IDCServiceListener, IDCUserListener> {

    private final static Logger LOG = LoggerFactory.getLogger(IDCService.class);

    public IDCService(final String logicalName) {
        super(logicalName, XfsServiceClass.IDC);

    }

    private void fireMediaRemove() {
        for (IDCServiceListener listener : serviceListeners) {
            listener.onMediaRemove();
        }
    }

    private void fireCardActionEvent(final CardAction3 cardAction) {
        for (final IDCServiceListener listener : serviceListeners) {
            listener.onCardAction(cardAction);
        }
    }

    private void fireMediaDetected(final ResetOut resetOut) {
        for (final IDCServiceListener listener : serviceListeners) {
            listener.onMediaDetected(resetOut);
        }
    }

    private void fireDevicePosition(final DevicePosition devicePosition) {
        for (final IDCServiceListener listener : serviceListeners) {
            listener.onDevicePosition(devicePosition);
        }
    }

    private void firePowerSaveChange(final PowerSaveChange310 powerSaveChange310) {
        for (final IDCServiceListener listener : serviceListeners) {
            listener.onPowerSaveChange(powerSaveChange310);
        }
    }

    private void fireRetainBinRemoved() {
        for (final IDCServiceListener listener : serviceListeners) {
            listener.onRetainBinRemoved();
        }
    }


    @Override
    public void fireServiceEvent(final WFSResult wfsResult) {
        final String method = "fireServiceEvent(WFSResult)";
        if (LOG.isDebugEnabled()) {
            LOG.debug(method, "wfsResult=" + wfsResult);
        }
        final IdcMessage message = wfsResult.getEventID(IdcMessage.class);
        switch (message) {
            case SRVE_MEDIAREMOVED:
                fireMediaRemove();
                break;
            case SRVE_CARDACTION:
                fireCardActionEvent(create(wfsResult.getResults(), CardAction3.class));
                break;
            case SRVE_MEDIADETECTED:
                fireMediaDetected(new XfsWord<>(ResetOut.class, wfsResult.getResults()).get());
                break;
            case SRVE_DEVICEPOSITION:
                fireDevicePosition(new XfsWord<>(DevicePosition.class, wfsResult.getResults()).get());
                break;
            case SRVE_POWER_SAVE_CHANGE:
                firePowerSaveChange(create(wfsResult.getResults(), PowerSaveChange310.class));
                break;
            case SRVE_RETAINBINREMOVED:
                fireRetainBinRemoved();
                break;
            default:
                throw new IllegalArgumentException("IdcMessage: " + message);
        }
    }


    @Override
    public void fireUserEvent(final WFSResult wfsResult) {
        final String method = "fireUserEvent(WFSResult)";
        if (LOG.isDebugEnabled()) {
            LOG.debug(method, "wfsResult=" + wfsResult);
        }
        final IdcMessage message = wfsResult.getEventID(IdcMessage.class);
        switch (message) {
            case USRE_RETAINBINTHRESHOLD:
                fireRetainBinThreshold(new XfsWord<>(RetainBin.class, wfsResult.getResults()).get());
                break;
            default:
                throw new IllegalArgumentException("IdcMessage: " + message);
        }
    }

    private void fireRetainBinThreshold(RetainBin retainBin) {
        userListeners.stream().forEach(idcUserListener -> idcUserListener.onRetainBinThreshold(retainBin));
    }

}
