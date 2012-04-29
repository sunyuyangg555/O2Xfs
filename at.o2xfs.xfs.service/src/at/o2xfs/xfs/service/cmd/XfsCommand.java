/*
 * Copyright (c) 2012, Andreas Fagschlunger. All rights reserved.
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

package at.o2xfs.xfs.service.cmd;

import java.util.concurrent.Callable;

import org.apache.commons.lang.builder.ToStringBuilder;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.service.XfsService;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.events.XfsEventNotification;
import at.o2xfs.xfs.type.REQUESTID;

/**
 * @author Andreas Fagschlunger
 * 
 */
public abstract class XfsCommand implements Callable<WFSResult> {

	private XfsService xfsService = null;

	private REQUESTID requestID = null;

	public XfsCommand(final XfsService xfsService) {
		if (xfsService == null) {
			throw new IllegalArgumentException("xfsService must not be null");
		}
		this.xfsService = xfsService;
	}

	public void execute(final XfsEventNotification xfsEventNotification)
			throws XfsException {
		synchronized (this) {
			if (requestID != null) {
				throw new IllegalStateException("already executed");
			}
			requestID = XfsServiceManager.getInstance().execute(this,
					xfsEventNotification);
		}
	}

	public void cancel() throws XfsException {
		synchronized (this) {
			if (requestID != null) {
				XfsServiceManager.getInstance().cancel(xfsService, requestID);
			}
		}
	}

	/**
	 * @see java.util.concurrent.Callable#call()
	 */
	@Override
	public WFSResult call() throws XfsException {
		return new SynchronousExecution(this).execute();
	}

	/**
	 * @return the xfsDevice
	 */
	public XfsService getXFSService() {
		return xfsService;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("xfsService", xfsService)
				.append("requestID", requestID).toString();
	}
}
