package at.o2xfs.xfs.service.crd.info;

import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.crd.CrdInfoCommand;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.crd.CrdFactory;
import at.o2xfs.xfs.service.crd.CrdService;
import at.o2xfs.xfs.v3_00.crd.*;

import java.util.concurrent.Callable;

public final class CapabilitiesCommand implements Callable<CrdCapabilities3> {

	private final CrdService crdService;

	public CapabilitiesCommand(CrdService crdService) {
		this.crdService = crdService;
	}

	@Override
	public CrdCapabilities3 call() throws XfsException {
		CrdCapabilities3 result;
		XfsInfoCommand<CrdInfoCommand> command = new XfsInfoCommand<CrdInfoCommand>(crdService, CrdInfoCommand.CAPABILITIES);
		WFSResult wfsResult = null;
		try {
			wfsResult = command.call();
			result = CrdFactory.create(crdService.getXfsVersion(), wfsResult.getResults(), CrdCapabilities3.class);
			return result;
		} finally {
			if (wfsResult != null) {
				XfsServiceManager.getInstance().free(wfsResult);
			}
		}
	}

}
