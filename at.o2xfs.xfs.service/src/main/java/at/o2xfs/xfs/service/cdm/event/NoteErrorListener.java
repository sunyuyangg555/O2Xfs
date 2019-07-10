package at.o2xfs.xfs.service.cdm.event;

import at.o2xfs.xfs.cdm.NoteErrorReason;

public interface NoteErrorListener {
    void onNoteError(NoteErrorReason reason);
}
