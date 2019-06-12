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

package at.o2xfs.xfs.service.ptr;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.ptr.Ink;
import at.o2xfs.xfs.ptr.Lamp;
import at.o2xfs.xfs.ptr.PtrMessage;
import at.o2xfs.xfs.ptr.Toner;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.v3_00.ptr.BinThreshold3;
import at.o2xfs.xfs.v3_00.ptr.MediaDetected3;
import at.o2xfs.xfs.v3_00.ptr.PaperThreshold3;
import at.o2xfs.xfs.v3_10.ptr.*;
import at.o2xfs.xfs.win32.XfsWord;

public final class PTRService extends XfsService<PtrServiceListener, PtrUserListener> {

    private static final Logger LOG = LoggerFactory.getLogger(PTRService.class);

    public PTRService(final String logicalName) {
        super(logicalName, XfsServiceClass.PTR);
    }

    @Override
    public void fireServiceEvent(final WFSResult wfsResult) {
        final String method = "fireServiceEvent(WFSResult)";
        if (LOG.isInfoEnabled()) {
            LOG.info(method, wfsResult);
        }
        PtrMessage message = wfsResult.getEventID(PtrMessage.class);
        switch (message) {
            case SRVE_PTR_MEDIATAKEN:
                fireMediaTaken();
                break;
            case SRVE_PTR_MEDIAINSERTED:
                fireMediaInserted();
                break;
            case SRVE_PTR_MEDIADETECTED:
                fireMediaDetected(create(wfsResult.getResults(), MediaDetected3.class));
                break;
            case SRVE_PTR_RETRACTBINSTATUS:
                fireRetractBinStatus(create(wfsResult.getResults(), BinThreshold3.class));
                break;
            case SRVE_PTR_DEFINITIONLOADED:
                fireDefinitionLoaded(create(wfsResult.getResults(), DefinitionLoaded310.class));
                break;
            case SRVE_PTR_MEDIAPRESENTED:
                fireMediaPresented(create(wfsResult.getResults(), MediaPresented310.class));
                break;
            case SRVE_PTR_MEDIAAUTORETRACTED:
                fireMediaAutoRetracted(create(wfsResult.getResults(), MediaRetracted310.class));
                break;
            case SRVE_PTR_DEVICEPOSITION:
                fireDevicePosition(create(wfsResult.getResults(), DevicePosition310.class));
                break;
            case SRVE_PTR_POWER_SAVE_CHANGE:
                firePowerSaveChange(create(wfsResult.getResults(), PowerSaveChange310.class));
                break;
            default:
                throw new IllegalArgumentException("PtrMessage: " + message);
        }
    }

    void fireMediaTaken() {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onMediaTaken());
    }


    void fireMediaInserted() {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onMediaInserted());
    }

    void fireMediaDetected(MediaDetected3 mediaDetected3) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onMediaDetected(mediaDetected3));
    }


    void fireRetractBinStatus(BinThreshold3 binThreshold3) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onRetractBinStatus(binThreshold3));
    }

    void fireDefinitionLoaded(DefinitionLoaded310 definitionLoaded310) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onDefinitionLoaded(definitionLoaded310));
    }


    void fireMediaPresented(MediaPresented310 mediaPresented310) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onMediaPresented(mediaPresented310));
    }

    void fireMediaAutoRetracted(MediaRetracted310 mediaRetracted310) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onMediaAutoRetracted(mediaRetracted310));
    }

    void fireDevicePosition(DevicePosition310 devicePosition310) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onDevicePosition(devicePosition310));
    }

    void firePowerSaveChange(PowerSaveChange310 powerSaveChange310) {
        serviceListeners.stream().forEach(ptrServiceListener -> ptrServiceListener.onPowerSaveChange(powerSaveChange310));
    }

    @Override
    public void fireUserEvent(final WFSResult wfsResult) {
        final String method = "fireUserEvent(WFSResult)";
        if (LOG.isInfoEnabled()) {
            LOG.info(method, wfsResult);
        }
        PtrMessage message = wfsResult.getEventID(PtrMessage.class);
        switch (message) {
            case USRE_PTR_TONERTHRESHOLD:
                fireTonerThreshold(new XfsWord<>(Toner.class, wfsResult.getResults()).get());
                break;
            case USRE_PTR_RETRACTBINTHRESHOLD:
                fireRetractBinThreshold(create(wfsResult.getResults(), BinThreshold3.class));
                break;
            case USRE_PTR_INKTHRESHOLD:
                fireInkThreshold(new XfsWord<>(Ink.class, wfsResult.getResults()).get());
                break;
            case USRE_PTR_PAPERTHRESHOLD:
                firePaperThreshold(create(wfsResult.getResults(), PaperThreshold3.class));
                break;
            case USRE_PTR_LAMPTHRESHOLD:
                fireLampThreshold(new XfsWord<>(Lamp.class, wfsResult.getResults()).get());
                break;
            default:
                throw new IllegalArgumentException("PtrMessage: " + message);
        }
    }

    private void fireRetractBinThreshold(BinThreshold3 binThreshold3) {
        userListeners.stream().forEach(ptrUserListener -> ptrUserListener.onRetractBinThreshold(binThreshold3));
    }

    void firePaperThreshold(PaperThreshold3 paperThreshold3) {
        userListeners.stream().forEach(ptrUserListener -> ptrUserListener.onPaperThreshold(paperThreshold3));
    }

    void fireTonerThreshold(Toner toner) {
        userListeners.stream().forEach(ptrUserListener -> ptrUserListener.onTonerThreshold(toner));

    }

    void fireLampThreshold(Lamp lamp) {
        userListeners.stream().forEach(ptrUserListener -> ptrUserListener.onLampThreshold(lamp));

    }

    void fireInkThreshold(Ink ink) {
        userListeners.stream().forEach(ptrUserListener -> ptrUserListener.onInkThreshold(ink));
    }
}