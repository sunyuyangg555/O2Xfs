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

package at.o2xfs.xfs.service.mcr;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsServiceClass;
import at.o2xfs.xfs.mcr.RetainBin;
import at.o2xfs.xfs.mcr.McrMessage;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.win32.XfsWord;

public class MCRService extends XfsService<MCRServiceListener, MCRUserListener> {

    private final static Logger LOG = LoggerFactory.getLogger(MCRService.class);

    public MCRService(final String logicalName) {
        super(logicalName, XfsServiceClass.IDC);

    }

    private void fireMediaRemove() {
        for (MCRServiceListener listener : serviceListeners) {
            listener.onMediaRemove();
        }
    }


    @Override
    public void fireServiceEvent(final WFSResult wfsResult) {
        final String method = "fireServiceEvent(WFSResult)";
        if (LOG.isDebugEnabled()) {
            LOG.debug(method, "wfsResult=" + wfsResult);
        }
        final McrMessage message = wfsResult.getEventID(McrMessage.class);
        switch (message) {
            case SRVE_MEDIAREMOVED:
                fireMediaRemove();
                break;
            default:
                throw new IllegalArgumentException("McrMessage: " + message);
        }
    }


    @Override
    public void fireUserEvent(final WFSResult wfsResult) {
        final String method = "fireUserEvent(WFSResult)";
        if (LOG.isDebugEnabled()) {
            LOG.debug(method, "wfsResult=" + wfsResult);
        }
        final McrMessage message = wfsResult.getEventID(McrMessage.class);
        switch (message) {
            case USER_RETAINBINTHRESHOLD:
                fireRetainBinThreshold(new XfsWord<>(RetainBin.class, wfsResult.getResults()).get());
                break;
            default:
                throw new IllegalArgumentException("McrMessage: " + message);
        }
    }

    private void fireRetainBinThreshold(RetainBin retainBin) {
        userListeners.stream().forEach(mcrUserListener -> mcrUserListener.onRetainBinThreshold(retainBin));
    }

}
