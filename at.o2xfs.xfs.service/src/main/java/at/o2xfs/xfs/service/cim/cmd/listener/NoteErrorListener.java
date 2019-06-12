package at.o2xfs.xfs.service.cim.cmd.listener;

import at.o2xfs.xfs.cim.Reason;

public interface NoteErrorListener {
    void onNoteError(Reason reason);
}
