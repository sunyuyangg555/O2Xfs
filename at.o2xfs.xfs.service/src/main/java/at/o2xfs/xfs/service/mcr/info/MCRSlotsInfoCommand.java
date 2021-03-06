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

package at.o2xfs.xfs.service.mcr.info;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.mcr.McrInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.mcr.MCRService;
import at.o2xfs.xfs.service.mcr.McrFactory;
import at.o2xfs.xfs.v3_10.mcr.SlotsInfo310;
import at.o2xfs.xfs.v3_10.mcr.Status310;

import java.util.concurrent.Callable;

/**
 * 获取外币包槽信息,返回本模块的所有存储槽信息。
 */
public class MCRSlotsInfoCommand implements Callable<SlotsInfo310> {

    private static final Logger LOG = LoggerFactory.getLogger(MCRSlotsInfoCommand.class);

    private MCRService mcrService = null;

    public MCRSlotsInfoCommand(final MCRService mcrService) {
        this.mcrService = mcrService;
    }

    @Override
    public SlotsInfo310 call() throws XfsException {
        SlotsInfo310 result;
        XfsInfoCommand<McrInfoCommand> command = new XfsInfoCommand<>(mcrService, McrInfoCommand.SLOTS_INFO);
        WFSResult wfsResult = null;
        try {
            wfsResult = command.call();
            result = McrFactory.create(mcrService.getXfsVersion(), wfsResult.getResults(), SlotsInfo310.class);
            if (LOG.isInfoEnabled()) {
                LOG.info("call()", result);
            }
            return result;
        } finally {
            if (wfsResult != null) {
                XfsServiceManager.getInstance().free(wfsResult);
            }
        }
    }
}
