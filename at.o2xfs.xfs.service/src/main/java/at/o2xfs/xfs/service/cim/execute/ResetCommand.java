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

package at.o2xfs.xfs.service.cim.execute;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.cdm.CdmExecuteCommand;
import at.o2xfs.xfs.cdm.CdmMessage;
import at.o2xfs.xfs.cim.CimExecuteCommand;
import at.o2xfs.xfs.cim.CimMessage;
import at.o2xfs.xfs.service.ReflectiveFactory;
import at.o2xfs.xfs.service.XfsServiceManager;

import at.o2xfs.xfs.service.cim.CimFactory;
import at.o2xfs.xfs.service.cim.CimService;

import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.v3_00.cim.CashUnitError3;
import at.o2xfs.xfs.v3_00.cim.ItemPosition3;
import at.o2xfs.xfs.v3_00.cim.P6Info3;
import at.o2xfs.xfs.v3_10.cim.ItemInfoSummary310;

import java.util.List;
import java.util.Optional;

public class ResetCommand extends AbstractAsyncXfsCommand<ResetListener, SuccessEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(ResetCommand.class);

	private final CimService service;

	private final Optional<ItemPosition3> itemPosition;

	public ResetCommand(CimService service) {
		this(service, null);
	}

	public ResetCommand(CimService service, ItemPosition3 itemPosition) {
		this.service = service;
		this.itemPosition = Optional.ofNullable(itemPosition);
	}

	@Override
	protected XfsCommand createCommand() {
		return new XfsExecuteCommand<CimExecuteCommand>(service, CimExecuteCommand.RESET, itemPosition.orElse(null));
	}

	@Override
	public void fireIntermediateEvent(WFSResult wfsResult) {
		String method = "fireIntermediateEvent(WFSResult)";
		if (LOG.isDebugEnabled()) {
			LOG.debug(method, "wfsResult=" + wfsResult);
		}
		try {
			CimMessage message = wfsResult.getEventID(CimMessage.class);
			switch (message) {
				case EXEE_CASHUNITERROR:
					fireCashUnitError(ReflectiveFactory.create(service.getXfsVersion(), wfsResult.getResults(), CashUnitError3.class));
					break;
				case EXEE_INPUT_P6:
					fireInputP6(CimFactory.fromNullTerminatedArray(service.getXfsVersion(), wfsResult.getResults(),
							P6Info3.class));
					break;
				case EXEE_INFO_AVAILABLE:
					fireInfoAvailable(CimFactory.fromNullTerminatedArray(service.getXfsVersion(), wfsResult.getResults(),
							ItemInfoSummary310.class));
					break;
				default:
					throw new IllegalArgumentException("CimMessage: " + message);
			}
		} finally {
			XfsServiceManager.getInstance().free(wfsResult);
		}
	}

	private void fireCashUnitError(CashUnitError3 cashUnitError) {
		String method = "fireCashUnitError(CashUnitError3)";
		if (LOG.isInfoEnabled()) {
			LOG.info(method, cashUnitError);
		}
		for ( ResetListener each : listeners) {
			each.onCashUnitError(cashUnitError);
		}
	}

	private void fireInputP6(List<P6Info3> p6Infos) {
		String method = "fireInputP6()";
		if (LOG.isInfoEnabled()) {
			LOG.info(method, "");
		}
		for (ResetListener each : listeners) {
			each.onInputP6(p6Infos);
		}
	}

	private void fireInfoAvailable(List<ItemInfoSummary310> itemInfoSummaries) {
		String method = "fireInfoAvailable(ItemInfoSummary330)";
		if (LOG.isInfoEnabled()) {
			LOG.info(method, itemInfoSummaries);
		}
		for (ResetListener each : listeners) {
			each.onInfoAvailable(itemInfoSummaries);
		}
	}


	@Override
	protected SuccessEvent createCompleteEvent(Pointer results) {
		return SuccessEvent.build();
	}
}
