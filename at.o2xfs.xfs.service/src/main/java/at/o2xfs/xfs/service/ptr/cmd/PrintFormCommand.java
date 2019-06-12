package at.o2xfs.xfs.service.ptr.cmd;

import at.o2xfs.log.Logger;
import at.o2xfs.log.LoggerFactory;
import at.o2xfs.win32.LPSTR;
import at.o2xfs.win32.Pointer;
import at.o2xfs.xfs.WFSResult;
import at.o2xfs.xfs.ptr.Ink;
import at.o2xfs.xfs.ptr.PtrExecuteCommand;
import at.o2xfs.xfs.ptr.PtrMessage;
import at.o2xfs.xfs.ptr.Toner;
import at.o2xfs.xfs.service.XfsServiceManager;
import at.o2xfs.xfs.service.cmd.AbstractAsyncXfsCommand;
import at.o2xfs.xfs.service.cmd.XfsCommand;
import at.o2xfs.xfs.service.cmd.XfsExecuteCommand;
import at.o2xfs.xfs.service.cmd.event.SuccessEvent;
import at.o2xfs.xfs.service.ptr.PTRService;
import at.o2xfs.xfs.service.ptr.PtrFactory;
import at.o2xfs.xfs.v3_00.ptr.FieldFailure3;
import at.o2xfs.xfs.v3_00.ptr.PrintForm3;
import at.o2xfs.xfs.v3_10.ptr.MediaRejected310;

public class PrintFormCommand extends AbstractAsyncXfsCommand<PrintFormListener, SuccessEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(PrintFormCommand.class);

    private final PTRService ptrService;
    private final PrintForm3 printForm3;

    public PrintFormCommand(PTRService ptrService, PrintForm3 printForm3) {
        if (ptrService == null) {
            throw new IllegalArgumentException("ptrService must not be null");
        }
        this.ptrService = ptrService;
        this.printForm3 = printForm3;
    }

    @Override
    protected XfsCommand createCommand() {
        return new XfsExecuteCommand<>(ptrService, PtrExecuteCommand.PRINT_FORM, printForm3);
    }

    @Override
    protected SuccessEvent createCompleteEvent(Pointer results) {
        return SuccessEvent.build();
    }

    @Override
    public void fireIntermediateEvent(WFSResult wfsResult) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("fireIntermediateEvent(WFSResult)", "wfsResult=" + wfsResult);
        }
        try {
            PtrMessage message = wfsResult.getEventID(PtrMessage.class);
            switch (message) {
                case EXEE_PTR_NOMEDIA:
                    fireNoMedia(((LPSTR) wfsResult.getResults()).get());
                    break;
                case EXEE_PTR_MEDIAINSERTED:
                    fireMediaInserted();
                    break;
                case EXEE_PTR_FIELDERROR:
                    fireFieldError(PtrFactory.create(ptrService.getXfsVersion(), wfsResult.getResults(), FieldFailure3.class));
                    break;
                case EXEE_PTR_FIELDWARNING:
                    fireFieldWarning(PtrFactory.create(ptrService.getXfsVersion(), wfsResult.getResults(), FieldFailure3.class));
                    break;
                case EXEE_PTR_MEDIAREJECTED:
                    fireMediaRejected(PtrFactory.create(ptrService.getXfsVersion(), wfsResult.getResults(), MediaRejected310.class));
                    break;
                default:
                    throw new IllegalArgumentException(message.toString());

            }
        } finally {
            XfsServiceManager.getInstance().free(wfsResult);
        }
    }

    private void fireNoMedia(String userPrompt) {
        for (PrintFormListener listener : listeners) {
            listener.onPrintNoMedia(userPrompt);
        }
    }

    private void fireMediaInserted() {
        for (PrintFormListener listener : listeners) {
            listener.onMediaInserted();
        }
    }

    private void fireFieldWarning(FieldFailure3 fieldFailure3) {
        for (PrintFormListener listener : listeners) {
            listener.onFieldWarning(fieldFailure3);
        }
    }

    private void fireFieldError(FieldFailure3 fieldFailure3) {
        for (PrintFormListener listener : listeners) {
            listener.onFieldError(fieldFailure3);
        }
    }


    private void fireMediaRejected(MediaRejected310 mediaRejected310) {
        for (PrintFormListener listener : listeners) {
            listener.onMediaRejected(mediaRejected310);
        }
    }

}
