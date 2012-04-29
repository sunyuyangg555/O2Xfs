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

package at.o2xfs.xfs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.DWORD;
import at.o2xfs.win32.HWND;
import at.o2xfs.win32.ZSTR;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.WFSStartUpException;
import at.o2xfs.xfs.WFSVersion;
import at.o2xfs.xfs.XFSVersionDWORD;
import at.o2xfs.xfs.XfsAPI;
import at.o2xfs.xfs.XfsEventClass;
import at.o2xfs.xfs.XfsException;
import at.o2xfs.xfs.XfsMessage;
import at.o2xfs.xfs.XfsVersion;
import at.o2xfs.xfs.service.cmd.XfsCloseCommand;
import at.o2xfs.xfs.service.cmd.XfsDeRegisterCommand;
import at.o2xfs.xfs.service.cmd.XfsOpenCommand;
import at.o2xfs.xfs.service.cmd.XfsRegisterCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.XfsInfoCommand;
import at.o2xfs.xfs.service.events.XfsEventNotification;
import at.o2xfs.xfs.type.HAPP;
import at.o2xfs.xfs.type.HSERVICE;
import at.o2xfs.xfs.type.REQUESTID;
import at.o2xfs.xfs.util.Bitmask;
import at.o2xfs.xfs.util.IXfsCallback;
import at.o2xfs.xfs.util.MessageHandler;

/**
 * @author Andreas Fagschlunger
 * 
 */
public class XfsServiceManager implements IXfsCallback {

	private final static Logger LOG = LoggerFactory
			.getLogger(XfsServiceManager.class);

	private XfsAPI xfsAPI = null;

	private WFSVersion version = null;

	private HAPP hApp = null;

	private HWND hWnd = null;

	private List<XfsService> xfsServices = null;

	private Map<REQUESTID, XfsEventNotification> requests = null;

	private static XfsServiceManager instance = null;

	private MessageHandler messageHandler = null;

	private EventDispatcher eventDispatcher = null;

	private List<WFSResult> wfsResults = null;

	private List<XfsServiceListener> serviceListeners = null;

	private XfsServiceManager() {
		xfsAPI = XfsAPI.getInstance();
		wfsResults = new ArrayList<WFSResult>();
		xfsServices = new ArrayList<XfsService>();
		requests = new HashMap<REQUESTID, XfsEventNotification>();
		serviceListeners = new CopyOnWriteArrayList<XfsServiceListener>();
		version = new WFSVersion();
		version.allocate();
		eventDispatcher = new EventDispatcher();
		messageHandler = new MessageHandler(this);
		messageHandler.start();
		hWnd = messageHandler.getHWnd();
	}

	public static XfsServiceManager getInstance() {
		if (instance == null) {
			synchronized (XfsServiceManager.class) {
				if (instance == null) {
					instance = new XfsServiceManager();
				}
			}
		}
		return instance;
	}

	public void addXfsServiceListener(final XfsServiceListener e) {
		serviceListeners.add(e);
	}

	public void removeXfsServiceListener(final XfsServiceListener e) {
		serviceListeners.remove(e);
	}

	public void initialize() throws XfsServiceManagerException {
		final String method = "initialize()";
		try {
			startUpXfs();
		} catch (final WFSStartUpException e) {
			throw new XfsServiceManagerException(e);
		}
		try {
			hApp = xfsAPI.wfsCreateAppHandle();
		} catch (final XfsException e) {
			if (LOG.isErrorEnabled()) {
				LOG.error(method, "Error creating application handle", e);
			}
			throw new XfsServiceManagerException(e);
		}
	}

	public <E extends XfsService> void openAndRegister(
			final String logicalName, final Class<E> serviceClass)
			throws XfsException {
		final E xfsService = XfsServiceFactory
				.create(logicalName, serviceClass);
		new XfsServiceStartUp(xfsService).startUp();
		xfsServices.add(xfsService);
		notifyXfsServiceStarted(xfsService);
	}

	public <E extends XfsService> E getService(final String logicalName,
			final Class<E> serviceClass) {
		synchronized (xfsServices) {
			for (final XfsService xfsService : xfsServices) {
				if (xfsService.getLogicalName().equals(logicalName)) {
					return (E) xfsService;
				}
			}
		}
		return null;
	}

	private void notifyXfsServiceStarted(final XfsService xfsService) {
		final String method = "notifyXfsServiceStarted(XfsService)";
		if (LOG.isInfoEnabled()) {
			LOG.info(method, "XfsService: " + xfsService);
		}
		synchronized (serviceListeners) {
			for (final XfsServiceListener e : serviceListeners) {
				e.xfsServiceStarted(xfsService);
			}
		}
	}

	public <E extends XfsService> List<E> getServices(final Class<E> serviceType) {
		final List<E> services = new ArrayList<E>();
		for (final XfsService service : xfsServices) {
			if (serviceType.isInstance(service)) {
				services.add((E) service);
			}
		}
		return services;
	}

	public <E extends XfsService> E getService(final Class<E> serviceClass) {
		for (final XfsService xfsService : xfsServices) {
			if (serviceClass.isInstance(xfsService)) {
				return (E) xfsService;
			}
		}
		return null;
	}

	public List<XfsService> getServices() {
		return new ArrayList<XfsService>(xfsServices);
	}

	/**
	 * @see IXfsCallback
	 */
	@Override
	public void callback(final XfsMessage msg, final WFSResult wfsResult) {
		final String method = "callback(XFSMessage, WFSResult)";
		if (LOG.isDebugEnabled()) {
			LOG.debug(method, "msg=" + msg + ",wfsResult=" + wfsResult);
		}
		try {
			synchronized (wfsResults) {
				wfsResults.add(wfsResult);
			}
			if (msg.isOperationComplete() || msg.isIntermediateEvent()) {
				synchronized (requests) {
					final REQUESTID requestID = wfsResult.getRequestID();
					XfsEventNotification xfsEventNotification = null;
					while ((xfsEventNotification = requests.get(requestID)) == null) {
						if (LOG.isDebugEnabled()) {
							LOG.debug(method, "Waiting for request: "
									+ requestID);
						}
						requests.wait();
					}
					if (msg.isOperationComplete()) {
						requests.remove(wfsResult.getRequestID());
					}
					eventDispatcher.dispatch(msg, xfsEventNotification,
							wfsResult);
				}
			} else {
				final XfsService xfsService = getXfsService(wfsResult
						.getService());
				if (xfsService == null) {
					if (LOG.isErrorEnabled()) {
						LOG.error(method,
								"Could not find XfsService for WFSResult: "
										+ wfsResult);
					}
					free(wfsResult);
				} else {
					eventDispatcher.dispatch(msg, xfsService, wfsResult);
				}
			}
		} catch (InterruptedException e) {
			LOG.error(method, "Interrupted while waiting", e);
		}
	}

	private XfsService getXfsService(final HSERVICE hService) {
		final String method = "getXfsService(HSERVICE)";
		for (XfsService xfsService : xfsServices) {
			if (xfsService.getService().equals(hService)) {
				return xfsService;
			}
		}
		if (LOG.isErrorEnabled()) {
			LOG.error(method, "Couldn't find XfsService: " + hService);
		}
		return null;
	}

	private REQUESTID addRequest(REQUESTID requestID,
			XfsEventNotification xfsEventNotification) {
		final String method = "addRequest(REQUESTID, IXfsEventNotification)";
		if (LOG.isDebugEnabled()) {
			LOG.debug(method, "requestID=" + requestID
					+ ",xfsEventNotification=" + xfsEventNotification);
		}
		synchronized (requests) {
			LOG.debug(method, "Added Request: " + requestID);
			requests.put(requestID, xfsEventNotification);
			requests.notify();
		}
		return requestID;
	}

	public void cancel(XfsService xfsService, REQUESTID requestID)
			throws XfsException {
		xfsAPI.wfsCancelAsyncRequest(xfsService.getService(), requestID);
	}

	public REQUESTID execute(XfsCommand xfsCommand,
			XfsEventNotification xfsEventNotification) throws XfsException {
		if (xfsCommand instanceof XfsOpenCommand) {
			return open((XfsOpenCommand) xfsCommand, xfsEventNotification);
		} else if (xfsCommand instanceof XfsCloseCommand) {
			return close((XfsCloseCommand) xfsCommand, xfsEventNotification);
		} else if (xfsCommand instanceof XfsInfoCommand) {
			return execute((XfsInfoCommand) xfsCommand, xfsEventNotification);
		} else if (xfsCommand instanceof XfsExecuteCommand) {
			return execute((XfsExecuteCommand) xfsCommand, xfsEventNotification);
		} else if (xfsCommand instanceof XfsRegisterCommand) {
			return register((XfsRegisterCommand) xfsCommand,
					xfsEventNotification);
		} else if (xfsCommand instanceof XfsDeRegisterCommand) {
			return deRegister((XfsDeRegisterCommand) xfsCommand,
					xfsEventNotification);
		}
		throw new RuntimeException("Unhandled XFSCommand: " + xfsCommand);
	}

	private REQUESTID register(XfsRegisterCommand xfsRegisterCommand,
			XfsEventNotification xfsEventNotification) throws XfsException {
		XfsService xfsService = xfsRegisterCommand.getXFSService();
		REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		xfsAPI.wfsAsyncRegister(xfsService.getService(),
				Bitmask.of(xfsRegisterCommand.getEventClasses()), hWnd, hWnd,
				requestID);
		return addRequest(requestID, xfsEventNotification);
	}

	private REQUESTID deRegister(XfsDeRegisterCommand xfsDeRegisterCommand,
			XfsEventNotification xfsEventNotification) throws XfsException {
		XfsService xfsService = xfsDeRegisterCommand.getXFSService();
		REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		XfsEventClass[] eventClasses = xfsDeRegisterCommand.getEventClasses();
		xfsAPI.wfsAsyncDeregister(xfsService.getService(),
				(eventClasses != null ? Bitmask.of(eventClasses) : null), hWnd,
				hWnd, requestID);
		return addRequest(requestID, xfsEventNotification);
	}

	private REQUESTID open(final XfsOpenCommand xfsOpenCommand,
			final XfsEventNotification xfsEventNotification)
			throws XfsException {
		final String method = "open(XFSOpenCommand, IXfsEventNotification)";
		if (LOG.isDebugEnabled()) {
			LOG.debug(method, "xfsOpenCommand=" + xfsOpenCommand
					+ ",xfsEventNotification=" + xfsEventNotification);
		}
		final XfsService xfsService = xfsOpenCommand.getXFSService();
		final DWORD dwTraceLevel = null;
		final DWORD dwTimeOut = new DWORD(0L);
		final REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		try {
			xfsAPI.wfsAsyncOpen(new ZSTR(xfsService.getLogicalName()), hApp,
					null, dwTraceLevel, dwTimeOut, xfsService.getService(),
					hWnd, xfsService.getSrvcVersionsRequired(),
					xfsService.getSrvcVersion(), xfsService.getSPIVersion(),
					requestID);
		} finally {
			if (LOG.isDebugEnabled()) {
				LOG.debug(method, "xfsService=" + xfsService);
			}
		}
		return addRequest(requestID, xfsEventNotification);
	}

	private REQUESTID close(final XfsCloseCommand xfsCloseCommand,
			final XfsEventNotification xfsEventNotification)
			throws XfsException {
		final String method = "close(XFSCloseCommand, IXFSEventNotification)";
		final XfsService xfsService = xfsCloseCommand.getXFSService();
		final REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		try {
			xfsAPI.wfsAsyncClose(xfsCloseCommand.getXFSService().getService(),
					hWnd, requestID);
		} finally {
			if (LOG.isDebugEnabled()) {
				LOG.debug(method, "xfsService=" + xfsService);
			}
		}
		return addRequest(requestID, xfsEventNotification);
	}

	private REQUESTID execute(final XfsExecuteCommand xfsExecuteCommand,
			final XfsEventNotification xfsEventNotification)
			throws XfsException {
		final REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		final DWORD command = new DWORD(xfsExecuteCommand.getCommand()
				.getValue());
		final DWORD dwTimeOut = new DWORD(0L);
		xfsAPI.wfsAsyncExecute(xfsExecuteCommand.getXFSService().getService(),
				command, xfsExecuteCommand.getCmdData(), dwTimeOut, hWnd,
				requestID);
		return addRequest(requestID, xfsEventNotification);
	}

	private REQUESTID execute(final XfsInfoCommand xfsInfoCommand,
			final XfsEventNotification xfsEventNotification)
			throws XfsException {
		final REQUESTID requestID = new REQUESTID();
		requestID.allocate();
		final DWORD category = new DWORD(xfsInfoCommand.getCategory()
				.getValue());
		final DWORD timeOut = new DWORD(0L);
		xfsAPI.wfsAsyncGetInfo(xfsInfoCommand.getXFSService().getService(),
				category, xfsInfoCommand.getQueryDetails(), timeOut, hWnd,
				requestID);
		return addRequest(requestID, xfsEventNotification);
	}

	private void startUpXfs() throws WFSStartUpException {
		final String method = "startUpXfs()";
		final XFSVersionDWORD versionsRequired = new XFSVersionDWORD();
		versionsRequired.allocate();
		versionsRequired.setLowVersion(XfsVersion.V2_00);
		versionsRequired.setHighVersion(XfsVersion.V3_20);
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug(method, "versionsRequired=" + versionsRequired);
			}
			version = xfsAPI.wfsStartUp(versionsRequired);
			if (LOG.isInfoEnabled()) {
				LOG.info(method, "XFS Version: " + version);
			}
		} catch (WFSStartUpException e) {
			LOG.error(method, "Error starting up XFS, versionsRequired="
					+ versionsRequired, e);
			throw e;
		}
	}

	public void free(final WFSResult wfsResult) {
		String method = "free(WFSResult)";
		if (LOG.isDebugEnabled()) {
			LOG.debug(method, "wfsResult=" + wfsResult);
		}
		try {
			xfsAPI.wfsFreeResult(wfsResult);
		} catch (XfsException e) {
			LOG.error(method, "Error freeing WFSResult: " + wfsResult);
		}
		synchronized (wfsResults) {
			wfsResults.remove(wfsResult);
		}
		wfsResult.free();
	}

	private void closeServices() {
		final String method = "closeServices()";
		synchronized (xfsServices) {
			while (xfsServices.size() >= 1) {
				XfsService xfsService = xfsServices.get(0);
				try {
					WFSResult wfsResult = new XfsCloseCommand(xfsService)
							.call();
					if (LOG.isDebugEnabled()) {
						LOG.debug(method, "wfsResult=" + wfsResult);
					}
					free(wfsResult);
				} catch (XfsException e) {
					if (LOG.isErrorEnabled()) {
						LOG.error(method, "Error closing XfsService: "
								+ xfsService, e);
					}
				}
				xfsServices.remove(0);
			}
		}
	}

	public void shutdown() {
		final String method = "shutdown()";
		closeServices();
		synchronized (wfsResults) {
			if (wfsResults.size() > 0) {
				LOG.warn(method, wfsResults.size() + " unfreed WFSResult's");
				for (int i = wfsResults.size() - 1; i >= 0; i--) {
					free(wfsResults.get(i));
				}
			}
		}
		if (hApp != null) {
			try {
				xfsAPI.wfsDestroyAppHandle(hApp);
			} catch (XfsException e) {
				LOG.error(method, "Error destroying application handle, hApp="
						+ hApp, e);
			}
		}
		try {
			xfsAPI.wfsCleanUp();
		} catch (XfsException e) {
			LOG.error(method, "Error cleaning up XFS", e);
		}
		if (eventDispatcher != null) {
			eventDispatcher.close();
		}
		messageHandler.close();
	}

}
