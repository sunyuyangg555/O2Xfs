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
import at.o2xfs.xfs.idc.IdcInfoCommand;
import at.o2xfs.xfs.mcr.McrInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;

import at.o2xfs.xfs.service.mcr.MCRService;
import at.o2xfs.xfs.service.mcr.McrFactory;
import at.o2xfs.xfs.v3_10.mcr.Capabilities310;

import java.util.concurrent.Callable;

/**
 * 获取当前设备的性能信息
 */
public class MCRCapabilitiesCommand implements Callable<Capabilities310> {

	private final Logger LOG = LoggerFactory.getLogger(MCRCapabilitiesCommand.class);

	private MCRService mcrService = null;

	public MCRCapabilitiesCommand(final MCRService mcrService) {
		this.mcrService = mcrService;
	}

	@Override
	public Capabilities310 call() throws XfsException {
		Capabilities310 result;
		final XfsCommand xfsCommand = new XfsInfoCommand<>(mcrService, McrInfoCommand.CAPABILITIES);
		WFSResult wfsResult = null;
		try {
			wfsResult = xfsCommand.call();
			result = McrFactory.create(mcrService.getXfsVersion(), wfsResult.getResults(), Capabilities310.class);
			if (LOG.isInfoEnabled()) {
				LOG.info("call()", result);
			}
		} finally {
			if (wfsResult != null) {
				XfsServiceManager.getInstance().free(wfsResult);
			}
		}
		return result;
	}
}
